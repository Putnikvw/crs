<h2>Client service registration service</h2>

<p>Client service provide to register  a client using REST API approach</p> 
<p> The service working with REST API request (POST for this moment) 
and propagate information about client (name, surname, wage and timestamp) to kafka service.
If produce to kafka was succeeded the information about client save into DB.</p>

Technology:
- Java 11
- Mysql 8.0
- Kafka
- Zookeeper 2.8.1

<b>!!! Before run app you should install docker and docker-compose!!!</b>
<h4>How to run app:</h4>

1. Using docker and docker-compose
   1. Clone code from repo
   2. Open project folder in terminal
   3. Run next commands:
    - mvn clean package
    - docker build --tag=app:latest . 
    - docker-compose up -d
   4. Make a test request use Postman or another HTTP client:
   - POST http://localhost:8080/api/v1/client
   - Example of the body:
   ```JSON 
   {
      "name": "John",
      "surname": "Lark",
      "wage": 2345.45,
      "eventTime": "2016-12-23T12:12:12.122Z"
   }
   ```
   
2. Using Intellij IDEA
   1. Clone project and open it in Intellij IDEA 
   2. Edit configuration choose ClientRegistrationServiceApplication and select 
   <b>spring.active.profiles = local</b>
   3. Run docker-compose up -d to setup kafka and mysql database
   4. To test app (see step 6 from previous method)

3. Open Swagger page:
   1. Run the app using method 1 or 2
   2. Open browser and type http://localhost:8080/swagger-ui.html
   3. Download the documentation for OpenApi open in browser  http://localhost:8080/v3/api-docs