package com.example.baris.bakingapp.helper;

import android.support.annotation.Nullable;
import android.support.test.espresso.IdlingResource;
import java.util.concurrent.atomic.AtomicBoolean;

public class idlingResource implements IdlingResource
{
    private AtomicBoolean atomicBoolean = new AtomicBoolean(true);

    @Override
    public String getName() {
        return this.getClass().getName();
    }
    @Override
    public boolean isIdleNow() {
        return atomicBoolean.get();
    }
    @Nullable
    private volatile ResourceCallback resourceCallback;
    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
    public void setIdleState(boolean bool) {
        atomicBoolean.set(bool);
        if (bool && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}