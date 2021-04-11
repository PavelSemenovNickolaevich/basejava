package com.urise.webapp.storage;

import com.urise.webapp.storage.serialize.JsonStreamSerializer;

public class JsonStorageTest extends AbstractStorageTest {

    public JsonStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
