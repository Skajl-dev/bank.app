package commands;

import dao.Dao;
import exceptions.NotEnoughMoneyException;
import helpers.ConsoleHelper;
import model.Account;
import org.decimal4j.util.DoubleRounder;

import java.io.IOException;

public class MakeTransactionCommand extends AbstractAccountCommand {

    public MakeTransactionCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
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

        ConsoleHelper.writeMessage("You will be charged with the " + commission + "% commission to perform this transaction.\n" +
               VERIFY);
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
                throw new NotEnoughMoneyException("Not enough money...");
            }
        }
    }
}
