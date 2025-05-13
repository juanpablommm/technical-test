package com.online.educamas.technical_test.users.infrastructure;

public enum GenderEnum {
    FEMININE("FEMININE"),
    MALE("MALE");

    private final String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
