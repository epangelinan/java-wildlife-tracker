## Wildlife Tracker

An app for the forest service to track animals for an environmental impact study.

### Description

The Forest Service is considering a proposal from a timber company to clearcut a nearby forest of Douglas Fir. Before this proposal may be approved, they must complete an environmental impact study. This application was developed to allow Rangers to track wildlife sightings in the area.

### Setup

To create the necessary databases, launch postgres, then psql, and run the following commands:

* `CREATE DATABASE wildlife_tracker;`
* `\c wildlife_tracker;`
* `CREATE TABLE animals (id serial PRIMARY KEY, name varchar, health varchar, age varchar, type varchar);`
* `CREATE TABLE sightings (id serial PRIMARY KEY, animal_id int, location varchar, ranger_name varchar, time_sighted timestamp);`
* `CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;`

To Backup Databases:

* _Clear the tables (skip the first two DELETE steps if you want to keep the data in the database).  From psql, enter:_
* _DELETE FROM animals;_
* _DELETE FROM sightings;_
* _DROP DATABASE wildlife_tracker_test;_
* _In your "normal" terminal window, not psql, enter: pg_dump wildlife_tracker > media.sql_
* _Add changes via add . and commit your changes_
* _Upload project to Github._

To Restore Databases:

* _With the java-wildlife-tracker project cloned or downloaded from Github:_
* _At terminal, enter postgres_
* _In a different terminal window, enter psql_
* _From psql, run: CREATE DATABASE wildlife_tracker;_
* _Run the following command in the terminal (not psql): psql wildlife_tracker < media.sql_
* _Confirm success.  Switch to psql and run:  \c wildlife_tracker_
* _Then run: \dt_

### Lynn's Changes
* Changed Animal class to be a parent class of EndangeredAnimal class
* Commented out the code in the EndangeredAnimal class that are no longer needed because they already exist in Animal class
* Removed endangered_animals table
* Added EndangeredAnimal's health and age properties to animals table
* Added type property to Animal and EndangeredAnimal classes.  In Animal class, set the type to 'nonendangered' and in EndangeredAnimal class, set the type to 'endangered'.
* Added type column to animals table.
* Updated EndangeredAnimal class code to save EndangeredAnimal objects to the animals table.
* Created constants to define options like "healthy", "ill", "okay", "newborn", "young", and "adult"
* Added database timestamps for each sighting
* Updated a few incorrect tests, added more tests
* Added CSS styling

## Known Bugs

_The app crashes if the user hits the Submit button when there are no animals entered (empty database)_

## Support and contact details

_If you have any questions, please contact Lynn at esveth@aol.com_

## Technologies Used

_Java, Gradle, Spark, JUnit, Velocity, Postgres, HTML, CSS, Bootstrap_

### License

Copyright (c) 2017 **_MIT License_**
