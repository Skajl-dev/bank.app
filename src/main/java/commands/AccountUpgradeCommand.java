package commands;

import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;
import model.AccountType;
import org.apache.log4j.Logger;

public class AccountUpgradeCommand extends AbstractAccountCommand {
    private static final Logger log = Logger.getLogger(AccountUpgradeCommand.class);

    public AccountUpgradeCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Select the required operation: ");
        ConsoleHelper.writeMessage("1. Make account multi-currency.");
        ConsoleHelper.writeMessage("2. Status up.");
        ConsoleHelper.writeMessage("Any other number to exit.");
        int answer = ConsoleHelper.readInt();
        switch (answer) {
            case 1:
                if (account.isCurrency()) {
                    ConsoleHelper.writeMessage("Your account is already multi-currency.");
                } else {
                    ConsoleHelper.writeMessage("By making the account multi-currency you will have the opportunity to exchange your grivnyas for " +
                            "euros and dollars at the best exchange rate and make transfers in currency.\nYou will be charged with 650UAH" +
                            " to make your account multi-currency." + PURCHASE_VERIFY);
                    int answer1 = ConsoleHelper.readInt();
                    if (answer1 == 1) {
                        payingForSmthMoneyChange(650);
                        account.setCurrency(true);
                        log.info("Account \'" + account.getPhoneNumber() + "\' became multi-currency. System balance increased by 650UAH.");
                        dao.buySomething(account, boss);
                    }

                }
                break;
            case 2:
                if (account.getAccountType() == AccountType.PLATINUM) {
                    ConsoleHelper.writeMessage("Your current status is PLATINUM." +
                            "\nYour status is above the sky, it can not be any higher.");
                } else if (account.getAccountType() == AccountType.GOLD) {
                    ConsoleHelper.writeMessage("Your current status is GOLD.\nWith PLATINUM status your transaction commission will be 0.0%. You will be charged with" +
                            " 10 000UAH to get this status." + PURCHASE_VERIFY);
                    int answer2 = ConsoleHelper.readInt();
                    if (answer2 == 1) {
                        payingForSmthMoneyChange(10000);
                        account.setAccountType(AccountType.PLATINUM);
                        log.info("Account \'" + account.getPhoneNumber() + "\' became PLATINUM. System balance increased by 10000UAH.");
                        dao.buySomething(account, boss);
                    }

                } else if (account.getAccountType() == AccountType.STANDARD) {
                    ConsoleHelper.writeMessage("Your current status is STANDARD.\nWith GOLD status your transaction commission will be 0.5%. You will be charged with" +
                            " 5 000UAH to get this status." + PURCHASE_VERIFY);
                    int answer2 = ConsoleHelper.readInt();
                    if (answer2 == 1) {
                        payingForSmthMoneyChange(5000);
                        account.setAccountType(AccountType.GOLD);
                        log.info("Account \'" + account.getPhoneNumber() + "\' became GOLD. System balance increased by 5000UAH.");
                        dao.buySomething(account, boss);
                    }
                }
                break;
        }
    }
}
