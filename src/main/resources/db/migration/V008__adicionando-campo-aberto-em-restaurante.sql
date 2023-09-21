ALTER TABLE restaurante ADD aberto BOOLEAN NOT NULL;
UPDATE restaurante SET restaurante.aberto = TRUE;