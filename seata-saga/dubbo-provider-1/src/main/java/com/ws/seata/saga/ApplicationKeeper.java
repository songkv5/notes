package com.ws.seata.saga;

import org.springframework.context.support.AbstractApplicationContext;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author willis
 * @desc
 * @since 2020年03月05日 13:14
 */
public class ApplicationKeeper {
    private static final ReentrantLock LOCK = new ReentrantLock();
    private static final Condition CDT = LOCK.newCondition();

    public ApplicationKeeper(AbstractApplicationContext applicationContext) {
        addShutdownHook(applicationContext);
    }

    private void addShutdownHook(final AbstractApplicationContext applicationContext) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    applicationContext.close();
                    System.out.println("ApplicationContext " + applicationContext + " is closed.");
                } catch (Exception e) {
                    System.out.println("Failed to close ApplicationContext" + e.getLocalizedMessage());
                }

                try {
                    LOCK.lock();
                    CDT.signal();
                } finally {
                    LOCK.unlock();
                }
            }
        }));
    }

    public void keep() {
        try {
            LOCK.lock();
            CDT.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
