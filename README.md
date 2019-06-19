# SalesMessagesPOC
This is POC to process Sales messages and generate the report

Scope: Process the incoming Sales Messages, keep it in memory and show the reports accordingly

Requirement: 
Implement a small message processing application that processing sales notification messages.

Implementation: 
   - Created In memory storage cache(SalesCache.java) to store sales data from the messages. Keeping below data in cache:
	- Sales Message Count – Count for how many sales messages are actually processed
                - Product wise sales Map – To keep the product type wise Sales Data. Product as a key and sales data as List
	- Product wise operations Map – To keep the product type wise adjustments done on sales data	
   - Created Enum for operations(Operations.java)
   - Created object to store sales data(Sales.java)
   - Created  one consumer to consume sales data(SalesMessagesConsumer.java)

Testing: 
 - Written the test cases to validate this feature(SalesMessagesTest.java)
 - Created different types of sample data to verify this feature

Java Version: jdk1.8.0_111
External Libraries: 
 - Junit-4.1.2 - For Unit Testing

