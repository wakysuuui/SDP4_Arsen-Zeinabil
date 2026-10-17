package robots;

public class Main {
    public static void main(String[] args) {
        RobotFacade robot = new RobotFacade();

        System.out.println(robot.deliver("BOX-101", 4, 4));
        System.out.println(robot.status());

        try {
            robot.deliver("BOX-HEAVY", 0, 4);
        } catch (DeliveryException failure) {
            System.out.println(failure.getMessage());
        }

        System.out.println(robot.status());
    }
}