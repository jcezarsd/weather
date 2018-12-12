# Weather Challenge!

Hi! This code was made to a challenge that was presented to me. 
The web app was build using the following technologies:

 - Java 8
 - Angular 7
 - Spring Boot 2.1.1
 - Bootstrap 4
 - H2 Database
 - OpenWeatherMap API

The folder dist has a .jar file and a h2 database file with some forecast info. So if you want to test it, download the dist folder and run the .jar file (java -jar weather-1.0.0.jar), the app will run on port 8888.

This project is set to fetch current and hourly info from the OpenWeatherMap API, this API requires we use a key, you can find that key on the application.properties file (if you want to use your own, please request at https://openweathermap.org/api). Every midnight a task get all info about the day and generate some historical data.

![App Example](https://i.imgur.com/sDRPRsO.png)
