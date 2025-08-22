CREATE TABLE IF NOT EXISTS "user"(
                                    id BIGSERIAL PRIMARY KEY,
                                    email VARCHAR(255) UNIQUE NOT NULL,
                                    password VARCHAR(255) NOT NULL,
                                    name VARCHAR(255),
                                    phone VARCHAR(20),
                                    role VARCHAR(10) CHECK (role IN ('CLIENT', 'MASTER', 'ADMIN')),
                                    createdAt TIMESTAMP,
                                    updatedAt TIMESTAMP
);

COMMENT ON COLUMN "user".role IS 'Possible values: CLIENT, MASTER, ADMIN';