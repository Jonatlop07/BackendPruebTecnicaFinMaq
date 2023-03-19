DROP TABLE IF EXISTS phones;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id UUID NOT NULL PRIMARY KEY,
    created TIMESTAMP(6) WITH TIME ZONE,
    email VARCHAR(255) NOT NULL UNIQUE,
    is_active BOOLEAN,
    last_login TIMESTAMP(6) WITH TIME ZONE,
    modified TIMESTAMP(6) WITH TIME ZONE,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    token VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS phones (
    id UUID NOT NULL PRIMARY KEY,
    city_code VARCHAR(255),
    country_code VARCHAR(255),
    number VARCHAR(255),
    user_id UUID,
    FOREIGN KEY (user_id) REFERENCES users
);