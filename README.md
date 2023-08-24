# Chinook

## Name
Assignment 2 - Chinook 

## Description
This is a Java RPG console application inspired by Diablo 2 & 3.
The purpose of this project is to demonstrate an implementation of Hero classes and equipment from Diablo 2 & 3 -
with state and behaviour.In the project there are 4 hero classes 'Archer', 'Barbarian', 'Swashbuckler' & 'Wizard'.
All of the heroes share the same behaviour in terms of being able to equip armor & weapons, leveling up.
Additionally, the heroes also share the same state properties in terms of name, stats and level. 
However, the heroes differ in terms of which armor & weapons they are able to equip and they have different stat values.
The stat values used to compute the damage is individual to each hero.  

## Programming Structure
The project is divided into 4 Java packages 'attributes', 'exceptions', 'equipment' and 'heroes'.

Hero Attributes:
The attributes package only contains one class which is the HeroAttribute that encapsulates Strength, Dexterity and Intelligence
instance variables. This class is used for both heroes and items to resemble their stat attributes.

Items:
Inside the items package an abstract Item class is located together with Armor and Weapon classes that inherit from the Item class.
Armor and Weapon share multiple state and behaviour such as name, required level and stat attributes and getters for these instance variables. However, the two subclasses differ from each other as Armor has an HeroAttribute field and ArmorType enumerator and Weapon has a WeaponType enumerator and weaponDamage property. Armor are items that can be equipped to the body, head or legs of a hero whereas a hero can only equip one weapon at a time. Both Armor and Weapon are used in computing the damage of a hero.

Heroes:
All of the hero classes inherit from the same abstract class called Hero. These heroes are'Archer', 'Barbarian', 'Swashbuckler' & 'Wizard'. When they are instantiated they all start at level 1 with stat attribute values relative to each hero. They all have an inventory consisting of one weapon slot and one slot for head, body and legs armor. However, the type of compatible weapon and armor are relative to each hero. Otherwise, they have the same behaviour in terms of calculating the sum of heroes' total attributes and damage. 

The 'exceptions' package contains 2 custom exception classes 'InvalidWeaponException' & 'InvalidArmorException'.
These classes are used to thhrow an exception with custom defined a message when a hero cannot equip a weapon or armor piece.
This can happen when the level of a hero is below the required level of an item or if the WeaponType or ArmorType are not compatible with a certain hero.

Unit Tests:
The intention behind the unit tests is to evaluate the behaviour of the implemented functionality in the hero and item classes.
This is done by comparing the expected results with the actual behaviour in a given scenario. 
Therefore, such test scenarios have been made for each hero and item subclasses. 

## Project Setup
1. Clone the repository from the master branch.
2. Open the project in a Java editor.

## Run Instrictions
The project only runs through unit tests that either pass of fail.
The directory containing the code used for unit tests consists of two Java packages 'equipment' & 'heroes'.
The classes in each package can be run seperately or all at once. 
Once the unit test has been run the result is printed to the console.

To run the program follow the instructions below:
1. Run the packages located in the directory src/test/java/ by right clicking and press run. 

## Author
Name: Alexander
Lastname: Jonstrup
E-Mail: Alexander.Jonstrup@outlook.com
