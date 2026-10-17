## Clean Code Principles

1. **Meaningful Names**  
   Names such as `deliver()`, `validateLoad()`, and `ensureAvailable()` clearly express what each method does, making the code easier to understand.

2. **Single Responsibility Principle**  
   Each class has a specific responsibility. `WarehouseMap` calculates routes, `BatterySystem` manages energy, and `RobotFacade` coordinates the delivery process without implementing subsystem logic.

3. **Encapsulation**  
   Fields are private, and the client interacts with the robot through `deliver()` and `status()`. The facade does not expose subsystem objects through its public interface.

4. **Small and Focused Methods**  
   Operations are divided into methods with clear purposes. For example, `requireItem()` checks whether cargo exists, while `consume()` updates the battery charge.

5. **Proper Error Handling**  
   The system checks cargo weight, destination, and available energy before movement begins. `DeliveryException` reports failures and preserves the original exception for debugging.

6. **Avoiding Duplication**  
   `DriveSystem.energyFor()` calculates movement energy for both the facade’s initial check and the actual movement, keeping the calculation consistent.

7. **Named Constants**  
   Constants such as `MAX_WEIGHT_KG`, `ENERGY_PER_STEP`, and `RESERVE` make operating limits clear and easy to change.
