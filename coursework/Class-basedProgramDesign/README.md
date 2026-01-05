Class-basedProgramDesign — quick run guide

**Overview**
This folder contains small Java example exercises for the Class-based Program Design course. The workspace includes helper tasks and launch configurations so you can compile and run tests locally without needing the full Java extension pack.

**Prerequisites**
- JDK 11 or later installed and `javac`/`java` on PATH.
- `tester.jar` and `javalib.jar` placed in `lib/` (see below).

**Where to put the JARs**
- Create a `lib/` folder at the project root (this repository folder):

  ```bash
  mkdir -p lib
  cp ~/Downloads/tester.jar lib/
  cp ~/Downloads/javalib.jar lib/
  ls -l lib
  ```

**Compile (terminal)**
- From the project root run:

  ```bash
  find . -name "*.java" > sources.txt
  javac -cp "lib/tester.jar:lib/javalib.jar" -d out @sources.txt
  ```

- This places `.class` files under `out/` with package directories preserved.

**Run tests (terminal)**
- To run the Tester on a specific Examples class:

  ```bash
  java -Djava.awt.headless=true -cp out:lib/tester.jar:lib/javalib.jar tester.Main Week_2.Book.ExamplesBooks
  ```

- Replace `Week_2.Book.ExamplesBooks` with the fully-qualified Examples class you want to run.

**VS Code: Tasks and Launch Configurations**
- Tasks are defined in `.vscode/tasks.json`:
  - `compile Class-basedProgramDesign` — compiles all `.java` into `out/` (uses `lib/tester.jar:lib/javalib.jar` on the javac classpath).
  - `compile and run (prompt)` — compiles and then prompts for a class to run.
  - `run tester Examples (prompt)` — runs `tester.Main` and prompts for the Examples class.

- Launch configs are in `.vscode/launch.json` (shell-based, do not require the Java extension):
  - `Shell: Run Examples (prompt)` — prompts for Examples class (default is current file basename) and runs it.
  - `Shell: Run Current Java (auto)` — detects the package and class name from the active editor file, compiles, and runs the Tester on that class.

How to use (VS Code)
- Open the Run view (Run and Debug).
- Choose `Shell: Run Current Java (auto)` to run tests for the active editor file.
- Or choose `Shell: Run Examples (prompt)` to enter the class name manually (the prompt defaults to the current file's base name).

**Auto-detection behavior**
- The helper script `.vscode/get_java_class.sh` reads the active file, extracts a `package` declaration (if present) and concatenates it with the file's class name. That computed fully-qualified class is passed to `tester.Main`.
- If there is no package declaration, the script uses the class name only.

**Troubleshooting**
- If the editor flags `import tester.*` as unresolved:
  - Make sure `.vscode/settings.json` references `lib/**/*.jar` OR open the project root as your workspace.
  - Command Palette → Developer: Reload Window.
  - Command Palette → Java: Clean the Java language server workspace (if you have Java extensions installed).

- If the debugger says `Could not find or load main class tester.Main`:
  - Ensure `lib/tester.jar` exists and `out/` contains compiled classes.
  - Run the same `java -Djava.awt.headless=true -cp out:lib/tester.jar:lib/javalib.jar tester.Main <ExamplesClass>` command in the terminal — this is the exact command the tasks/launchers use.

**Quick commands**
- Compile all: `./.vscode/tasks.json` → Run Task → `compile Class-basedProgramDesign` or run the `javac` command above.
- Compile + Run current file: Run → `Shell: Run Current Java (auto)`
