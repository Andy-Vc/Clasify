USE MASTER 
GO

IF DB_ID('DB_GESTOR_NOTAS') IS NOT NULL
BEGIN
	DROP DATABASE DB_GESTOR_NOTAS
END
GO

CREATE DATABASE DB_GESTOR_NOTAS
GO

USE DB_GESTOR_NOTAS
GO

CREATE TABLE TB_USUARIO(
	ID_USUARIO INT IDENTITY(1,1) PRIMARY KEY,
	NOMBRE_USUARIO VARCHAR(50) NOT NULL,
	APELLIDO_USUARIO VARCHAR(50) NOT NULL,
	EMAIL VARCHAR(50) UNIQUE NOT NULL,
	CONTRASENIA NVARCHAR(255) NOT NULL,
	ROL CHAR(1) CHECK (ROL IN ('P', 'E', 'A')),
	FLGELIMINADO BIT DEFAULT 0
)
GO

CREATE TABLE TB_GRADOS (
    ID_GRADO INT IDENTITY(1,1) PRIMARY KEY,
    NOMBRE_GRADO VARCHAR(50) NOT NULL UNIQUE,
	FLGELIMINADO BIT DEFAULT 0
)

CREATE TABLE TB_CURSOS(
	ID_CURSO VARCHAR(13) PRIMARY KEY,
	NOMBRE_CURSO VARCHAR(50) NOT NULL,
	ID_PROFESOR INT NOT NULL,
	ID_GRADO INT NOT NULL,
	FLGELIMINADO BIT DEFAULT 0,
	FOREIGN KEY (ID_PROFESOR) REFERENCES TB_USUARIO(ID_USUARIO),
	FOREIGN KEY (ID_GRADO) REFERENCES TB_GRADOS(ID_GRADO)
)
GO

CREATE TABLE TB_ESTUDIANTES_CURSOS(
	ID_CURSO VARCHAR(13) NOT NULL,
	ID_USUARIO INT NOT NULL,
	PRIMARY KEY (ID_CURSO, ID_USUARIO),
	FOREIGN KEY (ID_USUARIO) REFERENCES TB_USUARIO (ID_USUARIO),
	FOREIGN KEY (ID_CURSO) REFERENCES TB_CURSOS (ID_CURSO)
)
GO

CREATE TABLE TB_NOTAS (
    ID_NOTA INT IDENTITY(1,1) PRIMARY KEY,
    ID_CURSO VARCHAR(13) NOT NULL,
    ID_USUARIO INT NOT NULL,
	NRO_NOTA TINYINT NOT NULL CHECK (NRO_NOTA BETWEEN 1 AND 4),
    CALIFICACION DECIMAL(5,2) NOT NULL,
    COMENTARIO VARCHAR(255),
    FECHA_REGISTRO DATE DEFAULT GETDATE(),
    FOREIGN KEY (ID_CURSO) REFERENCES TB_CURSOS(ID_CURSO),
    FOREIGN KEY (ID_USUARIO) REFERENCES TB_USUARIO(ID_USUARIO)
)

-- Profesores (ROL = 'P')
INSERT INTO TB_USUARIO (NOMBRE_USUARIO, APELLIDO_USUARIO, EMAIL, CONTRASENIA, ROL)
VALUES 
('Carlos', 'Ramirez', 'carlos.ramirez@escuela.edu', '$2a$10$fw4ddVa2No5lkadsiYvj0um84MhWs6UCrKRzTtJxNH95lFLAKKay2', 'P'),--pass123
('Laura', 'Gomez', 'laura.gomez@escuela.edu', '$$2a$10$gCUQ8/nZJgvl.ipe7J8drudc1hJsnxTbKQi8YtOZbHrVq7.mWSai6', 'P'),--pass456

('Miguel', 'Torres', 'miguel.torres@estudiante.edu', '$2a$10$CshOmN5jewOXXTpExmtlM.jKXg/Fm6utGRKAQDcmPUOhUVMXYpq0.', 'E'),--pass789
('Ana', 'Martinez', 'ana.martinez@estudiante.edu', '$2a$10$.8484niRiAaCZsZlq23daevCgZ68ay9dEpRT8Zn44EmnKCKRymL0O', 'E'),--pass321
('Luis', 'Sanchez', 'luis.sanchez@estudiante.edu', '$2a$10$rh1TdLDmVeLE393mOtVmZOBZhP7LTDhmmYkq4DbK8qomkf1UXZmQq', 'E'),--	pass654

('Admin', 'Sistema', 'admin@gestor.edu', '$2a$10$14sTjoyRGcXXeifh2tteD.6zF0KjosvSJUSKNn7EdM/KLU/BpT96y', 'A');--adminpass

--Grados
INSERT INTO TB_GRADOS (NOMBRE_GRADO)
VALUES 
('Primero de Secundaria'),
('Segundo de Secundaria'),
('Tercero de Secundaria');

-- Cursos
INSERT INTO TB_CURSOS (ID_CURSO, NOMBRE_CURSO, ID_PROFESOR, ID_GRADO)
VALUES
('MATE1CR01', 'Matemáticas I', 1, 1),
('LENG2CR02', 'Lengua y Literatura', 2, 1),
('FISI1LG03', 'Física', 1, 2),
('HIST2LG04', 'Historia', 2, 2);

-- Estudiantes_Cursos
INSERT INTO TB_ESTUDIANTES_CURSOS (ID_CURSO, ID_USUARIO)
VALUES
('MATE1CR01', 3),
('MATE1CR01', 4),
('LENG2CR02', 3),
('LENG2CR02', 5),
('FISI1LG03', 4),
('HIST2LG04', 5);

--Notas
INSERT INTO TB_NOTAS (ID_CURSO, ID_USUARIO, NRO_NOTA, CALIFICACION, COMENTARIO)
VALUES
-- Usuario 3, Curso MATE1CR01, notas 1 a 4
('MATE1CR01', 3, 1, 15.50, 'Buen rendimiento'),
('MATE1CR01', 3, 2, 16.00, 'Mejoró en el segundo parcial'),
('MATE1CR01', 3, 3, 14.75, 'Debe repasar más'),
('MATE1CR01', 3, 4, 17.00, 'Buen cierre'),

-- Usuario 4, Curso MATE1CR01, notas 1 a 4
('MATE1CR01', 4, 1, 17.25, 'Excelente trabajo'),
('MATE1CR01', 4, 2, 18.00, 'Muy constante'),
('MATE1CR01', 4, 3, 17.50, 'Buen desempeño'),
('MATE1CR01', 4, 4, 19.00, 'Destacado'),

-- Usuario 3, Curso LENG2CR02, notas 1 a 4
('LENG2CR02', 3, 1, 14.00, 'Debe mejorar la redacción'),
('LENG2CR02', 3, 2, 15.00, 'Mejoría visible'),
('LENG2CR02', 3, 3, 13.50, 'Debe esforzarse más'),
('LENG2CR02', 3, 4, 16.00, 'Buen esfuerzo final'),

-- Usuario 5, Curso LENG2CR02, notas 1 a 4
('LENG2CR02', 5, 1, 18.00, 'Participación destacada'),
('LENG2CR02', 5, 2, 17.50, 'Muy buen análisis'),
('LENG2CR02', 5, 3, 18.25, 'Constante en sus entregas'),
('LENG2CR02', 5, 4, 19.00, 'Excelente presentación'),

-- Usuario 4, Curso FISI1LG03, notas 1 a 4
('FISI1LG03', 4, 1, 13.75, 'Entregó todas las prácticas'),
('FISI1LG03', 4, 2, 14.50, 'Mejoró en la teoría'),
('FISI1LG03', 4, 3, 14.00, 'Regular desempeño'),
('FISI1LG03', 4, 4, 15.00, 'Buen cierre'),

-- Usuario 5, Curso HIST2LG04, notas 1 a 4
('HIST2LG04', 5, 1, 19.00, 'Gran presentación final'),
('HIST2LG04', 5, 2, 18.50, 'Muy buen análisis'),
('HIST2LG04', 5, 3, 18.75, 'Constante en el curso'),
('HIST2LG04', 5, 4, 19.25, 'Excelente desempeño final');

