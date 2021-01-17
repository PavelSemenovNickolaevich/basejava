package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    public void save(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(r.getUuid())) {
                System.out.println("Resume is exist " + r.getUuid());
                size--;
            }
        }
        if (storage.length == size) {
            System.out.println("Storage is full");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        //TODO check if resume present
        int position = 0;
        for (int i = 0; i < size; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                position = i;
            }
        }
        if (!r.getUuid().equals(storage[position].getUuid())) {
            System.out.println("Resume doesnt exist for method update " + r.getUuid());
        } else {
            storage[position] = r;
        }

    }

    public Resume get(String uuid) {
        int position = 0;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                position = i;
            }
        }
        if (uuid.equals(storage[position].getUuid())) {
            return storage[position];
        } else if (!uuid.equals(storage[position].getUuid())) {
            System.out.println("Resume doesnt exist for method get! " + uuid);
        }
        return null;
    }

    public void delete(String uuid) {
        int position = 0;
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                position = i;
            }
        }
        if (!uuid.equals(storage[position].getUuid())) {
            System.out.println("Resume doesnt exist for method delete! " + uuid);
        } else if (uuid.equals(storage[position].getUuid())) {
            storage[position] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];
        for (int i = 0; i < size; i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    public int size() {
        return size;
    }

}

