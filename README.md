# SpringBootMicroServices-MongoDB

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-7.0-blue)
![Eureka](https://img.shields.io/badge/Eureka-4.1.1-orange)
![Gateway](https://img.shields.io/badge/Gateway-4.1.3-yellow)

SpringBootMicroServices-MongoDB is a comprehensive microservices application built with Spring Boot, MongoDB, and RESTful APIs. It showcases a modular architecture with multiple microservices, each serving specific functionalities and seamlessly integrated with Eureka for service discovery.

## Key Features

- **Modular Microservices:** Designed for scalability and easy maintenance.
- **MongoDB Integration:** Utilizes MongoDB for robust and flexible data storage.
- **RESTful APIs:** Clean and efficient APIs for seamless communication between services.
- **Eureka Service Discovery:** Efficient service registration and discovery using Netflix Eureka.
- **Centralized Logging:** Enhanced logging mechanism for better traceability and debugging.

## Technologies Used

- **Java:** Programming language for building the application.
- **Spring Boot:** Framework for building Java-based microservices.
- **MongoDB:** NoSQL database for data storage.
- **Eureka:** Service discovery and registration.
- **Log4j2/Logback:** Logging framework for monitoring and debugging.
- **REST API:** Communication standard for building and integrating APIs.

## Getting Started

Follow the instructions below to set up and run the application locally.

### Prerequisites

- Java 17+
- MongoDB
- Maven

### Installation

1. **Clone the repository:**
   git clone https://github.com/yourusername/SpringBootMicroServices-MongoDB.git
   cd SpringBootMicroMongo
2. **Start MongoDB:**
Ensure MongoDB is running on your machine. You can start it using the following command if MongoDB is installed:
3. **Configure API Keys:**
Update the dataService file in each microservice with your API keys:
````sh
in the dataService in the ms_nba and ms_population:
private final String RapidAPI_Key = "Your_Rapid_API_Key";

in the dataService in the ms_news:
private String gnewsApiKey = "Your_API_Key";
````
4. **Build and Run the Services in this order:**
- ms_service_discovery
- ms_api_gateway
- ms_nba, ms_news and ms_population

## Usage
This project comprises several microservices that fetch data from external APIs and save it to a MongoDB database. The services are connected through a gateway API that facilitates communication and are registered with the Eureka service discovery.

### Gateway API
The gateway service acts as a single entry point to route requests to the appropriate microservices. It is configured to work with Eureka for service discovery.

### Population Service
Endpoint: /population
Method: GET
Description: Fetches population data from an external API and saves it to MongoDB.

### NBA Service
Endpoint: /nba
Method: GET
Description: Fetches NBA data from an external API and saves it to MongoDB.

### News Service
Endpoint: /news
Method: GET
Description: Fetches news data from an external API and saves it to MongoDB.

### Example Request
````sh
# Fetch population data
curl -X GET http://localhost:8080/population/**

# Fetch NBA data
curl -X GET http://localhost:8080/nba/**

# Fetch news data
curl -X GET http://localhost:8080/news/**
````


## Workflow
1. **Gateway API:** The gateway API receives the request and routes it to the appropriate microservice.
2. **Microservice:** The targeted microservice (Population, NBA, or News) processes the request, fetches data from the external API, and saves it to the MongoDB database.
3. **MongoDB:** The fetched data is stored in MongoDB for persistent storage and future retrieval.

## Architecture Overview
1. **Gateway API:** Handles all incoming requests and routes them to the appropriate microservice.
2. **Eureka Server:** Manages service registration and discovery.
3. **Microservices:** Each microservice handles specific data fetching and processing tasks.
4. **MongoDB:** Centralized database for storing all fetched data.


## Contributing
Contributions are welcome! If you have any suggestions, bug fixes, or enhancements, feel free to open an issue or submit a pull request.
