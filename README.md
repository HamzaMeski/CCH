# 🚴‍♂️ Cycling Club Horizon API

A robust Spring REST API for managing cycling competitions, designed to handle race results, rankings, and cyclist management.

## 🌟 Features

- **Competition Management**
  - Create and manage cycling competitions
  - Define multiple stages for each competition
  - Track competition status and details

- **Cyclist Management**
  - Register and manage cyclists
  - Track cyclist participation in competitions
  - Maintain cyclist profiles and details

- **Results & Rankings**
  - Record stage results for each cyclist
  - Automatic calculation of general classification
  - Real-time ranking updates
  - Track individual stage performances

## 🛠 Tech Stack

- **Framework:** Spring Boot 3.x
- **Language:** Java 17
- **Database:** PostgreSQL
- **Build Tool:** Maven
- **Documentation:** Swagger/OpenAPI
- **Testing:** JUnit, Mockito

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Your favorite IDE (We recommend IntelliJ IDEA)

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/cycling-club-horizon.git
   cd cycling-club-horizon
   ```

2. **Configure PostgreSQL**
   ```properties
   # src/main/resources/application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/cch_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build the project**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080/cch-api`

## 🔄 API Endpoints

### Competitions
- `GET /api/v1/competitions` - Get all competitions
- `GET /api/v1/competitions/{id}` - Get competition by ID
- `POST /api/v1/competitions` - Create new competition
- `PUT /api/v1/competitions/{id}` - Update competition
- `DELETE /api/v1/competitions/{id}` - Delete competition

### Stages
- `GET /api/v1/stages` - Get all stages
- `GET /api/v1/stages/{id}` - Get stage by ID
- `POST /api/v1/stages` - Create new stage
- `PUT /api/v1/stages/{id}` - Update stage
- `DELETE /api/v1/stages/{id}` - Delete stage

### Cyclists
- `GET /api/v1/cyclists` - Get all cyclists
- `GET /api/v1/cyclists/{id}` - Get cyclist by ID
- `POST /api/v1/cyclists` - Register new cyclist
- `PUT /api/v1/cyclists/{id}` - Update cyclist
- `DELETE /api/v1/cyclists/{id}` - Delete cyclist

### Results
- `GET /api/v1/stage-results` - Get all stage results
- `GET /api/v1/stage-results/{stageId}/{cyclistId}` - Get specific stage result
- `POST /api/v1/stage-results` - Create stage result
- `DELETE /api/v1/stage-results/{stageId}/{cyclistId}` - Delete stage result

### General Classification
- `GET /api/v1/general-results` - Get general classification
- `GET /api/v1/general-results/{competitionId}/{cyclistId}` - Get specific general result
- `GET /api/v1/general-results/winner/{competitionId}` - Get competition winner

## 📊 Data Models

### Competition
