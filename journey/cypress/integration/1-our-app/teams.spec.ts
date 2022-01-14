describe("teams", () => {
  describe("creating a team", () => {
    function addTeam(team: string) {
      cy.findByLabelText("Team Name").type(team);
      cy.findByRole("button").click();
    }

    it("updates the displayed list of teams", () => {
      cy.visit("http://localhost:3000");
      addTeam("team-name");
      cy.findByText("team-name").should("exist");
    });
  });
});
