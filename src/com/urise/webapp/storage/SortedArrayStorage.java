package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getPosition(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void addResume(Resume resume, int index) {
        // http://codereview.stackexchange.com/questions/36221/binary-search-for-inserting-in-array#answer-36239
        int indexNew = -index - 1;
        System.arraycopy(storage, indexNew, storage, indexNew + 1, size - indexNew);
        storage[indexNew] = resume;
    }

    @Override
    protected void deleteResume(int index) {
        int length = size - index + 1;
        System.arraycopy(storage, index + 1, storage, index, length);
    }
}
