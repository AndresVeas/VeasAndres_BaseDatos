-- database: ../../database/PoliBiblio.db

/*
copyRight EPN 2025
Andrés Enrique Veas Loor
DML: PoliBiblio.db >> Permite la inserción de datos y la consulta de tablas
*/

INSERT INTO Usuario (Nombre,Apellido)
VALUES 
('Andrés','Veas'),
('María', 'López'),
('Carlos','Muñoz'),
('Juan', 'Torres'),
('Ana', 'Saltos');

INSERT INTO Libro (NombreLibro, NombreAutor, ApellidoAutor, Editorial)
VALUES
('Cien Años de Soledad', 'Gabriel', 'García Márquez', 'Editorial Sudamericana'),
('La Rebelión en la Granja', 'George', 'Orwell', 'Secker & Warburg'),
('El Principito', 'Antoine', 'de Saint-Exupéry', 'Reynal & Hitchcock'),
('1984', 'George', 'Orwell', 'Secker & Warburg'),
('Don Quijote', 'Miguel', 'de Cervantes', 'Francisco de Robles'),
('Crimen y Castigo', 'Fiódor', 'Dostoyevski', 'La Estrella'),
('Orgullo y Prejuicio', 'Jane', 'Austen', 'Whitehall'),
('El Gran Gatsby', 'F. Scott', 'Fitzgerald', 'Charles Scribner Sons'),
('Matar a un Ruiseñor', 'Harper', 'Lee', 'J.B. Lippincott & Co.'),
('En Busca del Tiempo Perdido', 'Marcel', 'Proust', 'Grasset'),

('Los Miserables', 'Victor', 'Hugo', 'A. Lacroix'),
('El Hobbit', 'J.R.R.', 'Tolkien', 'George Allen & Unwin'),
('Fahrenheit 451', 'Ray', 'Bradbury', 'Ballantine Books'),
('La Metamorfosis', 'Franz', 'Kafka', 'Kurt Wolff'),
('El Alquimista', 'Paulo', 'Coelho', 'HarperTorch'),
('Cumbres Borrascosas', 'Emily', 'Brontë', 'Thomas Cautley Newby'),
('Madame Bovary', 'Gustave', 'Flaubert', 'Revue de Paris'),
('El Retrato de Dorian Gray', 'Oscar', 'Wilde', 'Ward, Lock & Co.'),
('La Odisea', 'Homero', '', 'N/A'),
('El Viejo y el Mar', 'Ernest', 'Hemingway', 'Charles Scribners Sons'),

('Alicia en el País de las Maravillas', 'Lewis', 'Carroll', 'Macmillan'),
('Drácula', 'Bram', 'Stoker', 'Archibald Constable and Company'),
('Frankenstein', 'Mary', 'Shelley', 'Lackington, Hughes, Harding'),
('El Castillo', 'Franz', 'Kafka', 'Kurt Wolff'),
('La Divina Comedia', 'Dante', 'Alighieri', 'Niccolò di Lorenzo'),
('Rayuela', 'Julio', 'Cortázar', 'Sudamericana'),
('El Amor en los Tiempos del Cólera', 'Gabriel', 'García Márquez', 'Editorial Oveja Negra'),
('Cien Mil Millas Bajo el Mar', 'Julio', 'Verne', 'Hetzel'),
('Las Aventuras de Tom Sawyer', 'Mark', 'Twain', 'Chatto & Windus'),
('Los Pilares de la Tierra', 'Ken', 'Follett', 'Penguin Random House'),

('La Sombra del Viento', 'Carlos', 'Ruiz Zafón', 'Planeta'),
('It', 'Stephen', 'King', 'Viking'),
('El Resplandor', 'Stephen', 'King', 'Doubleday'),
('Los Juegos del Hambre', 'Suzanne', 'Collins', 'Scholastic'),
('Harry Potter y la Piedra Filosofal', 'J.K.', 'Rowling', 'Bloomsbury'),
('El Código Da Vinci', 'Dan', 'Brown', 'Doubleday'),
('Ángeles y Demonios', 'Dan', 'Brown', 'Pocket Books'),
('Inferno', 'Dan', 'Brown', 'Doubleday'),
('Crepúsculo', 'Stephenie', 'Meyer', 'Little, Brown'),
('Luna Nueva', 'Stephenie', 'Meyer', 'Little, Brown'),

('Eclipse', 'Stephenie', 'Meyer', 'Little, Brown'),
('Amanecer', 'Stephenie', 'Meyer', 'Little, Brown'),
('El Nombre del Viento', 'Patrick', 'Rothfuss', 'DAW Books'),
('El Temor de un Hombre Sabio', 'Patrick', 'Rothfuss', 'DAW Books'),
('Juego de Tronos', 'George R.R.', 'Martin', 'Bantam Spectra'),
('Choque de Reyes', 'George R.R.', 'Martin', 'Bantam Spectra'),
('Tormenta de Espadas', 'George R.R.', 'Martin', 'Bantam Spectra'),
('Festín de Cuervos', 'George R.R.', 'Martin', 'Bantam Spectra'),
('Danza de Dragones', 'George R.R.', 'Martin', 'Bantam Spectra'),
('El Señor de los Anillos: La Comunidad del Anillo', 'J.R.R.', 'Tolkien', 'George Allen & Unwin');

INSERT INTO EstadoPrestamo (Nombre) 
VALUES      ('Prestado'),('Devuelto');


INSERT INTO Prestamo (IdUsuario, IdLibro, IdEstadoPrestamo)
VALUES               ('1', '23', '1'),
                     ('2', '45', '2'),
                     ('3', '12', '1'),
                     ('4', '34', '2'),
                     ('5', '7', '1');


UPDATE Prestamo SET IdEstadoPrestamo = '2' WHERE idUsuario = 1;
UPDATE Prestamo
SET IdEstadoPrestamo = '2', FechaDevolucion = datetime('now','localtime')
WHERE idUsuario = 3;

SELECT 
     p.IdPrestamo           "Numero de Prestamo"
    ,u.Nombre               "Nombre Usuario"
    ,u.Apellido             "Apellido Usuario"
    ,l.NombreLibro          "Nombre del Libro"
    ,e.Nombre               "Estado de Prestamo"
    ,p.FechaDevolucion      

FROM Prestamo           as p
JOIN Usuario            as u ON p.IdUsuario         = u.IdUsuario
JOIN Libro              as l ON p.IdLibro           = l.IdLibro
JOIN EstadoPrestamo     as e ON p.IdEstadoPrestamo  = e.IdEstadoPrestamo
WHERE p.IdEstadoPrestamo = '2';

INSERT INTO Prestamo (IdUsuario, IdLibro, IdEstadoPrestamo)
VALUES               ('1', '4', '1');


CREATE VIEW ViewPrestamo AS
SELECT 
     p.IdPrestamo           AS "Numero de Prestamo"
    ,u.Nombre               AS "Nombre Usuario"
    ,u.Apellido             AS "Apellido Usuario"
    ,l.NombreLibro          AS "Nombre del Libro"
    ,e.Nombre               AS "Estado de Prestamo"
    ,p.FechaDevolucion      
FROM Prestamo           AS p
JOIN Usuario            AS u ON p.IdUsuario         = u.IdUsuario
JOIN Libro              AS l ON p.IdLibro           = l.IdLibro
JOIN EstadoPrestamo     AS e ON p.IdEstadoPrestamo  = e.IdEstadoPrestamo;

SELECT * FROM ViewPrestamo;

DROP VIEW ViewPrestamo;