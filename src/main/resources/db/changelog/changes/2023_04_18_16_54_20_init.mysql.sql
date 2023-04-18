CREATE TABLE heroes (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    created_at datetime NULL,
    deleted_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT heroesPK PRIMARY KEY (id)
);

INSERT INTO `heroes` (`name`, `created_at`, `updated_at`) VALUES
('Superman', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Spiderman', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Manolito el fuerte', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Linterna Verde', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);