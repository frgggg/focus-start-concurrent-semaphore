package feda.cft.concurrent.semaphore.storages;

public interface IStorage<E> {
    int DEFAULT_STORAGE_SIZE = 5;
    E getItem() throws InterruptedException;
    boolean putItem(E item) throws InterruptedException;
}
