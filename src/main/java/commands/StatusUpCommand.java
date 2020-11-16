package commands;

import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;
import model.AccountType;

public class StatusUpCommand extends AbstractAccountCommand {

    public StatusUpCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        if (account.getAccountType() == AccountType.PLATINUM) {
            ConsoleHelper.writeMessage("Your current status is PLATINUM." +
                    "\nYour status is above the sky, it can not be any higher.");
        } else if (account.getAccountType() == AccountType.GOLD) {
            ConsoleHelper.writeMessage("Your current status is GOLD.\nWith PLATINUM status your transaction commission will be 0.0%. You will be charged with" +
                    " 10 000UAH to get this status.\nBefore start make sure that you have enough money on your account." +
                    "\n\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            if (answer == 1) {
                payingForSmthMoneyChange(10000);
            }
            account.setAccountType(AccountType.PLATINUM);
            dao.buySomething(account, boss);
        } else if (account.getAccountType() == AccountType.STANDARD) {
            ConsoleHelper.writeMessage("Your current status is STANDARD.\nWith GOLD status your transaction commission will be 0.5%. You will be charged with" +
                    " 5 000UAH to get this status. \nBefore start make sure that you have enough money on your account." +
                    "\n\nIf you agree press 1, any other number to exit.");
            int answer = ConsoleHelper.readInt();
            if (answer == 1) {
                payingForSmthMoneyChange(5000);
            }
            account.setAccountType(AccountType.GOLD);
            dao.buySomething(account, boss);
        }
    }
}
