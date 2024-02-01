.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.3

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean install

installDist:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app -h

run:
	./gradlew run --args="./src/main/resources/file1.json ./src/main/resources/file2.json"

test:
	./gradlew test

report:
	./gradlew jacocoTestReport

lint:
	./gradlew checkstyleMain

lintTest:
	./gradlew checkstyleTest

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build
