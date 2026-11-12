package com.revature.junit.mocking;

public class FieldInjection {
    // This is an 'initializer' block. It will run when an object is created in memory
    {
        System.out.println("Field Injection - Initializer Block Run");
    }
    private AppData data;

    public boolean execute(int value) {
        return data.performTask(value);
    }
}
