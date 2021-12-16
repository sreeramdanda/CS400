DataWrangler README for Project One (CS400 @ UW Madison)
========================================================

Name of DataWrangler: Gautama Manja
@wisc.edu Email of DataWrangler: manja@wisc.edu
Group: AB
Team: Blue

Files Written by Me:
--------------------
Movie.java - represents all movie objects that are instantiated by the MovieDataReader class and
	stored in the hash table for further use.
MovieDataReader.java - reads a .csv file and converts the data into a list of Movie objects to be stored
	and manipulated.

Additional Contributions:
-------------------------
N/A

Signature:
----------
Gautama Manja
	- the only code that was not authored by me for this project in the above files is the line 
	"String[] tokens = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);" in MovieDataReader.java 
	This useage of split() with a regex string came from Bart Kiers on StackOverflow at the following url
	https://stackoverflow.com/questions/1757065/java-splitting-a-comma-separated-string-but-ignoring-commas-in-quotes 
	and was pointed to on Piazza @518.
