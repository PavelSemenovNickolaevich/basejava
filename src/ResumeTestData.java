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
    static LocalDate dateBeginWork1 = LocalDate.of(1990, 10, 31);
    static LocalDate dateEndWork1 = LocalDate.of(1999, 10, 31);
    static LocalDate dateBeginWork2 = LocalDate.of(2000, 10, 31);
    static LocalDate dateEndWork2 = LocalDate.of(2002, 10, 31);

    public static void main(String[] args) {
        List<String> achievement = new ArrayList<>();
        achievement.add("Тест 11111");
        achievement.add("Тест 22222");
        achievement.add("Тест 33333");

        List<String> qualification = new ArrayList<>();
        qualification.add("Java");
        qualification.add("Python");
        qualification.add("JavaScript");

        ListOfExperience firstStudy = new ListOfExperience(dateBeginOne, dateEndOne, "Test1", "Test1");
        ListOfExperience secondStudy = new ListOfExperience(dateBeginTwo, dateEndTwo, "Test2", "Test2");

        ListOfExperience firstWork = new ListOfExperience(dateBeginWork1, dateEndWork1, "Test1", "Test1");
        ListOfExperience secondWork = new ListOfExperience(dateBeginWork2, dateEndWork2, "Test2", "Test2");

        List<ListOfExperience> differentPeriodsOfStudy = new ArrayList<>();
        differentPeriodsOfStudy.add(firstStudy);
        differentPeriodsOfStudy.add(secondStudy);

        List<ListOfExperience> differentPeriodsOfWork = new ArrayList<>();
        differentPeriodsOfWork.add(firstWork);
        differentPeriodsOfWork.add(secondWork);

        Experience experienceOne = new Experience("M_G_U", differentPeriodsOfStudy);
        Experience experienceTwo = new Experience("Parlament", differentPeriodsOfWork);

        List<Experience> educationList = new ArrayList<>();
        educationList.add(experienceOne);

        List<Experience> workerList = new ArrayList<>();
        workerList.add(experienceTwo);

        Organization organizationOne = new Organization(educationList);
        Organization organizationEduOne = new Organization(workerList);

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
        achievementMap.put(SectionType.ACHIEVEMENT, listSectionAchievements.toString());
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
