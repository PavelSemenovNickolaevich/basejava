/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (r != storage[i] && storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    String get(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (uuid.equals(storage[i])) {
                System.out.println(storage[i]);
                ;
            }
        }
        return "Fail";
    }

    void delete(String uuid) {
        Object m;
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < storage.length; i++) {
                if (uuid.equals(storage[i])) {
                    isSorted = false;
                    m = storage[Integer.parseInt(uuid)];
                    storage[Integer.parseInt(uuid)] = storage[Integer.parseInt(uuid + 1)];
                    storage[Integer.parseInt(uuid + 1)] = (Resume) m;
                    storage[storage.length - 1] = null;

                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int size = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                size++;
            } else {
                break;
            }
        }
        return new Resume[size];
    }

    int size() {
        int size = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                size++;
            } else {
                break;
            }
        }
        return size;
    }
}

