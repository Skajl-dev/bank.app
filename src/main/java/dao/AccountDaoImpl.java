package dao;

import helpers.JpaUtil;
import model.Account;

import java.util.List;

public class AccountDaoImpl implements AccountDao {


    @Override
    public void save(Account account) {
        JpaUtil.doOperation((em -> em.persist(account)));
    }

    @Override
    public Account findByNumber(String number) {
        return JpaUtil.doOperationWithReturning(em -> em.find(Account.class, number));
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
}
