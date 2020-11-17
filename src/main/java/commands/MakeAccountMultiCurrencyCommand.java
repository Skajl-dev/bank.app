package commands;

import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;

public class MakeAccountMultiCurrencyCommand extends AbstractAccountCommand {
    public MakeAccountMultiCurrencyCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        if (account.isCurrency()) {
            ConsoleHelper.writeMessage("Your account is already multi-currency.");
        } else {
            ConsoleHelper.writeMessage("By making the account multi-currency you will have the opportunity to exchange your grivnyas for " +
                    "euros and dollars at the best exchange rate and make transfers in currency.\nYou will be charged with 650UAH" +
                    " to make your account multi-currency." + PURCHASE_VERIFY);
            int answer = ConsoleHelper.readInt();
            if (answer == 1) {
                payingForSmthMoneyChange(650);
            }
            account.setCurrency(true);
            dao.buySomething(account, boss);
        }
    }
}
