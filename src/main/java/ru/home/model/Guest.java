package ru.home.model;

public class Guest extends User{
    private final int MAX_AMOUNT_BOOKS = 1;
    private final int MAX_BORROW_DAYS = 7;

    public Guest(String name, String email) {
        super(name, email);
    }

    public Guest(Long userId, String name, String email) {
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
