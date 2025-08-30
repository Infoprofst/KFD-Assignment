package ru.home.dto.response;

import ru.home.model.UserType;

public record UserDataResponseDto(Long id, String name, String email, String type) {
}
