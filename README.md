# Developer ASI Solo Exercise  
This repository contains the content and instructions for your ASI Solo Exercise.

## Logistics

### Schedule
* Overview from Bill
* Time to read through the stories
* Come together as a group to ask questions
* Start development!

### Setup
* Fork this repo to your GitHub account and pull it to your laptop.
* Build and run the codebase on your laptop with support from a proctor/facilitator.
* Begin working on the project user stories which are listed below.

### Rules
* Work alone, utilize only your own skills, and don't discuss the project with anyone except the proctor.
* You may use _any_ online resources (GitHub, StackOverflow, your team's codebase, etc.) that don’t directly relate to solving problems in this domain (e.g. don’t google ‘how to implement tic-tac-toe’ if the exercise is a tic-tac-toe game).
* Work on the project until the end of the day (17:30). Don't work on the exercise after that time or during our 1-hour lunch break.
* Only your code that has been pushed to GitHub before the end of the day will be considered. You might consider committing/pushing frequently.

### Scoring
It’s up to you to balance feature completion with the quality of your result. It’s okay if you don’t complete all of the stories in the allotted time, but it would be good if you can.  
These are good things:
* Completing stories
* Test suite is comprehensive and understandable
* Behavior is correct (no significant bugs)
* Code is readable. Consider naming, size of methods/classes, consistency, and organization.
* Code is testable and flexible. It should be easy to add new features and refactor.
* Product is easy to use for the end user. 

### How to approach the exercise
* Work as similarly as possible to how you approach stories on any other day.   
* Evolve the code design and user experience as you see fit. For instance, you have access to MUI and can use as much or little as you like.  
* It's okay to ask the proctor for help if you are having trouble with a tool/framework/syntax.
* You can get clarification of the problem or make reasonable assumptions about the domain.  
* Work on one thing at a time, stay in a TDD workflow, and commit frequently. Don't hesitate to revert to the previous commit if things get complex.
* Don't rush. You're better off writing clean code than rushing to complete user stories.

# Parts Unlimited Project
Parts Unlimited, a large automobile parts manufacturer and supplier, needs to manage their inventory and fulfill orders from stores.

Your team is tasked with creating an application that manages warehouse inventory to help Inventory Managers keep track of orders and quantities of available products. The product manager expects you to implement the user stories in the order provided and complete each story before working on the next one.

# User stories
## Story: Add product (Already Completed ✅)
### Narrative
```
As an inventory manager,  
I want to create an inventory of products,  
so that store owners and I can see what products are offered.  
```
### Acceptance Criteria
```
When I add a product  
Then I can see that product listed in my product inventory. 
```
### Example
**Parts Unlimited Inventory**
| Product |
| :------ |
| Spark Plug - Champion Iridium - 9016 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 |

### Notes
- Products can be represented as a single string.
- Rows do not need to be distinguished with a color.
- Lines/delimiters are not needed to demark the table. 

## Story: Product quantities (Already Completed ✅)
### Narrative
```
As an inventory manager,  
I want to be able to view current inventory levels,  
so that I can know what products are in stock. 
```
### Acceptance Criteria
```
Given at least one product
When I view the inventory
Then I see a quantity for each product
```
### Example
**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 0 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |
### Notes
- Quantities are initially 0

## Story: Increase inventory for a product
### Narrative
```
As an inventory manager,  
I want to be able to add quantities of products to our inventory,  
so that the actual inventory levels are visible to store managers.  
```
### Acceptance Criteria
```
Given a product  
When I add an inventory quantity of that product  
Then I see that the total inventory for that product has increased by the amount added  
```
### Example
**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 0 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |  

Allow the user to select a product, enter a quantity, and indicate that they want to add that quantity. 
One way to do this is with a dropdown, a text box, and an 'Add' button, but you are free to choose whatever design
you feel accomplishes the job.

Display the updated quantity. For instance, if they added 12 Spark plugs, the result would be...

**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 12 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |  

## Story: Place order that can be completely fulfilled
### Narrative
```
As an inventory manager,  
I want to be able to place an order for a product, 
so that stores will have products to sell to their customers
```
### Acceptance Criteria
```
Given an in-stock product
When I order an amount of that product  
And that amount is in stock  
Then I see that the total inventory for that product has decreased by the amount ordered  
And I see a confirmation of the quantity of product that will be delivered  
```
### Example
**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 12 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |  

Use a pattern similar to increasing inventory to place an order. 

If the user ordered 5 Spark Plugs, the result would look like this...

**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 7 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |  

You will receive “Spark Plug - Champion Iridium - 9016” x 5.  
## Story: Place order that can only be partially fulfilled
### Narrative
```
As an inventory manager,  
I want to be able to place an order for a product even when that order is too large,
so that store will have more product to sell to their customers than if the order was not fulfilled at all. 
```
### Acceptance Criteria
```
Given an in-stock product
When I order an amount of that product that is greater than the current inventory
Then I see that the total inventory for that product becomes zero
And I see a confirmation of the quantity of product that will be delivered
And I see a notice that the order was partially fulfilled
```
### Example
**Parts Unlimited Inventory**
| Product | Quantity |
| :------ | :------- |
| Spark Plug - Champion Iridium - 9016 | 0 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart - 124315 | 0 |  

You will receive “Spark Plug - Champion Iridium - 9016” x 12.  
**Note that your order was NOT completely fulfilled. Your delivery will be short 6 items.**

### Notes
- The emphasis for the Note can be styled as you see fit.

## Story: Model Number
### Narrative
```
As an inventory manager,  
I want to associate model numbers with products, 
so that I can select products by model number.
```
### Acceptance Criteria
```
When I add a product  
Then I can see that product's model number listed.  
```
### Example
**Parts Unlimited Inventory**
| Product  | Model Number | Quantity |
| :------ | :------- | :------- |
| Spark Plug - Champion Iridium | 9016 | 12 |
| Full Synthetic Motor Oil 5W-30 - Mobil 1 Advanced, 1 Quart | 124315 | 0 |

## Story: Search 
### Narrative
```
As an inventory manager,  
I want to be able to search for a product by model number, 
so that I can see that product's information when I only know its model number.
```
### Acceptance Criteria
```
When I search for a product by model number   
Then I see that product's information.   
```

### Note
- Display nothing if the product is not found.
- It's up to you as to how you want to design this.

___

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


## Additional Notes
* If journey tests fail for duplicates, restart the application to get a clean DB
