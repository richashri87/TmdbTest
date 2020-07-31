# Test Project configuration

Please follow the steps below

## API_KEY and ACCESS_TOKEN

Refer [here](https://developers.themoviedb.org/4/auth/create-access-token) to create Access Token

Refer [here](https://www.themoviedb.org/settings/api) to receive api key
Then replace it at 
```
/src/test/resources/dev.properties and replace the api_key and write_access_token
```
## Tags to Run Different test scope
Please use appropriate tag in maven command to run specific test scope
```maven
tmdb-list-tests : This tag runs the tests related to TMDB List operations
tmdb-list-items-tests : This tag runs the tests related to TMDB Items operations
tmdb-error-tests : This tag runs the tests related to TMDB API errors
```
## Test Environment
Choose the environment to run the test and replace in the maven command to
```maven
For Development Environment : -Denv=dev
For Test Environment : -Denv=test
For Staging Environment : -Denv=staging
```

## Run Tests
maven command to run all the tests in Development Environment
```maven
mvn clean test -DincludeTags=tmdb-list-tests,tmdb-list-items-tests,tmdb-error-tests -Denv=dev
```

## TMDB Warning
Database might throw internal errors with 500 status code
