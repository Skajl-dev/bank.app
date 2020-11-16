package commands;

import dao.Dao;
import exceptions.NotEnoughMoneyException;
import helpers.ConsoleHelper;
import model.Account;
import model.Exchange_courses;
import org.decimal4j.util.DoubleRounder;

import java.util.Date;

public class ConvertToAnotherCurrencyCommand extends AbstractAccountCommand {
    public ConvertToAnotherCurrencyCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        if (account.isCurrency()) {
            Date date = new Date();

            Exchange_courses courses = dao.findByDate(date);
            if (courses != null) {
                ConsoleHelper.writeMessage("         " + dateFormat.format(date));
                ConsoleHelper.writeMessage("");
                ConsoleHelper.writeMessage("1.UAH to USD/2.USD to UAH\n" + "     " + courses.getUAHtoDollar() +
                        "         " + courses.getDollar_to_UAH());
                ConsoleHelper.writeMessage("3.UAH to EUR/4.EUR to UAH\n" + "     " + courses.getUAH_to_Euro() +
                        "         " + courses.getEuro_to_UAH());
                ConsoleHelper.writeMessage("\n\nSelect the exchange you need by entering its number or press any other number to exit:");
                int answer = ConsoleHelper.readInt();
                switch (answer) {
                    case 1:
                        ConsoleHelper.writeMessage("Enter the amount you want to change: ");
                        double sum1 = DoubleRounder.round(ConsoleHelper.readDouble(), 2);
                        ConsoleHelper.writeMessage(
                                "Please verify all the introduced data and make sure you have enough money on your account." +
                                "\n\nIf you agree press 1, any other number to exit.");
                        int answer1 = ConsoleHelper.readInt();
                        if (answer1 == 1) {
                            payingForSmthMoneyChange(sum1);
                            double dollars1 = sum1 / courses.getUAHtoDollar();
                            boss.setDollar_balance(boss.getDollar_balance() - dollars1);
                            account.setDollar_balance(account.getDollar_balance() + dollars1);
                            dao.buySomething(account, boss);
                        }
                        break;

                    case 2:
                        ConsoleHelper.writeMessage("Enter the amount you want to change: ");
                        double sum2 = DoubleRounder.round(ConsoleHelper.readDouble(), 2);
                        ConsoleHelper.writeMessage(
                                "Please verify all the introduced data and make sure you have enough money on your account." +
                                        "\n\nIf you agree press 1, any other number to exit.");
                        int answer2 = ConsoleHelper.readInt();
                        if (answer2 == 1) {
                            if (account.getDollar_balance() >= sum2) {
                                account.setDollar_balance(account.getDollar_balance() - sum2);
                                boss.setDollar_balance(boss.getDollar_balance() + sum2);
                                double grn2 = sum2 * courses.getDollar_to_UAH();
                                account.setUAH_balance(account.getUAH_balance() + grn2);
                                boss.setUAH_balance(boss.getUAH_balance() - grn2);
                                dao.buySomething(account, boss);
                            } else {
                                throw new NotEnoughMoneyException("Not enough money...");
                            }
                        }
                        break;
                    case 3:
                        ConsoleHelper.writeMessage("Enter the amount you want to change: ");
                        double sum3 = DoubleRounder.round(ConsoleHelper.readDouble(), 2);
                        ConsoleHelper.writeMessage(
                                "Please verify all the introduced data and make sure you have enough money on your account." +
                                        "\n\nIf you agree press 1, any other number to exit.");
                        int answer3 = ConsoleHelper.readInt();
                        if (answer3 == 1) {
                            payingForSmthMoneyChange(sum3);
                            double euro3 = sum3 / courses.getUAH_to_Euro();
                            boss.setEuro_balance(boss.getEuro_balance() - euro3);
                            account.setEuro_balance(account.getEuro_balance() + euro3);
                            dao.buySomething(account, boss);
                        }
                        break;
                    case 4:
                        ConsoleHelper.writeMessage("Enter the amount you want to change: ");
                        double sum4 = DoubleRounder.round(ConsoleHelper.readDouble(), 2);
                        ConsoleHelper.writeMessage(
                                "Please verify all the introduced data and make sure you have enough money on your account." +
                                        "\n\nIf you agree press 1, any other number to exit.");
                        int answer4 = ConsoleHelper.readInt();
                        if (answer4 == 1) {
                            if (account.getEuro_balance() >= sum4) {
                                account.setEuro_balance(account.getEuro_balance() - sum4);
                                boss.setEuro_balance(boss.getEuro_balance() + sum4);
                                double grn4 = sum4 * courses.getEuro_to_UAH();
                                account.setUAH_balance(account.getUAH_balance() + grn4);
                                boss.setUAH_balance(boss.getUAH_balance() - grn4);
                                dao.buySomething(account, boss);

                            } else {
                                throw new NotEnoughMoneyException("Not enough money...");
                            }
                        }
                        break;
                }
            } else {
                ConsoleHelper.writeMessage("Unfortunately, we have not yet determined the exchange rates for today." +
                        "\nPlease try again later.");
            }
        } else {
            throw new UnsupportedOperationException("Your account is not able to convert money.");
        }
    }
}
