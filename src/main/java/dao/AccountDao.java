package dao;

import model.Account;

import java.util.List;

public interface AccountDao {

    void save(Account account);

    Account findByNumber(String number);

    List<Account> findAll();

    void update(Account account);

    void remove(Account account);

}
