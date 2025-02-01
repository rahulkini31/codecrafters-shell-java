# Custom Shell - Java
A lightweight implementation of a shell written in Java, allowing execution of both built-in and external commands. This custom shell provides functionalities for command parsing, tokenization, execution, and extensions.
## Features
- **Supports Built-in Commands:**
   - `cd`: Change directory.
   - `pwd`: Print working directory.
   - `echo`: Print arguments to standard output.
   - `exit`: Exit shell with a specified exit code.
   - `type`: Determine if a command is built-in or external.

- **Execute External Commands:**
   - Leverages external executables located on the system's `PATH`.

- **Features Advanced Tokenization:**
   - Parses single/double-quoted tokens and escaped special characters.
   - Supports input/output redirection with append/overwrite modes.

- **Command Factories:** Modular approach to manage both built-in and external commands.
- **Error Handling:** Proper error propagation for commands and token parsing.
- **Auto-completion:** Tracks potential completions for commands.

## Project Structure
The project consists of multiple modularized components to handle shell behavior effectively:
### 1. **Command Execution**
- **Built-In Commands**: Built-in command implementations exist in `io.codecrafters.shell.command` package, e.g., `CdBuiltIn`, `EchoBuiltIn`, `ExitBuiltIn`, etc.
- **External Commands:** ExternalCommandFactory resolves commands found in the system `PATH` and executes them.

#### Key Classes:
- `BuiltInCommandFactory`: Registers and resolves built-in commands.
- `ExternalCommandFactory`: Resolves and executes commands located in external paths.
- `Shell`: Main implementation of the REPL (Read-Eval-Print Loop) behavior. Determines input flow and command execution.

### 2. **Core Parsing**
- Tokenizes shell input and parses it into expressions.
- Handles quoted literals, escape characters, and input redirections.

#### Key Classes:
- `TokenFactory`: Converts character sequences into tokens (`Literal`, `Redirection`).
- `ExpressionFactory`: Produces command or redirection expressions for execution.

### 3. **Executable Expressions**
- The mechanism for executing parsed commands as stream expressions.

#### Key Classes:
- `ExecutableExpression`: Core interface for execution.
- `ExternalCommand`: Wrapper for executing external commands.
- `BuiltInCommandFactory`: Provides executables for built-in command types.

### 4. **Shell Core**
- Represents the central engine driving the shell, including user input parsing and context switching.

#### Key Classes:
- `Core`: Handles user input for command execution or auto-completion.
- `Shell`: Manages command factories, input processing, and execution pipeline.

### 5. **Project Entry**
- `Main`: Entry point for the shell. Sets up directories, input/output streams, and command factory resolution.

## Requirements
To run the project, ensure you have the following:
1. Java 17 or newer installed.
2. A platform that supports external processes (e.g., Unix-based OS for `PATH` resolution).

## Setup & Installation
1. Clone the repository:
``` bash
   git clone https://github.com/rahulkini31/my-own-shell.git
   cd my-own-shell
```
1. Build the project:
``` bash
   ./gradlew build
```
1. Run the shell:
``` bash
   java -cp build/libs/my-own-shell-all.jar io.codecrafters.shell.Main
```
## Usage
### Running Commands
Once the shell is running, you can:
- Execute **built-in commands**:
   - `pwd`, `cd <path>`, `echo <args>`, `exit`, etc.

- Run **external commands**:
   - Example: `ls`, `cat`, `grep`, etc., if present in the system's `PATH`.

### Example Commands:
``` bash
$ pwd
/home/user
$ echo "Hello, Shell!"
Hello, Shell!
$ cd /path/to/directory
$ ls
file1.txt file2.java
```
### Error Handling:
- `cd <invalid_directory>`: Outputs an error message if the directory does not exist.

## Key Technology & Design Pattern
- **Java**: Language used for the shell implementation.
- **Sealed Classes**: Used for core concepts like `Token`, `CommandType`, and `Expression`.
- **Stream Processing**: Processes data streams between commands efficiently.
- **Immutable Objects**: Majority of core components use immutability for thread safety.

## Contributing
If you'd like to contribute:
1. Fork the repository.
2. Create a feature branch with your changes.
3. Submit a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
## Acknowledgement
The project concept and structure were inspired by challenges and exercises provided by [Codecrafters](https://codecrafters.io), which motivated the development of this shell.
