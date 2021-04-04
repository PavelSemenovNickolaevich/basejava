package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.StrategyPattern;

public class ObjectStreamPathStorageTest extends AbstractStorageTest {

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_DIR.getAbsolutePath(), new StrategyPattern()));
    }
}
