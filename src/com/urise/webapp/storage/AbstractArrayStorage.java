package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
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

    protected abstract int getPosition(String uuid);
}
