# Video Rental Store

## Architecture

The architecture of this application is based on "Modular Monolith" pattern.
Each module is a separate bounded context which can have a different structure and architecture 
depend on the business needs.

Such a architecture is flexible for changes and also in future some of the modules can be extracted easily
to the separate service/microservice.

## Framework/library stack
* Spring Boot
* JOOQ
* Liquibase
* Spock

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