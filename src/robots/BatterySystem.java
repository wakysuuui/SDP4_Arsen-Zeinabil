package robots;

public class BatterySystem {
    private static final int RESERVE = 10;
    private int charge = 100;

    public void ensureAvailable(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException("Negative energy");
        }

        if (charge - energy < RESERVE) {
            throw new IllegalStateException("Insufficient battery; recharge required");
        }
    }

    public void consume(int energy) {
        ensureAvailable(energy);
        charge -= energy;
    }

    public int percentage() {
        return charge;
    }
}