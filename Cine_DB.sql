-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS Cine_DB
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci;

USE Cine_DB;

-- Crear tabla cartelera según la entidad JPA
CREATE TABLE IF NOT EXISTS cartelera (
  id INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  director VARCHAR(50) NOT NULL,
  anio INT NOT NULL,
  duracion INT NOT NULL,
  genero VARCHAR(50) NOT NULL
);

-- Índices opcionales para mejorar búsquedas
CREATE INDEX idx_cartelera_titulo   ON cartelera (titulo);
CREATE INDEX idx_cartelera_director ON cartelera (director);
