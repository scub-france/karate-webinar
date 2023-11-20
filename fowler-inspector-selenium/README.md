# Documentation of Fowler Inspector implementation with Selenium

## Getting Started

### Local mode
To launch test on local web browser, execute the following command:
```
mvn test
```

### Docker mode
To launch test on selenium hub, execute the following command:
Launch selenium hub:
```
docker-compose up -d
```

Then launch test with the following command:
```
mvn test -Dbrowser=hub 
```


## Report
The report is generated in the target folder :
"target/cucumber-reports.html"





