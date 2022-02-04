const addProduct = (product: string) => {
  cy.findByLabelText("Product").type(product);
  cy.findByRole("button").click();
}
describe("inventory", () => {
  describe("when adding a product offering", () => {
    it("should display the new product", () => {
      cy.visit("http://localhost:8080");
      addProduct("shiny-new-product");
      cy.findByText("shiny-new-product").should("exist");
    });
  });
});
