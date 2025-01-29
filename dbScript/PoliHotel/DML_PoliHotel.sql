-- database: ../../database/PoliHotel.db

/*
copyRight EPN 2025
Andrés Enrique Veas Loor
DML: PoliHotel.db >> Permite la inserción de datos y la consulta de tablas
*/

INSERT INTO TipoHabitacion (Nombre, Precio)
VALUES
('Suite', 100),
('Doble', 70),
('Individual', 50),
('Familiar', 120),
('Presidencial', 200);

INSERT INTO Rol (Nombre) VALUES ('Huesped'),('Recepcionista');

INSERT INTO Persona (Nombre, Apellido, IdRol, Cedula)
VALUES
('Juan', 'Perez', 1, '12345678-1'),
('Maria', 'Gomez', 1, '12345678-2'),
('Carlos', 'Lopez', 1, '12345678-3'),
('Ana', 'Martinez', 1, '12345678-4'),
('Luis', 'Garcia', 1, '12345678-5'),
('Elena', 'Rodriguez', 1, '12345678-6'),
('Pedro', 'Hernandez', 1, '12345678-7'),
('Laura', 'Fernandez', 1, '12345678-8'),
('Jorge', 'Sanchez', 1, '12345678-9'),
('Sofia', 'Ramirez', 1, '12345678-10'),
('Miguel', 'Torres', 1, '12345678-11'),
('Lucia', 'Flores', 1, '12345678-12'),
('Diego', 'Rivera', 1, '12345678-13'),
('Valeria', 'Gonzalez', 1, '12345678-14'),
('Fernando', 'Castro', 1, '12345678-15'),
('Isabel', 'Vargas', 1, '12345678-16'),
('Ricardo', 'Ramos', 1, '12345678-17'),
('Patricia', 'Ortega', 1, '12345678-18'),
('Andres', 'Silva', 1, '12345678-19'),
('Gabriela', 'Morales', 1, '12345678-20'),
('Francisco', 'Reyes', 1, '12345678-21'),
('Natalia', 'Jimenez', 1, '12345678-22'),
('Hector', 'Ruiz', 1, '12345678-23'),
('Daniela', 'Mendoza', 1, '12345678-24'),
('Rafael', 'Guerrero', 1, '12345678-25'),
('Monica', 'Cruz', 1, '12345678-26'),
('Alberto', 'Ortiz', 1, '12345678-27'),
('Paula', 'Delgado', 1, '12345678-28'),
('Enrique', 'Rojas', 1, '12345678-29'),
('Sandra', 'Molina', 1, '12345678-30'),
('Victor', 'Navarro', 1, '12345678-31'),
('Carolina', 'Campos', 1, '12345678-32'),
('Eduardo', 'Peña', 1, '12345678-33'),
('Adriana', 'Soto', 1, '12345678-34'),
('Manuel', 'Suarez', 1, '12345678-35'),
('Rosa', 'Romero', 1, '12345678-36'),
('Felipe', 'Iglesias', 1, '12345678-37'),
('Claudia', 'Vega', 1, '12345678-38'),
('Oscar', 'Paredes', 1, '12345678-39'),
('Teresa', 'Cabrera', 1, '12345678-40'),
('Roberto', 'Salazar', 1, '12345678-41'),
('Carmen', 'Rivas', 1, '12345678-42'),
('Gustavo', 'Espinoza', 1, '12345678-43'),
('Alicia', 'Mejia', 1, '12345678-44'),
('Pablo', 'Lara', 1, '12345678-45'),
('Marta', 'Fuentes', 1, '12345678-46'),
('Raul', 'Campos', 1, '12345678-47'),
('Silvia', 'Carrillo', 1, '12345678-48'),
('Mario', 'Serrano', 2, '12345678-49'),
('Angela', 'Miranda', 2, '12345678-50');

INSERT INTO Habitacion (NumeroHabitacion, PisoHabitacion, TipoHabitacion)
VALUES
(101, 1, 1),
(102, 1, 2),
(103, 1, 3),
(104, 1, 4),
(105, 1, 5),
(106, 1, 1),
(107, 1, 2),
(108, 1, 3),
(109, 1, 4),
(110, 1, 5),

(201, 2, 1),
(202, 2, 2),
(203, 2, 3),
(204, 2, 4),
(205, 2, 5),
(206, 2, 1),
(207, 2, 2),
(208, 2, 3),
(209, 2, 4),
(210, 2, 5),

(301, 3, 1),
(302, 3, 2),
(303, 3, 3),
(304, 3, 4),
(305, 3, 5),
(306, 3, 1),
(307, 3, 2),
(308, 3, 3),
(309, 3, 4),
(310, 3, 5);

INSERT INTO ReservarHabitacion (IdRecepcionista, IdHuesped, IdHabitacion)
VALUES
(49, 1, 1),
(49, 2, 2),
(49, 3, 3),
(49, 4, 4),
(49, 5, 5),
(49, 6, 6),
(49, 7, 7),
(49, 8, 8),
(49, 9, 9),
(49, 10,10),

(50, 11, 11),
(50, 12, 12),
(50, 13, 13),
(50, 14, 14),
(50, 15, 15),
(50, 16, 16),
(50, 17, 17),
(50, 18, 18);


SELECT
     rh.IdReservarHabitacion    "Numero de Reserva"
    ,ha.NumeroHabitacion        "Numero de habitacion"
    ,ha.PisoHabitacion          "Piso de habitacion"
    ,th.Nombre                  "Tipo de habitacion"
    ,th.Precio                  "Precio por noche"
    ,hu.Nombre                  "Nombre Huesped"
    ,hu.Apellido                "Apellido Huesped"
    ,hu.Cedula                  "Cedula Huesped"
    ,re.Nombre                  "Nombre Recepcionista"
    ,re.Apellido                "Apellido Recepcionista"
    ,rh.FechaReservacion        "Fecha de Reservacion"
    ,rh.EstadoReserva           "Estado de Habitacion"
FROM ReservarHabitacion rh
JOIN Persona            hu   ON  rh.IdHuesped       = hu.IdPersona  
JOIN Persona            re   ON  rh.IdRecepcionista = re.IdPersona
JOIN Habitacion         ha   ON  rh.IdHabitacion    = ha.IdHabitacion
JOIN TipoHabitacion     th   ON  ha.TipoHabitacion  = th.IdTipoHabitacion;


SELECT * FROM ReservarHabitacion WHERE IdReservarHabitacion <=4;