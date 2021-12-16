IntegrationManager README for Project Three(CS400 @ UW Madison)
==============================================================

Name of IntegrationManager: Anthony Segedi
@wisc.edu Email of IntegrationManager: segedi@wisc.edu
Group: AB
Team: Blue

Complete List of Files:
-----------------------
>> Gautama Manja:Frontend Developer~
Frontend.java - Gautama Manja
FrontendTest.java - Gautama Manja
FrontEndDeveloper_README.txt - Gautama Manja

>> Nijae King:Backend Developer~
Backend.java - Nijae King
BackendTests.java - Nijae King
BackEndDeveloper_README.txt - Nijae King

>> Sreeram Danda:DataWrangler~
Connection.java - Sreeram Danda
DataReader.java - Sreeram Danda
Intersection.java - Sreeram Danda
Path.java - Sreeram Danda
DataWranglerTests.java - Sreeram Danda
DataWrangler_README.txt - Sreeram Danda
distances.csv - Sreeram Danda (w/ Anthony Reis: Red team)
pointOfInterests.csv - Sreeram Danda (w/ Anthony Reis: Red team)

>> Anthony Segedi:Integration Manager~
CS400Graph.java - Anthony Segedi
Makefile - Anthony Segedi

Instructions to Build, Run and Test your Project:
-------------------------------------------------
>> Requirements for full functionality~
In order to run the program, make sure that all files listed above are included (README files can be ignored)
Additionally make sure the following files are in the same directory:
junit5.jar - if testing

>> Running the program~
Simply use the default make command,
"make", or "make run" to start the program.
While running the program, you will get the most out of it if after starting the program
the first mode you visit is "overview mode", which can be accessed by using the "o" command.
After that use the other modes for what you want.

>> Testing the program~
Use "make test" to test the entire program. This tests all test files, although files
can be tested for individually if you use the make "testBackend", "testFrontend", and "testData" commands
separately. Note that junit will by default print all compiled files, so if sole output is
wanted from a single file, you should use the "make clean" command to wipe out all compiled
java class files, and then run the specific test wanted. "make clean" followed by "make testBackend"
will only print the Backend tests etc. I don't know why that would be wanted, but there you go.

Team Member Contributions:
--------------------------
I think that the guys did a great job handling the project, even though it was tougher to meet deadlines this
time around given Easter and taking time off.

I would like to commemerate Nijae for reaching out to TAs for his grade since I knew it was not reflective of his
efforts. That is something I tend to not do either - he did a great job advocating for himself.

Additionally I would like to commemorate Gautama Manja for taking responsibility of a mistake in the Makefile
of our last project, which called the wrong test file on Nijae's behalf. He did not blame anyone else - he handled it very well.

Finally, I would like to mention Sreeram Danda, who worked extensively with red team's data wrangler, Anthony Reis, 
in mapping out an arbitrary localized map of Madison using Google Maps and a print out that I gave the outline for. 
There is no need to boast about the amount of work he put in; it is evidenced from his contributions listed above.
On top of that, he finished the most extensive and essential classes about halfway through our alloted time, which
was crucial for the rest of our team members to develop with. Unlike previous data wranglers (myself included), 
Sreeram's (and Anthony's) roles were far harder since they needed to not only read from files to allow our program to
work, they needed to create, by hand, the data itself; data that was not made up. They did a great job and it turned out fantastic.

Signature:
----------
Anthony John Segedi
