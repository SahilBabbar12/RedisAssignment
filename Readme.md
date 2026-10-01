# Employee Redis Application

## Overview
This project demonstrates Spring Boot REST APIs, Maven artifact publishing
and consumption using Nexus Repository, database persistence, and Redis caching.

## Architecture

Employee Library
|
| mvn deploy
v
Nexus Repository
|
| Maven dependency
v
Employee Redis Application
|
+---- H2 Database
|
+---- Redis Cache

## Technologies

- Java 21
- Spring Boot
- Maven
- Nexus Repository
- Redis
- H2 Database
- Spring Data JPA
- Spring Cache
- Postman

## Employee Library

The employee-library project contains the reusable Employee model.

Maven coordinates:

com.assignment:employee-library:1.0.0

Build:

mvn clean package

Publish to Nexus:

mvn clean deploy

## Nexus

A Maven hosted repository named `employee-releases` is used to store
the employee-library artifact.

The Employee Redis Application consumes:

com.assignment:employee-library:1.0.0

from Nexus.

## REST APIs

POST   /employees
GET    /employees/{id}
GET    /employees
PUT    /employees/{id}
DELETE /employees/{id}

## Redis Caching

GET /employees/{id}

Uses:

@Cacheable(value = "employees", key = "#id")

The first request reads from the database and stores the result in Redis.
Subsequent requests are returned from Redis.

## Cache Update

PUT /employees/{id}

Uses:

@CachePut(value = "employees", key = "#id")

The database is updated and the latest employee value is stored in Redis.

## Cache Eviction

DELETE /employees/{id}

Uses:

@CacheEvict(value = "employees", key = "#id")

The employee is deleted from the database and removed from Redis.

## TTL

Redis cache entries are configured with a TTL.

After the TTL expires, Redis automatically removes the cached employee.
The next GET request retrieves the employee from the database and caches
it again.

## Running the Application

Start Redis.

Then run:

mvn spring-boot:run

The application runs on:

http://localhost:8080

## Testing

The APIs were tested using Postman.

Redis cache entries were verified using:

redis-cli

Commands used:

KEYS *
TTL employees::1