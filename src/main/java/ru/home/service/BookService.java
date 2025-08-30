package ru.home.service;

import ru.home.dto.request.BookAddRequestDto;
import ru.home.dto.response.BookAuthorResponseDto;
import ru.home.dto.response.BookDataResponseDto;

import java.util.List;
import java.util.Set;

public interface BookService {
    void addBook(BookAddRequestDto bookAddRequestDto);

    List<BookDataResponseDto> getAllBooks();

    Set<BookAuthorResponseDto> sortBooksByAuthor();

    boolean removeBook(String isbn);
}
