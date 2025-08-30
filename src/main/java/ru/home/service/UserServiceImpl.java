package ru.home.service;

import ru.home.dto.request.UserCreateRequestDto;
import ru.home.dto.response.UserDataResponseDto;
import ru.home.model.Book;
import ru.home.repository.UserRepo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService{
    private final UserRepo libraryUsers = new UserRepo();

    public UserServiceImpl() throws SQLException {
    }

    @Override
    public Long createUser(UserCreateRequestDto userCreateRequestDto) {
        return libraryUsers.createUser(userCreateRequestDto);
    }

    @Override
    public boolean removeUser(Long id) {
        return libraryUsers.removeUser(id);
    }

    @Override
    public List<Book> getUserBooksByUserId(Long id) {
        return List.of();
    }

    @Override
    public List<Book> getUserOverdueBooksByUserId(Long id) {
        return List.of();
    }

    @Override
    public Book borrowBookByName(String name) {
        return null;
    }

    @Override
    public Book borrowBookByAuthorAndName(String author, String name) {
        return null;
    }

    @Override
    public boolean returnBook(String isbn) {
        return false;
    }

    @Override
    public Map<String, List<UserDataResponseDto>> sortUsersByType() {
        return libraryUsers.sortUsersByType();
    }
}
