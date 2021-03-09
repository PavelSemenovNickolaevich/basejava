import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {

    static String person = "Смелый, ловкий, умелый!";
    static String objective = "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям";
    static LocalDate dateBeginOne = LocalDate.of(2000, 12, 31);
    static LocalDate dateEndOne = LocalDate.of(2021, 12, 31);
    static LocalDate dateBeginTwo = LocalDate.of(2010, 12, 31);
    static LocalDate dateEndTwo = LocalDate.of(2019, 10, 31);
    static LocalDate dateBeginEducation1 = LocalDate.of(1990, 10, 31);
    static LocalDate dateEndEducation1 = LocalDate.of(1999, 10, 31);
    static LocalDate dateBeginEducation2 = LocalDate.of(2000, 10, 31);
    static LocalDate dateEndEducation2 = LocalDate.of(2002, 10, 31);

    public static void main(String[] args) {

        List<String> achievement = new ArrayList<>();
        achievement.add("Тест 11111");
        achievement.add("Тест 22222");
        achievement.add("Тест 33333");

        List<String> qualification = new ArrayList<>();
        qualification.add("Java");
        qualification.add("Python");
        qualification.add("JavaScript");

        Experience experienceOne = new Experience("Gazprom", dateBeginOne
                , dateEndOne, "Test", "Test");

        Experience experienceTwo = new Experience("VTB", dateBeginTwo
                , dateEndTwo, "Test2", "Test2");

        Experience educationOne = new Experience("MGU", dateBeginEducation1
                , dateEndEducation1, "Test", "Test");

        Experience educationTwo = new Experience("UDGU", dateBeginEducation2
                , dateEndEducation2, "Test2", "Test2");

        List<Experience> experienceList = new ArrayList<>();
        experienceList.add(experienceOne);
        experienceList.add(experienceTwo);

        List<Experience> educationList = new ArrayList<>();
        educationList.add(educationOne);
        educationList.add(educationTwo);

        Organization organizationOne = new Organization(experienceList);
        Organization organizationEduOne = new Organization(educationList);

        Resume resumeTest = new Resume("1", "Ivanov Ivan");
        System.out.println(resumeTest.getFullName());

        Map<ContactsType, String> contactsData = new EnumMap<>(ContactsType.class);
      //  contactsData.put(PHONE, "3f3ff3")
        contactsData.put(ContactsType.PHONE, "443434334");
        contactsData.put(ContactsType.SKYPE, "testSkype");
        contactsData.put(ContactsType.MAIL, "123@gmail.com");
        contactsData.put(ContactsType.LINKEDIN, "443434334");
        contactsData.put(ContactsType.GITHUB, "TestGithub");
        contactsData.put(ContactsType.STACKOVERFLOW, "TestOverflow");
        contactsData.put(ContactsType.HOMEPAGE, "TestHomepage");
        contactsData.forEach((key, value) -> System.out.println(key + " : " + value));

        SingleLineSection textPersonal = new SingleLineSection(person);
        SingleLineSection textObjective = new SingleLineSection(objective);
        BulletedListSection listSectionAchievements = new BulletedListSection(achievement);
        BulletedListSection listSectionQualifications = new BulletedListSection(qualification);

        Map<SectionType, String> personalMap = new HashMap<>();
        personalMap.put(SectionType.PERSONAL, String.valueOf(textPersonal));
        personalMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));

        Map<SectionType, String> objectiveMap = new HashMap<>();
        objectiveMap.put(SectionType.OBJECTIVE, String.valueOf(textObjective));
        objectiveMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));

        Map<SectionType, String> achievementMap = new HashMap<>();
        achievementMap.put(SectionType.ACHIEVEMENT,listSectionAchievements.toString());
        achievementMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));

        Map<SectionType, String> qualificationMap = new HashMap<>();
        qualificationMap.put(SectionType.QUALIFICATIONS, String.valueOf(listSectionQualifications));
        qualificationMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));

        Map<SectionType, String> experienceMap = new HashMap<>();
        experienceMap.put(SectionType.EXPERIENCE, String.valueOf(organizationOne));
        experienceMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));

        Map<SectionType, String> educationMap = new HashMap<>();
        educationMap.put(SectionType.EDUCATION, String.valueOf(organizationEduOne));
        educationMap.forEach((key, value) -> System.out.println(key + ": " + "\n" + value));
    }
}
