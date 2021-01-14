/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    // int size = 0;


    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        Resume r = new Resume();
        for (int i = 0; i < size() - 1; i++) {
            if (uuid.equals(storage[i].toString())) {
                r = storage[i];
            }
        }
        return r;
    }

    void delete(String uuid) {
        for (int i = 0; i < size() - 1; i++) {
            if (uuid.contains(storage[i].toString())) {
                storage[i] = storage[size() - 1];
                storage[size() - 1] = null;

            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        int count = size();
        Resume[] resume = new Resume[count];
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                resume[i] = storage[i];
            }
        }
        return resume;
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

