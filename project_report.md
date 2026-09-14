# Project Report: Numerical Mastermind

## 1. Introduction

Numerical Mastermind is a console-based guessing game developed using Java. The program generates a random four-digit secret number in which no digit is repeated. The player has a maximum of 12 attempts to identify the secret number.

After every valid guess, the program provides feedback about the guess. It tells the player how many digits are in their correct positions and how many correct digits are present in the secret number but placed in the wrong positions.

The project demonstrates fundamental Java programming concepts such as methods, loops, conditional statements, collections, classes, objects, strings, input handling, and randomization.

## 2. Objectives

The main objectives of this project are:

- To develop a simple interactive game using Java.
- To generate a random four-digit number with unique digits.
- To validate user input.
- To compare the user's guess with the generated secret number.
- To provide useful feedback after every valid guess.
- To maintain a history of previous guesses.
- To practice the use of Java Collections Framework classes.
- To apply object-oriented programming concepts in a small project.

## 3. Problem Statement

The objective of the game is to allow a player to guess a randomly generated four-digit number.

The secret number must contain four different digits. The player enters a four-digit guess, which is checked for validity before being compared with the secret number.

The program provides two types of feedback:

1. **Position** – the number of digits that match the secret number and are in the correct position.
2. **Exist** – the number of digits that occur in the secret number but are located in a different position.

The player wins if the complete four-digit secret number is guessed within 12 valid attempts.

## 4. Game Rules

The game follows these rules:

- The secret number contains exactly four digits.
- No digit is repeated in the secret number.
- The player's guess must contain exactly four characters.
- The guess must contain digits only.
- The guess must not contain repeated digits.
- The player has up to 12 attempts.
- The last six valid guesses are displayed as history.
- The game ends immediately when the correct number is guessed.

## 5. Technologies Used

### Programming Language

**Java**

### Java Features and Libraries

The program uses several classes and interfaces from the Java Standard Library:

- `Scanner` – for reading user input.
- `ArrayList` – for storing digits and guess history.
- `HashSet` – for checking whether digits are unique.
- `HashMap` – for calculating digit frequencies.
- `Collections.shuffle()` – for randomly arranging the digits.
- `StringBuilder` – for constructing the secret number.
- `List`, `Set`, and `Map` – Java Collection Framework interfaces.

The program imports these classes using:

```java
import java.util.*;
```

## 6. Program Structure

The project contains one main Java class:

```text
NumericalMastermind
```

The class contains the following major methods and inner class:

- `g()`
- `check()`
- `countKey()`
- `displayHistory()`
- `HistoryEntry`
- `start()`
- `main()`

## 7. Method Descriptions

### 7.1 `g()`

The `g()` method generates the secret four-digit number.

First, the program creates a list containing the characters from `0` to `9`.

```java
for (char c = '0'; c <= '9'; c++) {
    digits.add(c);
}
```

The list is then randomly shuffled:

```java
Collections.shuffle(digits);
```

The first four digits are selected and added to a `StringBuilder`.

Since the digits are selected from a shuffled list without replacement, the generated number does not contain repeated digits.

### 7.2 `check(String guess)`

The `check()` method validates the player's input.

It performs three checks.

#### Length Check

The guess must contain exactly four characters.

```java
if (guess.length() != 4) {
    return "Guess must be 4 digits.";
}
```

#### Digit Check

Every character must be a digit.

```java
if (!Character.isDigit(guess.charAt(i))) {
    return "Guess must contain digits only.";
}
```

#### Repeated Digit Check

A `HashSet` is used to store the digits.

```java
Set<Character> uniqueDigits = new HashSet<>();
```

Because a set cannot contain duplicate elements, the size of the set can be used to determine whether the guess contains repeated digits.

```java
if (uniqueDigits.size() != 4) {
    return "Digits must not repeat.";
}
```

If all checks pass, the method returns `null`.

## 8. Guess Evaluation

### 8.1 `countKey(String key, String guess)`

The `countKey()` method compares the secret number with the player's guess.

The method first counts digits that appear in exactly the same position.

```java
for (int i = 0; i < 4; i++) {
    if (key.charAt(i) == guess.charAt(i)) {
        positions++;
    }
}
```

The method then creates two frequency maps:

```java
Map<Character, Integer> keyFreq = new HashMap<>();
Map<Character, Integer> guessFreq = new HashMap<>();
```

These maps contain the number of occurrences of each digit in the secret number and the guess.

The total number of matching digits is calculated using:

```java
totalMatches += Math.min(keyCount, guessCount);
```

Finally, digits that are present but in the wrong position are calculated using:

```java
int exist = totalMatches - positions;
```

The method returns:

```java
return new int[]{positions, exist};
```

The first value represents correct positions, while the second represents correct digits in incorrect positions.

## 9. Guess History

The program maintains a history of valid guesses using:

```java
List<HistoryEntry> history = new ArrayList<>();
```

Each guess is represented by a `HistoryEntry` object.

A `HistoryEntry` contains:

```java
String guess;
int pos;
int exist;
```

These represent:

- `guess` – the player's guess.
- `pos` – number of correct positions.
- `exist` – number of correct digits in incorrect positions.

The program stores each result using:

```java
history.add(new HistoryEntry(guess, pos, exist));
```

## 10. Displaying History

The `displayHistory()` method displays the most recent six valid guesses.

The starting position is calculated using:

```java
int start = Math.max(0, history.size() - 6);
```

This ensures that if there are more than six guesses, only the latest six are displayed.

For example:

```text
--- Guess History (Last 6) ---
  1234 -> position:1 exist:1
  5678 -> position:0 exist:2
  9012 -> position:2 exist:0
-----------------------------
```

## 11. Main Game Logic

The `start()` method controls the complete game.

First, a `Scanner` object is created:

```java
Scanner scanner = new Scanner(System.in);
```

The secret number is generated:

```java
String key = g();
```

An empty history list is created:

```java
List<HistoryEntry> history = new ArrayList<>();
```

The program then starts a loop that allows up to 12 attempts:

```java
for (int attempt = 1; attempt <= 12; attempt++)
```

For each attempt:

1. Previous guess history is displayed.
2. The player enters a guess.
3. The guess is validated.
4. If invalid, an error message is displayed.
5. If the guess matches the secret number, the player wins.
6. Otherwise, the guess is evaluated.
7. The result is added to the history.
8. The next attempt begins.

## 12. Handling Invalid Input

The program checks the guess before processing it.

```java
String err = check(guess);

if (err != null) {
    System.out.println(err);
    continue;
}
```

The `continue` statement causes the current iteration to end and allows the player to enter another guess.

Therefore, invalid guesses do not get added to the guess history.

## 13. Winning Condition

The player's guess is compared directly with the secret number:

```java
if (guess.equals(key)) {
    System.out.println("\nCorrect! The key was " + key);
    scanner.close();
    return;
}
```

If the two strings are equal, the player wins and the game terminates.

## 14. Game Over Condition

If the player fails to guess the number within 12 valid attempts, the loop ends.

The program then displays:

```text
Game Over!
The key was: XXXX
```

where `XXXX` is the generated secret number.

## 15. Example Execution

A possible execution of the program could look like:

```text
Numerical Mastermind (4 digits, no repeats)

--- Guess History (Last 6) ---
No guesses yet.
-----------------------------

Guess 1: 1234

--- Guess History (Last 6) ---
  1234 -> position:1 exist:1
-----------------------------

Guess 2: 5678

--- Guess History (Last 6) ---
  1234 -> position:1 exist:1
  5678 -> position:0 exist:2
-----------------------------

Guess 3: 5821

Correct! The key was 5821
```

The exact secret number and feedback will vary because the secret number is generated randomly.

## 16. Object-Oriented Concepts Used

The project uses basic object-oriented programming concepts.

### Class

The main program is organized inside the:

```java
public class NumericalMastermind
```

The program also contains an inner `HistoryEntry` class.

### Object

Each guess stored in the history is represented by a `HistoryEntry` object:

```java
new HistoryEntry(guess, pos, exist)
```

### Encapsulation

The data associated with a guess is grouped inside the `HistoryEntry` class.

### Methods

Different operations are separated into methods such as `g()`, `check()`, `countKey()`, and `displayHistory()`.

This makes the program easier to understand and maintain.

## 17. Advantages

The program has several advantages:

- Simple console-based interface.
- Easy-to-understand game rules.
- Random secret number generation.
- Input validation prevents invalid guesses.
- Unique digits make the game more challenging.
- Guess history helps the player remember previous results.
- Code is divided into separate methods.
- Uses standard Java libraries without external dependencies.

## 18. Limitations

The current implementation has some limitations:

- It supports only one player.
- The game runs only in the console.
- The number of digits is fixed at four.
- The maximum number of attempts is fixed at twelve.
- There is no difficulty selection.
- There is no score or leaderboard system.
- The game does not save results after the program terminates.
- The generated number can begin with `0`, since the secret is represented as a string rather than an integer.

## 19. Possible Future Improvements

The project could be extended with additional features such as:

- Difficulty levels with different numbers of digits and attempts.
- A scoring system based on the number of attempts.
- Multiple rounds.
- A leaderboard.
- Replay functionality.
- A graphical user interface.
- Timer-based gameplay.
- Player names and stored statistics.
- Improved feedback using terms such as "correct position" and "correct digit."
- Configurable game settings.
- Saving game results to a file.

## 20. Conclusion

Numerical Mastermind is a simple Java project that demonstrates how basic programming concepts can be combined to create an interactive game.

The project makes use of randomization, strings, loops, conditional statements, input validation, collections, methods, and classes. The use of `ArrayList`, `HashSet`, and `HashMap` also provides practical experience with the Java Collections Framework.

Although the current version is a basic console game, it provides a good foundation for future improvements such as scoring, difficulty levels, multiple rounds, persistent statistics, and a graphical interface.

Overall, the project demonstrates the practical application of fundamental Java programming concepts in a small but complete software project.
