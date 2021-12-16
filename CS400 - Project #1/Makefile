default: testMovieDataReader testBackend testFrontend

testMovieDataReader: TestMovieAndMovieDataReader.class
	java TestMovieAndMovieDataReader

testFrontend: TestFrontend.class
	java TestFrontend

testBackend: BackendInterface.class TestBackend.class
	java TestBackend

BackendInterface.class: BackendInterface.java
	javac BackendInterface.java

TestBackend.class: TestBackend.java
	javac TestBackend.java

MovieDataReaderInterface.class: MovieDataReaderInterface.java
	javac MovieDataReaderInterface.java

TestFrontend.class: TestFrontend.java
	javac TestFrontend.java

MovieInterface.class: MovieInterface.java
	javac MovieInterface.java

TestMovieAndMovieDataReader.class: TestMovieAndMovieDataReader.java
	javac TestMovieAndMovieDataReader.java

clean:
	rm *.class
