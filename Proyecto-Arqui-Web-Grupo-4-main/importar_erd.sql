CREATE TABLE DEPARTAMENTO (
    departamento_id INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE PROVINCIA (
    provincia_id INT PRIMARY KEY,
    departamento_id INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    FOREIGN KEY (departamento_id) REFERENCES DEPARTAMENTO(departamento_id)
);

CREATE TABLE DISTRITO (
    distrito_id INT PRIMARY KEY,
    provincia_id INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    FOREIGN KEY (provincia_id) REFERENCES PROVINCIA(provincia_id)
);

CREATE TABLE COLECCION (
    coleccion_id INT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

CREATE TABLE ROL (
    rol_id INT PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE USUARIO (
    usuario_id INT PRIMARY KEY,
    rol_id INT NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    contraseña VARCHAR(10) NOT NULL,
    telefono VARCHAR(9),
    foto_url VARCHAR(100),
    fecha_registro DATETIME NOT NULL,
    FOREIGN KEY (rol_id) REFERENCES ROL(rol_id)
);

CREATE TABLE PROPIEDAD (
    propiedad_id INT PRIMARY KEY,
    distrito_id INT NOT NULL,
    usuario_id INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descripcion VARCHAR(300),
    precio DECIMAL(12,2) NOT NULL,
    dirección VARCHAR(50) NOT NULL,
    fecha_publicación DATETIME NOT NULL,
    estado BINARY NOT NULL,
    metros_cuadrados INT,
    habitaciones INT,
    baños INT,
    url_vr VARCHAR(500),
    FOREIGN KEY (distrito_id) REFERENCES DISTRITO(distrito_id),
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(usuario_id)
);

CREATE TABLE IMAGENES (
    imagen_id INT PRIMARY KEY,
    propiedad_id INT NOT NULL,
    url_imagen VARCHAR(500) NOT NULL,
    descripción VARCHAR(300),
    FOREIGN KEY (propiedad_id) REFERENCES PROPIEDAD(propiedad_id)
);

CREATE TABLE FAVORITOS (
    favorito_id INT PRIMARY KEY,
    usuario_id INT NOT NULL,
    propiedad_id INT NOT NULL,
    colección_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(usuario_id),
    FOREIGN KEY (propiedad_id) REFERENCES PROPIEDAD(propiedad_id),
    FOREIGN KEY (colección_id) REFERENCES COLECCION(coleccion_id)
);

CREATE TABLE RECOMENDACIONES (
    recomendacion_id INT PRIMARY KEY,
    propiedad_id INT NOT NULL,
    usuario_id INT NOT NULL,
    FOREIGN KEY (propiedad_id) REFERENCES PROPIEDAD(propiedad_id),
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(usuario_id)
);

CREATE TABLE CONTACTO (
    contacto_id INT PRIMARY KEY,
    propiedad_id INT NOT NULL,
    usuario_id INT NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    correo VARCHAR(50) NOT NULL,
    telefono VARCHAR(9),
    mensaje VARCHAR(300),
    fecha DATETIME NOT NULL,
    FOREIGN KEY (propiedad_id) REFERENCES PROPIEDAD(propiedad_id),
    FOREIGN KEY (usuario_id) REFERENCES USUARIO(usuario_id)
);

CREATE TABLE CALIFICACION (
    calificación_id INT PRIMARY KEY,
    propiedad_id INT NOT NULL,
    puntuación INT NOT NULL,
    comentario VARCHAR(300),
    fecha DATETIME NOT NULL,
    FOREIGN KEY (propiedad_id) REFERENCES PROPIEDAD(propiedad_id)
);
