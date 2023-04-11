DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS distributioncenter;

CREATE TABLE distributioncenter (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    items_available INT NOT NULL,
    latitude DECIMAL(18, 10),
    longitude DECIMAL(18, 10)
);

CREATE TABLE item (
  id INT PRIMARY KEY AUTO_INCREMENT,
  brand_from VARCHAR(255),
  distribution_center_id INT,
  items_available INT,
  name VARCHAR(255),
  price DECIMAL,
  production_year INT
);

