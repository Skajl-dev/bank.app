
USE bank.app;

DROP TABLE IF EXISTS account;

CREATE TABLE account
(
    phoneNumber     VARCHAR(10) NOT NULL,
    password        VARCHAR(15)  NULL,
    name            VARCHAR(30)  NULL,
    surname         VARCHAR(30)  NULL,
    email           VARCHAR(70)  NULL,
    accountType     VARCHAR(9)   NULL,
    isCurrency      BIT(1)       NULL,
    UAH_balance     DOUBLE       NULL,
    dollar_balance  DOUBLE       NULL,
    euro_balance    DOUBLE       NULL,
    creation_time   DATE         NULL,
        PRIMARY KEY (phoneNumber)

)

