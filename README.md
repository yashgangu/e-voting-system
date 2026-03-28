# E-Voting System (Spring Boot)

## Project Overview

The E-Voting System is a backend application developed using Spring Boot, Hibernate, and MySQL that enables organizations to conduct online elections efficiently.

This system allows users to:

* Create elections
* Add candidates (election choices)
* Register voters
* Cast votes securely
* View election results including the winning candidate

---

## Tech Stack

* Backend: Spring Boot 3.0.0
* ORM: Hibernate (JPA)
* Database: MySQL
* Language: Java 17
* Build Tool: Maven
* Testing Tool: Postman

---

## Features

### Election Management

* Create new elections
* Retrieve all elections

### Voter Management

* Register users with unique usernames
* Fetch all registered users

### Election Choices

* Add candidates to elections
* Retrieve all election choices
* Count choices for a specific election

### Voting System

* Cast vote for a candidate
* Prevent duplicate voting (one vote per user per election)
* Retrieve all votes
* Count total votes

### Results

* Get total votes per election
* Identify winning candidate for an election

---

## Business Logic

* Each user can vote only once per election

* If a user tries to vote again:

  "You have already given your vote"

* Relationships implemented:

  * Election → ElectionChoice (One-to-Many)
  * Vote → User (One-to-One)
  * Vote → Election (Many-to-One)
  * Vote → ElectionChoice (Many-to-One)

---

## API Endpoints

### Election APIs

| Method | Endpoint       | Description       |
| ------ | -------------- | ----------------- |
| GET    | /get/elections | Get all elections |
| POST   | /add/election  | Create election   |

---

### Election Choice APIs

| Method | Endpoint             | Description                |
| ------ | -------------------- | -------------------------- |
| POST   | /add/electionChoice  | Add candidate              |
| GET    | /get/electionChoices | Get all choices            |
| GET    | /count/{electionId}  | Count choices per election |

---

### User APIs

| Method | Endpoint   | Description   |
| ------ | ---------- | ------------- |
| POST   | /add/user  | Register user |
| GET    | /get/users | Get all users |

---

### Vote APIs

| Method | Endpoint                    | Description       |
| ------ | --------------------------- | ----------------- |
| POST   | /add/vote                   | Cast vote         |
| GET    | /get/votes                  | Get all votes     |
| GET    | /count/votes                | Total votes       |
| GET    | /count/votes/{electionName} | Votes by election |

---

### Result APIs

| Method | Endpoint                        | Description |
| ------ | ------------------------------- | ----------- |
| GET    | /winner/election/{electionName} | Get winner  |

---

## Testing

The application is tested using Postman:

* Election creation
* Adding election choices
* User registration
* Voting validation (no duplicate votes)
* Fetching results and winner

---

## Project Structure

com.codingninjas.EVotingSystem

* controllers
* entities
* repositories
* services
* EVotingSystemApplication.java

---

## Important Notes

* The target folder is excluded from the repository
* Sensitive data like database credentials are not uploaded
* Ensure MySQL is running before starting the application

---

## How to Run

1. Clone the repository:
   git clone https://github.com/your-username/e-voting-system.git

2. Navigate to the project:
   cd e-voting-system

3. Configure database in application.properties

4. Run the project:
   mvn spring-boot:run

---

## Future Improvements

* Add JWT Authentication and Security
* Role-based access (Admin/User)
* UI using React
* Real-time voting updates

---

## Author

Yash Gangurde

---

