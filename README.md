## DSA Visualization Backend

Spring Boot backend for visualizing common data structures and algorithms:

- **Bubble Sort**
- **Merkle Tree**
- **Dijkstra shortest path**
- **A\* pathfinding**

### Requirements

- **Java**: 21 (or update `pom.xml` if you use a different version)
- **Maven**: 3.9+

### Build & Run

From the project root (`Dsa_project/demo`):

```bash
mvn clean package
mvn spring-boot:run
```

The app starts on **`http://localhost:8080`**.

### Common Request Body (`VisualizationRequest`)

All endpoints accept the same JSON structure:

```json
{
  "algorithm": "BUBBLE_SORT | MERKLE_TREE | DIJKSTRA | A_STAR",
  "array": [1, 2, 3],
  "nodeCount": 0,
  "edges": [[0, 1, 2]],
  "startNode": 0,
  "goalNode": 0,
  "dataBlocks": ["block-1", "block-2"]
}
```

Only some fields are used per algorithm (see below).

### Response Format

Each endpoint returns a **JSON array** of steps. Each step has:

```json
{
  "stepIndex": 0,
  "description": "what happens in this step",
  "arrayState": [/* current array for sorting, otherwise null */],
  "meta": { /* extra data depending on algorithm */ }
}
```

- **Bubble Sort**
  - `arrayState`: current array snapshot
  - `meta`: `{ "type": "compare" | "swap", "i": <outerIndex>, "j": <innerIndex> }`

- **Merkle Tree**
  - `meta` examples:
    - Leaf level: `{ "level": 0, "hashes": [/* leaf hashes */] }`
    - Inner levels: `{ "level": n, "nodes": [/* hashes */], "pairs": [[left, right, combined], ...] }`
    - Root: `{ "level": lastLevel, "root": "merkleRootHash" }`

- **Dijkstra**
  - `meta`: `{ "dist": [/* shortest distances or -1 */], "visited": [true/false], "current": <node>, "type": "init" | "visit" | "relax" | "done" }`

- **A\***
  - `meta`: `{ "gScore": [...], "fScore": [...], "inOpen": [...], "inClosed": [...], "current": <node>, "goal": <goalNode>, "type": "init" | "expand" | "update" | "goal" | "done" }`

### Endpoints

- **Bubble Sort**
  - **Method**: `POST`
  - **URL**: `http://localhost:8080/api/bubble-sort`
  - **Uses fields**: `algorithm="BUBBLE_SORT"`, `array`
  - **Sample JSON**:

    ```json
    {
      "algorithm": "BUBBLE_SORT",
      "array": [5, 3, 8, 1, 4],
      "nodeCount": 0,
      "edges": null,
      "startNode": 0,
      "goalNode": 0,
      "dataBlocks": null
    }
    ```

- **Merkle Tree**
  - **Method**: `POST`
  - **URL**: `http://localhost:8080/api/merkle`
  - **Uses fields**: `algorithm="MERKLE_TREE"`, `dataBlocks`
  - **Sample JSON**:

    ```json
    {
      "algorithm": "MERKLE_TREE",
      "array": null,
      "nodeCount": 0,
      "edges": null,
      "startNode": 0,
      "goalNode": 0,
      "dataBlocks": [
        "block-1-data",
        "block-2-data",
        "block-3-data"
      ]
    }
    ```

- **Dijkstra**
  - **Method**: `POST`
  - **URL**: `http://localhost:8080/api/dijkstra`
  - **Uses fields**: `algorithm="DIJKSTRA"`, `nodeCount`, `edges`, `startNode`
  - **Sample JSON**:

    ```json
    {
      "algorithm": "DIJKSTRA",
      "array": null,
      "nodeCount": 5,
      "edges": [
        [0, 1, 2],
        [0, 2, 4],
        [1, 2, 1],
        [1, 3, 7],
        [2, 4, 3],
        [3, 4, 1]
      ],
      "startNode": 0,
      "goalNode": 4,
      "dataBlocks": null
    }
    ```

- **A\* (A Star)**
  - **Method**: `POST`
  - **URL**: `http://localhost:8080/api/astar`
  - **Uses fields**: `algorithm="A_STAR"`, `nodeCount`, `edges`, `startNode`, `goalNode`
  - **Sample JSON**:

    ```json
    {
      "algorithm": "A_STAR",
      "array": null,
      "nodeCount": 5,
      "edges": [
        [0, 1, 2],
        [0, 2, 4],
        [1, 2, 1],
        [1, 3, 2],
        [2, 4, 3],
        [3, 4, 1]
      ],
      "startNode": 0,
      "goalNode": 4,
      "dataBlocks": null
    }
    ```

### Security

Spring Security starter is included. By default, on first run a generated password is logged to the console for the in‑memory user. Adjust the security configuration in `SecurityConfig` if you need custom rules or to expose these endpoints without authentication.


