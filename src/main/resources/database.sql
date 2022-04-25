CREATE SCHEMA coffee;

CREATE TABLE coffee.coffee
(
    id             SERIAL PRIMARY KEY,
    coffee_grade   CHARACTER VARYING(255) UNIQUE NOT NULL,
    price_per_gram DOUBLE PRECISION       NOT NULL

);

CREATE TABLE coffee.order(
    id SERIAL PRIMARY KEY,
    delivery_date DATE NOT NULL,
    delivery_end_time TIME NOT NULL,
    delivery_start_time TIME NOT NULL,
    delivery_type CHARACTER VARYING(255) NOT NULL,
    final_price DOUBLE PRECISION NOT NULL,
    grams_amount INTEGER NOT NULL,
    coffee_id INTEGER REFERENCES coffee.coffee (id)
);

