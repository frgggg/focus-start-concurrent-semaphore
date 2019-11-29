package feda.cft.concurrent.semaphore.applications.utils;

import feda.cft.concurrent.semaphore.storages.IStorage;

public class StorageIntegerUse {
    private static final int DEFAULT_GET_TIMEOUT = 100;
    private static final int DEFAULT_PUT_TIMEOUT = DEFAULT_GET_TIMEOUT * 2;
    private static final int DEFAULT_USE_TIME = DEFAULT_PUT_TIMEOUT * 10;

    private IStorage<Integer> storage;
    private int putTimeout, getTimeout, useTime;
    public StorageIntegerUse(IStorage<Integer> storage, int putTimeout, int getTimeout, int useTime)
    {
        this.storage = storage;
        this.putTimeout = putTimeout;
        this.getTimeout = getTimeout;
        this.useTime = useTime;

        if(this.getTimeout <= 0)
            this.getTimeout = DEFAULT_GET_TIMEOUT;
        if(this.putTimeout <= 0)
            this.putTimeout = DEFAULT_PUT_TIMEOUT;

        if(this.useTime <= 0)
            this.useTime = DEFAULT_USE_TIME;
    }

    public void use()
    {
        Thread consumerThread = new Thread(
                () -> {
                    Integer putItem = 0;
                    try {
                        while (true)
                        {
                            boolean putResult = storage.putItem(putItem);
                            if(putResult) {
                                System.out.println("Integer(" + putItem++ + ") is putted.");
                            }
                            Thread.sleep(putTimeout);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("producerThread is interrupted.");
                    }
                }
        );

        Thread producerThread = new Thread(
                () -> {
                    try {
                        Integer getItem;
                        while (true) {
                            if((getItem = storage.getItem()) != null)
                                System.out.println("Gotten Integer = " + getItem + ".");
                            Thread.sleep(getTimeout);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        System.out.println("consumerThread is interrupted.");
                    }
                }
        );

        consumerThread.start();
        producerThread.start();

        try {
            Thread.sleep(useTime);
        }
        catch (InterruptedException e)
        {
            System.out.println("StorageIntegerUse.use is interrupted.");
        }
        finally {
            producerThread.interrupt();
            consumerThread.interrupt();

            try {
                producerThread.join();
                consumerThread.join();
            } catch (InterruptedException e) {
                System.out.println("Throw in join!!!");
            }
        }
    }
}
