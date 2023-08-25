## Assignment 1
Assignment 1 is found the folder called Assignment 1. It have the indidvidual files for each subassignment.

## Assignment 2 - Chinook

## Description
This repository contains all of the parts in assignment 2. 

Appendix A - Superheroes Database
The SQL scripts that have been written in regards to appendix A are all located in the directory called "Assignment1". 
There is a seperate sql file for each task.
To run the scripts the content of each file can simply be copied into the query tool in PGAdmin.
Then press F5 to run the script.

Appendix B - Chinook
This project is a series of sql statements set forth by a wealthy media mogul. It is made in Spring boot using JDBC with a postgres database called Chinook.
The Chinook database models an iTunes database of customers purchasing songs. Therefore, the scope of this project is mainly focused on the data relating to customers.
Therefore, the spring boot application has been developed such that it can interact with the postgres databse using JDBC to retrieve and insert information concering the customers.
However, additional data from the postgres database is also used in certain aspects to retrieve certain information about the customers.

## Programming Structure
The project consists of multiple java packages such as models, repositories and runners that contain classes and or interfaces.
Inside of the models package there are multiple records which encapsulate some of the columns from the customer table in the Chinook database.

This project uses the "Repository" design pattern which the package called "repository" demonstrates.
Therefore, this package containes two interfaces and a single class.
The interface called "CRUDRepository" has been designed generically with the intention of other repository interfaces inheriting from it.
Even though CRUD is an acronym standing for "Create", "Read", "Update" and "Delete" there is no definiton for deleting an entity. 

Another interface in the repository package is called "CustomerReposity" and it inherits from CRUDRepository. 
Furthermore, the CustomerRepository interface also extends its parent by declaring methods that are specific to the Customer class.
Then the class called "CustomerRepositoryImpl" implements the CustomerRepository interface and overrides its methods.
It is inside the override methods that the CustomerRepositoryImpl class defines the interaction with the postgres database.

Then, the class in the package called "CustomerRunner" is able to use the CustomerRepository as dependency using dependency injection.
This means that the CustomerRunner class has a dependency on CustomerRepository where the CustomerRepositoryImpl is given to its constructor.
The CustomerRunner class also implements a ApplicationRunner interface and overrides its run method. 
Inside the run method each of the override methods from the CustomerRepositoryImpl instance are being called.

## Project Setup
To follow these instructions a Chinook database is assumed to be connected.
1. Clone the repository from the main branch.
2. Open the project in a Java editor.

## Run Instrictions - Spring Boot Application
To run the program follow the instructions below:
1. Run the method the class called "ChinookSpringApplication" by right clicking the file.

In the file called "CustomerRunner" located at "src/main/java/experis.chinookspring/runner/CustomerRunner there is an overridden run method.
This method print the output for each requirement for the customerRepository to the console.

## Authors
Name: Sigurd Schelde
Andersen
E-Mail: sigurdschelde@gmail.com

Name: Alexander
Jonstrup
E-Mail: Alexander.Jonstrup@outlook.com
