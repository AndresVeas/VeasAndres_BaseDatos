-- database: ../../database/PoliBiblio.db

/*
copyRight EPN 2025
Andrés Enrique Veas Loor
DDL: PoliBiblio.db >> Permite crear las tablas que dan estructura a la base de datos
*/
DROP TABLE IF EXISTS Prestamo;
DROP TABLE IF EXISTS EstadoPrestamo;
DROP TABLE IF EXISTS Libro;
DROP TABLE IF EXISTS Usuario;



CREATE TABLE Usuario( 
     IdUsuario              INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre                 TEXT        NOT NULL
    ,Apellido               TEXT        NOT NULL
    ,Estado                 TEXT        NOT NULL DEFAULT ('A')
    ,FechaCreacion          DATETIME    NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion      DATETIME 
);


CREATE TABLE Libro( 
     IdLibro                INTEGER PRIMARY KEY AUTOINCREMENT
    ,NombreLibro            TEXT    NOT NULL
    ,NombreAutor            TEXT    NOT NULL
    ,ApellidoAutor          TEXT    NOT NULL
    ,Editorial              TEXT    NOT NULL
    ,Estado                 TEXT        NOT NULL DEFAULT ('A')
    ,FechaCreacion          DATETIME    NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion      DATETIME 
);

CREATE TABLE EstadoPrestamo (
     IdEstadoPrestamo       INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre                 TEXT        NOT NULL
    ,Estado                 TEXT        NOT NULL DEFAULT ('A')
    ,FechaCreacion          DATETIME    NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModificacion      DATETIME 
);

CREATE TABLE Prestamo( 
     IdPrestamo             INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdUsuario              INTEGER NOT NULL REFERENCES Usuario (IdUsuario)
    ,IdLibro                INTEGER NOT NULL REFERENCES Libro (IdLibro)
    ,IdEstadoPrestamo       INTEGER NOT NULL REFERENCES EstadoPrestamo (IdEstadoPrestamo)
    ,Estado                 TEXT        NOT NULL DEFAULT ('A')
    ,FechaCreacion          DATETIME    NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaDevolucion        DATETIME 
);