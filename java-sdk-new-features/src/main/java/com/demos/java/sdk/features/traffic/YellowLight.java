package com.demos.java.sdk.features.traffic;

public final class YellowLight implements TrafficLight {
    @Override
    public String next() {
        return "Wait";
    }
}
