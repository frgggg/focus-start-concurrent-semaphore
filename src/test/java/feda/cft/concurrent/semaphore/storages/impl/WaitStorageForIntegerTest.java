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
    @Test
    public void getItemWaitTest()
    {
        try {

            WaitStorageForInteger emptySingleItemStorage = GET_EMPTY_SINGLE_ITEM_STORAGE();
            Thread getFromEmptySingleItemStorage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        emptySingleItemStorage.getItem();
                        isItemFromEmptyStorageGotten = true;
                    }
                    catch (InterruptedException e)
                    {
                        return;
                    }
                }
            });
            Thread.sleep(WAIT_TESTS_SLEEP_TIME);
            assertFalse(isItemFromEmptyStorageGotten);

        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger getItemWaitTest was interrupted.");
        }
    }

    private boolean isItemPuttedToFullStorageGotten = false;
    @Test
    public void putItemWaitTest()
    {
        try {

            WaitStorageForInteger fullSingleItemStorage = GET_FULL_SINGLE_ITEM_STORAGE();
            Thread putToFullSingleItemStorage = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        fullSingleItemStorage.getItem();
                        isItemPuttedToFullStorageGotten = true;
                    }
                    catch (InterruptedException e)
                    {
                        return;
                    }
                }
            });
            Thread.sleep(WAIT_TESTS_SLEEP_TIME);
            assertFalse(isItemPuttedToFullStorageGotten);

        }
        catch (InterruptedException e)
        {
            Assert.fail("WaitStorageForInteger putItemWaitTest was interrupted.");
        }
    }
}
