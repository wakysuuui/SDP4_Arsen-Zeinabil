package robots;

import java.util.List;

public class RobotFacade {
    private final WarehouseMap map = new WarehouseMap();
    private final BatterySystem battery = new BatterySystem();
    private final InventorySystem inventory = new InventorySystem();
    private final DriveSystem drive = new DriveSystem(map, battery);
    private final ArmSystem arm = new ArmSystem(battery);

    public String deliver(String item, int destinationX, int destinationY) {
        try {
            int source = inventory.location(item);
            int destination = map.cell(destinationX, destinationY);

            if (source == destination) {
                return item + " is already at destination";
            }

            arm.validateLoad(inventory.weight(item));

            List<Integer> pickupRoute = map.route(drive.position(), source);
            List<Integer> deliveryRoute = map.route(source, destination);

            int requiredEnergy = drive.energyFor(pickupRoute)
                    + drive.energyFor(deliveryRoute)
                    + arm.handlingEnergy();

            battery.ensureAvailable(requiredEnergy);

            drive.follow(pickupRoute);
            arm.pick(item, inventory, drive.position());
            drive.follow(deliveryRoute);
            arm.release();
            inventory.relocate(item, source, destination);

            return "Delivered " + item
                    + " to (" + destinationX + ", " + destinationY + ")";
        } catch (IllegalArgumentException | IllegalStateException failure) {
            throw new DeliveryException(
                    "Delivery failed: " + failure.getMessage(),
                    failure
            );
        }
    }

    public String status() {
        return "Robot cell: " + drive.position()
                + "; battery: " + battery.percentage() + "%";
    }
}