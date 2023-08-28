ALTER TABLE restaurante ADD ativo BOOLEAN NOT NULL;
UPDATE restaurante SET restaurante.ativo = TRUE;