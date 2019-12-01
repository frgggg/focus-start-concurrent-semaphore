package feda.cft.concurrent.semaphore.storages.impl;

public class WaitStorageForIntegerTestUtils {
    public static final int WAIT_TESTS_SLEEP_TIME = 10000;

    public static final Integer ITEM_IN_FULL_SINGLE_ITEM_STORAGE = 1;
    public static WaitStorageForInteger GET_FULL_SINGLE_ITEM_STORAGE() throws InterruptedException
    {
        WaitStorageForInteger storage = new WaitStorageForInteger(1);
        storage.putItem(ITEM_IN_FULL_SINGLE_ITEM_STORAGE);
        return storage;
    }

    public static WaitStorageForInteger GET_EMPTY_SINGLE_ITEM_STORAGE() throws InterruptedException
    {
        WaitStorageForInteger storage = new WaitStorageForInteger(1);
        return storage;
    }
}
