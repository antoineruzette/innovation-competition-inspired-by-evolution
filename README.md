# Multi-Agent Model of Innovation in a Competitive Environment

## Overview

This project simulates the evolution of multiple innovators (e.g., research groups) in a competitive environment, such as the Boston research landscape. Each agent evolves its innovation and competition levels over time, influencing its overall fitness, according to a system of differential equations.

| ![Image 1](../images/innovation_level_competition.png) | ![Image 2](../images/innovation_level_innovation.png) | ![Image 3](../images/median_innovation_competition.png) |
|:------------------------------:|:------------------------------:|:------------------------------:|
| Image 1 Description             | Image 2 Description             | Image 3 Description             |


## Mathematical model

The model is governed by the following system of differential equations for each agent:

$$
\frac{dI_i(t)}{dt} = r_{I,i} \cdot I_i(t) \cdot (1 - \delta \cdot C_i(t))
$$

$$
\frac{dC_i(t)}{dt} = r_{C,i} \cdot C_i(t) \cdot \left(\gamma \cdot F_i(t) - \frac{I_i(t)}{I_i(t) + 1}\right)
$$

$$
\frac{dF_i(t)}{dt} = \alpha \cdot I_i(t) - \beta \cdot C_i(t)
$$

Where:
- **I<sub>i</sub>(t)**: Innovation level of agent _i_ at time _t_.
- **C<sub>i</sub>(t)**: Competition level of agent _i_ at time _t_.
- **F<sub>i</sub>(t)**: Fitness level of agent _i_ at time _t_.
- **r<sub>I,i</sub>**: Innovation growth rate for agent _i_.
- **r<sub>C,i</sub>**: Competition growth rate for agent _i_.
- **α, β, γ, δ**: Constants representing interactions between innovation, competition, and fitness.

## Agents

### Innovator Class

Each innovator (e.g. a research group) is modeled as an agent with the following attributes:
- **Innovation**: Represents the innovator's capacity for developing new ideas.
- **Competition**: Represents the pressure the innovator faces from other innovators.
- **Fitness**: Calculated based on innovation and competition.

### Key Methods

- **innovate()**: Updates the innovation level according to the differential equation.
- **compete()**: Updates the competition level according to the differential equation.
- **updateFitness()**: Updates fitness based on innovation and competition.
- **passTime()**: Simulates the passage of one time step.

## Environment

### CompetitiveEnvironment Class

Manages the interactions between multiple research groups:
- **simulate()**: Runs the simulation over multiple time steps, updating the state of each innovator.
- **logState()**: Logs the state of all innovator at each time step.

## Running the Simulation

To run the simulation:

1. Compile: 

```bash
javac src/*.java
```

2. Run:

```bash
java -cp src CompetitiveEnvironment
```

3. Plot the results in the jupyter notebook `plot.ipynb`. Make sure to adapt the file path of the .csv file containing simulation results.