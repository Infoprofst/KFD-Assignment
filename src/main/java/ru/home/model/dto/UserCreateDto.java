package ru.home.model.dto;

import ru.home.model.UserType;

public record UserCreateDto (String name, String email, UserType type) {
}
