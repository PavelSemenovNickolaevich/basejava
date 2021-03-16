package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getPosition(String uuid);

    protected abstract void doSave(Resume resume, SK searchKey);

    protected abstract void doUpdate(Resume resume, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Resume> doCopyAll();


    public void save(Resume resume) {
        LOG.info("Save " + resume);
        SK searchKey = getNotExistSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        SK searchKey = getExistSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        return doGet(searchKey);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistSearchKey(uuid);
        doDelete(searchKey);
    }

    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> list = doCopyAll();
        Collections.sort(list);
        return list;
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getPosition(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + "already exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getPosition(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}



