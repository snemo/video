# Video Rental Store

## Summary
This is a very simple application of video rental store. It is not production ready, because there is still missing
better error handling and renting module is not tracking rented movies properly (transactions/locking is missing).


## Architecture
The architecture of this application is based on "Modular Monolith" pattern.
Each module is a separate bounded context which can have a different structure and architecture 
depend on the business needs.

Such a architecture is flexible for changes and also in future some of the modules can be extracted easily
to the separate service/microservice. 

The component size should be small enough, so when you do rm -rf you can write it from scratch in max 2 weeks.

Module communication/coupling can be done ONLY through facades or by emitting events.

## Framework/library stack
* Spring Boot
* JOOQ
* Liquibase
* Spock

## Open issues
* build issue
 WARNING: An illegal reflective access operation has occurred
 It is problem with Groovy and Java 11, please skip it for now, util will be solved in Groovy
 More details in official ticket: https://issues.apache.org/jira/browse/GROOVY-8339
 
## How to build/start
    ./gradlew clean build
    ./gradlew bootRun

## API documentation
API documentation is available via Swagger
http://localhost:8089/swagger-ui.html#/
    
### Request example from PDF Programming Test
#### POST /api/movie/rent
    {
      "customer": "750be9e2-5c5c-11e9-8647-d663bd873d93",
      "movies": [
        {
          "days": 1,
          "movieId": "31f136ba-5515-11e9-8647-d663bd873d93"
        },
        {
          "days": 5,
          "movieId": "31f13a84-5515-11e9-8647-d663bd873d93"
        },
        {
          "days": 2,
          "movieId": "b1f4be68-9ca0-11e9-a2a3-2a2ae2dbcce4"
        },
        {
          "days": 7,
          "movieId": "b1f4bfb2-9ca0-11e9-a2a3-2a2ae2dbcce4"
        }
      ]
    }
    
#### POST /api/movie/return
    {
      "customer": "750be9e2-5c5c-11e9-8647-d663bd873d93",
      "movies": [
        {
          "movieId": "31f136ba-5515-11e9-8647-d663bd873d93",
          "extraDays": 2
        },
        {
          "movieId": "31f13a84-5515-11e9-8647-d663bd873d93",
          "extraDays": 1
        }
      ]
    }

#### GET /api/invoice/order/{orderId}
    {
      "order": "c97f7620-e537-452b-a68b-1989318e1bcb",
      "customer": "750be9e2-5c5c-11e9-8647-d663bd873d93",
      "totalPrice": {
        "amount": 250,
        "currency": "SEK"
      }
    }
    
#### GET /api/bonus/points/{customerId}
    {
      "customerId": "750be9e2-5c5c-11e9-8647-d663bd873d93",
      "points": 5
    }