.DEFAULT_GOAL := build-run

setup:
	cd app && ./gradlew wrapper --gradle-version 8.3

clean:
	cd app && ./gradlew clean

build:
	cd app && ./gradlew clean build

install:
	cd app && ./gradlew clean install

installDist:
	cd app && ./gradlew installDist

run-dist:
	cd app && ./build/install/app/bin/app -h

run-app:
	cd app && ./gradlew run --args="-f plain ./src/test/resources/big-file1.json ./src/test/resources/big-file2.json"

run:
	cd app && ./gradlew run --args="-h"

test:
	cd app && ./gradlew test

report:
	cd app && ./gradlew test jacocoTestReport

lint:
	cd app && ./gradlew checkstyleMain checkstyleTest

check-deps:
	cd app && ./gradlew dependencyUpdates -Drevision=release


build-run: build run lint

.PHONY: build
