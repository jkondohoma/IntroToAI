Jaelle Kondohoma
CSCE 476 HW 2
Problem 1:  Implementing a simple-reflex agent


Data structures: 
------------------------------------------
World.java, represents the environment where an agent would perform. It has two data member rightStatus and leftStatus
leftStatus: status of left cell can be clean or dirty
rightStatus: status of right cell can be clean or dirty

Agent.java, represents our vacuum cleaner reflex agent. It has 2 data members location and world
location: where our agent is currently in our environment
world: environment where the agent will perform

Functions (in World.java):
--------------------------------------------
* goalReached(): checks if our world is in its goal state (both left and right cells are clean)

Functions (in Agent.java):
--------------------------------------------
* getStatusLocation(): returns status of whichever room the agent is in
* ReflexVaccuumAgent(): returns the action the agent should perform based on its location and status
* updateAgent(action): given the action the agent has performed the location of the agent and/or its environment is updated accordingly,
						the agent is also penalize accordingly for each step and each suck action


Functions (in CleanWorld.java):
--------------------------------------------
* ReflexAgent(Agent): given an agent go trough the process of cleaning up its world
* cleanWorld(leftStatus,rightStatus,location): given initial state clean up world


steps required to compile and run the program:
---------------------------------------------
Main function is located in Problem1.java
To run on command line:
* World.java, Agent.java, CleanWorld.java, Problem1.java must all be in same folder
* compile using "javac Problem1.java"
* run the program using "java Problem1"

ps: occasionally one room might get dirty again 
(for the purpose of this program its always the room where the agent is not currently in and rooms start
getting dirty after state goal is reached). After a certain number of steps is reached we exit the program.

The way I designed it either room might get dirty when the time variable is a multiple of 5. 
The max steps an agent can perform before they "lose power" is 30 (so we're not stuck in an infinite loop).

If needed time interval can also be changed (line 44 in CleanWorld.java) to any other integer 
The number of max steps can also be changed (line 43 in CleanWorld.java) to any other integer 