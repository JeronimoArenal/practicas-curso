package org.example3;

public enum FlightPriority {
    EMERGENCY(1), VIP(2), NORMAL(3);

    private final int level;

    FlightPriority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}

