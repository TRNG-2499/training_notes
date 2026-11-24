package com.revature.lambdas.methodreference;

@FunctionalInterface
public interface ObjectMaker<T> {
    public T makeObject();
}
