package ru.otus.spboot.domain;

public final class Student {
    private final String name;
    private final String sureName;

    public Student(String name, String sureName) {
        this.name = name;
        this.sureName = sureName;
    }

    public String getFullName() {
        return name + " " + sureName;
    }
}
