package helpers;

import commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private final Map<Operation, Command> allKnownCommandsMap = new HashMap<>();
    private Command balanceCheck;
    private Command makeTransactionCommand;
    private Command convertToAnotherCurrencyCommand;
   private Command upgrade;
    private Command showInfoCommand;
    private Command exitCommand;

    public CommandExecutor(BalanceCheckCommand balanceCheck, MakeTransactionCommand makeTransactionCommand,
                           ConvertToAnotherCurrencyCommand convertToAnotherCurrencyCommand,
                           AccountUpgradeCommand accountUpgradeCommand, ShowInfoCommand showInfoCommand, ExitCommand exitCommand)
    {
       this.balanceCheck = balanceCheck;
        this.makeTransactionCommand = makeTransactionCommand;
        this.convertToAnotherCurrencyCommand = convertToAnotherCurrencyCommand;
        this.upgrade = accountUpgradeCommand;
        this.showInfoCommand = showInfoCommand;
        this.exitCommand = exitCommand;
        allKnownCommandsMap.put(Operation.BALANCE_CHECK, balanceCheck);
        allKnownCommandsMap.put(Operation.TRANSACTION, makeTransactionCommand);
        allKnownCommandsMap.put(Operation.CONVERT_TO_ANOTHER_CURRENCY, convertToAnotherCurrencyCommand);
        allKnownCommandsMap.put(Operation.UPGRADE, upgrade);
        allKnownCommandsMap.put(Operation.INFO, showInfoCommand);
        allKnownCommandsMap.put(Operation.EXIT, exitCommand);
    }



    public void execute(Operation operation) {
        allKnownCommandsMap.get(operation).execute();
    }

}
