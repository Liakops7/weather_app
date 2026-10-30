# 🌤️ Weather App

A Spring Boot web application that lets you search any city and see its current weather, using the free [Open-Meteo API](https://open-meteo.com/).

The backend exposes a REST API that turns a city name into coordinates (Geocoding API), fetches a 10-day hourly forecast (Forecast API) and maps the JSON response to typed Java objects. A lightweight HTML + JavaScript page calls this API and displays the weather for the current hour.

This is my first Spring Boot project, built step by step to learn backend development with Java.

## ⚙️ How It Works

```
┌──────────────┐  GET /weather?city=Πάτρα   ┌──────────────────────────────────────────┐
│   Browser    │ ─────────────────────────► │              Spring Boot                 │
│ (index.html) │                            │                                          │
│              │ ◄───────────────────────── │  WeatherController  (HTTP layer)         │
└──────────────┘          JSON              │          │                               │
                                            │          ▼                               │
                                            │  WeatherService     (business logic)     │
                                            └──────────┬───────────────────────────────┘
                                                       │
                          1. "Πάτρα" → coordinates     │     2. coordinates → forecast
                       ┌───────────────────────────────┴───────────────────────────┐
                       ▼                                                           ▼
          Open-Meteo Geocoding API                                    Open-Meteo Forecast API
```

## 🛠️ Tech Stack

### Backend
| Tool | Purpose |
|------|---------|
| **Java 17** | Programming language |
| **Spring Boot 4** | Application framework (embedded Tomcat server, auto-configuration) |
| **Spring Web MVC** | REST controllers (`@RestController`, `@GetMapping`, `@RequestParam`) |
| **Spring `RestClient`** | HTTP client for calling the external Open-Meteo APIs |
| **Jackson** | JSON ↔ Java object conversion (serialization / deserialization) |
| **Java Records** | Immutable data models that mirror the JSON structure |
| **`java.time` API** | `LocalDateTime` for forecast timestamps |
| **Spring Boot DevTools** | Automatic restart during development |

### Frontend
| Tool | Purpose |
|------|---------|
| **HTML** | Page structure (served as a static file by Spring Boot) |
| **JavaScript (Fetch API, async/await)** | Calls the backend API and updates the page |
| **Tailwind CSS v4 (CDN)** | Utility-first CSS framework |

### External APIs
| API | Purpose |
|-----|---------|
| **[Open-Meteo Geocoding API](https://open-meteo.com/en/docs/geocoding-api)** | Converts a city name into latitude / longitude |
| **[Open-Meteo Forecast API](https://open-meteo.com/en/docs)** | Hourly weather forecast (ECMWF IFS model), no API key required |

### Build & Development Tools
| Tool | Purpose |
|------|---------|
| **Maven** + **Maven Wrapper** | Dependency management and build (`mvnw` works without installing Maven) |
| **Spring Initializr** | Project generation ([start.spring.io](https://start.spring.io/)) |
| **IntelliJ IDEA** | IDE |
| **Git** | Version control |
| **GitHub** | Remote repository, feature branches and Pull Requests |
| **Browser DevTools** | Inspecting network requests, headers and JavaScript errors |

## 🚀 How to Run

**Requirements:** JDK 17 or newer.

1. Clone the repository:
   ```bash
   git clone https://github.com/Liakops7/weather_app.git
   cd weather_app
   ```
2. Run the application with the Maven Wrapper:
   ```bash
   # Windows
   mvnw.cmd spring-boot:run

   # Linux / macOS
   ./mvnw spring-boot:run
   ```
3. Open your browser at **http://localhost:8080** and search for a city.

## 📡 API Endpoints

| Method | Endpoint | Description | Example |
|--------|----------|-------------|---------|
| GET | `/` | Weather search page | `http://localhost:8080/` |
| GET | `/weather` | 10-day hourly forecast for a city (defaults to `Αθήνα`) | `/weather?city=Patra` |
| GET | `/hello` | Test endpoint | `/hello` |
| GET | `/greet` | Greets the user (optional `name` parameter) | `/greet?name=Ilias` |

### Status codes for `/weather`

| Code | Meaning |
|------|---------|
| `200 OK` | Forecast returned successfully |
| `404 Not Found` | The city could not be found by the Geocoding API |

### Example response: `GET /weather?city=Patra`

```json
{
  "latitude": 38.25,
  "longitude": 21.75,
  "timezone": "Europe/Athens",
  "hourly": {
    "time": ["2026-10-30T00:00:00", "2026-10-30T01:00:00"],
    "rain": [0.0, 0.0],
    "temperature_2m": [16.2, 15.8],
    "wind_speed_10m": [8.1, 7.6],
    "wind_direction_10m": [315, 320],
    "relative_humidity_2m": [68, 70],
    "wind_gusts_10m": [14.2, 13.5]
  }
}
```

Units: temperature in °C, rain in mm, wind speed and gusts in knots, wind direction in degrees, relative humidity in %.

## 📁 Project Structure

```
src/main/
├── java/com/liakops/weather_app/
│   ├── WeatherAppApplication.java     # Application entry point
│   ├── controller/
│   │   ├── HelloController.java       # Test endpoints (/hello, /greet)
│   │   └── WeatherController.java     # Weather endpoint (/weather)
│   ├── service/
│   │   └── WeatherService.java        # Geocoding + forecast calls to Open-Meteo
│   └── model/
│       ├── WeatherResponse.java       # Full forecast JSON response
│       ├── HourlyData.java            # "hourly" forecast data
│       ├── GeocodingResponse.java     # Full geocoding JSON response
│       └── GeoResult.java             # A single city (name, coordinates, country)
└── resources/
    ├── application.properties         # Application configuration
    └── static/
        └── index.html                 # Search page (HTML + JavaScript + Tailwind)
```

## 📚 Development Log

### Step 1 – Setup
Installed the JDK and IntelliJ IDEA.

### Step 2 – Project creation
Generated the project with Spring Initializr using Maven. Learned the standard project structure (`pom.xml`, `src/main/java`, `src/main/resources`, `application.properties`) and how Spring Boot starts an embedded Tomcat server.

### Step 3 – First controller
Created `HelloController` with `@RestController` and `@GetMapping`. Used `@RequestParam` with a `defaultValue` to handle optional URL parameters, and learned about HTTP status codes (200, 400, 404, 500).

### Step 4 – Version control
Set up Git and published the project to GitHub. Learned the commit and push workflow and how `.gitignore` keeps build files out of the repository.

### Step 5 – Calling an external API
Used Spring's `RestClient` to call the Open-Meteo API. The `RestClient` is a class field so that it is created once and reused across all requests, since Spring controllers are singletons.

### Step 6 – Mapping JSON to Java objects
Created Java records (`WeatherResponse`, `HourlyData`) that mirror the JSON structure, so Jackson converts the response into typed objects automatically. Used `@JsonProperty` to map snake_case JSON keys to camelCase Java fields, and `LocalDateTime` for timestamps.

### Step 7 – Service layer and dependency injection
Moved the API logic from the controller into `WeatherService` (`@Service`), following the separation of concerns principle: the controller handles HTTP, the service handles business logic. The service is provided to the controller through constructor injection.

### Step 8 – City search
Added the Open-Meteo Geocoding API to convert a city name into coordinates before fetching the forecast. Used URI variables (`{name}`, `{lat}`, `{lon}`) so that Greek characters and spaces are encoded correctly. When a city is not found, the service throws a `ResponseStatusException` and the API returns `404 Not Found` instead of crashing with a `NullPointerException`.

### Step 9 – Search page
Built `index.html` as a static page served by Spring Boot. It uses JavaScript (`fetch`, `async/await`) to call `/weather`, finds the forecast entry for the current hour, and shows an error message for unknown cities. Tailwind CSS is loaded from a CDN.

### Step 10 – Git branching and Pull Requests
Developed the search page on a feature branch (`feature/city-search-ui`), opened a Pull Request on GitHub, merged it into `master` and deleted the branch, following a typical team workflow.

## 🗺️ Roadmap

- [x] Service layer and dependency injection
- [x] Search weather by city name (Open-Meteo Geocoding API)
- [x] Search page with HTML and JavaScript
- [ ] Error handling when the Open-Meteo API is unavailable
- [ ] Style the search page with Tailwind CSS
- [ ] Remove the unused Thymeleaf dependency
- [ ] Unit tests for `WeatherService`
- [ ] Store search history / favourite cities in PostgreSQL
- [ ] React frontend

## 👤 Author

**Ilias Liakopoulos**, Computer Science graduate, Athens University of Economics and Business

GitHub: [@Liakops7](https://github.com/Liakops7)