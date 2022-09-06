package Deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
    Account account1 = new Account();
    Account account2 = new Account();

    private Lock lock1 = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    // Mechanism to avoid deadlock in such scenario would be handling locks with
    // separate methods like below one
    private void acquireLocks(Lock lock1, Lock lock2) throws InterruptedException {

        while (true) {
            boolean gotLockOne = false;
            boolean gotLockTwo = false;
            try {
                gotLockOne = lock1.tryLock();
                gotLockTwo = lock2.tryLock();
            } finally {
                if (gotLockOne && gotLockTwo) {
                    return;
                }
                if (gotLockOne) {
                    lock1.unlock();
                }
                if (gotLockTwo) {
                    lock2.unlock();
                }
            }
            Thread.sleep(1);
        }
    }

    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            acquireLocks(lock1, lock2);
            try {
                Account.transfer(account1, account2, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {

            // lock1.lock();
            // lock2.lock();// If you reverse the order of lock1 and lock2 you will get deadlock
            acquireLocks(lock2, lock1);
            try {
                Account.transfer(account2, account1, random.nextInt(100));
            } finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished() {
        System.out.println("Account one balance is : " + account1.getBalance());
        System.out.println("Account two balance is : " + account2.getBalance());
        System.out.println("Total balance is : " + (account1.getBalance() + account2.getBalance()));
    }
}
