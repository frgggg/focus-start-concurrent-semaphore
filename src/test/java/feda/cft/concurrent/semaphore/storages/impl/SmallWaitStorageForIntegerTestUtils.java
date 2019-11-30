package feda.cft.concurrent.semaphore.storages.impl;

public class SmallWaitStorageForIntegerTestUtils {
    public static final int SINGLE_ITEM_STORAGE_SIZE = 1;

    public static final Integer PUTTED_ITEM_ONE_IN_EMPTY_SINGLE_ITEM_STORAGE = 1;
    public static final Integer PUTTED_ITEM_TWO_IN_FULL_SINGLE_ITEM_STORAGE = 2;

    public static final Integer GOTTEN_ITEM_FROM_SINGLE_ITEM_FULL_STORAGE = PUTTED_ITEM_ONE_IN_EMPTY_SINGLE_ITEM_STORAGE;
    public static final Integer GOTTEN_NULL_FROM_SINGLE_ITEM_EMPTY_STORAGE = null;
}
