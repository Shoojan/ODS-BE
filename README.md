# ODS-BE
## Online Delivery System - BackEnd Service

>A Backend REST API service for simple Online Delivery System using Java with SpringBoot framework


**Database Schemas**
--------------------

***Customers***
* id
* firstName
* lastName
* email
* mobile
* address
* createdAt
* updatedAt
* deletedAt

***Products***
* id
* productName
* description
* price
* imagePath
* createdAt
* updatedAt
* deletedAt

***Orders***
* id
* userId
* productId
* quantity
* unitPrice
* totalPrice
* orderedAt
* status
* checkoutCartId

***CheckoutCart***
* id
* customerId
* paymentType
* deliveryAddress
* totalPayment
* orderDate
