
Clean Code Principles
Meaningful names: deliver(), validateLoad(), and ensureAvailable() clearly describe their purpose.
Single Responsibility: Each subsystem handles one responsibility; the facade coordinates them.
Encapsulation: Private fields protect internal state, and the facade hides subsystem details.
Small methods: Methods focus on specific tasks, such as checking cargo or consuming energy.
Error handling: Invalid deliveries produce DeliveryException with the original cause.
Named constants: RESERVE, MAX_WEIGHT_KG, and ENERGY_PER_STEP replace magic numbers.
