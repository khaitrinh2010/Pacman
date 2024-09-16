## Overview

This project refactors the codebase to utilize the **Strategy** and **State** design patterns for managing ball behavior in a **BallPit**.

### Strategy Pattern:
- **Context**: The ball.
- **Strategy Interface**: The **`BallMovementStrategy`** interface.
- **Concrete Implementations**:
    - `ChaseFurthestStrategy`
    - `ChaseNearestStrategy`
    - `NoChaseStrategy`

The strategy will take the ball as input, find the target balls using the distance method in the **BallPit**, and then set the velocity of the source ball accordingly.

### State Pattern:
- **Context**: The ball.
- **State Interface**: The **`State`** interface.
- **Concrete Implementations**:
    - `RedState`
    - `BlueState`
    - `BlackState`

Each state implements the method `handleCollision()`, which manages the state transition and sets the ball's color when a collision occurs. The responsibility of handling the collision is delegated to the state, instead of the ball.
