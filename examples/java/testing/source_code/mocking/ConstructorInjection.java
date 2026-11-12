package com.revature.junit.mocking;

public class ConstructorInjection {
    private AppData daoImplementation;

    public ConstructorInjection(AppData daoImplementation) {
        System.out.println("Constructor Injection - Constructor Called");
        this.daoImplementation = daoImplementation;
    }

    /*
     * If I give the number 100 to 'execute' it SHOULD return true
     */
    public boolean execute(int value) {
        // Process data beforehand!
        value += 50; // give 100 -> 150

        // my 'execute' method is reliant on data from another class!
        boolean dataResult = daoImplementation.performTask(value);

        /*
         * Process data in some way...
         */

        if (dataResult) {
            return true;
        }

        return false;
    }
}
