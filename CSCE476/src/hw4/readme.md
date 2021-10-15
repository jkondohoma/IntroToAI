Jaelle Kondohoma
------------------------------------------
CSCE 476 HW 4
------------------------------------------
Problem 1-3:  answered in Homework4.pdf
------------------------------------------
Problem 4:  Romanian Holidays
------------------------------------------


Data structures: 
------------------------------------------
* City.java: represents a city on the romanian map. Has the information about the distances between two cities linked by a road as well as the distance from any given city to Bucharest

Task 4.1.1 (RomanianHolidayTest.java)
--------------------------------------------
I created a function that automates the testing of functionality of tasks 1-7. The function takes in an array list of city names, array list of city structures (objects), index of a city in the list, index of another city in the, and distance value in km list (this second city and distance value is used for task 7). 
* If you want to run all 7 tasks at once you can do so my using *tasks.testFunctions(cities, cityObjects, cityOneIndex,cityTwoIndex, km) [line 37 in Problem4.java]*

* if you want to run one function at a time you can comment out line 37 in *Problem4.java* and call each individual function using functions in *RomanianHolidayUtilities.functionName()* where function name is the name of the required function as stated in the assignment handout.

Functions used for task 4.1.1 (RomanianHolidayUtils.java):
--------------------------------------------
* *allCitiesFromList()*: returns a list of all names of cities on the map, useing *allCitiesList*

* *allCitiesFromHtable()*:  returns a list of all the structures of cities on the map, using  *allCitiesHtable*

* *getCityFromHtable(name)*: take the name of a city as input and return the corresponding structure, using *allCitiesHtable*
    * *name* is case sensistive and must be in the same way that it is in  *allCitiesHtable*

* *getCityFromList(name)*:take the name of a city as input and return the corresponding structure, using *allCitiesList*

    * *name* is case sensistive and must be in the same way that it is in  *allCitiesList*

 * *neighborsUsingHtable(name)*: take the name of a city as input and return the list of structures of its direct neighbors, using *getCityFromHtable()*
    * *name* is case sensistive and must be in the same way that it is in  *allCitiesHtable*


* *neighborsUsingList(name)*: take the name of a city as input and return the list of structures of its direct neighbors, using *getCityFromList()*
    * *name* is case sensistive and must be in the same way that it is in  *allCitiesList*    


 * *neighborsWithinD(myCity,distance)*: returns, for all direct neighbors within distance from myCityusing *allCitiesHtable*
    * *name* is case sensistive and must be in the same way that it is in  *allCitiesHtable*

 * *neighborsP(cityOne,cityTwo)*: returns the distance between two cities if they are directly connected or nil if they are not,using *allCitiesHtable*
    * *cityOne* and *cityTwo* are case sensistive and must be in the same way that it is in  *allCitiesHtable*

Steps required to compile and run the program (Problem4.java):
------------------------------------------------------------
Main function is located in Problem1.java
To run on command line:
* while in folder jkondohoma_HW4, compile using "javac Problem4.java"
* run the program using "java Problem4"

    Implementing Search:
    ------------------------------------------------------------

    after running the program with *java Problem4" results for all 3 searches are displayed on the console and outputed to a csv file

    * bestFirstAstarGraphSearch.csv
    * bestFirstAstarTreeSearch.csv
    * greedyBestFirstGraphSearch.csv
    * greedyBestFirstTreeSearch.csv
    * uniformCostGraphSearch.csv
    * uniformCostTreeSearch.csv

Note: if the program is run on cse server the folowing notes will appear the first time you run *javac Problem4.java*

*   ./RomanianHolidayUtilities.java uses unchecked or unsafe operations.
*  Recompile with -Xlint:unchecked for details.

this is because of the way I create hasmaps before storing values, it does not affect the program in any way, running "java Problem4" should still give you an output and generate output files

BONUS (Problem5.java):
------------------------------------------------------------
I think I have a solution (displaced tiles) for the bonus but I'm not able to generate random solvable states. Source code for problem 5 is also submitted

* it can be compliled using *javac Problem5.java* and can be run using *java Problem5*