# AlgoVision 🚀

AlgoVision is a high-performance RESTful API service that orchestrates the execution and state tracking of 16 complex data structure and algorithm routines. Built with Spring Boot 3 and a React frontend, it uses Strategy and Factory design patterns to cleanly decouple algorithmic logic from the visualization layer.

## About the project

- Backend: Spring Boot 3 REST API exposing endpoints for algorithm execution and step-wise state tracking.
- Frontend: React-based visualization layer that consumes the API responses to animate algorithm states.
- Design patterns: Strategy and Factory patterns to plug in new algorithms without changing controller code.
- Algorithms: Multiple complex data structure and algorithm implementations (for example, graphs, trees, sorting, and searching – adjust to match your exact list).

Update this section with the exact 16 algorithms you have implemented.

## Tech stack

- Java (Spring Boot 3)
- React, JavaScript, HTML, CSS
- RESTful APIs for communication between backend and frontend

## Getting started

1. Clone the repository:
``` 
git clone https://github.com/kunnaaalll/AlgoVision.git
cd AlgoVision
```

2. Backend setup (Spring Boot):

- Make sure you have Java (JDK 17+ or your project version) and Maven/Gradle installed.
- If using Maven:

  ```
  mvn clean install
  mvn spring-boot:run
  ```

- If using Gradle:

  ```
  ./gradlew bootRun
  ```

The backend will start on the configured port (for example `http://localhost:8080`).

3. Frontend setup (React):

If the React app is in a subfolder (for example `client` or `frontend`), from that folder run:

```
npm install
npm start
```
The frontend will usually be available at `http://localhost:3000` and will call the Spring Boot API.

Adjust folder names and commands to match your actual structure (for example `./frontend`, `./ui`, etc.).

## Usage

- Open the React app in your browser.
- Select an algorithm from the UI (for example, sorting, graph traversal, tree operations).
- Configure input size or initial data if the UI supports it.
- Start the visualization to see step-wise state changes driven by the backend’s API responses.

You can extend the UI and API to support new algorithms, parameters, and visualization modes.

## Architecture

- Controllers expose REST endpoints for each algorithm family.
- Strategy pattern encapsulates different algorithm implementations behind a common interface.
- Factory pattern dynamically selects the correct algorithm strategy based on request parameters.
- The frontend polls or streams state updates to animate the algorithm execution.

Add class or package names here (for example `com.algo.vision.algorithms`, `com.algo.vision.strategy`) for more detail.

## Future improvements

- Add more advanced data structures and algorithms.
- Add authentication and user-specific saved sessions.
- Add performance metrics and benchmarking for each algorithm.
- Add better error handling and input validation in both API and UI.

## License

This project is intended for learning, experimentation, and showcasing data structure and algorithm visualizations. You are free to fork and extend it according to your needs.
