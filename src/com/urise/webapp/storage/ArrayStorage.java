package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
    }

    public void save(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index != -1) {
            System.out.println("Resume is exist: " + resume.getUuid() + " !");
        } else if (storage.length == size) {
            System.out.println("Storage is full!");
        } else {
            storage[size] = resume;
            size++;
        }
    }

    public void update(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume doesnt exist for method update: " + resume.getUuid() + " !");
        } else {
            storage[index] = resume;
        }

    }

    public Resume get(String uuid) {
        int index = getPosition(uuid);
        if (index == -1) {
            System.out.println("Resume doesnt exist for method get: " + uuid + " !");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) {
        int index = getPosition(uuid);
        if (index == -1) {
            System.out.println("Resume doesnt exist for method delete: " + uuid + " !");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resume = new Resume[size];
        if (size >= 0) System.arraycopy(storage, 0, resume, 0, size);
        return resume;
    }

    public int size() {
        return size;
    }

    private int getPosition(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}

