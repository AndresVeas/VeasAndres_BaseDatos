-- database: ../database/PoliSalud.db

/*
copyRight EPN 2025
Andrés Enrique Veas Loor
DDL: PoliSalud.db >> Permite crear las tablas que dan estructura a la base de datos
*/

DROP TABLE IF EXISTS ConsultaMedica;
DROP TABLE HorarioAtencion;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Rol;

CREATE TABLE Rol (
     IdRol              INTEGER  PRIMARY KEY AUTOINCREMENT
    ,Nombre             TEXT     NOT NULL

    ,Estado             TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion      DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion  DATETIME 
);

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


CREATE TABLE HorarioAtencion (
     IdHorarioAtencion  INTEGER  PRIMARY KEY AUTOINCREMENT
    ,Horario            TEXT     NOT NULL

    ,Estado             TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion      DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion  DATETIME 
);


CREATE TABLE ConsultaMedica (
     IdConsultaMedica         INTEGER  PRIMARY KEY AUTOINCREMENT
    ,IdPaciente               VARCHAR  (1) UNIQUE NOT NULL REFERENCES Persona (IdPersona)
    ,IdMedico                 VARCHAR  (1) NOT NULL REFERENCES Persona (IdPersona)  
    ,IdHorarioAtencion        TEXT     UNIQUE NOT NULL REFERENCES HorarioAtencion (IdHorarioAtencion)
    
    ,Estado                   TEXT     NOT NULL DEFAULT ('A')
    ,FechaCreacion            DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion        DATETIME 
);
