package com.urise.webapp.storage;

import com.urise.webapp.storage.taskWithStrategy.Strategy;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    protected ObjectStreamPathStorage(String dir, Strategy strategy) {
        super(dir, strategy);
    }

}
