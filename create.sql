CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE fauna_species (id SERIAL PRIMARY KEY, name VARCHAR, type VARCHAR);
CREATE TABLE animals (id SERIAL PRIMARY KEY, name VARCHAR, type VARCHAR, health VARCHAR, age VARCHAR, animalid INTEGER);
CREATE TABLE sightings (id SERIAL PRIMARY KEY, animalid INTEGER, location VARCHAR, rangername VARCHAR, sighted TIMESTAMP);
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;