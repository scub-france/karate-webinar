# Documentation of Fowler Inspector implementation with Karate

## Getting Started
### Local mode
To launch test on local web browser, execute the following command:
```
mvn test
```

### Docker mode
To launch test on Karate image, execute the following command:
```
docker-compose up -d
```

Then launch test with the following command:
```
mvn test -Dbrowser=karate
```

