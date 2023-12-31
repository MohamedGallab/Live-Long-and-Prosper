# Live Long and Prosper
# Summary
This project aims to represent generic search problem then make a specific one. We then construct an agent that provides a solution to the problem by using a generic search method that implements different algorithms.

# Description
The problem described is a town management scenario where the goal is to increase the prosperity level of a town to 100 by strategically planning resource requests and building constructions. The town requires three types of resources (food, materials, energy), and the agent has a budget constraint of 100,000 with no additional income sources. There is also a limit on the amount of resources that can be stored in the town at any given time.

The agent can perform various actions such as requesting resource deliveries (food, materials, energy), waiting for deliveries, and building constructions (BUILD1, BUILD2). Each action has associated costs, resource consumption, and delay parameters. The goal is to find a sequence of actions that lead to a prosperity level of 100 while minimizing the amount of money spent.

The search space for finding a solution involves exploring different combinations of actions, considering the constraints and parameters provided. The actions have immediate and delayed effects on resource levels and prosperity. The search agent needs to explore different sequences of actions, considering the budget constraint and resource limits, to find an optimal plan.

The implementation involves creating a search agent in Java with different search strategies, such as breadth-first search, depth-first search, iterative deepening search, uniform cost search, greedy search, and A* search. The agent will explore the search space, comparing solutions based on runtime, number of expanded nodes, memory utilization, and CPU utilization.

The provided classes, `GenericSearch`, `LLAPSearch`, and `Node`, will be used to structure the implementation. The `solve` function in the `LLAPSearch` class is the key function responsible for finding a sequence of steps to achieve the town's prosperity goal. The function takes the initial state, search strategy, and a boolean parameter for visualization. The result includes a plan (sequence of actions), monetary cost, and the number of nodes expanded during the search.

Overall, the problem involves creating an intelligent search agent to efficiently navigate the search space and find an optimal plan for the town to reach a prosperity level of 100. The agent's performance will be evaluated based on various search strategies and their impact on runtime, resource utilization, and solution quality.


# Implementation
Here we delve into the implementation details.

## search problem abstract data type.
This was represented as the abstract class called GenericSearch.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/GenericSearch.java#L1-L20

This mirrors the 5-tuple definition:
1. Operators.
2. Initial state.
3. State space is implicitly generated.
4. Goal test.
5. Path cost.

most of these should be implemented by a more specific problem that inherits it.

## LLAP problem.
This problem is a specific instance of the generic search problem.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L1-L336

The main method of program is solve and it works as follows:

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L22-L29

1. an instance is created which calls the init method.

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L248-L287

3. The init method decodes the input string and saves the parameters into the instance variables. Also returns a root state.

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/Search.java#L1-L71
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/LLAPSearch.java#L6-L12
   
3. This problem and strategy are then passed onto the [search function](###search-class) which starts searching for a solution based on the specified algorithm.

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/LLAPSearch.java#L26-L26
   
5. finally we visualize the solution.
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/LLAPSearch.java#L28-L28
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/LLAPSearch.java#L31-L47

   
## Search Implementation.
This class implements the general search procedure.

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/7063c878ebd85657542ab08589903c74b344475a/src/code/Search.java#L14-L70

1. Create a HashSet to avoid repeated states

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L48-L48

2. Create the first node of the search tree

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L49

3. Create a queue based on the strategy specified

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L48-L48
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/LLAPSearch.java#L49-L76

   ### The search algorithms.

   we create a priority queue that sorts differently according to the specified strategy.
   
   1. "BF": Breadth first, sorts elements based on the depth ascendingly.
   2. "DF": Depth-first, sorts based on depth descendingly.
   3. "ID": Iterative Depth, same as DF but stops at a certain depth that gets incremented every loop. If there is no solution the loop will run indefinitely until the program times out (120s), this was specified by the professor. An alternate solution that we did can be found commented out at the end of the code snippet, this solution tracks the max depth reached in each iteration until the max depth stops increasing and thus the loop terminates(we have reached the maximum possible depth).
      
      https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L15-L39
     
   5. "UC": Uniform cost, sorts based on cost ascendingly.
   6. "GR": Greedy, sorts based on a choice between 2 heuristics.
   7. "AS": A*, sorts based on a combination of cost and heuristics.

   ### The cost function
   the cost in our problem is simply the money spent.
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/LLAPSearch.java#L82-L84

   ### The Heuristics
   1. Greedy 1: we simply choose the node that maximizes our prosperity

      https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/LLAPSearch.java#L289-L291

   2. A* 1: This heuristic assumes the best ratio possible of cost per prosperity level. then we calculate how many prosperity levels we need and calculate how much those remaining levels will cost. this function ignores the cost of materials and assumes a ratio that might not be possible as it combines the lowest cost with the most levels gained between both build operators which means it will be admissible.

      https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/LLAPSearch.java#L300-L305
   
   3. A* 2: this heuristic improves upon A* 1 by calculating a lower bound of the money required for the resources which was previously ignored. it always assumes minimum cost for each resource as well as assuming the best starting conditions. this means this will always be admissible.

      https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/LLAPSearch.java#L307-L321
      
5. Add first node

   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L53-L54

6. while the queue is not empty take out a node, check if it is the goal, expand it if not.
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L56-L68
   
7. return null signaling No Solution if queue is empty
   
   https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/Search.java#L69

## Search-tree node abstract data type.
This class represents each node in the search tree. it encapsulates all the needed info about this node. 

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/8ed381617fa5c4d8f1f8f8908e9f85fb30948849/src/code/Node.java#L1-L46

as well as a state

https://github.com/MohamedGallab/Live-Long-and-Prosper/blob/707d84a825738ff85b06309a5b74ba5bd475d347/src/code/State.java#L3-L21

these variables are what makes a state different from another one.

# Performance comparison
Considering that in our specific problem, the search tree is never infinite, this means all algorithms are complete. These performance metrics were ran on the initial string of test 10 and monitored using [VisualVM](https://visualvm.github.io/)

```java
String init = "32;" +
                "20,16,11;" +
                "76,14,14;" +
                "9,1;9,2;9,1;" +
                "358,14,25,23,39;" +
                "5024,20,17,17,38;";
```

BFS 502100

Optimal: Generally not optimal

<img width="1009" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/a76796c1-084f-44cf-a918-9c70a678c977">

DF 12181201

Optimal: Generally not optimal

<img width="1008" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/e720eced-79af-4726-a107-ade906f3955d">

ID 4664886

Optimal: Generally not optimal

<img width="1068" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/d14cb566-cc71-4d32-8eee-3fd1230ff71b">

UC 12294033

Optimal: Yes

<img width="1008" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/ed85abcd-625f-4c1d-ab36-d1842e69c731">

GR1 45976

Optimal: Generally not optimal

<img width="1007" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/db4f2678-1e48-4abb-8b1e-2bd19a36e55b">

GR2 46700

Optimal: Generally not optimal

<img width="1009" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/336d413b-3ed8-47c6-ace8-49f1ade7ec2d">

AS1 11527159

Optimal: Yes

<img width="1008" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/8c4d8e36-fcad-4175-a1fe-e68bf67cf410">

AS2 8884635

Optimal: Yes

<img width="1008" alt="image" src="https://github.com/MohamedGallab/Live-Long-and-Prosper/assets/74183135/c6d136d3-ba66-4d53-926c-5a7080c58fb9">

## Discussion
We notice that AS1 and AS2 provide the most optimal solution but at the cost of expanding 8 million+ nodes as compared to the greedy approach which provided a solution (although not optimal) while only expanding 18 thousand nodes.

The UC is never worth using when we have access to the A* strategies as it just takes longer to do the same job. BFS, DFS and ID all depend heavily on the problem. In this case there existed a goal that was not too deep so BFS performed best out of the three.

The Greedy approach provided the fastest result but it was not optimal.

