import commands.*;
import dao.Dao;
import dao.DaoJPA_Impl;
import helpers.CommandExecutor;
import helpers.ConsoleHelper;
import helpers.Operation;
import model.Account;
import model.AccountType;
import model.Exchange_courses;

public class Maijn {

    public static void main(String[] args) {
        Dao dao = new DaoJPA_Impl();
        Account sonia = dao.findByNumber("0978521346");


         CommandExecutor commandExecutor = new CommandExecutor(new BalanceCheckCommand(sonia, dao),
                 new MakeTransactionCommand(sonia, dao), new ConvertToAnotherCurrencyCommand(sonia, dao),
                new StatusUpCommand(sonia, dao), new MakeAccountMultiCurrencyCommand(sonia, dao), new ShowInfoCommand(), new ExitCommand(sonia, dao));
        Operation operation = null;
        do {
            try {
                operation = askOperation();
            } catch (Exception e) {
                e.printStackTrace();
            }
            commandExecutor.execute(operation);
        } while (operation != Operation.EXIT);


    }

    public static Operation askOperation() throws Exception {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Select an operation: ");
        ConsoleHelper.writeMessage(String.format("\t %d - check balance", Operation.BALANCE_CHECK.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - make a transaction", Operation.TRANSACTION.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - money exchange", Operation.CONVERT_TO_ANOTHER_CURRENCY.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - status up", Operation.STATUS_UP.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - make account multi-currency", Operation.MAKE_MULTICURRENCY.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - info", Operation.INFO.ordinal()));
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage(String.format("\t %d - exit", Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }


}
