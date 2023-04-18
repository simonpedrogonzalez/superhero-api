CREATE TABLE heroes (
    id BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    created_at datetime NULL,
    deleted_at datetime NULL,
    updated_at datetime NULL,
    CONSTRAINT heroesPK PRIMARY KEY (id)
);

INSERT INTO `heroes` (`id`, `name`, `created_at`, `updated_at`) VALUES
(1, 'Superman', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Spiderman', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Manolito el fuerte', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Linterna Verde', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);