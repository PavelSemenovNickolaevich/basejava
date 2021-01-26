package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index > 0) {
            System.out.println("Resume is exist: " + resume.getUuid() + " !");
        } else if (STORAGE_LIMIT == size) {
            System.out.println("Storage is full!");
        } else {
            addResume(resume, index);
        }
    }

    public void update(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index < 0) {
            System.out.println("Resume doesnt exist for method update: " + resume.getUuid() + " !");
        } else {
            storage[index] = resume;
        }

    }

    public void delete(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            System.out.println("Resume doesnt exist for method delete: " + uuid + " !");
        } else {
            deleteResume(index);
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


    public Resume get(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            System.out.println("Resume doesnt exist for method get: " + uuid + " !");
            return null;
        } else {
            return storage[index];
        }
    }

    protected abstract int getPosition(String uuid);

    protected abstract void addResume(Resume resume, int index);

    protected abstract void deleteResume(int index);

}
