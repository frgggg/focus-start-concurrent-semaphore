package feda.cft.concurrent.semaphore.storages.impl;

public class WaitStorageForIntegerTestUtils {
    public static final int TEST_SLEEP_TIME = 10000;

    public static final Integer ITEM_IN_FULL_SINGLE_ITEM_STORAGE = 1;
    public static final Integer ITEM_FOR_PUT_IN_FULL_STORAGE = 2;
    public static final Thread.State THREAD_STATE_AFTER_PUT_IN_FULL_STORAGE = Thread.State.TIMED_WAITING;
    public static final Thread.State THREAD_STATE_AFTER_PUT_AND_GET_FROM_FULL_STORAGE = Thread.State.TERMINATED;
    public static WaitStorageForInteger GET_FULL_SINGLE_ITEM_STORAGE() throws InterruptedException
    {
        WaitStorageForInteger storage = new WaitStorageForInteger(1);
        storage.putItem(ITEM_IN_FULL_SINGLE_ITEM_STORAGE);
        return storage;
    }

    public static final Thread.State THREAD_STATE_AFTER_GET_FROM_EMPTY_STORAGE = Thread.State.TIMED_WAITING;
    public static Integer ITEM_FOR_PUT_IN_EMPTY_SINGLE_ITEM_STORAGE = 3;
    public static WaitStorageForInteger GET_EMPTY_SINGLE_ITEM_STORAGE() throws InterruptedException
    {
        WaitStorageForInteger storage = new WaitStorageForInteger(1);
        return storage;
    }
}
