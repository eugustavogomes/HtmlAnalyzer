# HtmlAnalyzer

## How to compile and run

javac HtmlAnalyzer.java

java HtmlAnalyzer <url>

The `<url>` must be the address of a text file containing the HTML, formatted according to the challenge guidelines.

## Output Specification

The program produces only one of these outputs to the console, exactly as requested:
- The deepest text found in the HTML (according to parsing rules).
- The message `malformed HTML` (if invalid structure is detected).
- The message `URL connection error` (if it cannot access/download the URL).

## Strategy Adopted

- Every opening tag increments the depth.
- Every closing tag decrements the depth.
- When text is found at a depth greater than any previously seen, it is stored as the result.
- If a malformed structure is detected (tags not closed correctly or out of order), the program prints `malformed HTML`.
- The output matches the functional requirements exactly, and no extra information is printed.

## Requirements

- No external libraries or frameworks are used.
- Only JDK 17 standard features.

