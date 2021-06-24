# Ant Colony Simulator
This is a simulation project for school to simulate ants looking for food and returning it back to their colony. 

I was inspired by https://www.youtube.com/watch?v=81GQNPJip2Y and I would like to try my own implementation.

# User Documentation

**Basics of the project**
1. Looking for food

The Ants will at first randomly move around the map and they will leave behind pheromones which help future pathing to be more effective.

2. The find and return

When the forager ant in question finds the food they will backtrack and follow any tracks they can see until eventually they arrive back at the colony while avoiding danger and nodes they cannot get to.

3. Managing the nest

Worker ants will manage the nest to make it bigger and stronger for let more ants join the colony and survive better.

**Pathing algorithm**

I want the ants to have some form of memory of travel, and be able to mark their tracks with two types of pheromones. Those being positive and negative. The ants then probabilistically move based on what the next best path is in terms of positive/negative pheromones. 
When an ant encounters a wall or an obstacle it will make sure the next step is a valid step on the map. 
When an ant encounters an enemy it will drop negative pheromones and run away to prevent more ants from going in that direction
When an ant encounters food it will start dropping positive pheromones and navigate back to the next based on its path history and other positive pheromones.
Overtime these pheromones fade so only the most used path will remain a path.

**Variables to the colony**

1. **Weather**

    I will implement some things which will make it harder/easier for the ants to collect food. For example rain, which with some intensity randomly drops rain on certain nodes, and if an ant is present on that node than it has some chance to die and drop the food. During rain ants may want to stay hidden in the nest.

2. **Obstacles**

    I will create some (either random or preset) obstacles on the map, such as puddle of water or walls which the ants cannot cross, or sticks which the ant needs to climb over slowing it down. 

The Ants will have a chance to die based on their age in terms of number of turns taken according to some probabilistic function.

**Classes**

1. **Ant Class**

    This class defines the base Ant object with x,y coordinates and states. 
    It also holds the movement functions of the ant, which moves in some specific manner based on it's surroundings.
    Each type of Ant has a different lifetime meaning they all eventually die after a certain number of turns.

2. **AntSimulation Class**

    This class defines the actual simulation and all the states of every class are initialized here

3. **Node Class**

    This class defines the map (board) where ants travel, food is placed on and other objects such as obstacles/enemies and rain are set. This treats individual nodes.

4. **Nest**

    Manages size of nest mainly.

5. **Food**

    To manage food on the whole map.

6. **Rain**

    Manages rain which kills ants.

7. **Nest**

    Class that holds the ant nest properties such as how many ants/larvae and so on. It also holds the size and strength of the nest which changes the probability of the nest surviving when attacked by an ant eater.

8. **Queen Ant**

    This is the queen ant and it lays eggs for new ants to be born, if the queen dies the colony will die over time unless a new queen arrives.

9. **Wall**

    Generates walls on the map which the ants cannot pass through.


## Goals

I would like to have some sort of cool visual realtime sim of the all the ants traveling around looking for food. The thing that interests me the most is implementing some complex pathfinding algorithm to maximize the efficiency of the ants. I want there to be a chance for an ant colony to die out if enough random events happen which cause this to be so, such as too much rain.
