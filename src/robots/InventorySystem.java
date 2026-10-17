package robots;

import java.util.HashMap;
import java.util.Map;

public class InventorySystem {
    private final Map<String, Integer> locations = new HashMap<>();
    private final Map<String, Double> weights = new HashMap<>();

    public InventorySystem() {
        locations.put("BOX-101", 4);
        locations.put("BOX-202", 20);
        locations.put("BOX-HEAVY", 1);

        weights.put("BOX-101", 3.5);
        weights.put("BOX-202", 2.0);
        weights.put("BOX-HEAVY", 12.0);
    }

    public int location(String item) {
        requireItem(item);
        return locations.get(item);
    }

    public double weight(String item) {
        requireItem(item);
        return weights.get(item);
    }

    public void relocate(String item, int expectedLocation, int destination) {
        if (location(item) != expectedLocation) {
            throw new IllegalStateException("Inventory location changed");
        }

        locations.put(item, destination);
    }

    private void requireItem(String item) {
        if (item == null || !locations.containsKey(item)) {
            throw new IllegalArgumentException("Unknown item: " + item);
        }
    }
}