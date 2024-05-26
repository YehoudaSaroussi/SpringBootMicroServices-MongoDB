# NBA Data Fetcher with Spring Data and Spring Boot

This project is a Java application developed using Spring Data and Spring Boot to fetch data from the NBA Rapid API, convert it into Java objects, and store it in a MongoDB database. It provides functionality to retrieve data related to games, teams, players, game statistics, player statistics, and team statistics.

## Features
- Fetches data from the NBA Rapid API
- Converts API responses into Java objects
- Stores the fetched data in a MongoDB database
- Provides endpoints to retrieve and manipulate data using HTTP requests

## Technologies Used
- Java
- Spring Boot
- Spring Data MongoDB
- MongoDB
- NBA Rapid API

## Getting Started
To run this project locally, follow these steps:

1. Clone the repository to your local machine.
2. Make sure you have Java and MongoDB installed on your system.
3. Configure your MongoDB connection settings in the application.properties file.
4. Build the project using Maven or your preferred build tool.
5. Run the application:
Copy code
git clone https://github.com/yourusername/fetchApiToMongodbWithSpringData.git
cd fetchApiToMongodbWithSpringData
mvn clean install
mvn spring-boot:run

## Configuration
In the DataService class, set your RapidAPI credentials:
```sh
in DataService class:
private final String RapidAPI_Key = "YOUR_RAPIDAPI_KEY";
private final String RapidAPI_Host = "YOUR_RAPIDAPI_HOST";
```

## Usage
Once the application is up and running, you can use the following endpoints to interact with the data:

/games: Retrieve information about NBA games.
/teams: Retrieve information about NBA teams.
/players: Retrieve information about NBA players.
/games/statistics: Retrieve statistics for NBA games.
/players/statistics: Retrieve statistics for NBA players.
/teams/statistics: Retrieve statistics for NBA teams.

You can also explore the source code to understand how the data fetching, conversion, and storage processes are implemented: [more about the NBA rapid api](https://rapidapi.com/api-sports/api/api-nba/)


## Contributing
Contributions are welcome! If you have any suggestions, bug fixes, or enhancements, feel free to open an issue or submit a pull request.
