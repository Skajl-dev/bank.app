package helpers;

import commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    private Command balanceCheck;
    private Command makeTransactionCommand;
    private Command convertToAnotherCurrencyCommand;
    private Command statusUpCommand;
    private Command makeAccountMultiCurrencyCommand;
    private Command showInfoCommand;
    private Command exitCommand;

    public CommandExecutor(BalanceCheckCommand balanceCheck, MakeTransactionCommand makeTransactionCommand, ConvertToAnotherCurrencyCommand convertToAnotherCurrencyCommand, StatusUpCommand statusUpCommand, MakeAccountMultiCurrencyCommand makeAccountMultiCurrencyCommand, ShowInfoCommand showInfoCommand, ExitCommand exitCommand) {
       this.balanceCheck = balanceCheck;
        this.makeTransactionCommand = makeTransactionCommand;
        this.convertToAnotherCurrencyCommand = convertToAnotherCurrencyCommand;
        this.statusUpCommand = statusUpCommand;
        this.makeAccountMultiCurrencyCommand = makeAccountMultiCurrencyCommand;
        this.showInfoCommand = showInfoCommand;
        this.exitCommand = exitCommand;
        allKnownCommandsMap.put(Operation.BALANCE_CHECK, balanceCheck);
        allKnownCommandsMap.put(Operation.TRANSACTION, makeTransactionCommand);
        allKnownCommandsMap.put(Operation.CONVERT_TO_ANOTHER_CURRENCY, convertToAnotherCurrencyCommand);
        allKnownCommandsMap.put(Operation.STATUS_UP, statusUpCommand);
        allKnownCommandsMap.put(Operation.MAKE_MULTICURRENCY, makeAccountMultiCurrencyCommand);
        allKnownCommandsMap.put(Operation.INFO, showInfoCommand);
        allKnownCommandsMap.put(Operation.EXIT, exitCommand);
    }



    public void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }

}
