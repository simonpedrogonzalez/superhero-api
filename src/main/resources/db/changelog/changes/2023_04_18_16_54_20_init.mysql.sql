CREATE TABLE heros (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    created_at datetime NULL,
    deleted_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT herosPK PRIMARY KEY (id)
);

INSERT INTO heros (name) VALUES
('Superman'),
('Spiderman'),
('Manolito el fuerte'),
('Linterna Verde');