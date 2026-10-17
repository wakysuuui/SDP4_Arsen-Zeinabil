package robots;

import java.util.List;

public class DriveSystem {
    private static final int ENERGY_PER_STEP = 2;

    private final WarehouseMap map;
    private final BatterySystem battery;
    private int position;

    public DriveSystem(WarehouseMap map, BatterySystem battery) {
        this.map = map;
        this.battery = battery;
    }

    public int energyFor(List<Integer> route) {
        return route.size() * ENERGY_PER_STEP;
    }

    public void follow(List<Integer> route) {
        int cursor = position;

        for (int next : route) {
            if (!map.adjacent(cursor, next)) {
                throw new IllegalArgumentException("Route contains an invalid step");
            }

            cursor = next;
        }

        battery.ensureAvailable(energyFor(route));

        for (int next : route) {
            battery.consume(ENERGY_PER_STEP);
            position = next;
        }
    }

    public int position() {
        return position;
    }
}