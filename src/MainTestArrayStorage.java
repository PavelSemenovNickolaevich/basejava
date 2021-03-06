import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ListStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    // private static final Storage ARRAY_STORAGE = new ArrayStorage();
    //private static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    private static final Storage ARRAY_STORAGE = new ListStorage();


    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1");
        //  r1.setUuid("uuid1");
        final Resume r2 = new Resume("uuid2");
        //     r2.setUuid("uuid2");
        final Resume r3 = new Resume("uuid3");
        //      r3.setUuid("uuid3");
        final Resume r4 = new Resume("uuid4");
        //      r4.setUuid("uuid4");
        final Resume r5 = new Resume("uuid5");
        //     r5.setUuid("uuid5");
        final Resume r6 = new Resume("uuid6");
        //      r6.setUuid("uuid6");
        final Resume r7 = new Resume("uuid7");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r4);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r6);

//        ARRAY_STORAGE.save(r4);
        // r1.setUuid("Test");
        // ARRAY_STORAGE.update(r1);


        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        System.out.println("Get r6: " + ARRAY_STORAGE.get(r6.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        //  System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        // System.out.println("Get index: " + Arrays.binarySearch(ARRAY_STORAGE.storage, 0, ARRAY_STORAGE.size(), r2));

        printAll();
        ARRAY_STORAGE.delete(r6.getUuid());
        ARRAY_STORAGE.delete(r3.getUuid());
        ARRAY_STORAGE.delete(r1.getUuid());
        ARRAY_STORAGE.delete(r4.getUuid());

        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Object r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}
