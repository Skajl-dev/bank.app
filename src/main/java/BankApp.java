
import helpers.CommandExecutor;
import helpers.Operation;
import model.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class BankApp {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(BankConfig.class);
        Account account = (Account) context.getBean(Account.class);
        CommandExecutor commandExecutor = (CommandExecutor) context.getBean(CommandExecutor.class);


        Operation operation = null;
        do {
            operation = (Operation) context.getBean(Operation.class);
            commandExecutor.execute(operation);
        } while (operation != Operation.EXIT);


    }

}
