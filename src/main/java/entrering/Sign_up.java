package entrering;
import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Sign_up extends EnteringTheSystem {
    EmailCodeSender emailCodeSender;
    private static final Logger log = Logger.getLogger(Sign_up.class);

    public Sign_up(Dao dao, EmailCodeSender emailCodeSender) {
        super(dao);
        this.emailCodeSender = emailCodeSender;
    }

    public Account signUp() throws IOException {
        Account newAccount = null;
        ConsoleHelper.writeMessage("    Sign up");
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("phone number: " + "\n*must be valid, non used phone number, start with 0*");
        String number = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("password: " + "\n*minimum eight characters, maximum fifteen, at least one uppercase letter, one lowercase letter and one number*");
        String password = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Name: " + "\n*start with uppercase letter, use up to 30 characters*");
        String name = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Surname: " + "\n*start with uppercase letter, use up to 30 characters*");
        String surname = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("email: " + "\n*should be valid email*");
        String email = ConsoleHelper.readString();
        if (isDataCorrect(number, password, name, surname, email)) {
            while (!emailVerification(email)) {

            }
            newAccount = new Account(number, password, name, surname, email);
            newAccount.setUAH_balance(50.0);
            dao.save(newAccount);
            log.info("Account \'" + newAccount.getPhoneNumber() + "\' joined the system.");
            return newAccount;
        }
        return signUp();
    }

    private boolean emailVerification(String email) throws IOException {
        ConsoleHelper.writeMessage("The verification code was sanded to your email... Check your email and enter the code below: ");
        int code = (int) (10000 + Math.random() * 90000);
        emailCodeSender.send(String.valueOf(code), email);
        String userCode = ConsoleHelper.readString();
        return userCode.equals(String.valueOf(code));
    }

    private boolean isDataCorrect(String phoneNumber, String password, String name, String surname, String email) throws IOException {
        if (!validateNumber(phoneNumber)) {
            ConsoleHelper.writeMessage("\nWrong or used phone number... Try again.\n");
            return false;
        }
        if (!validatePassword(password)) {
            ConsoleHelper.writeMessage("\nIncorrect password... Try again.\n");
            return false;
        }
        if (!validateEmail(email)) {
            ConsoleHelper.writeMessage("\nIncorrect email... Try again.\n");
            return false;
        }
        if (!validateNameAndSurname(name)) {
            ConsoleHelper.writeMessage("\nIncorrect name... Try again.\n");
            return false;
        }
        if (!validateNameAndSurname(surname)) {
            ConsoleHelper.writeMessage("\nIncorrect surname... Try again.\n");
            return false;
        }
        return true;
    }
}
