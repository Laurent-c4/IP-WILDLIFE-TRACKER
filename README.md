# WILDLIFE TRACKER
#### A web app for tracking animal sightings in an area
#### By **Laurent Juma**
## Description
This is a web app for tracking animal sightings in an area

## Setup/Installation Requirements
* To use this project, clone it.
* Make sure java, gradle, heroku and postgresql is installed
* Create a postgres database using the commands below:
* CREATE DATABASE wildlife_tracker;  
  \c wildlife_tracker;  
  CREATE TABLE fauna_species (id SERIAL PRIMARY KEY, name VARCHAR, type VARCHAR);  
  CREATE TABLE animals (id SERIAL PRIMARY KEY, name VARCHAR, type VARCHAR, health VARCHAR, age VARCHAR, animalid INTEGER);  
  CREATE TABLE sightings (id SERIAL PRIMARY KEY, animalid INTEGER, location VARCHAR, rangername VARCHAR, sighted TIMESTAMP);  
  CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;  
* If running locally, type command gradle run in project root directory 
* Otherwise go to https://medium.com/@bmarete/deploying-a-spark-java-app-with-a-postgresql-database-to-heroku-bf54c2e664b8 for more
 information on setting up heroku to work with the project
* Open the project in the IDE and push to heroku master, then open the link provided in the terminal

## Technologies Used
Java
Heroku
Postgresql

## Website overview
![Hero Squad](/src/main/resources/public/images/screen1.png)
![Hero Squad](/src/main/resources/public/images/screen2.png)
![Hero Squad](/src/main/resources/public/images/screen3.png)

Go to //https://c4-wildlife-tracker.herokuapp.com for a live demo

## Support and contact details
Contact +254792599994 for any questions concerning the app. Feel free to give your feedback too.
### License
* MIT License

Copyright (c) 2019 Laurent Juma

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

Copyright (c) 2019 **Laurent Juma**
