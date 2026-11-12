package com.revature.junit.mocking;

public class AppData {
    /*
     * This method performs an arbitrary task, but is simply being
     * used to showcase Injection with Mockito
     *
     * See 'InjectionTests.java' for more details...
     */
    public boolean performTask(Integer arg) {
        return arg > 10 ? true : false;
    }
}
