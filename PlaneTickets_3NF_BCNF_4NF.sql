SET foreign_key_checks = 0;
DROP TABLE IF EXISTS DepartureTimes, FlightPaths, CarrierNrs, FullNames, OperatorNrs, Reservations, TicketPESEL, Passwords;
SET foreign_key_checks = 1;

CREATE TABLE FlightPaths (
    CarrierNr   CHAR(6) PRIMARY KEY NOT NULL,
    FlightPath  CHAR(7)     NOT NULL
);

CREATE TABLE DepartureTimes (
    CarrierNr           CHAR(6)     NOT NULL,
    DepartureTime       DATETIME    NOT NULL,
    FOREIGN KEY (CarrierNr)
        REFERENCES FlightPaths (CarrierNr)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE CarrierNrs (
    TicketNr    CHAR(9)     NOT NULL,
    CarrierNr   CHAR(6)     NOT NULL,
    UNIQUE KEY(TicketNr, CarrierNr),
    FOREIGN KEY (CarrierNr)
        REFERENCES FlightPaths (CarrierNr)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE FullNames (
    PESEL       CHAR(11) PRIMARY KEY NOT NULL,
    FullName    VARCHAR(24) NOT NULL 
);

CREATE TABLE TicketPESEL (
    TicketNr        CHAR(9) NOT NULL,
    PESEL       CHAR(11)    NOT NULL,
    FOREIGN KEY (TicketNr)
        REFERENCES CarrierNrs (TicketNr)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (PESEL)
        REFERENCES FullNames (PESEL)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Passwords (
    PESEL       CHAR(11)    NOT NULL,
    Password    VARCHAR(24) NOT NULL,
    FOREIGN KEY (PESEL)
        REFERENCES FullNames (PESEL)
        ON DELETE CASCADE
        ON UPDATE CASCADE    
);

CREATE TABLE Reservations (
    TicketNr        CHAR(9) NOT NULL,
    ReservationNr   CHAR(6) NOT NULL,
    Price           INT     NOT NULL,
    FOREIGN KEY (TicketNr)
        REFERENCES CarrierNrs (TicketNr)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE OperatorNrs (
    CarrierNr   CHAR(6)     NOT NULL,
    OperatorNr  CHAR(7)     NOT NULL,
    FOREIGN KEY (CarrierNr)
        REFERENCES FlightPaths (CarrierNr)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

INSERT INTO FlightPaths(CarrierNr, FlightPath)
VALUES
    ('IZ3476', 'MQT-AMA'),
    ('XH2256', 'BMI-TLH'),
    ('XH2995', 'TLH-MKE'),
    ('XH5143', 'MKE-CID');

INSERT INTO DepartureTimes(CarrierNr, DepartureTime)
VALUES
    ('IZ3476', '2022-9-16 03:20:00'),
    ('XH2256', '2022-3-1 19:40:00' ),
    ('XH2995', '2022-3-3 21:10:00' ),
    ('XH5143', '2022-3-4 21:10:00' );

INSERT INTO CarrierNrs(TicketNr, CarrierNr)
VALUES
    ('546824001', 'IZ3476'),
    ('546824000', 'IZ3476'),
    ('230590000', 'XH2256'),
    ('230590000', 'XH2995'),
    ('230590000', 'XH5143');

INSERT INTO FullNames(PESEL, FullName)
VALUES
    ('41487625529', 'Olivia Jones'),
    ('59668398803', 'Noah WIlliams'),
    ('12287952565', 'Emma Taylor');

INSERT INTO Reservations(TicketNr, ReservationNr, Price)
VALUES
    ('546824001', 'KPMXCT', 7900),
    ('546824000', 'KPMXCT', 7900),
    ('230590000', 'BXENSV', 10000);

INSERT INTO OperatorNrs(CarrierNr, OperatorNr)
VALUES    
    ('XH5143', 'KC8088');