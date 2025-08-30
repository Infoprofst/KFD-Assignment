package ru.home.service;

import ru.home.dto.request.BookAddRequestDto;
import ru.home.dto.response.BookAuthorResponseDto;
import ru.home.dto.response.BookDataResponseDto;
import ru.home.repository.BookRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class BookServiceImpl implements BookService {
    private final BookRepo library = new BookRepo();

    public BookServiceImpl() throws SQLException {
    }

    @Override
    public void addBook(BookAddRequestDto bookAddRequestDto) {
        library.addBook(bookAddRequestDto);
    }

    @Override
    public List<BookDataResponseDto> getAllBooks() {
        return library.getAllBooks();
    }

    @Override
    public Set<BookAuthorResponseDto> sortBooksByAuthor() {
        return library.sortBooksByAuthor();
    }

    @Override
    public boolean removeBook(String isbn) {
        return library.removeBook(isbn);
    }
}
