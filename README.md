# Contacts API
This project is written by Huynh Minh, using Java programming language and Spring Boot framework.

This project is a simple API, where a user can get a quick overview over all contacts resources like person, skills...

The website have two type of users (admin, user). The website has one and only one admin
* admin role :
  > Login

  > Read, create, update, delete skills

  > Read, create, update, delete contacts 
* user role :
  > Login

  > Read, create contact

  > Users can only change their contact and skills 
## Guide start project  
Clone this repository using:  
```
git clone https://github.com/huynhngocminh2511/Contacts_API.git
```

Create database mysql have name is "contacts".

Edit value of spring.datasource.username and spring.datasource.password to connect database in file src\main\resources\application.properties

Open command line inside folder contain gradlew file enter "gradlew bootRun" to run project

### After run project, you can view swagger by access link below:

swagger-ui:http://localhost:8080/swagger-ui.html

swagger-api-docs:http://localhost:8080/v2/api-docs

### Note   
Users:
* administrator
  > Email: admin@gmail.com

  > Password: admin
* user:  
  > Email: user@gmail.com

  > Password: admin  