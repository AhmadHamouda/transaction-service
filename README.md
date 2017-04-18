# Transactions Services Statistics

The service is to calculate real time statistic from the last 60 seconds.

## Transactions Services Statistics API:

**1-Provide two main functions:**

1.1)  One of them is called every time a transaction is made. It is also the sole input of this rest API.

1.2)  Second returns the statistic based of the transactions of the last 60 seconds.


For Development purpose Application provide Swagger-ui interface which can access through http://localhost:8060/swagger-ui.html

## Business assumption covered by exist code:

1) If old transaction out of the 60 seconds interval scope [old or future] it will reject from the every time API and return 204.

3) Schedule Job run to delete old transactions

**System solution:**

1) Depend on Seconds make circular array with size 61 = 60 second interval +1 
2) Collect all transactions of the same second in partial statistics
3) Crone job each 1 second work to remove obsolete second transaction and update Statistic result
4) When user ask for statistic return result direct from him
5) This solution work on time O(61)=O(1) and constant memory also O(1)
6) Reason to modify my solution and use this approach is Simplicity and Scale-out 

**Future Add**

We can Scale-out the solution by duplicate the server and make all of them flush for one server and user can get data from this server to serve large scale application.

**Note**

1) I worked on 1 second as latency for my system update in the worst case, we can enhance this time by increase the period but need to measure the calculation computing time for the application to make meaning.
2) We can decide to calculate the Total statistics with each request not with the crone job but I depend on business case dose it worth or needed or call it in both crone and get statistics result.

**First system solution idea [partially founded in the first commit]:**

1) Depend on consumer producer pattern with blocking queue for [received transaction]
2) New transaction add to the priority queue [producers]
3) Consumer take the new added transaction and update statistic class add to another queue [statisc queue]
4) Crone job will run every 1 sec to check every expired [out of interval] remove it from the queue and from statistics
5) when user ask for statistics API will just return it back to him.

## All parameters are configurable from properties file:

**URl  parameters**

1) Add Transaction
```
curl -X POST --header 'Content-Type: application/json' --header 'Accept: */*' -d '{
  "amount": 0,
  "timestamp": 0
}' 'http://localhost:8060/transactions'
```
Request Body
```
{
  "amount": 0,
  "timestamp": 0
}
```
Response
```
no content with code 201
```

2) Read statistics
```
curl -X GET --header 'Accept: application/json' 'http://localhost:8060/statistics'
```
Response
```
{
  "sum": 0,
  "avg": 0,
  "max": 0,
  "min": 0,
  "count": 0
}
```
## Development requirement:

1) JDK 8


## Deployment requirement:

1) JRE 8


## Deployment

1) run jar through command "java -jar transactions-service-1.0.jar"

## Logging

1) Log file create next to jar and properties file.

## Testing Application

1) Use swagger ui for test functionality [http://localhost:8060/swagger-ui.html]

2) Use Postman or any RESTful APIs tool.