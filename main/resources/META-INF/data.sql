-- Add admin user when USER table is empty
INSERT INTO USER (LOGIN, PASSWORD) SELECT 'admin', 'admin' WHERE NOT EXISTS (SELECT * FROM USER);
