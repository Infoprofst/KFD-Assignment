package ru.home.service;

import ru.home.model.Book;
import ru.home.dto.request.UserCreateRequestDto;

import java.util.List;

public interface UserService {
    Long createUser(UserCreateRequestDto userCreateRequestDto);

    boolean removeUser(Long id);

    List<Book> getUserBooksByUserId(Long id);

    List<Book> getUserOverdueBooksByUserId(Long id);

    Book borrowBookByName(String name);

    Book borrowBookByAuthorAndName(String author, String name);

    boolean returnBook(String isbn);
}
