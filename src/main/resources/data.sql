DROP TABLE IF EXISTS shrimp_farm;

CREATE TABLE shrimp_farm (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO shrimp_farm (name) VALUES
  ('La Victoria'),
  ('La Cocha'),
  ('James Blue');