package com.yincheng.surface_anim;

public class MagicSurfaceViewLock {

    private static SimpleLock sLock = new SimpleLock();

    public static void lock() {
        sLock.lock();
    }

    public static void unlock() {
        sLock.unlock();
    }

}
