package commands;

import dao.Dao;
import exceptions.NotEnoughMoneyException;
import helpers.ConsoleHelper;
import model.Account;
import model.Exchange_courses;
import org.apache.log4j.Logger;
import org.decimal4j.util.DoubleRounder;

import java.util.Date;

public class ConvertToAnotherCurrencyCommand extends AbstractAccountCommand {
    private static final Logger log = Logger.getLogger(ConvertToAnotherCurrencyCommand.class);

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
                        double sum1 = askingForAmount();
                        ConsoleHelper.writeMessage(VERIFY);
                        int answer1 = ConsoleHelper.readInt();
                        if (answer1 == 1) {
                            double dollars1 = sum1 / courses.getUAHtoDollar();
                            if (boss.getDollar_balance() >= dollars1) {
                                payingForSmthMoneyChange(sum1);
                                boss.setDollar_balance(boss.getDollar_balance() - dollars1);
                                account.setDollar_balance(account.getDollar_balance() + dollars1);
                                log.info("Account \'" + account.getPhoneNumber() + "\' bought " + dollars1 + "$. Paid " +
                                        sum1 + "UAH.");
                                dao.buySomething(account, boss);
                            } else {
                                log.error("System out of dollars!!!");
                                ConsoleHelper.writeMessage(SYSTEM_OUT_OF_MONEY);
                            }
                        }
                        break;

                    case 2:
                        double sum2 = askingForAmount();
                        ConsoleHelper.writeMessage(VERIFY);
                        int answer2 = ConsoleHelper.readInt();
                        if (answer2 == 1) {
                            if (account.getDollar_balance() >= sum2) {
                                double grn2 = sum2 * courses.getDollar_to_UAH();
                                if (boss.getUAH_balance() >= grn2) {
                                    account.setDollar_balance(account.getDollar_balance() - sum2);
                                    boss.setDollar_balance(boss.getDollar_balance() + sum2);
                                    account.setUAH_balance(account.getUAH_balance() + grn2);
                                    boss.setUAH_balance(boss.getUAH_balance() - grn2);
                                    log.info("Account \'" + account.getPhoneNumber() + "\' bought " + grn2 + "UAH. Paid " +
                                            sum2 + "$.");
                                    dao.buySomething(account, boss);
                                } else {
                                    ConsoleHelper.writeMessage(SYSTEM_OUT_OF_MONEY);
                                }
                            } else {
                                log.error("System out of UAH's!!!");
                                throw new NotEnoughMoneyException("Not enough money...");
                            }
                        }
                        break;

                    case 3:
                        double sum3 = askingForAmount();
                        ConsoleHelper.writeMessage(VERIFY);
                        int answer3 = ConsoleHelper.readInt();
                        if (answer3 == 1) {

                            double euro3 = sum3 / courses.getUAH_to_Euro();
                            if (boss.getEuro_balance() >= euro3) {
                                payingForSmthMoneyChange(sum3);
                                boss.setEuro_balance(boss.getEuro_balance() - euro3);
                                account.setEuro_balance(account.getEuro_balance() + euro3);
                                log.info("Account \'" + account.getPhoneNumber() + "\' bought " + euro3 + "€. Paid " +
                                        sum3 + "UAH.");
                                dao.buySomething(account, boss);
                            } else {
                                log.error("System out of euros!!!");
                                ConsoleHelper.writeMessage(SYSTEM_OUT_OF_MONEY);
                            }
                        }
                        break;

                    case 4:
                        double sum4 = askingForAmount();
                        ConsoleHelper.writeMessage(VERIFY);
                        int answer4 = ConsoleHelper.readInt();
                        if (answer4 == 1) {
                            if (account.getEuro_balance() >= sum4) {
                                double grn4 = sum4 * courses.getEuro_to_UAH();
                                if (boss.getUAH_balance() >= grn4) {
                                    account.setEuro_balance(account.getEuro_balance() - sum4);
                                    boss.setEuro_balance(boss.getEuro_balance() + sum4);
                                    account.setUAH_balance(account.getUAH_balance() + grn4);
                                    boss.setUAH_balance(boss.getUAH_balance() - grn4);
                                    log.info("Account \'" + account.getPhoneNumber() + "\' bought " + grn4 + "UAH. Paid " +
                                            sum4 + "€.");
                                    dao.buySomething(account, boss);
                                } else {
                                    log.error("System out of UAH's!!!");
                                    ConsoleHelper.writeMessage(SYSTEM_OUT_OF_MONEY);
                                }
                            } else {
                                throw new NotEnoughMoneyException("Not enough money...");
                            }
                        }
                        break;
                }
            } else {
                log.error("The exchange rates wasn't determined!!!");
                ConsoleHelper.writeMessage("Unfortunately, we have not yet determined the exchange rates for today." +
                        "\nPlease try again later.");
            }
        } else {
            throw new UnsupportedOperationException("Your account is not able to convert money.");
        }
    }

    private double askingForAmount() {
        ConsoleHelper.writeMessage("Enter the amount you want to change: ");
        return DoubleRounder.round(ConsoleHelper.readDouble(), 2);
    }
}
