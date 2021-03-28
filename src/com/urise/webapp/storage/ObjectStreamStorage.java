package com.urise.webapp.storage;

import com.urise.webapp.storage.taskWithStrategy.Strategy;

import java.io.*;

public class ObjectStreamStorage extends AbstractFileStorage {

    protected ObjectStreamStorage(File directory, Strategy strategy) {
        super(directory, strategy);
    }
}
