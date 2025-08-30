package ru.home.service;

import ru.home.dto.response.UserDataResponseDto;
import ru.home.model.Book;
import ru.home.dto.request.UserCreateRequestDto;

import java.util.List;
import java.util.Map;

public interface UserService {
    Long createUser(UserCreateRequestDto userCreateRequestDto);

    boolean removeUser(Long id);

    List<Book> getUserBooksByUserId(Long id);

    List<Book> getUserOverdueBooksByUserId(Long id);

    Book borrowBookByName(String name);

    Book borrowBookByAuthorAndName(String author, String name);

    boolean returnBook(String isbn);

    Map<String, List<UserDataResponseDto>> sortUsersByType();
}
