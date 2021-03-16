package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<String> {
    private Map<String, Resume> resumeMap = new HashMap<>();

    @Override
    protected String getPosition(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume resume, String searchKey) {
        resumeMap.put(searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, String searchKey) {
        resumeMap.put(searchKey, resume);
    }

    @Override
    protected Resume doGet(String searchKey) {
        return resumeMap.get(searchKey);
    }

    @Override
    protected void doDelete(String searchKey) {
        resumeMap.remove(searchKey);
    }

    @Override
    public void clear() {
        resumeMap.clear();
    }

    @Override
    protected List<Resume> doCopyAll() {
        return new ArrayList<>(resumeMap.values());
    }

    @Override
    protected boolean isExist(String uuid) {
        return resumeMap.containsKey(uuid);
    }

    @Override
    public int size() {
        return resumeMap.size();
    }
}
