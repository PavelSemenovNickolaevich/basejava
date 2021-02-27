package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {
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
    protected List<Resume> doCopyAll() {
        return new ArrayList<Resume>(resumeMap.values());
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
