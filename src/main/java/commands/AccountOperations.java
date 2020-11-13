package commands;

import dao.AccountDao;
import exceptions.NotEnoughMoneyException;
import helpers.ConsoleHelper;
import helpers.JpaUtil;
import model.Account;
import model.AccountType;

public class AccountOperations {
    Account account;
    AccountDao dao;
    Account boss;

    public AccountOperations(Account account, AccountDao dao) {
        this.account = account;
        this.dao = dao;
        boss = dao.findByNumber("0500536283");
    }

    public void statusUp() {
        if (account.getAccountType() == AccountType.PLATINUM) {
            ConsoleHelper.writeMessage("Your status is above the sky, you can not make it better.");
        } else if (account.getAccountType() == AccountType.GOLD) {
            ConsoleHelper.writeMessage("With PLATINUM status your transaction commission will be 0.0%. You will be charged with" +
                    " 10 000UAH to get this status. Before start make sure that you have enough money on your account." +
                    "\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            statusUpPerforming(answer, 10000.0, AccountType.PLATINUM);
        } else if (account.getAccountType() == AccountType.STANDARD) {
            ConsoleHelper.writeMessage("With GOLD status your transaction commission will be 0.5%. You will be charged with" +
                    " 5 000UAH to get this status. Before start make sure that you have enough money on your account." +
                    "\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            statusUpPerforming(answer, 5000.0, AccountType.GOLD);
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
                "only 400UAH once." +
                "\n\n   If there will appear any questions about the app or cooperation proposals - feel free to contact " +
                "me on this email bat93222888@gmail.com.");
    }

    public void exit() {
        ConsoleHelper.writeMessage("Goodbye! Hope to see you soon...");
        JpaUtil.close();
        System.exit(0);
    }















    public void statusUpPerforming(int answer, double amount, AccountType nextStatus) {
        if (answer == 1) {
            if (account.getUAH_balance() >= amount) {
                account.setUAH_balance(account.getUAH_balance() - amount);
                account.setAccountType(nextStatus);
                boss.setUAH_balance(boss.getUAH_balance() + amount);
                dao.update(account);
                dao.update(boss);
            } else {
                throw new NotEnoughMoneyException("Not enough money to perform status changing");
            }
        }
    }
}
