.DEFAULT_GOAL := build-run

setup:
	./gradlew wrapper --gradle-version 8.3

clean:
	./gradlew clean

build:
	cd app && ./gradlew clean build

install:
	./gradlew clean install

installDist:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app -h

#run:
#	cd app && ./gradlew run --args="-f plain ./src/test/resources/test-file1.json ./src/test/resources/test-file2.json"

run:
	cd app && ./gradlew run --args="-h"

test:
	cd app && ./gradlew test

report:
	cd app && ./gradlew test jacocoTestReport

lint:
	cd app && ./gradlew checkstyleMain checkstyleTest

#lintTest:
#	cd app && ./gradlew checkstyleTest

check-deps:
	./gradlew dependencyUpdates -Drevision=release


build-run: build run lint

.PHONY: build
