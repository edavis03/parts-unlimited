# ASI Exercise
## PARTS UNLIMITED Project
### Context
Parts Unlimited, a large automobile parts manufacturer and supplier, needs to manage their inventory and fulfill orders from stores.

Your team is tasked with creating an application that manages warehouse inventory to help Inventory Managers keep track of orders and quantities of available products. The product manager expects you to implement the user stories in the order provided and complete each story before working on the next one.

### User stories
#### Add product
_Narrative_  
```
As an inventory manager,  
I want to create an inventory of products,  
so that store owners and I can see what products are offered.  
```

_Acceptance Criteria_
```
When I add a product  
Then I can see that product listed in my product inventory. 
```

_Example_  
**Parts Unlimited Inventory**
| Product |
| :------ |
| Spark Plug - Champion Iridium - 9016 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 |

_Notes_  
```
* Products can be represented as a single string.
* Row do not need to be distiguished with a color.
* Lines/delimiters are not needed to demark the table. 
```
#### Name
_Narrative_  
```

```

_Acceptance Criteria_
```
```

_Example_  
**Parts Unlimited Inventory**
| Product |
| :------ |
| Spark Plug - Champion Iridium - 9016 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 |

_Notes_  
```
```

#### Product quantities
_Narrative_  
```
As a store manager,  
I want to be able to view current inventory levels,  
so that I can know what products are in stock. 
```

_Acceptance Criteria_
```
Given at least one product
When I view the inventory
Then I see a quantity for each product
```

_Example_  
**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 12 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |

_Notes_  
```
* Quantities are initially 0
```
#### Name
_Narrative_  
```

```

_Acceptance Criteria_
```
```

_Example_  
**Parts Unlimited Inventory**
| Product |
| :------ |
| Spark Plug - Champion Iridium - 9016 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 |

_Notes_  
```
```

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
* Make it clear that MUI is an available dependency but that the soldiers don't need to use it if they don't want to
