CREATE TABLE IF NOT EXISTS tb_usuario
(
	id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    tx_email VARCHAR(30) NOT NULL UNIQUE KEY,
    tx_senha CHAR(200) NOT NULL,
    tx_codigo CHAR(6)
)