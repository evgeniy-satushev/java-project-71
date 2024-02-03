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
	cd app && ./gradlew run --args="-f plain ./src/test/resources/big-file1.json ./src/test/resources/big-file2.json"

test:
	cd app && ./gradlew test

report:
	cd app && ./gradlew test jacocoTestReport

lint:
	./gradlew checkstyleMain

lintTest:
	./gradlew checkstyleTest

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run

.PHONY: build
