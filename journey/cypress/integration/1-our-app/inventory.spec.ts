const addProduct = (product: string) => {
    cy.findByLabelText("Product to add").type(product);
    cy.findByRole("button").click();
}

const viewInventory = () => {
    cy.visit("http://localhost:8080");
};

const createMyPrettyProduct = () => {
    addProduct("MyPrettyProduct");
};

const increaseQuantityOfMyPrettyProductByOneHundred = () => {
    cy.findByRole("combobox").select("MyPrettyProduct");
    cy.findByLabelText("quantity").type("100");
    cy.findByRole("button", {name: /update/i}).click();
};

const expectToSeeOneHundred = () => {
    cy.findByText("100").should("exist");
};

describe("inventory", () => {
    describe("when adding a product offering", () => {
        it("should display the new product with a default quantity of 0", () => {
            viewInventory();
            createMyPrettyProduct();
            cy.findByText("MyPrettyProduct").should("exist");
            cy.findByText("0").should("exist");
        });
    });

    describe("when adding a quantity to an existing product", () => {
        it("should display the increased inventory for the product", () => {
            viewInventory();

            //OUR TESTS ARE NOT INDEPENDENT, SO WE ARE USING THE PRODUCT MADE IN THE PREVIOUS TEST
            increaseQuantityOfMyPrettyProductByOneHundred();

            expectToSeeOneHundred();
        })
    });

});
