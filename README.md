

# Chinook

## Name
Assignment 2 - Chinook

## Description
This project is a series of sql statements set forth by a wealthy media mogul. It is made in Spring boot using JDBC with a postgres database called chinook.
Chinook models an iTunes database of customers purchasing songs. Therefore, the scope of this project is mainly focused on the customer data.
However, additional data is also used in certain aspects that somehow relates to the customer.

## Programming Structure
The project consists of multiple java packages such as models, repository and runner that contain classes and or interfaces.
Inside of the models package there are multiple records which encapsulate some from the customer table in the Chinook database.

This project uses the "Repository" design pattern which the repository package demonstrates.
Therefore, the package containes two interfaces and a single class.
The interface called "CRUDRepository" has been designed generically with the intention of other repository interfaces inheriting from it.
This is exactly what the other interface called "CustomerReposity" does such that it can specify non-primitive data types for the defined generic types in the CRUDRepository interface.
Furthermore, the CustomerRepository interface also extends its parent by declaring methods that are specific to the Customer class.
Then the class called "CustomerRepositoryImpl" implements the CustomerRepository interface and overrides its methods.
Thus, the class in the package called "CustomerRunner" is able to use the CustomerRepository as dependency using dependency injection.
The CustomerRunner class also implements a ApplicationRunner interface and overrides its run method.

## Project Setup
1. Clone the repository from the main branch.
2. Open the project in a Java editor.

## Run Instrictions
To run the program follow the instructions below:
1. Run the method the class called "ChinookSpringApplication" by right clicking the file.

## Authors
Name: Sigurd S.
Lasname: Andersen
E-Mail: sigurdschelde@gmail.com

Name: Alexander
Lastname: Jonstrup
E-Mail: Alexander.Jonstrup@outlook.com
