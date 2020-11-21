package commands;

import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;

public class ShowInfoCommand extends AbstractAccountCommand {


    public ShowInfoCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("    Welcome to my personal bank app. In this app you are able" +
                " to transfer money by phone number, change money into another currencies. \n" + "Some options are paid: \n " +
                "1. Status increasing. \n" +
                "There are 3 types of status in app: " +
                "\n-Standard (your transaction commission will be 1%). " +
                "\n-GOLD (your transaction commission will be 0.5%) it costs 5000UAH to get this status from standard one." +
                "\n-PLATINUM (your transaction commission will be 0.0%) it costs 10000UAH to get this status from gold one." +
                "\n2. Making your account multi-currency." +
                "\nYou will be able to contain and change your money into dollars and euro. This option costs " +
                "only 650UAH once." +
                "\n\n   If there will appear any questions about the app or cooperation proposals - feel free to contact " +
                "me on this email bat93222888@gmail.com.");
    }
}
