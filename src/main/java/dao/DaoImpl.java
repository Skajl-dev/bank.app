package dao;

import helpers.JpaUtil;
import model.Account;
import model.Exchange_courses;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class DaoImpl implements Dao {


    @Override
    public void save(Account account) {
        JpaUtil.doOperation((em -> em.persist(account)));
    }

    @Override
    public Account findByNumber(String number) {
        return JpaUtil.doOperationWithReturning(em -> em.find(Account.class, number));
    }

    @Override
    public Exchange_courses findByDate(LocalDate date) {
        return JpaUtil.doOperationWithReturning(em -> em.find(Exchange_courses.class, date));
    }

    @Override
    public List<Account> findAll() {
        return JpaUtil.doOperationWithReturning(em -> em.createQuery("select a from Account a", Account.class).getResultList());
    }

    @Override
    public void update(Account account) {
        JpaUtil.doOperation(em -> em.merge(account));
    }

    @Override
    public void remove(Account account) {
    JpaUtil.doOperation(em -> {
        Account mergedAccount = em.merge(account);
        em.remove(mergedAccount);
    });
    }

    @Override
    public void save(Exchange_courses courses) {
        JpaUtil.doOperation((em -> em.persist(courses)));
    }


}
