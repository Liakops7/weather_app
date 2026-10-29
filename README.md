# 🌤️ Weather App

A Spring Boot REST API that fetches hourly weather forecasts from the [Open-Meteo API](https://open-meteo.com/) and maps the JSON response to Java objects.

This is my first Spring Boot project, built step by step to learn backend development with Java.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 4** (Spring Web, Thymeleaf, DevTools)
- **Maven**
- **Jackson** for JSON serialization/deserialization
- **Open-Meteo API** (free, no API key required)

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/Liakops7/weather_app.git
   cd weather_app
   ```
2. Run the application with the Maven Wrapper (no Maven installation needed):
   ```bash
   # Windows
   mvnw.cmd spring-boot:run

   # Linux / macOS
   ./mvnw spring-boot:run
   ```
3. Open your browser at `http://localhost:8080`

## 📡 API Endpoints

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | `/hello` | Test endpoint | `/hello` |
| GET | `/greet` | Greets the user (optional `name` parameter) | `/greet?name=Ilias` |
| GET | `/weather` | 10-day hourly forecast for Athens | `/weather` |

### Example response from `/weather`

```json
{
  "latitude": 37.97,
  "longitude": 23.73,
  "timezone": "Europe/Athens",
  "hourly": {
    "time": ["2026-10-29T00:00", "2026-10-29T01:00"],
    "rain": [0.0, 0.0],
    "temperature_2m": [16.2, 15.8],
    "wind_speed_10m": [8.1, 7.6],
    "wind_direction_10m": [315, 320],
    "relative_humidity_2m": [68, 70],
    "wind_gusts_10m": [14.2, 13.5]
  }
}
```

Weather data includes temperature (°C), rain (mm), wind speed and gusts (knots), wind direction (°) and relative humidity (%), using the ECMWF IFS forecast model.

## 📁 Project Structure

```
src/main/java/com/liakops/weather_app/
├── WeatherAppApplication.java    # Application entry point
├── controller/
│   ├── HelloController.java      # Test endpoints (/hello, /greet)
│   └── WeatherController.java    # Weather endpoint (/weather)
└── model/
    ├── WeatherResponse.java      # Maps the full Open-Meteo JSON response
    └── HourlyData.java           # Maps the "hourly" forecast data
```

## 📚 Development Log

### Step 1 – Setup
Installed the JDK and IntelliJ IDEA.

### Step 2 – Project creation
Generated the project with [Spring Initializr](https://start.spring.io/) using Maven and the Spring Web, Thymeleaf and DevTools dependencies. Learned the standard project structure (`pom.xml`, `src/main/java`, `src/main/resources`, `application.properties`).

### Step 3 – First controller
Created `HelloController` with `@RestController` and `@GetMapping`. Used `@RequestParam` with a `defaultValue` to handle optional URL parameters, and learned about HTTP status codes (200, 400, 404, 500).

### Step 4 – Version control
Set up Git and published the project to GitHub. Learned the commit and push workflow and how `.gitignore` keeps build files out of the repository.

### Step 5 – Calling an external API
Created `WeatherController`, which uses Spring's `RestClient` to call the Open-Meteo API. The `RestClient` is a class field so that it is created once and reused across all requests.

### Step 6 – Mapping JSON to Java objects
Created Java records (`WeatherResponse`, `HourlyData`) that mirror the JSON structure, so Jackson converts the response into typed objects automatically. Used `@JsonProperty` to map snake_case JSON keys to camelCase Java fields, and `LocalDateTime` from the `java.time` API for timestamps.

## 🗺️ Roadmap

- [ ] Service layer and dependency injection
- [ ] Web UI with Thymeleaf
- [ ] Search weather by city name (Open-Meteo Geocoding API)
- [ ] Error handling for API failures
- [ ] Store forecast history in PostgreSQL

## 👤 Author

**Ilias Liakopoulos**, Computer Science graduate, Athens University of Economics and Business
GitHub: [@Liakops7](https://github.com/Liakops7)