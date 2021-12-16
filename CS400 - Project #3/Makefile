run: compile
	java Frontend
compile: Backend.class CS400Graph.class Connection.class DataReader.class Frontend.class GraphADT.class Intersection.class Path.class
Backend.class: Backend.java
	javac Backend.java
BackendTests.class: BackendTests.java
	javac -cp .:junit5.jar BackendTests.java
CS400Graph.class: CS400Graph.java
	javac CS400Graph.java
Connection.class: Connection.java
	javac Connection.java
DataReader.class: DataReader.java
	javac DataReader.java
DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java
Frontend.class: Frontend.java
	javac Frontend.java
FrontendTest.class: FrontendTest.java
	javac -cp .:junit5.jar FrontendTest.java
GraphADT.class: GraphADT.java
	javac GraphADT.java
Intersection.class: Intersection.java
	javac Intersection.java
Path.class: Path.java
	javac Path.java
testFrontend: FrontendTest.class
	java -jar junit5.jar -cp . --scan-classpath
testBackend: BackendTest.class
	java -jar junit5.jar -cp . --scan-classpath
testData: DataWranglerTests.class
	java -jar junit5.jar -cp . --scan-classpath
test: testCompile
	java -jar junit5.jar -cp . --scan-classpath
testCompile: compile BackendTests.class DataWranglerTests.class FrontendTest.class 
clean:
	rm *.class
