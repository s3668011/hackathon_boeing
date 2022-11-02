CREATE TABLE INCOME2018
    (
	lga_code INTEGER,
	MEDINCOME REAL,
	MEDAGE INTEGER,
	MEDMORTGAGE REAL,
	MEDRENT REAL,
        PRIMARY KEY(LGACODE),
	FOREIGN KEY lga_code REFERENCES LGA(lga_code)
    );

CREATE TABLE POPULATION
    (
        lga_code INTEGER,
	year INTEGER,
	population INTEGER
        PRIMARY KEY(LGACODE, year),
	FOREIGN KEY lga_code REFERENCES LGA(lga_code)
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
        PRIMARY KEY(StudentID)
    );
