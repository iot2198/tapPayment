# Tap Payment service
The project contains HTTP REST API for processing e-commerce payments via multiple payment gateways.
This handles routing transactions to different payment gateways, retrying with an alternative gateway if the primary one fails, and address various edge cases, including double spending prevention.
## Steps to test
- To run the complete build:
  `gradle clean build`
- Then get postgres container:
  ` docker run --name my_pg -e POSTGRES_DB=tap -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres`
- Test the Api with postman
- Dummy data is present in intial-data.xml

The tap payment project has been implemented using Java and Gradle. 
It adheres to the MVC (Model-View-Controller) design pattern, aiming to separate the business logic from the controller logic while maintaining their abstraction from each other. 
Communication between these components is facilitated through Data Transfer Objects and their corresponding mapping.
To handle edge cases, the system extracts a unique transaction_id from each request, initiated by various apps to their respective payment gateways. 
Typically, the API calling the payment gateway generates this transaction ID, ensuring its uniqueness for each transaction. 
To prevent duplicate transactions with the same transaction ID, the system stores them in the database as a safeguard.
Furthermore, the PayerId and PayeeId are stored in the database to verify that only authorized app users can make the transactions. 
Similarly, currency validation is enforced to ensure that only valid currencies supported by the system are accepted.
The payment gateway is implemented as an enum, allowing for easy integration of new gateway versions as they are released. Users select their preferred gateway, and transactions are initially attempted through that gateway. 
However, if the chosen gateway fails unexpectedly, the system will attempt the transaction through all available payment gateways, except for the user's preferred choice, as a fallback mechanism.
