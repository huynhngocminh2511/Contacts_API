# Contacts API
This project is written by Huynh Minh, using Java programming language and Spring framework (MVC)
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
Create database mysql have name is "contacts"
Edit spring.datasource.username and spring.datasource.password to connect database
Using Intellij Idea to open project Contacts-API and run project  
### Note   
Users:
* administrator
  > User: admin@gmail.com  
  > Password: admin
* user:  
  > User: user@gmail.com  
  > Password: admin  