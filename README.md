# Train Ticket Manager

## Java Portfolio Project

---

Version 0.6

---

### The project is made with:

- IntelliJ IDEA
- Java 17
- Spring Boot
- Gradle
- H2
- Flyway
- Project Lombok

---

### Concept:

A backend part of a web app, ticket purchasing for a few specific trains trough REST endpoints.
Both path variables and JSONs are used to transmit information.  
Costumers can register to get an ID, upload balance (sadly not with real money transactions yet), 
and use that balance to purchase a ticket for a specific train, on a specific day. 
All of this is saved in an in-memory database.  
Train conductors can check for a valid ticket trough checking the customers ID. 

---
### How to run:

1. Open project in preferred IDE.
2. Wait for the project to automatically process build files.

After the IDE recognised the project, you have 2 options:

1. Add new configuration.
2. Select Application.
3. Select module with preferably *java 17* as **java version** and select **-cp trainticket-manager-java.main**
4. Select **com.dorogi.trainticketmanager.TrainticketmanagerApplication** as Main class.
5. Press Apply and Ok.
6. Now you should be able to run the project.

***OR***

1. Search Main class **src/main/java/com/dorogi/trainticketmanager/TrainticketmanagerApplication.java** and open it.
2. Press green play button on the left, at the line 7 or 9, it could automatically configure itself and start application.

---

### Usage

The following REST endpoints are available, after starting the application (Program to test E.g.: Postman):

1. **Get all trains:**

> GET localhost:8080/train/get

*Lists all available trains to chose from.*

2. **Register new customer:**

> POST localhost:8080/customer/add

*You have to paste a JSON in the Request Body, in the format of*

```
{
    "name": "{name}",
    "email": "{email}"
}
```

*where you have to replace  
`{name}` with any name you like,  
`{email}` with any email address you like, it is not checked.  
all new customers get 1 free balance.*

3. **Add balance**

> PUT localhost:8080/customer/balance

*You have to paste a JSON in the Request Body, in the format of*

```
{
    "id": "{id}",
    "amount": "{amount}"
}
```

*where you have to replace  
`{id}` with the chosen customer ID in numeric value,  
`{amount}` with the chosen amount to add in numeric value.*

4. **Buy ticket**

> POST localhost:8080/customer/ticket

*You have to paste a JSON in the Request Body, int the format of*

```
{
    "customerId": "{customerID}",
    "trainId": "{trainID}",
    "year": "{year}",
    "month": "{month}",
    "day": "{day}"
}
```

*where you have to replace  
`{customerID}` with the chosen customer ID in numeric value,  
`{trainID}` with the chosen train ID in numeric value,  
`{year}` with the chosen year in numeric value,  
`{month}` with the chosen month in numeric value,  
`{day}` with the chosen day in numeric value.*

5. **Check for ticket as conductor**

> GET localhost:8080/conductor/{id}

*You have to replace the  
`{id}` with any numeric value.  
To get a `VALID` feedback, you have to have a ticket in the database 
with the date of today, any other case gives back `NO VALID TICKET` as feedback.*

6. **Search for a specific customer as manager**

> GET localhost:8080/manager/get-customer/{id}

*You have to replace the  
`{id}` with any numeric value.  
Preferably `1` or `2` or `3` or `4` to get values from preloaded database.*


---
