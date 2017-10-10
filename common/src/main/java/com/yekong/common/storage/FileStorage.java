package com.yekong.common.storage;

/**
 * 文件存储
 */
public class FileStorage {

    private static volatile FileStorage singleton;

    private FileStorage() {
    }

    public static FileStorage getInstance() {
        if (singleton == null) {
            synchronized (FileStorage.class) {
                if (singleton == null) {
                    singleton = new FileStorage();
                }
            }
        }
        return singleton;
    }
}