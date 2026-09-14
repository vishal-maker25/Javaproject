# Numerical Mastermind

A simple **Numerical Mastermind** game written in Java.

The computer generates a random 4-digit secret number with **no repeated digits**. The player gets up to **12 attempts** to guess the number. After each valid guess, the game shows how many digits are in the correct position and how many correct digits exist in the secret number but are in the wrong position.

## Features

- Generates a random 4-digit secret number.
- Ensures that all four digits in the secret number are unique.
- Validates player guesses.
- Rejects guesses that:
  - Are not exactly 4 digits long.
  - Contain non-digit characters.
  - Contain repeated digits.
- Shows the number of:
  - **Correct positions** — digits that are correct and in the correct position.
  - **Existing digits** — correct digits that are present in the secret number but in the wrong position.
- Stores and displays the **last 6 guesses**.
- Allows a maximum of **12 valid attempts**.
- Reveals the secret number when the game ends.

## Requirements

- Java Development Kit (**JDK 8 or later**)
- A terminal/command prompt or Java-compatible IDE

## How to Run

### 1. Save the code

Save the Java code in a file named:

```text
NumericalMastermind.java
```

### 2. Compile the program

Open a terminal in the directory containing the file and run:

```bash
javac NumericalMastermind.java
```

### 3. Run the program

```bash
java NumericalMastermind
```

## How the Game Works

The program first generates a secret number containing four different digits.

For example:

```text
Secret number: 5821
```

The secret number is not shown to the player.

The player then enters a 4-digit guess.

For example:

```text
Guess 1: 5283
```

The program compares the guess with the secret number.

In this example:

- `5` is in the correct position.
- `2` exists in the secret number but is in the wrong position.
- `8` exists in the secret number but is in the wrong position.
- `3` does not exist.

The result would therefore contain:

```text
position:1 exist:2
```

## Input Rules

A valid guess must:

1. Contain exactly **4 characters**.
2. Contain **digits only**.
3. Have **no repeated digits**.

### Valid input

```text
1234
```

```text
9072
```

### Invalid input

```text
123
```

Reason:

```text
Guess must be 4 digits.
```

```text
1123
```

Reason:

```text
Digits must not repeat.
```

```text
12a4
```

Reason:

```text
Guess must contain digits only.
```

## Guess History

The game keeps track of previous valid guesses.

Only the most recent **6 guesses** are displayed.

Example:

```text
--- Guess History (Last 6) ---
  1234 -> position:1 exist:1
  5678 -> position:0 exist:2
  9012 -> position:2 exist:0
-----------------------------
```

## Program Structure

The program is divided into several methods.

### `g()`

Generates a random 4-digit number with unique digits.

It creates a list containing digits `0` through `9`, shuffles the list, and selects the first four digits.

### `check(String guess)`

Checks whether the player's guess is valid.

It verifies:

- Length
- Whether all characters are digits
- Whether any digit is repeated

The method returns an error message when the input is invalid and `null` when the guess is valid.

### `countKey(String key, String guess)`

Compares the secret number with the player's guess.

It calculates:

- The number of digits in the correct position.
- The number of digits that exist in the secret number but are in the wrong position.

The method returns these values as an integer array.

### `displayHistory(List<HistoryEntry> history)`

Displays the player's previous guesses and their results.

It limits the displayed history to the latest six guesses.

### `HistoryEntry`

A small class used to store information about each guess:

- The guessed number
- Number of correct positions
- Number of existing digits

### `start()`

Controls the main game.

It:

1. Creates the scanner.
2. Generates the secret number.
3. Repeatedly asks the player for guesses.
4. Validates each guess.
5. Checks whether the guess is correct.
6. Calculates the result.
7. Stores the guess in the history.
8. Ends the game after 12 attempts or when the correct number is guessed.

### `main(String[] args)`

The entry point of the Java program. It calls the `start()` method to begin the game.

## Example Output

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
```

If the player guesses the secret number:

```text
Correct! The key was 5821
```

If the player uses all 12 attempts without finding the number:

```text
Game Over!
The key was: 5821
```

## Technologies Used

- **Java**
- `ArrayList`
- `HashSet`
- `HashMap`
- `Collections.shuffle()`
- `Scanner`
- `StringBuilder`

## License

This project is intended for educational and learning purposes.
