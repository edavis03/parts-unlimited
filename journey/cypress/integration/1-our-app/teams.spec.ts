const addTeam = (team: string) => {
  cy.findByLabelText("Team Name").type(team);
  cy.findByRole("button").click();
}
describe("teams", () => {
  describe("when adding a team", () => {
    it("should display the new team", () => {
      cy.visit("http://localhost:8080");
      addTeam("our-team-name");
      cy.findByText("our-team-name").should("exist");
    });
  });
});
