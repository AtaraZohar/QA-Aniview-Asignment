# QA-Aniview-Asignment

This repository contains a complete katalon project for basic manual QA testing in Vimeo.
It contains one main test case that includes Vimeo's API testing and test script. 

## How to install
- Download the project. 
- Go to katalon Studio -> file -> open project -> pick the project file.
- Now you have the project ready for use.

## run the test case
The project contains one main script for running the tests called "mainTC" you can find it in the "Test Explorer" folder under "Test Cases". 
By pressing the play button on the top bar you will run the script and it will run the API testing as well as other functionalities using WebUI mostly.
If you want to change some variables you can do so, for example changing the comment in the post request and such.

### What the object repositories folder contains: 

- 2 API testing. The GET API call is use for connecting to the user, the POST API post call used to leave the comment on this video (you can change the url in the variables, as well as the comment content).
  This is the first stage of the scripts.
- The object repository contains other items too, used for the script testing with the WebUI. you don't need to change any of those.


### What the test case folder contains:

There is one single test case that includes all tests this is the one you run.

### What the profile folder contains:

It's the default profile, required for the global variable, which holds the number of views and likes of the video we chose.
