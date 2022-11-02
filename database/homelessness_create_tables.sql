PRAGMA foreign_keys = OFF;
drop table if exists LGA;
drop table if exists HomlessGroup;
drop table if exists INCOME2018;
drop table if exists POPULATION;
drop table if exists PERSONA;
drop table if exists STUDENT;
PRAGMA foreign_keys = ON;

CREATE TABLE LGA (
    lga_code          INTEGER NOT NULL,
    lga_name          TEXT NOT NULL,
    lga_type          CHAR (2),
    area_sqkm         DOUBLE,
    latitude          DOUBLE,
    longitude         DOUBLE,
    PRIMARY KEY (lga_code)
);

CREATE TABLE HomlessGroup (
    lga_code          INTEGER NOT NULL,
    year              INTEGER NOT NULL,
    status            CHAR (10) NOT NULL,
    sex               CHAR (2),
    age_group         CHAR (10),
    count             INTEGER,
    PRIMARY KEY (lga_code, status, year, sex, age_group)
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
);

CREATE TABLE INCOME2018
    (
    lga_code INTEGER,
    MEDINCOME REAL,
    MEDAGE INTEGER,
    MEDMORTGAGE REAL,
    MEDRENT REAL,
    PRIMARY KEY(lga_code),
    FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    );

CREATE TABLE POPULATION
    (
        lga_code INTEGER,
        year INTEGER,
        population INTEGER, 
        PRIMARY KEY(lga_code),
        FOREIGN KEY (lga_code) REFERENCES LGA(lga_code)
    );

CREATE TABLE PERSONA
    (
        name VARCHAR(30),
        Attribute   BLOB,
        Description TEXT,
        PRIMARY KEY(name)
    );

CREATE TABLE STUDENT
    (
        studentID  TEXT,
        Name  TEXT,
        PRIMARY KEY(studentID)
    );
