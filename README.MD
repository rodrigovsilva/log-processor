
# Log Processor
Technical challenge to demonstrate an extraction, processing and analysis of log data.

## Getting Started

These instructions will get you a copy of the project to prepare the compiled jar using Ant build.

### Prerequisites

You will need to install Ant 1.9 and Java 8 to build and run this project.

- Ant installation: https://ant.apache.org/manual/install.html
- Java 8 installation: http://www.oracle.com/technetwork/pt/java/javase/downloads/jre8-downloads-2133155.html

### Installing

After install Ant and Java on your machine, you will need to execute the steps below:

 1. Clone this project:
 ```
git clone https://github.com/rodrigovsilva/log-processor.git 
  ```
 2. Build the project using ant, executing this command inside build.xml directory:
```
ant
```
 3. Run the program with 2 arguments:
```
java -jar ./dist/log-processor.jar <log_file_name> <number_of_top_requests>
```
 4. Show the program's help:

```
java -jar ./dist/log-processor.jar -h
```
