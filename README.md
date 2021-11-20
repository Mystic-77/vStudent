# vStudent - API

An API for a student portal application made for project component of Object Oriented Software Development Course.



### Requirements

1. Have [maven](https://maven.apache.org/download.cgi) installed and `mvn` added to path.
2. Have an instance of [mysql server](https://www.mysql.com/downloads/), prefereably running on `localhost:3306`



### Running the code

1. Import the project from github
2.  Open the **application.properties** file and modify the database user, password and url as needed.
3. In the project directory, open terminal and run the command `mvn clean package`
4. goto the target directory which must now contain a JAR file. 
5. Run the jar file using `java -jar filename.jar`
6. Open `localhost:8080/swagger-ui/index.html#/` for an api doc, and `localhost:8080/v2/api-docs` for a json version of docs. 
