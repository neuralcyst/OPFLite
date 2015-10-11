package ru.ncedu.opflite.util;

import ru.ncedu.opflite.domain.storage.DataStorageGateway;
import ru.ncedu.opflite.domain.storage.DataStorageInMemoryImpl;

public final class Context {

    private Context() {
        // class instance doesn't make sense
    }

    public static DataStorageGateway getDataStorageGateway() {
        return DataStorageInMemoryImpl.getInstance();
    }

}
