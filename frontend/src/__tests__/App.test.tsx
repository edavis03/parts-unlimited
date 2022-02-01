import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {createTeam, getTeams} from "../teamsApiClient";

jest.mock("../teamsApiClient");

const getTeamsApiClient = getTeams as jest.MockedFunction<typeof getTeams>;

describe("Teams Page", () => {
  describe("when I view teams", () => {
    it("should display existing teams", async () => {
      getTeamsApiClient.mockResolvedValue(["a team", "a second team"]);

      render(<App/>);

      const listItems = await screen.findAllByRole("listitem");
      expect(listItems[0].innerHTML).toEqual("a team");
      expect(listItems[1].innerHTML).toEqual("a second team");
    });
  });

  describe("when I add a new team", () => {
    it("should display the new team in existing teams", async () => {
      when(createTeam)
        .calledWith("a team")
        .mockResolvedValueOnce("a team");

      getTeamsApiClient.mockResolvedValueOnce([]);
      getTeamsApiClient.mockResolvedValueOnce(["a team"]);

      render(<App/>);

      userEvent.type(screen.getByLabelText("Team Name"), "a team");
      userEvent.click(screen.getByRole("button", {name: /submit/i}));
      expect(await screen.findByText("a team")).toBeVisible();
    });
  });
});
