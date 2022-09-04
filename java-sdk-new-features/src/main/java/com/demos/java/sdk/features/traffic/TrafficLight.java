package com.demos.java.sdk.features.traffic;

public sealed interface TrafficLight permits RedLight,GreenLight,YellowLight
{
    String next();
}
