import dao.Dao;
import dao.DaoImpl;
import model.Exchange_courses;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Maijn {

    public static void main(String[] args) {
        Dao dao = new DaoImpl();
        //Account sonya = dao.findByNumber("0939874561");
        //AccountOperations accountOperations = new AccountOperations(sonya, dao);
        //accountOperations.makeTransaction();
       Exchange_courses ex = dao.findByDate(LocalDateTime.now().toLocalDate());
        System.out.println(ex.toString());





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
