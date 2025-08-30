package ru.home.repository;

import ru.home.dto.request.UserCreateRequestDto;
import ru.home.dto.response.UserDataResponseDto;
import ru.home.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    private final String USER_TABLE = "users";
    private final Connection connection;
    private final int INVALID_ID = 0;
    private static final int FIRST_INDEX = 1;
    private static final int SECOND_INDEX = 2;
    private static final int THIRD_INDEX = 3;

    public UserRepo() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public Long createUser(UserCreateRequestDto userCreateRequestDto) {
        String queryCreateUser = "INSERT INTO %s (name, email, user_type) VALUES (?, ?, ?);".formatted(USER_TABLE);
        try (PreparedStatement statement = connection.prepareStatement(queryCreateUser, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(FIRST_INDEX, userCreateRequestDto.name());
            statement.setString(SECOND_INDEX, userCreateRequestDto.email());
            statement.setObject(THIRD_INDEX, userCreateRequestDto.type().toString(), Types.OTHER);
            statement.executeUpdate();

            ResultSet userId = statement.getGeneratedKeys();
            if (userId.next()) return userId.getLong("id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0L;
    }

    public boolean removeUser(Long id) {
        String queryDeleteUser = "DELETE FROM %s WHERE id = ?;".formatted(USER_TABLE);
        try (PreparedStatement statement = connection.prepareStatement(queryDeleteUser)) {
            statement.setLong(FIRST_INDEX, id);
            return statement.executeUpdate() != INVALID_ID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, List<UserDataResponseDto>> sortUsersByType() {
        String querySortUsers = "SELECT name, email, user_type FROM %s".formatted(USER_TABLE);
        Map<String, List<UserDataResponseDto>> userMap = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement(querySortUsers)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                if (!userMap.containsKey(resultSet.getString("user_type"))) {
                    List<UserDataResponseDto> userList = new ArrayList<>();
                    userList.add(builder(resultSet));
                    userMap.put(resultSet.getString("user_type"), userList);
                } else {
                    userMap.get(resultSet.getString("user_type")).add(builder(resultSet));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userMap;
    }

    private UserDataResponseDto builder(ResultSet resultSet) throws SQLException {
        return new UserDataResponseDto(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("user_type"));
    }

}
