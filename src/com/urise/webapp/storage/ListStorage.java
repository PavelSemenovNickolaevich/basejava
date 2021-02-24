package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> resumeList = new ArrayList<>();

    @Override
    public void clear() {
        resumeList.clear();
    }

    @Override
    public void doSave(Resume resume, Object index) {
        resumeList.add(resume);
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        resumeList.set((Integer) index, resume);
    }

    @Override
    public Resume doGet(Object index) {
        return resumeList.get((Integer) index);
    }

    @Override
    public void doDelete(Object searchKey) {
        resumeList.remove((int) searchKey);
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }

//    @Override
//    public List<Resume> getAllSorted() {
//
//        return resumeList;
//    }
//

    @Override
    public List<Resume> createListResume() {
        return resumeList;
    }



    @Override
    public int size() {
        return resumeList.size();
    }

    @Override
    public Object getPosition(String uuid) {
        for (int i = 0; i < resumeList.size(); i++) {
            if (resumeList.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
