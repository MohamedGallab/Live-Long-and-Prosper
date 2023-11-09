# Live Long and Prosper
## Summary
This project aims to represent generic search problem then make a specific one. We then construct an agent that provides a solution to the problem by using a generic search method that implements different algorithms.

## Description
The problem described is a town management scenario where the goal is to increase the prosperity level of a town to 100 by strategically planning resource requests and building constructions. The town requires three types of resources (food, materials, energy), and the agent has a budget constraint of 100,000 with no additional income sources. There is also a limit on the amount of resources that can be stored in the town at any given time.

The agent can perform various actions such as requesting resource deliveries (food, materials, energy), waiting for deliveries, and building constructions (BUILD1, BUILD2). Each action has associated costs, resource consumption, and delay parameters. The goal is to find a sequence of actions that lead to a prosperity level of 100 while minimizing the amount of money spent.

The search space for finding a solution involves exploring different combinations of actions, considering the constraints and parameters provided. The actions have immediate and delayed effects on resource levels and prosperity. The search agent needs to explore different sequences of actions, considering the budget constraint and resource limits, to find an optimal plan.

The implementation involves creating a search agent in Java with different search strategies, such as breadth-first search, depth-first search, iterative deepening search, uniform cost search, greedy search, and A* search. The agent will explore the search space, comparing solutions based on runtime, number of expanded nodes, memory utilization, and CPU utilization.

The provided classes, `GenericSearch`, `LLAPSearch`, and `Node`, will be used to structure the implementation. The `solve` function in the `LLAPSearch` class is the key function responsible for finding a sequence of steps to achieve the town's prosperity goal. The function takes the initial state, search strategy, and a boolean parameter for visualization. The result includes a plan (sequence of actions), monetary cost, and the number of nodes expanded during the search.

Overall, the problem involves creating an intelligent search agent to efficiently navigate the search space and find an optimal plan for the town to reach a prosperity level of 100. The agent's performance will be evaluated based on various search strategies and their impact on runtime, resource utilization, and solution quality.


## Implementation
Here we delve into the implementation details.

### search problem abstract data type.
This was represented as the abstract class called GenericSearch.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/GenericSearch.java#L1C1-L20C2

This mirrors the 5-tuple definition:
1. Operators.
2. Initial state.
3. State space is implicitly generated.
4. Goal test.
5. Path cost.

most of these should be implemented by a more specific problem that inherits it.

### LLAP problem.
This problem is a specific instance of the generic search problem.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L1C1-L336C2

The main method of program is solve and it works as follows:

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L22C5-L29C6

1. an instance is created which calls the init method.

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L248C5-L287C6

3. The init method decodes the input string and saves the parameters into the instance variables. Also returns a root state.

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/Search.java#L1C1-L71C2
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L6C5-L12C27
   
3. This problem and strategy are then passed onto the [search function](###search-class) which starts searching for a solution based on the specified algorithm.

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/LLAPSearch.java#L26C9-L26C65
   
5. finally we visualize the solution.
6. 
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/LLAPSearch.java#L28C9-L28C57
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/LLAPSearch.java#L31C5-L47C6
8. 
### Search Class.
This class implements the general search procedure.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/Search.java#L14C1-L70C6


### search-tree node abstract data type.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/Node.java#L1C1-L46C2

### A description of the main functions you implemented.
### A discussion of how you implemented the various search algorithms.
### A discussion of the heuristic functions you employed and, in the case of greedy
or A*, an argument for their admissibility.
