# Weather Data consumer - Spring Boot AMQP messaging consumer with RabbitMQ

This is a Spring Boot application that shows how to easily configure RabbitMQ with AMQP for consuming messages in JSON Format

In this project, each message is received as JSON and then decoded:

There are three components and all those are mentioned as services in docker compose file

1. weather-data-producer 
2. weather-data-consumer
3. RabbitMQ server 

All the three components are mentioned as services in docker compose file.


Steps :

1. Check out the projects weather-data-producer , weather-data-consumer
   And run  `mvn clean install` on both the projects
   
2. Go to weather-data-producer and run below command 
   And run  `sudo docker image build -t weather-data-producer .` 
  
3. Go to weather-data-consumer and run below command 
    And run  `sudo docker image build -t weather-data-consumer .`

4. Use the included `docker-compose.yml` in weather-data-consumer project file to start a new server using Docker: `sudo docker-compose up -d`

After that everything should be running.

For Health check just verify whether both apps are up or not and you should be able to access below urls 

Consumer Url : http://localhost:8080/

Producer Url : http://localhost:8081/

If we want to have a look at logs we can have look at   `docker logs containerid`


# Assignment and Inline Answers

# Task1 : Given that it is difficult to obtain real data from the weather balloon we would first like to be able to generate test data for use in simulation and testing. This producer service should be able to generate at least 1 million lines of data for testing your consumer (next steps). The service should add every line into a RabbitMQ queue.

Venkatesh Lekkala : Followed the Principle of KISS, I do not want to complicate this test data generation so have written a scheduler to pump the send the data for every 30 milliseconds. So i t will generate the data very dynamically once after it is started till it is stopped

Code generation for Dynamic data is there in WeatherDataMessageSender.class

# Task2 :
Create a consumer service which produces statistics of the flight. The program should be able to display the current information (at best in real time) (reading from the queue):

The minimum temperature - Temperature result will be in the format of Kelvin  
http://localhost:8080/weather/minTemperature

The maximum temperature.  
http://localhost:8080/weather/maxTemperature

The mean temperature. 
http://localhost:8080/weather/meanTemperature

The number of observations from each observatory. 
http://localhost:8080/weather/observations

The total distance travelled. 
http://localhost:8080/weather/distance (This needs to be implemented after discussing with them )

Persist every line into a Postgres or h2 DB. 

# Task3 : Create a program which can display past information (see above) stored in the database - given a time range

Venkatesh Lekkala : Have written this by using the existing code itself just append the startDate & endDate as request parameters to the above urls

http://localhost:8080/weather/minTemperature?startDate=2020-12-27T00:00&endDate=2020-12-28T00:00
http://localhost:8080/weather/maxTemperature?startDate=2020-12-27T00:00&endDate=2020-12-28T00:00
http://localhost:8080/weather/meanTemperature?startDate=2020-12-27T00:00&endDate=2020-12-28T00:00
http://localhost:8080/weather/observations?startDate=2020-12-27T00:00&endDate=2020-12-28T00:00

# Unit Test classes for above url’s are written in WeatherDataControllerTest.class

Still scope for improvement ?

Yes, Swagger can be integrated and many more can be integrated based on the need and requirement





