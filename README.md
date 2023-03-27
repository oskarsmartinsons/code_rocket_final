# code_rocket_final

1. SmartRequestApp:
    - implements backend sercice, which has an incoming API with one method, it calls 3rd party service (weather info from https://yr.no) and returns the current temperature for a specific location on a map.
      4
      ​
      8
2. Business Logic - On every client request, make a request to the 3rd party, except if the data is already stored in the cache (cache must be implemented by using a relational SQL database). Assume the
   temperature for the current hour never changes, therefore if the time matches then the temperature must be returned from cache instead of asking 3rd party.

3. Incoming API - has only one method, it returns the current temperature for a specific location on a map.
    - PORT: configurable from config.properties
    - HTTP
    - Request - GET http://localhost:8080/weather?lat=<latitude>&lon=<longitude>
    - Response:
      {
      "temperature": 15.2
      }

4. Outgoing API - weather API  https://api.met.no/weatherapi/locationforecast/2.0/  retrieves temperature for each hour for a specific location.

5. Used technologies:
    - Java 17, Spring Boot Web, in-memory database H2, JUnit, Gradle

6. Implementation:
    - project has been implemented using a hexagonal architecture (known as Ports and Adapters architecture or Clean Architecture)
