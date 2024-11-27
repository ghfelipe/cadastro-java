CREATE TABLE tb_usuario_perfil
(
	id_usuario INT,
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario(id_usuario), -- FK
	id_perfil INT,
    FOREIGN KEY (id_perfil) REFERENCES tb_perfil(id_perfil) -- FK
)