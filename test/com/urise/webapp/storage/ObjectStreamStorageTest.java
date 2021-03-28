package com.urise.webapp.storage;

import com.urise.webapp.storage.taskWithStrategy.Strategy;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIR, new Strategy()));
    }
}
