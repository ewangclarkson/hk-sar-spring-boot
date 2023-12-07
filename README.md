# Employee Sector Registration
This repository contains a spring boot application. The application authenticated employees to register themselves on their various sectors within the company.It uses spring security for user authentication

## Getting Started
To get started with the application, follow the instructions below.

## Prerequisites
Make sure you have the following software installed on your system and have cloned React front end counterpart  of this application:

``Java Development Kit (JDK) 17 or later``

``Maven Dependency Manager``

 ``IDE compatible with Java 17 (IntelliJ preferable)``

###Installation
Clone this repository to your local machine:

#####React Front End
```
git clone https://github.com/ewangclarkson/hk-sar-react.git
```


#####Spring boot backend
```
git clone https://github.com/ewangclarkson/hk-sar-spring-boot.git
```

####Navigate to the project directory:

```
cd hk-sar-spring-boot-athentication
```

###Install the dependencies:

```
mvn install
```
### Import Database
  Create a database call hk-sar and import the .sql script found in the docs folder
  Update the application.yml file with the proper database credentials
###Usage
Start the development server:

```
mvn spring-boot:run
```


###Access The Application
Open your web browser and visit http://localhost:8080 to access the application.
To access the application online, visit the link https://hong-kong-sar.onrender.com



## Features
The application includes the following features:

### Authentication and Authorization
   #####Account Creation
        Create an account if you don't have one
   ##### Authentication
        Login to your account with your credentials

### Employee Sector Registration
 Employees are required to enter their sectors information and register or edit if the already are under one.

  Access the swagger documentation via the link  http://localhost:8080/hk-sar-service/swagger-ui/index.html
##Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

##License
This project is licensed under the MIT license.
