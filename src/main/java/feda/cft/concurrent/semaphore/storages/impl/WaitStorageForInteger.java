package feda.cft.concurrent.semaphore.storages.impl;

import feda.cft.concurrent.semaphore.storages.IStorage;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

public class WaitStorageForInteger implements IStorage<Integer> {
    private int storageSize;
    private ArrayDeque<Integer> items;

    private Semaphore putSemaphore;
    private Semaphore getSemaphore;

    public WaitStorageForInteger()
    {
        this(DEFAULT_STORAGE_SIZE);
    }

    public WaitStorageForInteger(int storageSize)
    {
        this.storageSize = storageSize;
        items = new ArrayDeque<>(storageSize);

        putSemaphore = new Semaphore(1);
        getSemaphore = new Semaphore(1);
    }

    public Integer getItem() throws InterruptedException
    {
        getSemaphore.acquire();
        while(items.size() == 0)
        {
            getSemaphore.release();
            Thread.sleep(1);
            getSemaphore.acquire();
        }

        Integer item = items.pop();
        getSemaphore.release();
        return item;
    }

    public boolean putItem(Integer item) throws InterruptedException
    {
        putSemaphore.acquire();
        getSemaphore.acquire();

        while (items.size() == storageSize) {
            getSemaphore.release();
            Thread.sleep(1);
            getSemaphore.acquire();
        }

        if(!items.add(item))
            throw new IllegalStateException("Throw in ConcurrentIntegerStorage.put(): ArrayDeque.add() return false.");

        getSemaphore.release();
        putSemaphore.release();
        return true;
    }
}
