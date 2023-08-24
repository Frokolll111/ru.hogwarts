CREATE TABLE person
(
    "name" TEXT PRIMARY KEY UNIQUE NOT NULL,
    age INTEGER CHECK (age > 0 ) NOT NULL,
    license BOOLEAN  NOT NULL,
    idCar INTEGER REFERENCES  car (id)
);
CREATE TABLE car
(
   id INTEGER PRIMARY KEY UNIQUE NOT NULL,
   "brand" TEXT,
   "model" TEXT,
   price INTEGER
);

SELECT person.name, person.age, person.license,
car.brand, model, price FROM person LEFT JOIN car ON person.idCar = car.id;