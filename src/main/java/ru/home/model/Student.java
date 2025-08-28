package ru.home.model;

public class Student extends User{
    private final int MAX_AMOUNT_BOOKS = 3;
    private final int MAX_BORROW_DAYS = 14;

    public Student(String name, String email) {
        super(name, email);
    }

    public Student(Long userId, String name, String email) {
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
