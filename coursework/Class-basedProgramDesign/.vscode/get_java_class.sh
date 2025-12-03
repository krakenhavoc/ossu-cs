#!/usr/bin/env bash
# Usage: get_java_class.sh /path/to/File.java
file="$1"
if [ -z "$file" ]; then
  echo ""
  exit 1
fi
if [ ! -f "$file" ]; then
  echo ""
  exit 1
fi
# Class name is the file basename without extension
class_name=$(basename "$file" .java)
# Look for a package declaration in the first 50 lines
pkg=$(sed -n '1,50p' "$file" | awk '/^[[:space:]]*package[[:space:]]+/ {gsub(/^[[:space:]]*package[[:space:]]*/,"", $0); gsub(/;[[:space:]]*$/, "", $0); print $0; exit}')
if [ -n "$pkg" ]; then
  echo "$pkg.$class_name"
else
  echo "$class_name"
fi
