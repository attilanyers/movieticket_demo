CREATE SEQUENCE movie_seq START WITH 1000 INCREMENT BY 50;
CREATE TABLE movie (
    id INTEGER PRIMARY KEY,
    title VARCHAR(255),
    director VARCHAR(255),
    genre VARCHAR(255),
    duration INTEGER,
    length SMALLINT,
    created_at TIMESTAMP,
    create_by UUID,
    updated_at TIMESTAMP,
    updated_by UUID,
    deleted_at TIMESTAMP,
    deleted_by UUID
);

CREATE SEQUENCE cinema_seq START WITH 1000 INCREMENT BY 50;
CREATE TABLE cinema (
    id INTEGER PRIMARY KEY,
    name VARCHAR(255),
    location VARCHAR(255),
    created_at TIMESTAMP,
    create_by UUID,
    updated_at TIMESTAMP,
    updated_by UUID,
    deleted_at TIMESTAMP,
    deleted_by UUID
);

CREATE TABLE "user"
 (
    id UUID PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    password_reset_token VARCHAR(255),
    created_at TIMESTAMP,
    create_by UUID,
    updated_at TIMESTAMP,
    updated_by UUID,
    deleted_at TIMESTAMP,
    deleted_by UUID
);

CREATE SEQUENCE screening_seq START WITH 1000 INCREMENT BY 50;
CREATE TABLE screening (
    id INTEGER PRIMARY KEY,
    cinema_id INTEGER REFERENCES cinema(id),
    movie_id INTEGER REFERENCES movie(id),
    start_time TIMESTAMP,
    created_at TIMESTAMP,
    create_by UUID,
    updated_at TIMESTAMP,
    updated_by UUID,
    deleted_at TIMESTAMP,
    deleted_by UUID
);

CREATE SEQUENCE reservation_seq START WITH 1000 INCREMENT BY 50;
CREATE TABLE reservation (
    id INTEGER PRIMARY KEY,
    screening_id INTEGER REFERENCES screening(id),
    user_id UUID REFERENCES "user"
(id),
    seat_number VARCHAR(255),
    created_at TIMESTAMP,
    create_by UUID,
    updated_at TIMESTAMP,
    updated_by UUID,
    deleted_at TIMESTAMP,
    deleted_by UUID
);

CREATE TABLE user_authority (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES "user"
(id),
    authority VARCHAR(255),
    created_at TIMESTAMP,
    create_by UUID,
    updated_at TIMESTAMP,
    updated_by UUID,
    deleted_at TIMESTAMP,
    deleted_by UUID
);

