package ru.home.repository;

import ru.home.model.dto.UserCreateDto;
import ru.home.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepo {
    private final Connection connection;
    private final String USER_TABLE = "users";
    private final int INVALID_ID = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;

    public UserRepo() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public Long createUser(UserCreateDto userCreateDto){
        String queryCreateUser = "INSERT INTO %s (name, email, user_type) VALUES (?, ?, ?)".formatted(USER_TABLE);
        try(PreparedStatement statement = connection.prepareStatement(queryCreateUser, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(FIRST_INDEX, userCreateDto.name());
            statement.setString(SECOND_INDEX, userCreateDto.email());
            statement.setString(THIRD_INDEX, userCreateDto.type().toString());
            statement.executeUpdate();

            ResultSet userId = statement.getGeneratedKeys();
            if(userId.next()) return userId.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }

    public boolean removeUser(Long id) {
        String queryDeleteUser = "DELETE FROM %s WHERE id = ?".formatted(USER_TABLE);
        try(PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(FIRST_INDEX, id);
            return statement.executeUpdate() != INVALID_ID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
