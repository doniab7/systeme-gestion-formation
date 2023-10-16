# Système de Gestion de Formations - Training Management System 


## Project Overview
The Training Management System is a Spring Boot-based application designed to facilitate the management of training programs, trainers, sessions, participants, and other related aspects. This README provides an overview of the project and essential information for developers and users.


## Features

- **User Roles**: Support different user roles, including administrators, trainers, and participants.

- **Manage Formateurs**: Create, update, and delete trainers or formateurs in the system.

- **Session Management**: Schedule and manage training sessions, including session details, dates, and locations.

- **Participant Registration**: Allow participants to register for specific training sessions.

- **Payment Management**: Handle payment processing and management, including fee collection, invoicing, and financial reporting.

- **Training Programs**: Organize and manage training programs or courses with various sessions and formateurs.


## Technologies Used

- **Spring Boot**: Backend framework for building the application.

- **Spring Data JPA**: Simplifies database access and management.

- **Database**: Use a relational database (PostgreSQL) to store application data.

- **RESTful API**: Implement RESTful endpoints for communication with the front end.

- **Maven/Gradle**: Dependency management and build tools.

- **Front-end Framework**: Develop a front-end application (Angular) to interact with the back end (Still in development).


## Getting Started

To get the project up and running on your local machine, follow these steps:

1. **Clone the Repository**:

   ```shell
   https://github.com/doniab7/systeme-gestion-formation.git
   ```

2. **Set Up the Database**:

   - Create a database instance and configure the database connection in the `application.properties` file.

3. **Build and Run the Project**:

   - Use Maven or Gradle to build and run the Spring Boot application.

   ```shell
   mvn spring-boot:run
   ```

4. **Access the Application**:

   - Open a web browser and access the application at `http://localhost:8080` (or the configured port).
  

