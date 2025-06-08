# Learning Projects

This repository contains several Java mini-projects used to practice data structures, algorithms and simple system design concepts. Each directory is self contained and includes source code and where relevant, unit tests.

## Projects

### Caching
Implementation of a basic in-memory cache that supports different eviction strategies. Both **LRU** and **LFU** implementations are provided along with simple storage and tests.

### ConsistentHashing
A small implementation of the consistent hashing algorithm for evenly distributing keys across nodes. Includes tests verifying node addition and removal.

### IsraeliQueue
A queue implementation that keeps tasks grouped by their type (similar to queues found in some public services in Israel). Demonstrates custom queue behaviour.

### PersistentArray
An example of a persistent array data structure that stores previous versions of the array whenever it is updated.

### RateLimiter
A sliding window rate limiter. Service configuration is loaded from `RateLimiter/config.json` using Gson. Includes a JUnit test.

### Splitwise
Utility for splitting expenses among a group so everyone pays an equal share. Comes with unit tests covering several scenarios.

## Running the tests

The `lib` directory contains the required JAR files (JUnit and Gson). Compile the sources and run the tests using the following commands:

```bash
# Compile all source files
find . -name '*.java' > sources.txt
javac -cp 'lib/*' @sources.txt

# Execute the tests
java -cp 'lib/junit-platform-console-standalone-1.11.3.jar:lib/*:.' \
  org.junit.platform.console.ConsoleLauncher -scan-class-path
```


