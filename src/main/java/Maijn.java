import commands.AccountOperations;
import dao.AccountDao;
import dao.AccountDaoImpl;
import model.Account;
import model.AccountType;
import org.decimal4j.util.DoubleRounder;

import java.util.List;

public class Maijn {

    public static void main(String[] args) {
        AccountDao dao = new AccountDaoImpl();
        Account sonya = dao.findByNumber("0939874561");
        AccountOperations accountOperations = new AccountOperations(sonya, dao);
        accountOperations.makeTransaction();

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
