package com.urise.webapp.storage;

import com.urise.webapp.storage.taskWithStrategy.Strategy;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.getAbsolutePath(), new Strategy()));
    }
}
