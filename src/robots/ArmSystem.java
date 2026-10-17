package robots;

public class ArmSystem {
    private static final double MAX_WEIGHT_KG = 5.0;
    private static final int ENERGY_PER_ACTION = 3;

    private final BatterySystem battery;
    private String cargo;

    public ArmSystem(BatterySystem battery) {
        this.battery = battery;
    }

    public void validateLoad(double weight) {
        if (!Double.isFinite(weight) || weight <= 0 || weight > MAX_WEIGHT_KG) {
            throw new IllegalArgumentException(
                    "Cargo must weigh between 0 and 5 kg (exclusive of 0)"
            );
        }

        if (cargo != null) {
            throw new IllegalStateException("Arm is already carrying cargo");
        }
    }

    public int handlingEnergy() {
        return 2 * ENERGY_PER_ACTION;
    }

    public void pick(String item, InventorySystem inventory, int robotPosition) {
        validateLoad(inventory.weight(item));

        if (inventory.location(item) != robotPosition) {
            throw new IllegalStateException("Robot is not at pickup location");
        }

        battery.consume(ENERGY_PER_ACTION);
        cargo = item;
    }

    public void release() {
        if (cargo == null) {
            throw new IllegalStateException("No cargo to release");
        }

        battery.consume(ENERGY_PER_ACTION);
        cargo = null;
    }
}