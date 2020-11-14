package model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "accountType")
    private AccountType accountType;

    @Column(name = "isCurrency")
    private boolean isCurrency;

    @Column(name = "creation_time")
    private LocalDate creation_time;

    @Column(name = "UAH_balance")
    private double UAH_balance;
    @Column(name = "dollar_balance")
    private double dollar_balance;
    @Column(name = "euro_balance")
    private double euro_balance;



    public Account(String phoneNumber, String password, String name, String surname, String email, LocalDate date) {
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.email = email;
        accountType = AccountType.STANDARD;
        isCurrency = false;
        UAH_balance = 0.0;
        dollar_balance = 0.0;
        euro_balance = 0.0;
        creation_time = date;
    }

    public Account() {
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean isCurrency() {
        return isCurrency;
    }

    public void setCurrency(boolean currency) {
        isCurrency = currency;
    }

    public double getUAH_balance() {
        return UAH_balance;
    }

    public void setUAH_balance(double UAH_balance) {
        this.UAH_balance = UAH_balance;
    }

    public double getDollar_balance() {
        return dollar_balance;
    }

    public void setDollar_balance(double dollar_balance) {
        this.dollar_balance = dollar_balance;
    }

    public double getEuro_balance() {
        return euro_balance;
    }

    public void setEuro_balance(double euro_balance) {
        this.euro_balance = euro_balance;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreationTime() {
        return creation_time;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creation_time = creationTime;
    }

    @Override
    public String toString() {
        return "Account{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", accountType=" + accountType +
                ", isCurrency=" + isCurrency +
                ", creation_time=" + creation_time +
                ", UAH_balance=" + UAH_balance +
                ", dollar_balance=" + dollar_balance +
                ", euro_balance=" + euro_balance +
                '}';
    }
}
