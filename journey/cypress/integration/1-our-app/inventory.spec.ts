const addProduct = (product: string) => {
  cy.findByLabelText("Product to add").type(product);
  cy.findByRole("button").click();
}
describe("inventory", () => {
  describe("when adding a product offering", () => {
    it("should display the new product with a default quantity of 0", () => {
      cy.visit("http://localhost:8080");
      addProduct("shiny-new-product");
      cy.findByText("shiny-new-product").should("exist");
      cy.findByText("0").should("exist");
    });
  });
});
