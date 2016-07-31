# tour-of-heroes-demo-play2.5-angular2-decoupled
This project is implementation of Angular 2 demo tour of hereos from https://angular.io/docs/ts/latest/tutorial/ but with a twist. In the tutorial in-memory databse is used to demonstrate how Angular 2 works. In this project Play framework app written in Scala and using MySQL database is used as a backend. Angular app runs standalone making CORS calls to the play app.

As of now this can be only used to demonstrate apps runing in debug mode, under no circumstances use it in production. In the future I am planning to add production ready configurations to both play adn angular apps and easy switching to debug mode, maybe using heroku.

# Running
Running this project will require some setup:
* MySQL database will need to be created by running:
```
mysql
CREATE DATABASE playTourOfHeroes
```
   Then you need to set you database username and password in `../play-tour-of-heroes/conf/application.conf` file
* You will need to disable CORS in you browser. For Chrome I am using this extension: https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi
* To run Angular app go to folder
`../angular2-tour-of-heroes`
and run command
```
npm start
```
* To run play app go to folder
`../play-tour-of-heroes`
and run command
```
sbt run
```
