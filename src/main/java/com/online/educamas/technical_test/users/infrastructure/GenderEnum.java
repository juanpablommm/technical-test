package com.online.educamas.technical_test.users.infrastructure;

public enum GenderEnum {
    FEMININE("F"),
    MALE("M");

    private final String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
