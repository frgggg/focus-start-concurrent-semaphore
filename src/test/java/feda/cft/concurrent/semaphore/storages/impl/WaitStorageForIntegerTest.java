package feda.cft.concurrent.semaphore.storages.impl;

import org.junit.Assert;
import org.junit.Test;

import static feda.cft.concurrent.semaphore.storages.impl.WaitStorageForIntegerTestUtils.*;
import static org.junit.Assert.*;

public class WaitStorageForIntegerTest {

    @Test
    public void putItemTest() {
        try {
            WaitStorageForInteger fullSingleItemStorage = WaitStorageForIntegerTestUtils.GET_FULL_SINGLE_ITEM_STORAGE();
            Thread putInFullStorageThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fullSingleItemStorage.putItem(ITEM_FOR_PUT_IN_FULL_STORAGE);
                    }
                    catch (InterruptedException e)
                    {
                        return;
                    }
                }
            });
            putInFullStorageThread.start();
            Thread.sleep(TEST_SLEEP_TIME);
            assertEquals(putInFullStorageThread.getState(), THREAD_STATE_AFTER_PUT_IN_FULL_STORAGE);
            assertEquals(fullSingleItemStorage.getItem(), ITEM_IN_FULL_SINGLE_ITEM_STORAGE);
            Thread.sleep(TEST_SLEEP_TIME);
            assertEquals(putInFullStorageThread.getState(), THREAD_STATE_AFTER_PUT_AND_GET_FROM_FULL_STORAGE);

            putInFullStorageThread.interrupt();
        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger putItemTest was interrupted.");
        }
    }

    @Test
    public void getItemTest()
    {
        try {
            WaitStorageForInteger emptySingleItemStorage = GET_EMPTY_SINGLE_ITEM_STORAGE();
            Thread getFromEmptySingleItemStorage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        emptySingleItemStorage.getItem();
                    }
                    catch (InterruptedException e)
                    {
                        return;
                    }
                }
            });
            getFromEmptySingleItemStorage.start();
            Thread.sleep(TEST_SLEEP_TIME);
            assertEquals(getFromEmptySingleItemStorage.getState(), THREAD_STATE_AFTER_GET_FROM_EMPTY_STORAGE);

            WaitStorageForInteger fullSingleItemStorage = GET_FULL_SINGLE_ITEM_STORAGE();
            assertEquals(fullSingleItemStorage.getItem(), ITEM_IN_FULL_SINGLE_ITEM_STORAGE);
        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger getItemTest was interrupted.");
        }
    }
}
