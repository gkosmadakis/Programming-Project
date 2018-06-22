# Programming-Project

This is a programming project completed for the course Java Programming in my Master's degree in the University.

It is a program that maintains referees and the user can allocate referees to specific areas. 

In detail these are the functionalities:

1) Add a new referee along with his/her qualification, their allocations
to a match, their home location and the location that he/she is willing to travel. The 1 in the referee qualification means that a referee can attend only Junior matches while the 2 means that can attend both Junior and Senior matches.
The NNN, NYY, NNY etc represents the referee willingness to travel in an area match. So if a referee has NNN means that he/she is not willing to travel North, Central,South whereas NNY means that he/she is willing to travel to South but not in North and Central.
YYY means that he/she is willing to travel to North,Central and South, NYY means that he/she is willing to travel to Central and South but not to North.

2) Delete a referee either from those who are loaded or from those you add

3) Search for a referee and their details either from those who are loaded
or from those you add.

4) Allocate one or two referees to a match choosing a level of match, area match and week 

5) The BarChart displays all the referees, loaded and added in corelation with their allocations to a match.

6) The program loads from a file six referees, you can add another six and allocate
them to a match with qualification, home location and location to travel. The program
selects automatically two suitable referees according to their home location, the least match allocations and if they are willing to travel to the match area. The suitable referees are saved in a file called matchAllocs.txt

7) The Save and Exit button saves the current referees added in a file called RefereesOut.txt.
