CREATE table books
(
    isbn      VARCHAR(256) PRIMARY KEY,
    title     VARCHAR(256) NOT NULL,
    author    VARCHAR(256) NOT NULL,
    available BOOLEAN   default TRUE,
);

CREATE TYPE user_type_enum AS ENUM ('STUDENT', 'FACULTY', 'GUEST');
CREATE table users
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(256)   NOT NULL,
    email     VARCHAR(256)   NOT NULL,
    user_type user_type_enum NOT NULL,            -- amount of allowed books need to hard code in app
);

CREATE table borrowed_books
(
    record_id  BIGSERIAL PRIMARY KEY,
    user_id    BIGINT       NOT NULL,
    isbn       VARCHAR(256) NOT NULL,
    borrow_day timestamp    NOT NULL,
    return_day timestamp    NOT NULL,

    FOREIGN KEY (user_id) REFERENCEs users(id) ON DELETE CASCADE,
    FOREIGN KEY (isbn) REFERENCEs books (isbn) ON DELETE CASCADE
);



