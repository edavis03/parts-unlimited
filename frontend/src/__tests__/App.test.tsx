import React from "react";
import { render, screen } from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import { when } from "jest-when";
import { createTeam, getTeams } from "../teamsApiClient";

jest.mock("../teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("Teams Page", () => {
  describe("when the page loads", () => {
    beforeEach(() => {
      getTeamsApiClient.mockReturnValue(["first-team", "second-team"]);
      render(<App />);
    });

    it("requests the teams from the api", () => {
      const listItems = screen.getAllByRole("listitem");
      expect(listItems[0].innerHTML).toEqual("first-team");
      expect(listItems[1].innerHTML).toEqual("second-team");
    });
  });

  describe("creating", () => {
    beforeEach(() => {
      when(createTeam)
        .calledWith("example-team-name")
        .mockReturnValueOnce("example-team-name");

      getTeamsApiClient.mockReturnValueOnce([]);
      getTeamsApiClient.mockReturnValueOnce(["example-team-name"]);

      render(<App />);
    });

    it("appends the team name to the list", async () => {
      const teamNameInput = screen.getByLabelText("Team Name");
      userEvent.type(teamNameInput, "example-team-name");
      userEvent.click(screen.getByRole("button", { name: /submit/i }));
      expect(await screen.findByText("example-team-name")).toBeVisible();
    });
  });
});
