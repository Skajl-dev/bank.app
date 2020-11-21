import commands.*;
import dao.Dao;
import dao.DaoJPA_Impl;
import entrering.EmailCodeSender;
import entrering.Sign_in;
import entrering.Sign_up;
import helpers.CommandExecutor;
import helpers.ConsoleHelper;
import helpers.Operation;
import model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
public class BankConfig {

    @Bean
    @Scope("singleton")
    public Dao dao() {
        return new DaoJPA_Impl();
    }

    @Bean
    @Scope("singleton")
    public Sign_in sign_in() {
        return new Sign_in(dao());
    }

    @Bean
    @Scope("singleton")
    public EmailCodeSender emailCodeSender() {
        return new EmailCodeSender();
    }

    @Bean
    @Scope("singleton")
    public Sign_up sign_up() {
        return new Sign_up(dao(), emailCodeSender());
    }

    @Bean
    @Scope("singleton")
    public Account account()  {
        ConsoleHelper.writeMessage("    Login");
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("1. Sign in");
        ConsoleHelper.writeMessage("2. Sign up");
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("3. Exit");
        int answer = ConsoleHelper.readInt();
        switch (answer) {
            case 1:
                try {
                    return sign_in().signIn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 2:
                try {
                    return sign_up().signUp();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            case 3:
                dao().close();
                System.exit(0);
        }
        return account();
    }

    @Bean
    @Scope("singleton")
    public BalanceCheckCommand balanceCheckCommand() {
        return new BalanceCheckCommand(account(), dao());
    }

    @Bean
    @Scope("singleton")
    public ConvertToAnotherCurrencyCommand convertToAnotherCurrencyCommand() {
        return new ConvertToAnotherCurrencyCommand(account(), dao());
    }

    @Bean
    @Scope("singleton")
    public ExitCommand exitCommand() {
        return new ExitCommand(account(), dao());
    }

    @Bean
    @Scope("singleton")
    public MakeAccountMultiCurrencyCommand makeAccountMultiCurrencyCommand() {
        return new MakeAccountMultiCurrencyCommand(account(), dao());
    }

    @Bean
    @Scope("singleton")
    public MakeTransactionCommand makeTransactionCommand() {
        return new MakeTransactionCommand(account(), dao());
    }

    @Bean
    @Scope("singleton")
    public ShowInfoCommand showInfoCommand() {
        return new ShowInfoCommand();
    }

    @Bean
    @Scope("singleton")
    public StatusUpCommand statusUpCommand() {
        return new StatusUpCommand(account(), dao());
    }

    @Bean
    @Scope("singleton")
    public CommandExecutor commandExecutor() {
        return new CommandExecutor(balanceCheckCommand(), makeTransactionCommand(), convertToAnotherCurrencyCommand(),
                statusUpCommand(), makeAccountMultiCurrencyCommand(), showInfoCommand(), exitCommand());
    }

    @Bean
    @Scope("prototype")
    public Operation operation() {
        Operation operation = null;
        try {
           operation = askOperation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return operation;
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
