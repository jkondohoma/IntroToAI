Jaelle Kondohoma
CSCE 476 HW 2
Part 1: Allen's time Relations

Data structures: 
------------------------------------------
TimePoint.java, has one data member, which is an integer representing seconds
Interval.java, has data members: beginTime, and endTime which are of type TimePoint

Functions (located in Allen.java):
--------------------------------------------
* End(Interval i): return endTime of interval i
* Start(Interval j): return beginTime of interval j
* Meet(Interval i,Interval j), returns true if End(i) = Start(j)
* Before(Interval i,Interval j), returns true if  End(i) < Start(j)
* After(Interval i,Interval j), returns true if  Before(j,i) is true
* During(Interval i,Interval j), returns true if  Start(j) <= Start(i) AND End(i) <= End(j)
* Overlap(Interval i,Interval j), returns true if  there exists k where During(k,i) is true AND During(k, j) is true
* Equals(Interval i,Interval j), returns true if  Start(i) = Start(j) AND End(i) = End(j)
* Finishes(Interval i,Interval j), returns true if  End(i) = End(j)
* Contains(Interval i,Interval j), returns true if Start(i) < Start(j) AND End(i) > End(j)

steps required to compile and run the program:
---------------------------------------------
Main function is in Allen.java
can be run from the command line using "javac Allen.Java",or can be run in any java IDE such as eclipe or IntelliJ
package name will need to be changed to whichever default package you create if you make a different java project
