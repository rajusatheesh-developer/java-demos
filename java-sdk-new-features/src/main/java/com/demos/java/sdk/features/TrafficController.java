package com.demos.java.sdk.features;

import com.demos.java.sdk.features.traffic.GreenLight;
import com.demos.java.sdk.features.traffic.RedLight;
import com.demos.java.sdk.features.traffic.TrafficLight;
import com.demos.java.sdk.features.traffic.YellowLight;

public class TrafficController {
    public String onSignal(String signal) {
        return switch (signal) {
            case "Green" -> "Allow";
            case "Red" -> "Stop";
            case "Yellow" -> "Wait";
            default -> throw new IllegalStateException("Unexpected value: " + signal);
        };
    }

    public String next(String signal) {
        TrafficLight light = switch (signal) {
            case "Green" -> new GreenLight();
            case "Red" -> new RedLight();
            case "Yellow" -> new YellowLight();
            default -> throw new IllegalStateException("Unexpected value: " + signal);
        };

        return light.next();
    }
}
