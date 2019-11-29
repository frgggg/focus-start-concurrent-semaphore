package feda.cft.concurrent.semaphore.storages.impl;

import feda.cft.concurrent.semaphore.storages.IStorage;

import java.util.ArrayDeque;
import java.util.concurrent.Semaphore;

public class SmallWaitStorageForInteger implements IStorage<Integer> {
    private int storageSize;
    private ArrayDeque<Integer> items;

    private boolean inProcess = false;
    private Semaphore processSemaphore;

    public SmallWaitStorageForInteger()
    {
        this(DEFAULT_STORAGE_SIZE);
    }

    public SmallWaitStorageForInteger(int storageSize)
    {
        this.storageSize = storageSize;
        items = new ArrayDeque<>(storageSize);

        processSemaphore = new Semaphore(1);
    }

    public Integer getItem() throws InterruptedException
    {
        if(startProcess())
        {
            Integer item = null;
            if(items.size() > 0)
                item = items.pop();
            stopProcess();
            return item;
        }
        return null;
    }

    public boolean putItem(Integer item) throws InterruptedException
    {
        if(startProcess())
        {
            boolean putResult = false;
            if(items.size() < storageSize)
                putResult = items.add(item);
            stopProcess();
            return putResult;
        }

        return false;
    }

    private boolean startProcess() throws InterruptedException
    {
        boolean canStartProcess = false;
        processSemaphore.acquire();
        if(!inProcess) {
            inProcess = true;
            canStartProcess = true;
        }
        processSemaphore.release();
        return canStartProcess;
    }

    private void stopProcess() throws InterruptedException
    {
        processSemaphore.acquire();
        inProcess = false;
        processSemaphore.release();
    }
}
