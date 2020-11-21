package entrering;

import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Sign_in extends EnteringTheSystem {
    private static final Logger log = Logger.getLogger(Sign_in.class);

    public Sign_in(Dao dao) {
        super(dao);
    }

    public Account signIn() throws IOException {
        Account account = null;
        ConsoleHelper.writeMessage("    Sign in");
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Enter your phone number, start with 0: ");
        String number = ConsoleHelper.readString();
        account = dao.findByNumber(number);
        ConsoleHelper.writeMessage("Enter your password: ");
        String pass = ConsoleHelper.readString();
        if (pass.equals(account.getPassword())) {
            log.info("Account \'" + account.getPhoneNumber() + "\' entered the system.");
            return account;
        }
        log.warn("Account \'" + number + "\' tried to enter the system.");
        return signIn();
    }
}
