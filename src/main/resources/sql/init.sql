CREATE table books
(
    isbn      VARCHAR(256) PRIMARY KEY,
    title     VARCHAR(256) NOT NULL,
    author    VARCHAR(256) NOT NULL,
    available BOOLEAN default TRUE
);

CREATE TYPE user_type_enum AS ENUM ('STUDENT', 'FACULTY', 'GUEST');
CREATE table users
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(256)   NOT NULL,
    email     VARCHAR(256)   NOT NULL,
    user_type user_type_enum NOT NULL -- amount of allowed books need to hard code in app
);

CREATE table borrowed_books
(
    record_id  BIGSERIAL PRIMARY KEY,
    user_id    BIGINT       NOT NULL,
    isbn       VARCHAR(256) NOT NULL,
    borrow_day timestamp    NOT NULL,
    return_day timestamp    NOT NULL,

    FOREIGN KEY (user_id) REFERENCEs users (id) ON DELETE CASCADE,
    FOREIGN KEY (isbn) REFERENCEs books (isbn) ON DELETE CASCADE
);

INSERT INTO books (isbn, title, author, available)
VALUES ('978-0-06-112008-4', 'To Kill a Mockingbird', 'Harper Lee', TRUE),
       ('978-0-7432-7356-5', 'The Great Gatsby', 'F. Scott Fitzgerald', TRUE),
       ('978-0-452-28423-4', '1984', 'George Orwell', TRUE),
       ('978-0-14-143951-8', 'Pride and Prejudice', 'Jane Austen', TRUE),
       ('978-0-7432-4722-4', 'The Catcher in the Rye', 'J.D. Salinger', TRUE),
       ('978-0-316-76948-0', 'The Lord of the Rings', 'J.R.R. Tolkien', TRUE),
       ('978-0-439-70818-8', 'Harry Potter and the Sorcerers Stone', 'J.K. Rowling', TRUE),
       ('978-0-525-47535-5', 'The Seven Husbands of Evelyn Hugo', 'Taylor Jenkins Reid', TRUE),
       ('978-0-385-53328-3', 'The Handmaids Tale', 'Margaret Atwood', TRUE),
       ('978-0-7653-7654-3', 'Dune', 'Frank Herbert', TRUE),
       ('978-1-5011-3973-5', 'Educated', 'Tara Westover', TRUE),
       ('978-0-14-017739-8', 'Brave New World', 'Aldous Huxley', TRUE),
       ('978-0-7432-7357-2', 'The Da Vinci Code', 'Dan Brown', TRUE),
       ('978-0-06-231500-7', 'Gone Girl', 'Gillian Flynn', TRUE),
       ('978-0-525-55844-9', 'Where the Crawdads Sing', 'Delia Owens', TRUE);

INSERT INTO users (name, email, user_type)
VALUES ('Alice Johnson', 'alice.johnson@email.com', 'STUDENT'),
       ('Dr. Robert Smith', 'robert.smith@university.edu', 'FACULTY'),
       ('Emily Davis', 'emily.davis@student.edu', 'STUDENT'),
       ('Prof. Sarah Wilson', 'sarah.wilson@university.edu', 'FACULTY'),
       ('Michael Brown', 'michael.brown@visitor.com', 'GUEST');