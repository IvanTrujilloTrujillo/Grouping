# Grouping App

Grouping is a platform where you can share your opinion of the sites you visit and see the opinion of other users. You can publish your reviews publicly or you can join a private group and publish them there.

Create a group easily and comfortably for your friends or your family and interact privately about restaurants, bars, shops, ... which you want to leave your opinion and share it with others. Show off that spot you discovered to your friends and show them its location by entering the Google Maps link.

It will also help you to check public opinions about a place before publishing it and reading the comments.

## Installation

1. Download the proyect from the repository or clone it.

2. Create the local databases with the sql files in '/src/main/resources/sql' inside the folowing microservices: groups-service, sites-service and user-service (in groups-service it's necessary to add a group called 'Global')

3. Add the properties in 'application.properties' to connect to each database (there is a config-repo but isn't modifiable so you have to write the properties with your credentials and not run the config-service microservice)

4. Open and run the microservices except config-service as a project on a IDE as IntelliJ.

5. Run the client directory as an Angular proyect (you'll need to run 'npm install' and then 'ng serve'.

6. Open a browser and nagivate to 'localhost:4200'.

## Functionalities

- Create a private group

## Diagrams

- Microservice Diagram:

![Microservice Diagram](https://github.com/IvanTrujilloTrujillo/grouping/blob/main/edge-service/src/main/resources/diagrams/Grouping%20microservice.jpg)

- Case Diagram:

![Case Diagram](https://github.com/IvanTrujilloTrujillo/grouping/blob/main/edge-service/src/main/resources/diagrams/Grouping%20User%20Case.jpg)

- Class Diagram:

![Class Diagram](https://github.com/IvanTrujilloTrujillo/grouping/blob/main/edge-service/src/main/resources/diagrams/Grouping%20Class.jpg)

## Screenshots

![Screenshot1]()
![alt text]()
![alt text]()
![alt text]()
![alt text]()

## Author

**Iván Trujillo**
