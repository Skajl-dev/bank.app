USE bank.app;

DROP TABLE IF EXISTS exchange_courses;

CREATE TABLE exchange_courses
(
    creation_time   DATE        NOT NULL,
    UAH_to_Dollar     DOUBLE       NULL,
    Dollar_to_UAH     DOUBLE       NULL,
    UAH_to_Euro       DOUBLE       NULL,
    Euro_to_UAH       DOUBLE       NULL,
    PRIMARY KEY (creation_time)
)
