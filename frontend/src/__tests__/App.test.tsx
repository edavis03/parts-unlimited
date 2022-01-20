import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {when} from "jest-when";
import {createTeam, getTeams} from "../teamsApiClient";

jest.mock("../teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("Teams Page", () => {
  describe("when the page loads", () => {
    it("requests the teams from the api", async () => {
      getTeamsApiClient.mockResolvedValue(["first-team", "second-team"]);

      render(<App/>);

      const listItems = await screen.findAllByRole("listitem");
      expect(listItems[0].innerHTML).toEqual("first-team");
      expect(listItems[1].innerHTML).toEqual("second-team");
    });
  });

  describe("creating", () => {

    it("appends the team name to the list", async () => {
      when(createTeam)
        .calledWith("example-team-name")
        .mockResolvedValueOnce("example-team-name");

      getTeamsApiClient.mockResolvedValueOnce([]);
      getTeamsApiClient.mockResolvedValueOnce(["example-team-name"]);

      render(<App/>);

      userEvent.type(screen.getByLabelText("Team Name"), "example-team-name");
      userEvent.click(screen.getByRole("button", {name: /submit/i}));
      expect(await screen.findByText("example-team-name")).toBeVisible();
    });
  });
});
