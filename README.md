# ODS-BE
## Online Delivery System - BackEnd Service

>A Backend REST API service for simple Online Delivery System using Java with SpringBoot framework

**Resources Used**
1. Java 16 (Springboot 2.4.5)
2. H2 Database
3. IntelliJ IDE

**Following features have been implemented in the project**
1.	Java  Version = 16,  Port = 5500
2.	JWT Authentication has been used that provides Bearer token once authenticated.
3.	Embedded H2 database has been used in which user details, product details and checkout orders shall be pre-inserted into database during project deployment. (used CommandLineRunner as bean configuration for this. )
4.	Swagger has been implemented for API documentation. (please refer to http://localhost:5500/swagger-ui/ once project is running)


**Database Schemas**
--------------------

***Customers*** | ***Products*** | ***Orders*** | ***CheckoutCart***
----------------|----------------|--------------|--------------------
id | id | id | id
firstName | productName | customerId | customerId
lastName | description | quantity | paymentType
email | price | unitPrice | deliveryAddress
mobile | imagePath | totalPrice | totalPayment
address | createdAt | orderedAt | orderDate
createdAt | updatedAt | orderStatus | expectedDate
updatedAt | deletedAt | createdAt | .
deletedAt | . | updatedAt | .
. | . | deletedAt | .
. | . | checkoutCartId | .
