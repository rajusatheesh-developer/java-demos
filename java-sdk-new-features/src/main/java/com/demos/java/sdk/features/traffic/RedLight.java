package com.demos.java.sdk.features.traffic;

public final class RedLight implements TrafficLight {
    @Override
    public String next() {
        return "Stop";
    }
}
