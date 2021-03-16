package com.urise.webapp.storage;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    //    public static void main(String[] args) {
//        List<String> achievement = new ArrayList<>();
//        achievement.add("Тест 11111");
//        achievement.add("Тест 22222");
//        achievement.add("Тест 33333");
//
//        List<String> qualification = new ArrayList<>();
//        qualification.add("Java");
//        qualification.add("Python");
//        qualification.add("JavaScript");
//
//        Experience firstStudy = new Experience(dateBeginOne, dateEndOne, "Test1", "Test1");
//        Experience secondStudy = new Experience(dateBeginTwo, dateEndTwo, "Test2", "Test2");
//
//        Experience firstWork = new Experience(dateBeginWork1, dateEndWork1, "Test1", "Test1");
//        Experience secondWork = new Experience(dateBeginWork2, dateEndWork2, "Test2", "Test2");
//
//        List<Experience> differentPeriodsOfStudy = new ArrayList<>();
//        differentPeriodsOfStudy.add(firstStudy);
//        differentPeriodsOfStudy.add(secondStudy);
//
//        List<Experience> differentPeriodsOfWork = new ArrayList<>();
//        differentPeriodsOfWork.add(firstWork);
//        differentPeriodsOfWork.add(secondWork);
//
//        Organization experienceOne = new Organization("M_G_U", "www.exampleOne1.ru", differentPeriodsOfStudy);
//        Organization experienceTwo = new Organization("Parlament", "www.exampleOne2.ru", differentPeriodsOfWork);
//
//        List<Organization> educationList = new ArrayList<>();
//        educationList.add(experienceOne);
//
//        List<Organization> workerList = new ArrayList<>();
//        workerList.add(experienceTwo);
//
//        OrganizationSection organizationOne = new OrganizationSection(educationList);
//        OrganizationSection organizationEduOne = new OrganizationSection(workerList);
//
//        Resume resumeTest = new Resume("1", "Ivanov Ivan");
//        System.out.println(resumeTest.getFullName());
//
//        Map<ContactsType, String> contactsData = new EnumMap<>(ContactsType.class);
//        //  contactsData.put(PHONE, "3f3ff3")
//        contactsData.put(ContactsType.PHONE, "443434334");
//        contactsData.put(ContactsType.SKYPE, "testSkype");
//        contactsData.put(ContactsType.MAIL, "123@gmail.com");
//        contactsData.put(ContactsType.LINKEDIN, "443434334");
//        contactsData.put(ContactsType.GITHUB, "TestGithub");
//        contactsData.put(ContactsType.STACKOVERFLOW, "TestOverflow");
//        contactsData.put(ContactsType.HOMEPAGE, "TestHomepage");
//        contactsData.forEach((key, value) -> System.out.println(key + " : " + value));
//
//        SingleLineSection textPersonal = new SingleLineSection(person);
//        SingleLineSection textObjective = new SingleLineSection(objective);
//        BulletedListSection listSectionAchievements = new BulletedListSection(achievement);
//        BulletedListSection listSectionQualifications = new BulletedListSection(qualification);
//
//        Map<SectionType, String> personalMap = new HashMap<>();
//        personalMap.put(SectionType.PERSONAL, String.valueOf(textPersonal));
//        personalMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
//
//        Map<SectionType, String> objectiveMap = new HashMap<>();
//        objectiveMap.put(SectionType.OBJECTIVE, String.valueOf(textObjective));
//        objectiveMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
//
//        Map<SectionType, String> achievementMap = new HashMap<>();
//        achievementMap.put(SectionType.ACHIEVEMENT, listSectionAchievements.toString());
//        achievementMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
//
//        Map<SectionType, String> qualificationMap = new HashMap<>();
//        qualificationMap.put(SectionType.QUALIFICATIONS, String.valueOf(listSectionQualifications));
//        qualificationMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
//
//        Map<SectionType, String> experienceMap = new HashMap<>();
//        experienceMap.put(SectionType.EXPERIENCE, String.valueOf(organizationOne));
//        experienceMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
//
//        Map<SectionType, String> educationMap = new HashMap<>();
//        educationMap.put(SectionType.EDUCATION, String.valueOf(organizationEduOne));
//        educationMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
    public Resume createResume(String uuid, String name) {
        SingleLineSection textPersonal = new SingleLineSection(person);
        SingleLineSection textObjective = new SingleLineSection(objective);

        List<String> achievement = new ArrayList<>();
        achievement.add("Тест 11111");
        achievement.add("Тест 22222");
        achievement.add("Тест 33333");

        List<String> qualification = new ArrayList<>();
        qualification.add("Java");
        qualification.add("Python");
        qualification.add("JavaScript");

        BulletedListSection listSectionAchievements = new BulletedListSection(achievement);
        BulletedListSection listSectionQualifications = new BulletedListSection(qualification);

        Experience firstStudy = new Experience(dateBeginOne, dateEndOne, "Test1", "Test1");
        Experience secondStudy = new Experience(dateBeginTwo, dateEndTwo, "Test2", "Test2");
        Experience firstWork = new Experience(dateBeginWork1, dateEndWork1, "Test1", "Test1");
        Experience secondWork = new Experience(dateBeginWork2, dateEndWork2, "Test2", "Test2");

        List<Experience> differentPeriodsOfStudy = new ArrayList<>();
        differentPeriodsOfStudy.add(firstStudy);
        differentPeriodsOfStudy.add(secondStudy);

        List<Experience> differentPeriodsOfWork = new ArrayList<>();
        differentPeriodsOfWork.add(firstWork);
        differentPeriodsOfStudy.add(secondWork);

        Organization organizationExEdu = new Organization("MGU", "www.test.ru", differentPeriodsOfStudy);
        Organization organizationExWork = new Organization("INTEL", "www.intel.ru", differentPeriodsOfWork);

        List<Organization> educationList = new ArrayList<>();
        educationList.add(organizationExEdu);

        List<Organization> workList = new ArrayList<>();
        educationList.add(organizationExWork);

        OrganizationSection studyOne = new OrganizationSection(educationList);
        OrganizationSection workOne = new OrganizationSection(workList);

        Resume resumeTest = new Resume(uuid, name);
        resumeTest.addContact(ContactsType.PHONE, "1234567890");
        resumeTest.addContact(ContactsType.MAIL, "123@gmail.com");
        resumeTest.addContact(ContactsType.SKYPE, "123445");
        resumeTest.addContact(ContactsType.LINKEDIN, "Linkedin");
        resumeTest.addContact(ContactsType.GITHUB, "Github");
        resumeTest.addContact(ContactsType.STACKOVERFLOW, "Test");
        resumeTest.addContact(ContactsType.HOMEPAGE, "Test");
        resumeTest.addSection(SectionType.PERSONAL, textPersonal);
        resumeTest.addSection(SectionType.OBJECTIVE, textObjective);
        resumeTest.addSection(SectionType.ACHIEVEMENT, listSectionAchievements);
        resumeTest.addSection(SectionType.QUALIFICATIONS, listSectionQualifications);
        resumeTest.addSection(SectionType.EXPERIENCE, studyOne);
        resumeTest.addSection(SectionType.EXPERIENCE, workOne);
        return resumeTest;
    }


}


