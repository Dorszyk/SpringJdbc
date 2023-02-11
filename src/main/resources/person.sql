DROP TABLE  IF EXISTS person CASCADE;

create TABLE person
(
    id SERIAL NOT NULL,
    age     INT,
    name    VARCHAR(255),
    PRIMARY KEY (id),
    UNIQUE (name)
);