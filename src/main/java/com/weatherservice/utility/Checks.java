package com.weatherservice.utility;

public class Checks {

    public static void checkThat(boolean condition, String errorMessage) {
        if (!condition)
            throw new IllegalStateException(errorMessage);
    }

    private Checks() {
        throw new AssertionError("No Checks instances for you!");
    }
}
