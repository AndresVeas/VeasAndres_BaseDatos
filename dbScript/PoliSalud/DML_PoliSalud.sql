-- database: ../database/PoliSalud.db

/*
copyRight EPN 2025
Andrés Enrique Veas Loor
DML: PoliSalud.db >> Permite la inserción de datos y la consulta de tablas
*/

INSERT INTO Rol (Nombre) VALUES ('Paciente'),('Médico');

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
('Roberto', 'Salazar', 2, '12345678-41'),
('Carmen', 'Rivas', 2, '12345678-42'),
('Gustavo', 'Espinoza', 2, '12345678-43'),
('Alicia', 'Mejia', 2, '12345678-44'),
('Pablo', 'Lara', 2, '12345678-45'),
('Marta', 'Fuentes', 2, '12345678-46'),
('Raul', 'Campos', 2, '12345678-47'),
('Silvia', 'Carrillo', 2, '12345678-48'),
('Mario', 'Serrano', 2, '12345678-49'),
('Angela', 'Miranda', 2, '12345678-50');

INSERT INTO HorarioAtencion (Horario)
VALUES
        ('07:00 - 07:40'),
        ('07:50 - 08:30'),
        ('08:40 - 09:20'),
        ('09:30 - 10:10'),
        ('10:20 - 11:00'),
        ('11:10 - 11:50'),
        ('12:00 - 12:40'),
        ('12:50 - 13:30'),
        ('13:40 - 14:20'),
        ('14:30 - 15:10');

INSERT INTO ConsultaMedica (IdPaciente, IdMedico, IdHorarioAtencion)
VALUES      (1, 41, 1),
            (2, 42, 8),
            (3, 43, 3),
            (4, 44, 4),
            (5, 45, 5),
            (6, 46, 6),
            (7, 47, 7);


UPDATE ConsultaMedica SET IdHorarioAtencion = 9, IdMedico = 45 WHERE IdPaciente = 1;

SELECT 
     cm.IdConsultaMedica   "Numero de Consulta"
    ,p.Nombre              "Nombre Paciente"
    ,p.Apellido            "Apellido Paciente"
    ,p.Cedula              "Cedula Paciente"
    ,m.Nombre              "Nombre Doctor/a"
    ,m.Apellido            "Apellido Doctor/a"
    ,h.Horario             

FROM ConsultaMedica     as cm
JOIN Persona            as p    ON cm.IdPaciente        = p.IdPersona
JOIN Persona            as m    ON cm.IdMedico          = m.IdPersona
JOIN HorarioAtencion    as h    ON cm.IdHorarioAtencion = h.IdHorarioAtencion;

