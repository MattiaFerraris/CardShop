# CardShop - Collectible Card Marketplace

CardShop is a Spring Boot application designed to manage the buying and selling of collectible game cards. Users can list their cards, provide descriptions, set prices, and upload images.

## Features

- **User Management**: Sign up, login, and role-based access control.
- **Card Listing**: Create, read, update, and delete (CRUD) card listings.
- **Image Upload**: Upload card images directly to the database.
- **Dynamic Marketplace**: View available cards with details like condition, price, and game type.

## Tech Stack

- **Backend**: Java 21, Spring Boot 3.4.4.
- **Persistence**: Spring Data JPA, H2 Database.
- **Security**: Spring Security 6.
- **Frontend**: Thymeleaf, Bootstrap 5.3.3.
- **Build Tool**: Maven.
- **Deployment**: Docker.

## Prerequisites

- Java 21 or higher
- Maven 3.x
- Docker (optional)

## Getting Started

### Running with Maven

1. Clone the repository.
2. Navigate to the project root directory.
3. Run the application:
   ```bash
   mvn spring-boot:run
