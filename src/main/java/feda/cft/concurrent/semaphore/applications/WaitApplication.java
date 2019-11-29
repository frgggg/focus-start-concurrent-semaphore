package feda.cft.concurrent.semaphore.applications;

import feda.cft.concurrent.semaphore.applications.utils.StorageIntegerUse;
import feda.cft.concurrent.semaphore.storages.IStorage;
import feda.cft.concurrent.semaphore.storages.impl.WaitStorageForInteger;

public class WaitApplication {
    public static void main(String[] args)
    {
        IStorage<Integer> storage = new WaitStorageForInteger();
        StorageIntegerUse storageIntegerUse = new StorageIntegerUse(storage, 800, 100, 10000);
        storageIntegerUse.use();
    }
}
