-- database: ../../database/PoliHotel.db

/*
copyRight EPN 2025
Andrés Enrique Veas Loor
DDL: PoliHotel.db >> Permite crear las tablas que dan estructura a la base de datos
*/

DROP TABLE IF EXISTS ReservarHabitacion;
DROP TABLE IF EXISTS Habitacion;
DROP TABLE IF EXISTS TipoHabitacion;
DROP TABLE IF EXISTS Rol;
DROP TABLE IF EXISTS Persona;

CREATE TABLE Persona (
     IdPersona          INTEGER  PRIMARY KEY AUTOINCREMENT
    ,IdRol              TEXT     NOT NULL REFERENCES Rol (IdRol)
    ,Nombre             TEXT     NOT NULL
    ,Apellido           TEXT     NOT NULL
    ,Cedula             INTEGER  NOT NULL
    
    ,Estado             TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion      DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion  DATETIME  
);

CREATE TABLE Rol (
     IdRol              INTEGER  PRIMARY KEY AUTOINCREMENT
    ,Nombre             TEXT     NOT NULL

    ,Estado             TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion      DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion  DATETIME 
);

CREATE TABLE TipoHabitacion (
     IdTipoHabitacion   INTEGER  PRIMARY KEY AUTOINCREMENT
    ,Nombre             TEXT     UNIQUE NOT NULL
    ,Precio             INTEGER  NOT NULL

    ,Estado             TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion      DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion  DATETIME 
);


CREATE TABLE Habitacion  (
     IdHabitacion       INTEGER  PRIMARY KEY AUTOINCREMENT
    ,NumeroHabitacion   TEXT     UNIQUE NOT NULL
    ,PisoHabitacion     INTEGER  NOT NULL
    ,TipoHabitacion     INTEGER  REFERENCES TipoHabitacion (IdTipoHabitacion)

    ,Estado             TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion      DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion  DATETIME 
);


CREATE TABLE ReservarHabitacion (
     IdReservarHabitacion     INTEGER  PRIMARY KEY AUTOINCREMENT
    ,IdRecepcionista          VARCHAR  (1) NOT NULL REFERENCES Persona (IdPersona)
    ,IdHuesped                VARCHAR  (1) NOT NULL REFERENCES Persona (IdPersona)  
    ,IdHabitacion             TEXT     UNIQUE NOT NULL REFERENCES Habitacion (IdHabitacion)
    ,EstadoReserva            TEXT     DEFAULT ('Ocupado')
    ,FechaReservacion         DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    
    ,Estado                   TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion            DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion        DATETIME 
);

