# Snowflake ID Generator

This project implements a **Snowflake ID** generator in Java. Snowflake IDs are unique 64-bit identifiers, often used in distributed systems. The structure includes time-based uniqueness, a worker ID, and sequence numbers.

## Features
- customisable bit configuration
- 64-bit unique ID generation
- Time-stamp based to ensure ID uniqueness across distributed environments
- Configurable worker and data center IDs

## Getting Started

### Prerequisites

- JDK 11 or later
- Maven for project management

### import the Project

   ```xml
    <dependencies>
        ...
    	<dependency>
			<groupId>com.github.alquama00s</groupId>
			<artifactId>snowflakeId</artifactId>
			<version>main-SNAPSHOT</version>
		</dependency>
    </dependencies>

    <repositories>
        ...
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
   ```
