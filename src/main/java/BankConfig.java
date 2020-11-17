import dao.Dao;
import dao.DaoJPA_Impl;
import helpers.ConsoleHelper;
import model.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BankConfig {

    @Bean
    @Scope("singleton")
    public Dao dao() {
        return new DaoJPA_Impl();
    }


}
