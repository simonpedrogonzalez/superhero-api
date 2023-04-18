CREATE TABLE heroes (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    created_at datetime NULL,
    deleted_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT heroesPK PRIMARY KEY (id)
);

INSERT INTO heroes (name) VALUES
('Superman'),
('Spiderman'),
('Manolito el fuerte'),
('Linterna Verde');