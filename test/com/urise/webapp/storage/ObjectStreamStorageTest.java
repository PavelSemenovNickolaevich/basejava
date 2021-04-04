package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.StrategyPattern;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new ObjectStreamFileStorage(STORAGE_DIR, new StrategyPattern()));
    }
}
