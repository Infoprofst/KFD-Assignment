package ru.home.model;

public class Faculty extends User{
    private final int MAX_AMOUNT_BOOKS = 10;
    private final int MAX_BORROW_DAYS = 30;

    public Faculty(String name, String email) {
        super(name, email);
    }

    public Faculty(Long userId, String name, String email) {
        super(userId, name, email);
    }

    @Override
    public int getMaxBooks() {
        return MAX_AMOUNT_BOOKS;
    }

    @Override
    public int getBorrowDays() {
        return MAX_BORROW_DAYS;
    }
}
