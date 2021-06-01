SET foreign_key_checks = 0;
DROP TABLE IF EXISTS Tickets, Users;
SET foreign_key_checks = 1;

CREATE TABLE Users (
    PESEL    CHAR(11)    NOT NULL,
    Password VARCHAR(24) NOT NULL,
    FullName VARCHAR(24) NOT NULL,
    PRIMARY KEY (PESEL)
);

CREATE TABLE Tickets (
    TicketNr        CHAR(9)     NOT NULL,
    CarrierNr       CHAR(6)     NOT NULL,
    ReservationNr   CHAR(6)     NOT NULL,
    Price           INT         NOT NULL,
    PESEL           CHAR(11)    NOT NULL,
    FlightPath      CHAR(7)     NOT NULL,
    DepartureTime   DATETIME    NOT NULL,
    OperatorNr      CHAR(7),
    PRIMARY KEY (TicketNr, CarrierNr),
    FOREIGN KEY (PESEL) REFERENCES Users(PESEL)
);

INSERT INTO Users (PESEL, Password, FullName) VALUES
    ('41487625529', '123', 'Olivia Jones'),
    ('59668398803', '123', 'Noah WIlliams'),
    ('12287952565', '123', 'Emma Taylor');

INSERT INTO Tickets (TicketNr, CarrierNr, ReservationNr, Price, PESEL, FlightPath, DepartureTime, OperatorNr) VALUES
    ('546824001', 'IZ3476', 'KPMXCT', '7900' ,	'41487625529',	'MQT-AMA',	'2022-9-16 03:20', NULL),
    ('546824000', 'IZ3476', 'KPMXCT', '7900' , '59668398803',	'MQT-AMA',	'2022-9-16 03:20', NULL),
    ('230590000', 'XH2256', 'BXENSV', '10000',	'12287952565',	'BMI-TLH',	'2022-3-1 19:40', NULL),
    ('230590000', 'XH2995', 'BXENSV', '10000',	'12287952565',	'TLH-MKE',	'2022-3-3 21:10', NULL),
    ('230590000', 'XH5143', 'BXENSV', '10000',	'12287952565',	'MKE-CID',	'2022-3-4 21:10', 'KC8088');