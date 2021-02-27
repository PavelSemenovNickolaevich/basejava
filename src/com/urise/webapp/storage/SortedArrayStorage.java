package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {

    //    private static class ResumeComparator implements Comparator<Resume> {
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    }

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

    @Override
    public Integer getPosition(String uuid) {
        Resume searchKey = new Resume(uuid, "ResumeWithFullName");
        return Arrays.binarySearch(storage, 0, size, searchKey, RESUME_COMPARATOR);
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
