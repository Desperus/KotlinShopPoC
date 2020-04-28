# Kotlin Shopify PoC

Sample application to provide examples of using technologies we will need to implement the project.

Contains Spring Boot application with following features:
* Kafka consumer in **ProductChangeEventConsumer**
* Communication with external REST resource in **BusinessLogic**
* REST controller in **FulfillmentService**
* Integration with Shopify API in **ShopifyApp**

To run any of the examples there are tests present for each corresponding class. These tests start embedded instances of
all external resources we might need so you will be able to track down flow of each of those classes.

Configuration is supposed to be from external source which is why **application.yml** has most of its properties 
commented. For tests those properties are populated with credentials of embedded resources in **application-test.yml**. 