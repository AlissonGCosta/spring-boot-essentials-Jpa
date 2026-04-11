#!/bin/sh

set -eu

BASE_DIR=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)
WRAPPER_PROPERTIES="$BASE_DIR/.mvn/wrapper/maven-wrapper.properties"

if [ ! -f "$WRAPPER_PROPERTIES" ]; then
  echo "Maven wrapper properties not found: $WRAPPER_PROPERTIES" >&2
  exit 1
fi

DISTRIBUTION_URL=$(grep '^distributionUrl=' "$WRAPPER_PROPERTIES" | sed 's/^distributionUrl=//')

if [ -z "$DISTRIBUTION_URL" ]; then
  echo "distributionUrl not found in $WRAPPER_PROPERTIES" >&2
  exit 1
fi

ZIP_NAME=$(basename "$DISTRIBUTION_URL")
ZIP_PATH="$BASE_DIR/.mvn/wrapper/$ZIP_NAME"
MAVEN_DIR_NAME=${ZIP_NAME%.zip}
MAVEN_DIR_NAME=${MAVEN_DIR_NAME%-bin}
MAVEN_HOME="$BASE_DIR/.mvn/wrapper/$MAVEN_DIR_NAME"

if [ ! -d "$MAVEN_HOME" ]; then
  if [ ! -f "$ZIP_PATH" ]; then
    echo "Downloading Maven distribution... $DISTRIBUTION_URL"
    if command -v curl >/dev/null 2>&1; then
      curl -fsSL "$DISTRIBUTION_URL" -o "$ZIP_PATH"
    elif command -v wget >/dev/null 2>&1; then
      wget -qO "$ZIP_PATH" "$DISTRIBUTION_URL"
    else
      echo "curl or wget is required to download Maven." >&2
      exit 1
    fi
  fi

  echo "Extracting Maven distribution... $ZIP_PATH"
  unzip -q -o "$ZIP_PATH" -d "$BASE_DIR/.mvn/wrapper"
fi

exec "$MAVEN_HOME/bin/mvn" "$@"
