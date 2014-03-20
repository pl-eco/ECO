ECO
===

ECO compiler impelementation

This project implements Eco compiler, a novel energy-aware programming model centering around sustainability. 
A sustainable program adaptively adjusts its own behaviors to stay on a given energy budget, avoiding both deficit
 that would lead to battery drain or CPU overheating, and surplus that could have been used to improve the quality 
 of the program output. Sustainability management in Eco is built on a key insight where sustainability is viewed 
 as a form of supply and demand matching, and a sustainable program consistently maintains the equilibrium between supply 
 and demand. Concretely, Eco introduces a novel language abstraction-sustainable blocks-to achieve fine-grained programmable 
 sustainability. Eco is implemented as a minimal extension to Java, and we validate its design by upgrading real-world Java
  programs, enabling two crucial features toward energy-aware software: battery awareness and temperature awareness.
