# PARTS UNLIMITED 

## Running the App Locally
### Start backend server
```shell script
./gradlew bootRun
```

The app will be running locally at [https://localhost:8080](https://localhost:8080).

### Start frontend development server
Run `yarn install` from within the frontend directory before booting up the application for the first time.  This will
pull in all the front end dependencies.


For hot reloading of the frontend, run the following command from the `frontend` directory:
```shell script
yarn start
```

## Running Tests
| Tests to Run       | Command(s)           |
| :----------------- |:---------------------|
| Backend | Run `./gradlew test` in the project root directory |
| Frontend | Run `yarn test` in the `frontend` directory |
| Journey | Run the application: `./gradlew bootRun` from the top level directory then run `yarn cypress:open` from the `journey` directory. Remember to run `yarn install` from within the journey directory before running the journey tests for the first time.|


## Notes
* If journey tests fail for duplicates, restart the application to get a clean DB

# Todos of Tech Leads
* Services?
* Remove streams
* Do we need to test application context in contextLoads() test?