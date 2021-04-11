## 15 puzzle game
This game is implemented according to https://en.wikipedia.org/wiki/15_puzzle specification.

## Compile project
We need to execute several simple steps to compile the game:
* get source files from the github
* go to the project directory
* execute command 
```shell
mvn clean package
```
## Run project
* go to 'target' directory and in the project directory and execute
* java 11 should be installed on a computer
```shell
java -jar 15_puzzle-1.0-SNAPSHOT.jar
```
## UI interaction
Swing UI is implemented by default. There are several major abstraction which can be overwritten by other type of interaction 