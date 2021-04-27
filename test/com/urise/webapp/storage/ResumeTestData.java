package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;

public class ResumeTestData {

    static String person = "Смелый, ловкий, умелый!";
    static String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
    static LocalDate dateBeginOne = LocalDate.of(2000, 12, 15);
    static LocalDate dateEndOne = LocalDate.of(2021, 12, 15);
    static LocalDate dateBeginTwo = LocalDate.of(2010, 12, 15);
    static LocalDate dateEndTwo = LocalDate.of(2019, 10, 15);
    static LocalDate dateBeginWork1 = LocalDate.of(1990, 10, 15);
    static LocalDate dateEndWork1 = LocalDate.of(1999, 10, 15);
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
        resumeTest.addSection(SectionType.PERSONAL, new SingleLineSection("Личные качества"));
        resumeTest.addSection(SectionType.OBJECTIVE, new SingleLineSection("Позиция"));
        resumeTest.addSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2"));
        resumeTest.addSection(SectionType.QUALIFICATIONS, new ListSection("Qualifications1", "Qualifications2"));
        resumeTest.addSection(SectionType.EDUCATION, new OrganizationSection(new Organization("Education1", "123@gmail.com"
                , new Organization.Experience(dateBeginOne, dateEndOne, "Title1", "Description1")
                , new Organization.Experience(dateBeginWork1, dateEndWork1, "Title2", "Description2"))));
        resumeTest.addSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Experience1", "321@mail.com"
                , new Organization.Experience(dateBeginTwo, dateEndTwo, "Title1", "Description1")
                , new Organization.Experience(dateBeginWork1, dateEndWork1, "Title2", "Description2"))));
        return resumeTest;

    }
}



