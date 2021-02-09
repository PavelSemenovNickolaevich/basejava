package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {

    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Object[] getAll();


    int size();


}
