ALTER TABLE Kunde
    DROP CONSTRAINT IF EXISTS fk_kunde_versicherter;
DROP TABLE IF EXISTS Antrag;
DROP TABLE IF EXISTS Vorerkrankung;
DROP TABLE IF EXISTS Versicherungspolice;
DROP TABLE IF EXISTS Versicherter;
DROP TABLE IF EXISTS Kunde;
DROP TABLE IF EXISTS Anschrift;

DROP TABLE IF EXISTS Ort;
DROP TABLE IF EXISTS Erkrankung;
DROP TABLE IF EXISTS Tarif;
DROP TABLE IF EXISTS Antragsklassifizierung;

DROP SEQUENCE IF EXISTS versicherter_id_seq;
DROP SEQUENCE IF EXISTS antrag_id_seq;
DROP SEQUENCE IF EXISTS erkrankung_id_seq;
DROP SEQUENCE IF EXISTS tarif_id_seq;
DROP SEQUENCE IF EXISTS antragsklassifizierung_id_seq;
DROP SEQUENCE IF EXISTS anschrift_id_seq;
DROP SEQUENCE IF EXISTS versicherungspolice_id_seq;

-- atomare Tabellen

CREATE TABLE Ort
(
    plz  VARCHAR(5) PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

INSERT INTO Ort
VALUES ('51643', 'Gummersbach');
INSERT INTO Ort
VALUES ('51515', 'Kürten');

CREATE SEQUENCE erkrankung_id_seq;
CREATE TABLE Erkrankung
(
    erkrankung_id INT PRIMARY KEY,
    kategorie     INT NOT NULL,
    beschreibung  TEXT
);

INSERT INTO Erkrankung
VALUES (nextval('erkrankung_id_seq'), 3, 'Herzfehler');
INSERT INTO Erkrankung
VALUES (nextval('erkrankung_id_seq'), 1, 'Raucherlunge');
INSERT INTO Erkrankung
VALUES (nextval('erkrankung_id_seq'), 2, 'Fettleber');

CREATE SEQUENCE tarif_id_seq;
CREATE TABLE Tarif
(
    tarif_id    INT PRIMARY KEY,
    bezeichnung VARCHAR(255)
);

INSERT INTO Tarif
VALUES (nextval('tarif_id_seq'), 'Basistarif');
INSERT INTO Tarif
VALUES (nextval('tarif_id_seq'), 'Premiumtarif');

CREATE SEQUENCE antragsklassifizierung_id_seq;
CREATE TABLE Antragsklassifizierung
(
    klassifizierung_id INT PRIMARY KEY,
    beschreibung       VARCHAR(255) NOT NULL
);

INSERT INTO Antragsklassifizierung
VALUES (nextval('antragsklassifizierung_id_seq'), 'versicherungsfähig');
INSERT INTO Antragsklassifizierung
VALUES (nextval('antragsklassifizierung_id_seq'), 'nicht versicherungsfähig');


-- einfache Abhängigkeit

CREATE SEQUENCE anschrift_id_seq;
CREATE TABLE Anschrift
(
    anschrift_id INT PRIMARY KEY,
    strasse      VARCHAR(255) NOT NULL,
    hausnummer   VARCHAR(10)  NOT NULL,
    ort_id       VARCHAR(5)   NOT NULL,
    FOREIGN KEY (ort_id) REFERENCES Ort (plz)
);

INSERT INTO Anschrift
VALUES (nextval('anschrift_id_seq'), 'Am Glockenberg', '10', '51515');
INSERT INTO Anschrift
VALUES (nextval('anschrift_id_seq'), 'Kaiserstr.', '42c', '51643');

CREATE TABLE Kunde
(
    kundennr        INT PRIMARY KEY,
    eintritt        DATE,
    versicherter_id INT
);

INSERT INTO Kunde
VALUES (12345, '2001-10-05', 1);
INSERT INTO Kunde
VALUES (12693, '2001-05-05', 3);

CREATE TABLE Versicherter
(
    versicherter_id INT PRIMARY KEY,
    nachname        VARCHAR(255) NOT NULL,
    vorname         VARCHAR(255) NOT NULL,
    geburtsdatum    DATE         NOT NULL,
    geschlecht      CHAR(1)      NOT NULL,
    groesse         INT          NOT NULL,
    gewicht         INT          NOT NULL,
    anschrift_id    INT          NOT NULL,
    kundennr        INT          NOT NULL,
    FOREIGN KEY (anschrift_id) REFERENCES Anschrift (anschrift_id),
    FOREIGN KEY (kundennr) REFERENCES Kunde (kundennr)
);

INSERT INTO Versicherter
VALUES (1, 'Pufpaff', 'Sebastian', now(), 'm', 187, 72, 1, 12345);
INSERT INTO Versicherter
VALUES (2, 'Müller', 'Sarah', '2001-10-05', 'w', 187, 72, 2, 12345);
INSERT INTO Versicherter
VALUES (3, 'Page', 'Eliot', '1987-02-21', 'd', 155, 57, 2, 12693);


ALTER TABLE Kunde
    ADD CONSTRAINT fk_kunde_versicherter FOREIGN KEY (versicherter_id) REFERENCES Versicherter (versicherter_id);


--ALTER TABLE Kunde ADD COLUMN

-- mehrfache Abhängigkeit

CREATE SEQUENCE versicherungspolice_id_seq;
CREATE TABLE Versicherungspolice
(
    versicherungspolice_id     INT PRIMARY KEY,
    kundennr                   INT            NOT NULL,
    neukunde                   BOOLEAN        NOT NULL,
    risikozuschlag             NUMERIC(10, 2),
    risikozuschlagsbegruendung TEXT,
    monatlicher_beitrag        NUMERIC(10, 2) NOT NULL,
    initiale_beitragshoehe     NUMERIC(10, 2) NOT NULL,
    vertragsbeginn             DATE           NOT NULL,
    tarif_id                   INT            NOT NULL,
    FOREIGN KEY (kundennr) REFERENCES Kunde (kundennr),
    FOREIGN KEY (tarif_id) REFERENCES Tarif (tarif_id)
);

INSERT INTO Versicherungspolice
VALUES (nextval('versicherungspolice_id_seq'), 12345, true, 12.4, 's. Vorerkrankung', 97.54, 85.10, '2020-02-15',
        1);
INSERT INTO Versicherungspolice
VALUES (nextval('versicherungspolice_id_seq'), 12693, false, NULL, NULL, 97.54, 85.10, '2020-02-15',
        1);

CREATE SEQUENCE antrag_id_seq;
CREATE TABLE Antrag
(
    antrag_id                 INT PRIMARY KEY,
    antragsklassifizierung_id INT,
    antragsdatum              DATE NOT NULL,
    versicherungspolice_id    INT  NOT NULL,
    FOREIGN KEY (antragsklassifizierung_id) REFERENCES Antragsklassifizierung (klassifizierung_id),
    FOREIGN KEY (versicherungspolice_id) REFERENCES Versicherungspolice (versicherungspolice_id)
);

INSERT INTO Antrag
VALUES (nextval('antrag_id_seq'), 1, now(), 1);
INSERT INTO Antrag
VALUES (nextval('antrag_id_seq'), 2, now(), 2);


CREATE TABLE Vorerkrankung
(
    versicherungspolice_id INT NOT NULL,
    erkrankung_id          INT NOT NULL,
    FOREIGN KEY (versicherungspolice_id) REFERENCES Versicherungspolice (versicherungspolice_id),
    FOREIGN KEY (erkrankung_id) REFERENCES Erkrankung (erkrankung_id)
);

INSERT INTO Vorerkrankung
VALUES (1, 1);
INSERT INTO Vorerkrankung
VALUES (2, 2);
INSERT INTO Vorerkrankung
VALUES (2, 3);
