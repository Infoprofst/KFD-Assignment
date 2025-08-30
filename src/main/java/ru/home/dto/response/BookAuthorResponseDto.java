package ru.home.dto.response;

public record BookAuthorResponseDto(String author) {
    @Override
    public String toString() {
        return author;
    }
}
