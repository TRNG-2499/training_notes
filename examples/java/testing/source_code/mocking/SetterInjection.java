package com.revature.junit.mocking;

public class SetterInjection {
    private AppData data;

    public boolean execute(int value) {
        return data.performTask(value);
    }

    public void setData(AppData data) {
        System.out.println("Setter Injection - Setter Called");
        this.data = data;
    }
}
