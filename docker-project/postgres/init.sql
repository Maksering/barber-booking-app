CREATE TABLE IF NOT EXISTS "user"(
                                    id BIGSERIAL PRIMARY KEY,
                                    email VARCHAR(255),
                                    password VARCHAR(255),
                                    name VARCHAR(255),
                                    phone VARCHAR(255),
                                    role VARCHAR(10) CHECK (role IN ('CLIENT', 'MASTER', 'ADMIN'))
);

COMMENT ON COLUMN "user".role IS 'Possible values: CLIENT, MASTER, ADMIN';