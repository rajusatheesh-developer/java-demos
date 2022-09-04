package com.demos.java.sdk.features.traffic;

public final class GreenLight implements TrafficLight {
    @Override
    public String next() {
        return "Allow";
    }
}
