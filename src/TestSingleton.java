import com.urise.webapp.model.SectionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSingleton {
    // private static TestSingleton instance = new TestSingleton();
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }

    private TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton instance = Singleton.valueOf("INSTANCE");
        System.out.println(instance.name());
        System.out.println(instance.ordinal());
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }

        List<String> array = new ArrayList<>();
        Collections.addAll(array, "Test", "Test2", "Test3");
        array.forEach((s) -> System.out.println(s));

    }

    public enum Singleton {
        INSTANCE
    }

}
