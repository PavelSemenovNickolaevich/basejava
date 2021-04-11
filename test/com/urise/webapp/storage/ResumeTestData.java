package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;

public class ResumeTestData {

    static String person = "Смелый, ловкий, умелый!";
    static String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
    static LocalDate dateBeginOne = LocalDate.of(2000, 12, 31);
    static LocalDate dateEndOne = LocalDate.of(2021, 12, 31);
    static LocalDate dateBeginTwo = LocalDate.of(2010, 12, 31);
    static LocalDate dateEndTwo = LocalDate.of(2019, 10, 31);
    static LocalDate dateBeginWork1 = LocalDate.of(1990, 10, 31);
    static LocalDate dateEndWork1 = LocalDate.of(1999, 10, 31);
    static LocalDate dateBeginWork2 = LocalDate.of(2000, 10, 31);
    static LocalDate dateEndWork2 = LocalDate.of(2002, 10, 31);

    public static Resume createResume(String uuid, String name) {

        Resume resumeTest = new Resume(uuid, name);
        resumeTest.addContact(ContactsType.PHONE, "1234567890");
        resumeTest.addContact(ContactsType.MAIL, "123@gmail.com");
        resumeTest.addContact(ContactsType.SKYPE, "123445");
        resumeTest.addContact(ContactsType.LINKEDIN, "Linkedin");
        resumeTest.addContact(ContactsType.GITHUB, "Github");
        resumeTest.addContact(ContactsType.STACKOVERFLOW, "Test");
        resumeTest.addContact(ContactsType.HOMEPAGE, "Test");
        resumeTest.addSection(SectionType.PERSONAL, new SingleLineSection("Test"));
        resumeTest.addSection(SectionType.OBJECTIVE, new SingleLineSection("Test2"));
        resumeTest.addSection(SectionType.ACHIEVEMENT, new ListSection("333432", "332432432"));
        resumeTest.addSection(SectionType.QUALIFICATIONS, new ListSection("Test", "Test2"));
        resumeTest.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Test", "123"
                , new Organization.Experience(dateBeginOne, dateEndOne, "Title", "Description")
                , new Organization.Experience(dateBeginWork1, dateEndWork1, "Test", "Test"))));
        resumeTest.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Test", "123"
                , new Organization.Experience(dateBeginTwo, dateEndTwo, "Title1", "Description1")
                , new Organization.Experience(dateBeginWork1, dateEndWork1, "Test", "Test"))));
        return resumeTest;
    }
}


