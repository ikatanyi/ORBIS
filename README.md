# File-Monitor
This repository contains a a simple Application which generates random string with a 50% probability of Generating ORBIS as a String value.

Essentially it's a simple on-demand application backend for File Reading and Writing.

#Technology
This Project is based on spring boot framework with maven for dependency management

# How To Run It
1. Fork or clone this project on your Hdd
2. Open it in your favorite IDE (Eclipse, netbeans, IDEA etc)
3. Resolve the dependencies
4. Run it.

# Configuration
- The project uses the application.properties file located in resource to set its configurations.
- Here you can configure the interval (cron job) that determines when or after how long should the system generate new Random Strings
- can also configure where the generated files should be stored by default they are stored in the root directory of the project
- Configure the interval (cron job) for Logging the generated String.

# Project Structure
The project Is made of Three Main files :-
 - <b>WriterService</b>:- This service is responsible for generating random String and saving the strings in random files
 - <b>LogService</b>:- This is service analyses the generated files and counts the number of occurences of the word Orbis, then logs this to a file call search_results Located in root
   directory (R)
 - <b>FileService</b>:- This services is responsible for creation, deletion, managing of directories and files.

