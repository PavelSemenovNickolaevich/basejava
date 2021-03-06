package com.urise.webapp.model;

public enum ContactsType {
    PHONE("Моб.телефон"),
    SKYPE("Скайп"),
    MAIL("Почта"),
    LINKEDIN("LinkedIn"),
    GITHUB("Github"),
    STACKOVERFLOW("StackOverflow"),
    HOMEPAGE("Домашняя страница");

    private String contact;

    ContactsType(String contact) {
        this.contact = contact;
    }

    public String getTitle() {
        return contact;
    }
}
