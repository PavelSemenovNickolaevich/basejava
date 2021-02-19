package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected Object getPosition(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        resumeMap.put((String) searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        resumeMap.put((String) searchKey, resume);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return resumeMap.get((String) searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        resumeMap.remove((String) searchKey);
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
    protected boolean isExist(Object searchKey) {
        return resumeMap.containsKey((String) searchKey);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
