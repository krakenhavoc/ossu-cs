#!/usr/bin/env bash
# Run all classes in a Java source file whose names start with "Examples"
# Usage: run_all_examples.sh /path/to/SomeFile.java
file="$1"
if [ -z "$file" ]; then
  echo "Usage: $0 /path/to/File.java"
  exit 2
fi
if [ ! -f "$file" ]; then
  echo "File not found: $file"
  exit 2
fi

# compute package (first 200 lines)
pkg=$(sed -n '1,200p' "$file" | awk '/^[[:space:]]*package[[:space:]]+/ {gsub(/^[[:space:]]*package[[:space:]]*/,"", $0); gsub(/;[[:space:]]*$/, "", $0); print $0; exit}')

# collect class names that start with Examples
mapfile -t names < <(grep -E 'class[[:space:]]+Examples[A-Za-z0-9_]*' "$file" | sed -E 's/.*class[[:space:]]+([A-Za-z0-9_]+).*/\1/' )

if [ ${#names[@]} -eq 0 ]; then
  echo "No Examples* classes found in $file"
  exit 0
fi

echo "Found Examples classes: ${names[*]}"

echo "Compiling project..."
find . -name "*.java" > sources.txt
javac -cp "lib/tester.jar:lib/javalib.jar" -d out @sources.txt
if [ $? -ne 0 ]; then
  echo "Compilation failed"
  exit 1
fi

for cls in "${names[@]}"; do
  if [ -n "$pkg" ]; then
    fq="$pkg.$cls"
  else
    fq="$cls"
  fi
  echo "\n--- RUNNING: $fq ---"
  java -Djava.awt.headless=true -cp out:lib/tester.jar:lib/javalib.jar tester.Main "$fq"
  rc=$?
  echo "Exit code: $rc"
done

echo "\nAll done."
