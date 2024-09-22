# BROKERAGE FIRM APP

# General Structure

* Project written in Java 17.
* Dev profile used to run Spring boot project.
* Sql script added.
* Postman collection added for testing.


## Running the application locally
Follow the instructions below to test it out

* Import project to your ide.
* Run scripts in terminal
```shell
mvn clean install
```
* Build and run project.
* Open h2 database in browser with (username: sa, password:sa)
* Run the sql script in h2 database. ([script.sql](scripts.sql))
* Import postman collections. ([BrokerageFirmCollection.postman_collection.json](BrokerageFirmCollection.postman_collection.json))
* Test the code and provided methods.

## API
* api/orders/list?customerId={customerId}
* api/orders/create
* api/orders/cancel/{orderId}
* withdraw?customerId={customerId}&amount={amount}&iban={iban}
* api/orders/deposit?customerId={customerId}&amount={amount}
