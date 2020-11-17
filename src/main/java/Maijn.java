import commands.*;
import dao.Dao;
import dao.DaoJPA_Impl;
import entrering.EmailCodeSender;
import entrering.Sign_up;
import helpers.CommandExecutor;
import helpers.ConsoleHelper;
import helpers.Operation;
import model.Account;
import model.AccountType;
import model.Exchange_courses;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class Maijn {

    public static void main(String[] args) throws IOException {
        Dao dao = new DaoJPA_Impl();
        //ApplicationContext context = new AnnotationConfigApplicationContext(BankConfig.class);
       // Dao dao = (DaoJPA_Impl) context.getBean(DaoJPA_Impl.class);
       //Account sonia = dao.findByNumber("0978521346");
        EmailCodeSender emailCodeSender = new EmailCodeSender();
        Account account = new Sign_up(dao, emailCodeSender).signUp();
        //
        //emailCodeSender.send("Hi girls", "hellocaniboostya@gmail.com ");

        /* CommandExecutor commandExecutor = new CommandExecutor(new BalanceCheckCommand(sonia, dao),
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
*/

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
