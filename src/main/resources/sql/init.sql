CREATE TABLE IF NOT EXISTS company(
       id SERIAL PRIMARY KEY,
       name VARCHAR(128) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employee(
       id SERIAL PRIMARY KEY,
       firstname VARCHAR(128) NOT NULL,
       lastname VARCHAR(128) NOT NULL,
       birth_day DATE,
       salary INTEGER NOT NULL,
       company_id INTEGER REFERENCES company(id)
);
