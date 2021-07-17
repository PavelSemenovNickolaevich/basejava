package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.UUID;

public class ResumeTestData {

//    static String person = "Смелый, ловкий, умелый!";
//    static String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
//    static LocalDate dateBeginOne = LocalDate.of(2000, 12, 15);
//    static LocalDate dateEndOne = LocalDate.of(2021, 12, 15);
//    static LocalDate dateBeginTwo = LocalDate.of(2010, 12, 15);
//    static LocalDate dateEndTwo = LocalDate.of(2019, 10, 15);
//    static LocalDate dateBeginWork1 = LocalDate.of(1990, 10, 15);
//    static LocalDate dateEndWork1 = LocalDate.of(1999, 10, 15);
//    static LocalDate dateBeginWork2 = LocalDate.of(2000, 10, 31);
//    static LocalDate dateEndWork2 = LocalDate.of(2002, 10, 31);
//
//    public static Resume createResume(String uuid, String name) {
//
//        Resume resumeTest = new Resume(uuid, name);
//        resumeTest.setContact(ContactsType.PHONE, "1234567890");
//        resumeTest.setContact(ContactsType.MAIL, "123@gmail.com");
//        resumeTest.setContact(ContactsType.SKYPE, "123445");
//        resumeTest.setContact(ContactsType.LINKEDIN, "Linkedin");
//        resumeTest.setContact(ContactsType.GITHUB, "Github");
//        resumeTest.setContact(ContactsType.STACKOVERFLOW, "Test");
//        resumeTest.setContact(ContactsType.HOMEPAGE, "Test");
//        resumeTest.setSection(SectionType.PERSONAL, new SingleLineSection("Личные качества"));
//        resumeTest.setSection(SectionType.OBJECTIVE, new SingleLineSection("Позиция"));
//        resumeTest.setSection(SectionType.ACHIEVEMENT, new ListSection("Achievement1", "Achievement2"));
//        resumeTest.setSection(SectionType.QUALIFICATIONS, new ListSection("Qualifications1", "Qualifications2"));
//        resumeTest.setSection(SectionType.EDUCATION, new OrganizationSection(new Organization("Education1", "123@gmail.com"
//                , new Organization.Experience(dateBeginOne, dateEndOne, "Title1", "Description1")
//                , new Organization.Experience(dateBeginWork1, dateEndWork1, "Title2", "Description2"))));
//        resumeTest.setSection(SectionType.EXPERIENCE, new OrganizationSection(new Organization("Experience1", "321@mail.com"
//                , new Organization.Experience(dateBeginTwo, dateEndTwo, "Title1", "Description1")
//                , new Organization.Experience(dateBeginWork1, dateEndWork1, "Title2", "Description2"))));
//        return resumeTest;

    public static final String UUID_1 = UUID.randomUUID().toString();
    public static final String UUID_2 = UUID.randomUUID().toString();
    public static final String UUID_3 = UUID.randomUUID().toString();
    public static final String UUID_4 = UUID.randomUUID().toString();

    public static final Resume R1;
    public static final Resume R2;
    public static final Resume R3;
    public static final Resume R4;

    static {
        R1 = new Resume(UUID_1, "Name1");
        R2 = new Resume(UUID_2, "Name2");
        R3 = new Resume(UUID_3, "Name3");
        R4 = new Resume(UUID_4, "Name4");

        R1.setContact(ContactsType.MAIL, "mail1@ya.ru");
        R1.setContact(ContactsType.PHONE, "11111");

        R4.setContact(ContactsType.PHONE, "44444");
        R4.setContact(ContactsType.SKYPE, "Skype");

        R1.setSection(SectionType.OBJECTIVE, new SingleLineSection("Objective1"));
        R1.setSection(SectionType.PERSONAL, new SingleLineSection("Personal data"));
        R1.setSection(SectionType.ACHIEVEMENT, new ListSection("Achivment11", "Achivment12", "Achivment13"));
        R1.setSection(SectionType.QUALIFICATIONS, new ListSection("Java", "SQL", "JavaScript"));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization11", "http://Organization11.ru",
                                new Organization.Experience(2005, Month.JANUARY, "position1", "content1"),
                                new Organization.Experience(2001, Month.MARCH, 2005, Month.JANUARY, "position2", "content2"))));
        R1.setSection(SectionType.EXPERIENCE,
                new OrganizationSection(
                        new Organization("Organization2", "http://Organization2.ru",
                                new Organization.Experience(2015, Month.JANUARY, "position1", "content1"))));
        R1.setSection(SectionType.EDUCATION,
                new OrganizationSection(
                        new Organization("Institute", null,
                                new Organization.Experience(1996, Month.JANUARY, 2000, Month.DECEMBER, "aspirant", null),
                                new Organization.Experience(2001, Month.MARCH, 2005, Month.JANUARY, "student", "IT facultet")),
                        new Organization("Organization12", "http://Organization12.ru")));

        R2.setContact(ContactsType.SKYPE, "skype2");
        R2.setContact(ContactsType.PHONE, "22222");
    }
}



