package dao;

import model.Account;
import model.Exchange_courses;

import java.util.Date;
import java.util.List;

public interface Dao {

    void save(Account account);

    Account findByNumber(String number);

    List<Account> findAll();

    void update(Account account);

    void remove(Account account);

    void save(Exchange_courses courses);

    Exchange_courses findByDate(Date date);

    void close();

    void makeTransaction(Account from, Account to, Account boss);

    void buySomething(Account from, Account boss);

}
