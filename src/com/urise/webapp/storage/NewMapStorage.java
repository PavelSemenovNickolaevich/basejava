package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class NewMapStorage extends AbstractStorage {

    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getPosition(String fullName) {
        return fullName;
    }

    @Override
    protected void doSave(Resume resume, Object fullName) {
        resumeMap.put((String) fullName, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object fullName) {
        resumeMap.put((String) fullName, resume);
    }

    @Override
    protected Resume doGet(Object fullName) {
        return resumeMap.get((String) fullName);
    }

    @Override
    protected void doDelete(Object fullName) {
        resumeMap.remove((String) fullName);
    }

    @Override
    protected boolean isExist(Object fullName) {
        return resumeMap.containsKey((String) fullName);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        ArrayList<Resume> resumeList = new ArrayList<Resume>(resumeMap.values());
        Collections.sort(resumeList);
        return resumeList;
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
