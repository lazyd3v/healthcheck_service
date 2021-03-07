CREATE TABLE monitorable_server (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  url VARCHAR (255) NOT NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NOT NULL,
  last_checked TIMESTAMP,
  STATUS VARCHAR(20)
);
