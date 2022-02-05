## How to use

### Run application

Go to directory with a project and run command
```
./gradlew bootRun
```

### Client requests example

#### Increment example
```
curl --location --request POST 'http://localhost:8087/v1/increment' --header 'Content-Type: application/json' --data-raw '{"countryCode": "CY"}'
```

#### Get info example

```
curl --location --request GET 'http://localhost:8087/v1/country-counter-info'
```

#### Clean up

This application creates file with data. You can remove it if run this command

```
rm -rf ~/wisebits/
```