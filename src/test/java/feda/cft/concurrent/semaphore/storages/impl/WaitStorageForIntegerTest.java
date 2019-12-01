package feda.cft.concurrent.semaphore.storages.impl;

import org.junit.Assert;
import org.junit.Test;

import static feda.cft.concurrent.semaphore.storages.impl.WaitStorageForIntegerTestUtils.*;
import static org.junit.Assert.*;

public class WaitStorageForIntegerTest {

    @Test
    public void storagePutAndGetItemTest() {
        try {
            WaitStorageForInteger fullSingleItemStorage = WaitStorageForIntegerTestUtils.GET_FULL_SINGLE_ITEM_STORAGE();
            assertEquals(ITEM_IN_FULL_SINGLE_ITEM_STORAGE, fullSingleItemStorage.getItem());
        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger storagePutAndGetItemTest was interrupted.");
        }
    }

    private boolean isItemFromEmptyStorageGotten = false;
    private boolean isStorageTestThreadInterrupted = false;
    @Test
    public void getItemWaitTest()
    {
        isStorageTestThreadInterrupted = false;
        try {

            WaitStorageForInteger emptySingleItemStorage = GET_EMPTY_SINGLE_ITEM_STORAGE();
            new Thread(() -> {
                try {
                    emptySingleItemStorage.getItem();
                    isItemFromEmptyStorageGotten = true;
                }
                catch (InterruptedException e)
                {
                    isStorageTestThreadInterrupted = true;
                    return;
                }
            }).start();
            Thread.sleep(WAIT_TESTS_SLEEP_TIME);

            assertFalse(isItemFromEmptyStorageGotten);
            assertFalse(isStorageTestThreadInterrupted);

        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger getItemWaitTest was interrupted.");
        }
    }

    private boolean isItemPuttedToFullStorage = false;
    @Test
    public void putItemWaitTest()
    {
        isStorageTestThreadInterrupted = false;
        try {
            WaitStorageForInteger fullSingleItemStorage = GET_FULL_SINGLE_ITEM_STORAGE();
            new Thread(() -> {
                try {
                    fullSingleItemStorage.putItem(ITEM_FOR_PUT_IN_FULL_SINGLE_ITEM_STORAGE);
                    isItemPuttedToFullStorage = true;
                }
                catch (InterruptedException e)
                {
                    isStorageTestThreadInterrupted = true;
                    return;
                }
            }).start();
            Thread.sleep(WAIT_TESTS_SLEEP_TIME);

            assertFalse(isStorageTestThreadInterrupted);
            assertFalse(isItemPuttedToFullStorage);

        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger putItemWaitTest was interrupted.");
        }
    }
}
