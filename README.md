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

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
