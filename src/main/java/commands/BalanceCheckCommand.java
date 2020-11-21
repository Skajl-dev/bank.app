package commands;


import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;


public class BalanceCheckCommand extends AbstractAccountCommand {

    public BalanceCheckCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage(String.format("Your balance is: " +
                "\n %.2f UAH " +
                "\n %.2f USD " +
                "\n %.2f EUR", account.getUAH_balance(), account.getDollar_balance(), account.getEuro_balance()));
    }
}
