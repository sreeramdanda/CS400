BackEndDeveloper README for Project One (CS400 @ UW Madison)
========================================================

Name of BackEndDeveloper: Anthony John Segedi
@wisc.edu Email of BackEndDeveloper: segedi@wisc.edu
Group: AB
Team: Blue

Files Written by Me:
--------------------
Backend.java
	Contains the backend object for this project. It is optimized as well
	as I got to, uses a HashTableMap<String, ArrayList<String>>, and
	a runtime complexity of O(N) for updates, and otherwise runs in
	constant time. Updates only occur when structural changes occur
	that require them (in additon to calling the getNumberOfMovies()
	or getThreeMovies(int) methods). ArrayLists were used for sets of
	objects that were unknown in length but not set to change after initialization
	(due to ArrayLists having expensive resizing and deletion, but great access time).
	all other cases utilized LinkedLists to maximize program efficiency (due to 
	inexpensive insertion / deletion and no resizing requirements).

TestBackend.java
	Contains all of my test code for the backend. Utilizes some dummy Movie
	objects that I made (the way I had to go about integrating this when we
	brought our project together is very weird - there are two boolean variables
	in both Backend.java and TestBackend.java that are labeled "USING_DUMMY" that
	control whether to use the dummies or not. Program output is expected only when
	both those variables match (test wise). Normal expected function of Backend.java
	occurs only when the "USING_DUMMY" is set to false).

README_Backend
	This is simply a text file that I kept updated with the latest stuff going on with the
	backend in terms of progress and etc. Part of the communication with my team.

Additional Contributions:
-------------------------
Communcation
Minor Help
Identifying Bugs

Signature:
----------
Anthony John Segedi
