# Project configuration

Please follow the steps below

## API_KEY and ACCESS_TOKEN

Refer [here](https://developers.themoviedb.org/4/auth/create-access-token) to create Access Token

Refer [here](https://www.themoviedb.org/settings/api) to receive api key
Then replace it at 
```
/src/test/resources/dev.properties and replace the api_key and write_access_token
```

## Run Tests
Choose the environment to run the test and replace the -Denv in maven command 
```maven
mvn clean test -DincludeTags=tmdb-list-tests,tmdb-list-items-tests -Denv=dev
```

## TMDB Failures
Database might throw internal errors with 500 status code
