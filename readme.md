## This is an application to track inventory for a logistic company

## Features:
#### Inventory
- Create inventory items
- Update an inventory items
- Delete an inventory item
- View a list of inventory items
#### Warehouse
- Create a warehouse
- assign inventories to specific warehouse
## How to use this application
- app installation
  - clone this repo to your local machine
  ```
  git clone git@github.com:DoRightThingWill/InventoryTrackingApp.git
  ```
- start app in local
  ```
  src/main/InventoryTrackApplication  run main()
  ```
- in browser visit the 
  ``` 
  http://localhost:8080/swagger-ui/index.html#/
  ```
- as per instruction, type in parameters to call API and view responses accordingly
## Tech Stack
- Spring Boot
- Spring MVC
- Spring REST
- Gradle
- Lombok
- Flyway

## API Document
```
http://localhost:8080/v3/api-docs
```

## Future works
- Tests
  - integration test
  - end-to-end test
- Data persistence
  - in current repo, an H2 database with file based storage is used. For actual production in large scale, we could use other more scalable database, like MySQL or PGSql provided by AWS RDS.
  - to make this database migration easier, we used a tool called Flyway in the project.
- CI/CD
  - set up continuous integration and continuous deployment via tools like Github action, TravisCI, etc
  - deploy the application onto cloud servers, like AWS, Azure, or GCP
