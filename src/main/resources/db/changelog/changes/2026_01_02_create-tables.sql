CREATE TABLE cities(
    id SERIAL PRIMARY KEY,
    code varchar,
    citi_name varchar
);

CREATE TABLE habbits(
    id SERIAL PRIMARY KEY,
    duration numeric,
    habbit_name varchar,
    meta varchar,
    created_at date,
    updated_at date
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    full_name varchar,
    profession varchar,
    city_id int
);

CREATE TABLE users_habbits(
    user_id int,
    habbits_id int
)