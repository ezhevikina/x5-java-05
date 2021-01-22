DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    holder varchar(126) NOT NULL,
    amount int NOT NULL default 0,
    is_active boolean NOT NULL
    );

INSERT INTO accounts ("holder", "amount", "is_active")
VALUES
  ('Holder1', 100, FALSE),
  ('Holder2', 200, TRUE),
  ('Holder3', 300, TRUE),
  ('Holder4', 400, TRUE),
  ('Holder5', 500, TRUE);