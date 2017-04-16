#Transactions Services Statistics

The service is to calculate realtime statistic from the last 60 seconds.

##Transactions Services Statistics API:

**1-Provide two main functions:**

1.1)  One of them is called everytime a transaction is made. It is also the sole input of this rest API.

1.2)  Second returns the statistic based of the transactions of the last 60 seconds.


For Development purpose Application provide Swagger-ui interface which can access through http://localhost:8060/swagger-ui.html

##Business assumption covered by exist code:

1) If old transaction out of the 60 seconds interval scope [old or future] it will reject from the everytime API and return 204.

3) Schedule Job run to delete old shorten url

**System solution:**

1) Deepend on consumer producer pattern with blocking queue for [recived transaction]
2) New transaction add to the priority queue [producers]
3) Consumer take the new added transaction and update statistic class add to another queue [statisc queue]
4) Crone job will run every 1 sec to check every expired [out of interval] remove it from the queue and from statistics
5) when user ask for statistics API will just return it back to him.

##All parameters are configurable from properties file:

**URl parameters**

1) domain.name=http://localhost:8060/r/

2) statistics.interval=

##Development requirment:

1) JDK 8


##Deployment requirment:

1) JRE 8


##Deployment

1) run jar through command "java -jar shortenurl-1.0.jar"

##Logging

Log file create next to jar and properties file.

##Testing Application

1) Use swagger ui for test Anonymous user functionality [http://localhost:8060/swagger-ui.html]

 