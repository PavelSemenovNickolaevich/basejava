package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void save(Resume resume) {
        int index = getPosition(resume.getUuid());
        if (index >= 0) {
            System.out.println("Resume " + resume.getUuid() + " is exist!");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Storage is full!");
        } else {
            int indexNew = -index - 1;
            //   System.arraycopy(storage, indexNew, storage, indexNew + 1, size++);
            storage[indexNew] = resume;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getPosition(uuid);
        if (index < 0) {
            System.out.println("Resume doesnt exist for method delete: " + uuid + " !");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size--);
        }
    }


    @Override
    protected int getPosition(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
