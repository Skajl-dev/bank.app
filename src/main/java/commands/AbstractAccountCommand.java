package commands;

import dao.Dao;
import exceptions.NotEnoughMoneyException;
import exceptions.WrongNumberException;
import model.Account;
import model.AccountType;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Pattern;

public abstract class AbstractAccountCommand implements Command {
    protected Account account;
    protected Dao dao;
    protected static Account boss;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    protected final String VERIFY = "Please verify all the introduced data and make sure you have enough money on your account." +
            "\n\nIf you agree press 1, any other number to exit.";
    protected final String SYSTEM_OUT_OF_MONEY = "\n\nUnfortunately, there are not enough funds in the system to perform this operation..." +
            "\nYour balance will not be changed.";
    protected final String PURCHASE_VERIFY = "\nBefore start make sure that you have enough money on your account." +
            "\n\nIf you agree press 1, any other number to exit.";

    public AbstractAccountCommand(Account account, Dao dao) {
        this.account = account;
        this.dao = dao;
        boss = dao.findByNumber("0000000000");
    }



    protected void numberRealityCheck(String phoneNumber) {

        if (account.getPhoneNumber().equals(phoneNumber)) {
            throw new WrongNumberException("You can not send money to yourself... Try again.");
        }

        if (!Pattern.matches("^(039|050|063|066|067|068|091|092|093|094|095|096|097" +
                        "|098|099|031|032|033|034|035|036|037|038|041|042|043|044|046|047" +
                        "|048|049|051|052|053|054|055|056|057|058|059|061|062|064|065|069)\\d{7}",
                phoneNumber)) {
            throw new WrongNumberException("It is impossible for this phone number to exist... Try again.");
        }

        List<Account> allAccounts = dao.findAll();
        boolean match = false;
        for (Account acc : allAccounts) {
            if (phoneNumber.equals(acc.getPhoneNumber())) {
                match = true;
            }
        }
        if (!match) {
            throw new WrongNumberException("There is no such account in our system... Try again.");
        }
    }

    protected double defineCommission(AccountType accountType) {
        switch (accountType) {
            case PLATINUM:
                return 0.0;
            case GOLD:
                return 0.5;
            case STANDARD:
                return 1.0;
        }
        return 1.0;
    }

    protected void payingForSmthMoneyChange(double amount) {
        if (account.getUAH_balance() >= amount) {
            account.setUAH_balance(account.getUAH_balance() - amount);
            boss.setUAH_balance(boss.getUAH_balance() + amount);
        } else {
            throw new NotEnoughMoneyException("Not enough money...");
        }
    }
}
