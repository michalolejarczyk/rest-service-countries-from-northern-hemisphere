# rest-service-countries-from-northern-hemisphere
Restful Web Service using Spring Boot and Gradle that takes IP addresses and returns list of countries from the northern hemisphere.
Information about IP addresses are taken from IP Vigilante API https://www.ipvigilante.com/api-developer-docs/.

Service info:
- GET request
- endpoint accept at least 1 and maximum 5 ip addresses
- response contain list of unique names (no repetitions of names) sorted alphabetically

Example case:
Request:

curl "http://localhost:8080/northcountries?ip=8.8.8.8&ip=89.76.170.118&ip=177.0.0.0&ip=180.0.0.0&ip=190.0.0.0"

Response:

{
    "northcountries": [
        "Colombia",
        "Japan",
        "Poland",
        "United States"
    ]
}
