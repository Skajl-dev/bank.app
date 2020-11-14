package commands;

import dao.Dao;
import exceptions.NotEnoughMoneyException;
import exceptions.WrongNumberException;
import helpers.ConsoleHelper;
import model.Account;
import model.AccountType;
import model.Exchange_courses;
import org.decimal4j.util.DoubleRounder;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class AccountOperations {
    Account account;
    Dao dao;
    Account boss;

    public AccountOperations(Account account, Dao dao) {
        this.account = account;
        this.dao = dao;
        boss = dao.findByNumber("0500536283");
    }

    public void makeAccountMultiCurrency() {
        if (account.isCurrency()) {
            ConsoleHelper.writeMessage("Your account is already multi-currency.");
        } else {
            ConsoleHelper.writeMessage("By making the account multi-currency you will have the opportunity to exchange your grivnyas for " +
                    "euros and dollars at the best exchange rate and make transfers in currency.\nYou will be charged with 650UAH" +
                    " to make your account multi-currency.\nBefore start make sure that you have enough money on your account." +
                    "\n\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            payingForSmth(answer, 650);
            account.setCurrency(true);
            dao.buySomething(account, boss);
        }
    }

    public void convertToAnotherCurrency() {
        if (account.isCurrency()) {
            Exchange_courses courses;

        } else {
            throw new UnsupportedOperationException("Your account is not able to convert money.");
        }


    }

    public void makeTransaction() {

        ConsoleHelper.writeMessage("Enter the phone number to which you want to send funds: ");
        String number = "";
        try {
            number = ConsoleHelper.readString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        numberRealityCheck(number);

        ConsoleHelper.writeMessage("Enter the amount you want to send: ");
        double sum = ConsoleHelper.readDouble();
        sum = DoubleRounder.round(sum, 2);
        double commission = defineCommission(account.getAccountType());

        ConsoleHelper.writeMessage("You will be charged with the " + commission + "% commission to perform this transaction." +
                "\nPlease verify all the introduced data and make sure you have enough money on your account." +
                "\n\nIf you agree press 1, any other number to exit.");
        int answer = ConsoleHelper.readInt();
        if (answer == 1) {
            if (account.getUAH_balance() >= sum) {
                account.setUAH_balance(account.getUAH_balance() - sum);
                double sumToSend = sum * (100 - commission) / 100;
                double fee = sum - sumToSend;
                boss.setUAH_balance(boss.getUAH_balance() + fee);
                Account receiver = dao.findByNumber(number);
                receiver.setUAH_balance(receiver.getUAH_balance() + sumToSend);
                dao.makeTransaction(account, receiver, boss);
            } else {
                throw new NotEnoughMoneyException("Not enough money to perform status changing");
            }
        }
    }


    public void statusUp() {
        if (account.getAccountType() == AccountType.PLATINUM) {
            ConsoleHelper.writeMessage("Your current status is PLATINUM." +
                    "\nYour status is above the sky, it can not be any higher.");
        } else if (account.getAccountType() == AccountType.GOLD) {
            ConsoleHelper.writeMessage("Your current status is GOLD.\nWith PLATINUM status your transaction commission will be 0.0%. You will be charged with" +
                    " 10 000UAH to get this status.\nBefore start make sure that you have enough money on your account." +
                    "\n\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            payingForSmth(answer, 10000);
            account.setAccountType(AccountType.PLATINUM);
            dao.buySomething(account, boss);
        } else if (account.getAccountType() == AccountType.STANDARD) {
            ConsoleHelper.writeMessage("Your current status is STANDARD.\nWith GOLD status your transaction commission will be 0.5%. You will be charged with" +
                    " 5 000UAH to get this status. \nBefore start make sure that you have enough money on your account." +
                    "\n\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            payingForSmth(answer, 5000);
            account.setAccountType(AccountType.GOLD);
            dao.buySomething(account, boss);
        }
    }

    public void showInfo() {
        ConsoleHelper.writeMessage("    Welcome to my personal bank app. In this app you are able" +
                " to transfer money by phone number, change money into another currencies. \n" + "Some options are paid: \n " +
                "1. Status increasing. \n" +
                "There are 3 types of status in app: " +
                "\n-Standard (your transaction commission will be 1%). " +
                "\n-GOLD (your transaction commission will be 0.5%) it costs 5000UAH to get this status from standard one." +
                "\n-PLATINUM (your transaction commission will be 0.0%) it costs 10000UAH to get this status from gold one." +
                "\n2. Making your account multi-currency." +
                "\nYou will be able to contain and change your money into dollars and euro. This option costs " +
                "only +650UAH once." +
                "\n\n   If there will appear any questions about the app or cooperation proposals - feel free to contact " +
                "me on this email bat93222888@gmail.com.");
    }

    public void exit() {
        ConsoleHelper.writeMessage("Goodbye! Hope to see you soon...");
        dao.close();
        System.exit(0);
    }


    private void numberRealityCheck(String phoneNumber) {

        if (account.getPhoneNumber().equals(phoneNumber)) {
            throw new WrongNumberException("You can not send money to yourself... Try again");
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
            throw new WrongNumberException("There is no such account in our system... Try again");
        }
    }

    private double defineCommission(AccountType accountType) {
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

    private void payingForSmth(int answer, double amount) {
        if (answer == 1) {
            if (account.getUAH_balance() >= amount) {
                account.setUAH_balance(account.getUAH_balance() - amount);
                boss.setUAH_balance(boss.getUAH_balance() + amount);
            }  else {
                throw new NotEnoughMoneyException("Not enough money to perform status changing");
            }
        }
    }


}
