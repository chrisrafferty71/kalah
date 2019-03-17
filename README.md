# kalah
Rest implementation of the game 6 stone Kalah

Edit src\main\resources\application.properties to set the host and port 

## Build
mvn clean install

## Run
mvn spring-boot:run

## Play
curl --header "Content-Type: application/json" --request POST http://localhost:8080/games

curl --header "Content-Type: application/json" --request PUT http://localhost:8080/games/{gameId}/pits/{pitId}
