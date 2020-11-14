import commands.AccountOperations;
import dao.Dao;
import dao.DaoJPA_Impl;
import model.Account;
import model.AccountType;

public class Maijn {

    public static void main(String[] args) {
        Dao dao = new DaoJPA_Impl();
        Account vasya = dao.findByNumber("0500536283");
        AccountOperations accountOperations = new AccountOperations(vasya, dao);
        //accountOperations.makeAccountMultiCurrency();

      vasya.setUAH_balance(18200);
      dao.update(vasya);




        //List<Account> all = dao.findAll();
        //Account me = dao.findByNumber("0500536283");
        //AccountOperations accountOperations = new AccountOperations(me, dao);
        //accountOperations.showInfo();
        //me.setUAH_balance(me.getUAH_balance() + 1000);
        //dao.update(me);
        //System.out.println(me.toString());

       // for (Account a : all) {
        //    System.out.println(a.toString());
       // }



    }



}
