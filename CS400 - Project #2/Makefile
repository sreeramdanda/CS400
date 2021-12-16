testData: Word.class DictionaryDataReader.class DataWranglerTests.class
	java -jar junit5.jar -cp . --scan-classpath
Word.class: Word.java
	javac Word.java
DictionaryDataReader.class: DictionaryDataReader.java
	javac DictionaryDataReader.java
DataWranglerTests.class: DataWranglerTests.java
	javac -cp .:junit5.jar DataWranglerTests.java
testBackend: Backend.class BackEndDeveloperTests.class BackendInterface.class
	java -jar junit5.jar -cp . --scan-classpath
Backend.class: Backend.java SortedCollectionInterface.class
	javac Backend.java
BackEndDeveloperTests.class: BackEndDeveloperTests.java
	javac -cp .:junit5.jar BackEndDeveloperTests.java
BackendInterface.class: BackendInterface.java
	javac BackendInterface.java
test: testData testBack testFront testTree
testFrontend: Frontend.class FrontendTests.class
	java -jar junit5.jar -cp . --scan-classpath
Frontend.class: Frontend.java
	javac Frontend.java
FrontendTests.class: FrontendTests.java
	javac -cp .:junit5.jar FrontendTests.java
SortedCollectionInterface.class: SortedCollectionInterface.java
	javac SortedCollectionInterface.java
clean:
	rm *.class
TestRBTree.class: TestRBTree.java
	javac -cp .:junit5.jar TestRBTree.java
testTree: TestRBTree.class RedBlackTree.class
	java -jar junit5.jar -cp . --scan-classpath
RedBlackTree.class: RedBlackTree.java
	javac RedBlackTree.java
run: compile
	java Frontend
compile: Backend.class BackendInterface.class DictionaryDataReader.class Frontend.class RedBlackTree.class SortedCollectionInterface.class Word.class
	@echo "Compile Command Finished"
default: run
