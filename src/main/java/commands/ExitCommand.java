package commands;

import dao.Dao;
import helpers.ConsoleHelper;
import model.Account;

public class ExitCommand extends AbstractAccountCommand {
    
    public ExitCommand(Account account, Dao dao) {
        super(account, dao);
    }

    @Override
    public void execute() {
        ConsoleHelper.writeMessage("Goodbye! Hope to see you soon...");
        dao.close();
        System.exit(0);
    }
}
