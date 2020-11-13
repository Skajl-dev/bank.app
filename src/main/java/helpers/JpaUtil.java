package helpers;

import exceptions.JpaUtilException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.function.Consumer;
import java.util.function.Function;

public class JpaUtil {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("org.hibernate.bankApp");

    public static void close() {
        emf.close();
    }

    public static void doOperation(Consumer<EntityManager> operation) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            operation.accept(entityManager);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new JpaUtilException("Error performing JPA operation. Transaction is rolled back", e);
        } finally {
            entityManager.close();
        }
    }

    public static <T> T doOperationWithReturning(Function<EntityManager, T> entityManagerTFunction) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            T result = entityManagerTFunction.apply(entityManager);
            entityManager.getTransaction().commit();
            return result;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new JpaUtilException("Error performing JPA operation. Transaction is rolled back", e);
        } finally {
            entityManager.close();
        }


    }
}
