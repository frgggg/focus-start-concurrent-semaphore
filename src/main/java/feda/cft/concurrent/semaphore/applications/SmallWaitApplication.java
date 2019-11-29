package feda.cft.concurrent.semaphore.applications;

import feda.cft.concurrent.semaphore.applications.utils.StorageIntegerUse;
import feda.cft.concurrent.semaphore.storages.IStorage;
import feda.cft.concurrent.semaphore.storages.impl.SmallWaitStorageForInteger;

public class SmallWaitApplication {
    public static void main(String[] args)
    {
        IStorage<Integer> storage = new SmallWaitStorageForInteger();
        StorageIntegerUse storageIntegerUse = new StorageIntegerUse(storage, 800, 100, 10000);
        storageIntegerUse.use();
    }
}
