package ru.home.repository;

import ru.home.dto.request.BookAddRequestDto;
import ru.home.dto.response.BookAuthorResponseDto;
import ru.home.dto.response.BookDataResponseDto;
import ru.home.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookRepo {
    private final String BOOK_TABLE = "books";
    private final Connection connection;
    private final int INVALID_ID = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;

    public BookRepo() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }

    public void addBook(BookAddRequestDto bookAddRequestDto) {
        String queryAddBook = "INSERT INTO %s (isbn, title, author) VALUES (?, ?, ?);".formatted(BOOK_TABLE);
        try (PreparedStatement statement = connection.prepareStatement(queryAddBook)) {
            statement.setString(FIRST_INDEX, bookAddRequestDto.isbn());
            statement.setString(SECOND_INDEX, bookAddRequestDto.title());
            statement.setString(THIRD_INDEX, bookAddRequestDto.author());
            statement.executeUpdate();
            System.out.println("Книга успешно добавлена в библиотеку");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookDataResponseDto> getAllBooks() {
        List<BookDataResponseDto> libraryBooks = new ArrayList<>();
        String queryAllBooks = "SELECT author,name FROM %s".formatted(BOOK_TABLE);
        try (PreparedStatement statement = connection.prepareStatement(queryAllBooks)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                libraryBooks.add(new BookDataResponseDto(
                        resultSet.getString("author"),
                        resultSet.getString("title")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return libraryBooks;
    }

    public Set<BookAuthorResponseDto> listBooksByAuthor(String author) {
        Set<BookAuthorResponseDto> authorsList = new HashSet<>();
        String queryBooksByAuthor = "SELECT author FROM %s WHERE author = ?".formatted(BOOK_TABLE);
        try (PreparedStatement statement = connection.prepareStatement(queryBooksByAuthor)) {
            statement.setString(FIRST_INDEX, author);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                authorsList.add(new BookAuthorResponseDto(resultSet.getString("author")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public boolean removeBook(String isbn) {
        String queryRemoveBook = "DELETE FROM %s WHERE isbn = ?".formatted(BOOK_TABLE);
        try (PreparedStatement statement = connection.prepareStatement(queryRemoveBook)) {
            statement.setString(FIRST_INDEX, isbn);
            return statement.executeUpdate() != INVALID_ID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
