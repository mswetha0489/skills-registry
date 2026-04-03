# Skill Registry Microservice

Skill Registry is a Spring Boot microservice used to normalize and resolve skills from different input sources.

It identifies whether the input skill is:
1. Already available in Skill table
2. Present as an Alias of an existing skill
3. Not mapped (stored in UnmappedSkill table)

---

## Tech Stack

- Java 17+
- Spring Boot 3
- Spring Data JPA
- H2 Database
- Maven

---