package feda.cft.concurrent.semaphore.storages.impl;

import org.junit.Assert;
import org.junit.Test;

import static feda.cft.concurrent.semaphore.storages.impl.SmallWaitStorageForIntegerTestUtils.*;
import static org.junit.Assert.*;

public class SmallWaitStorageForIntegerTest {
    @Test
    public void smallWaitStorageIntegerTest() {
        try {
            SmallWaitStorageForInteger storage = new SmallWaitStorageForInteger(TEST_STORAGE_SIZE_IS_ONE);

            assertTrue(storage.putItem(PUTTED_ITEM_ONE_IN_EMPTY_SINGLE_ITEM_STORAGE));
            assertFalse(storage.putItem(PUTTED_ITEM_TWO_IN_FULL_SINGLE_ITEM_STORAGE));

            assertEquals(storage.getItem(), GOTTEN_ITEM_FROM_SINGLE_ITEM_FULL_STORAGE);
            assertEquals(storage.getItem(), GOTTEN_NULL_FROM_SINGLE_ITEM_EMPTY_STORAGE);
        }
        catch (InterruptedException e)
        {
            Assert.fail("Test for SmallWaitStorageInteger was interrupted!");
        }
    }
}
