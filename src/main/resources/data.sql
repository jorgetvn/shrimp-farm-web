DROP TABLE IF EXISTS shrimp_farm;
DROP TABLE IF EXISTS pond;

CREATE TABLE shrimp_farm (
  idShrimpFarm INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE pond (
  idPond INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  size DECIMAL(18, 5) NOT NULL,
  idShrimpFarm INT,
  foreign key (idShrimpFarm) references shrimp_farm(idShrimpFarm)
);

INSERT INTO shrimp_farm (name) VALUES
  ('La Victoria'),
  ('La Cocha'),
  ('James Blue');

INSERT INTO pond (name, size, idShrimpFarm) VALUES
  ('La Victoria', 100.3546, 1),
  ('La Cocha', 100.3546, 1 ),
  ('James Blue', 100.3546, 1);