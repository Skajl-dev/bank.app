package dao;

import exceptions.JpaUtilException;
import model.Account;
import model.Exchange_courses;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class DaoJPA_Impl implements Dao {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.bankApp");


    public void close() {
        entityManagerFactory.close();
    }

    @Override
    public void makeTransaction(Account from, Account to, Account boss) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(from);
            entityManager.merge(to);
            entityManager.merge(boss);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new JpaUtilException("Error performing JPA operation. Transaction is rolled back", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void buySomething(Account from, Account boss) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
            entityManager.merge(from);
            entityManager.merge(boss);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw new JpaUtilException("Error performing JPA operation. Transaction is rolled back", e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void save(Account account) {
    doOperation((em -> em.persist(account)));
    }

    @Override
    public Account findByNumber(String number) {
        return doOperationWithReturning(em -> em.find(Account.class, number));
    }

    @Override
    public Exchange_courses findByDate(LocalDate date) {
        return doOperationWithReturning(em -> em.find(Exchange_courses.class, date));
    }

    @Override
    public List<Account> findAll() {
        return doOperationWithReturning(em -> em.createQuery("select a from Account a", Account.class).getResultList());
    }

    @Override
    public void update(Account account) {
        doOperation(em -> em.merge(account));
    }

    @Override
    public void remove(Account account) {
    doOperation(em -> {
        Account mergedAccount = em.merge(account);
        em.remove(mergedAccount);
    });
    }

    @Override
    public void save(Exchange_courses courses) {
        doOperation((em -> em.persist(courses)));
    }



    private void doOperation(Consumer<EntityManager> operation) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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

    private <T> T doOperationWithReturning(Function<EntityManager, T> entityManagerTFunction) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
