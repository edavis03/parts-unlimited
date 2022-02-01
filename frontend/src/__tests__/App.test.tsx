import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {createTeam, getTeams} from "../teamsApiClient";

jest.mock("../teamsApiClient");

const mockGetTeams = getTeams as jest.MockedFunction<typeof getTeams>;
const mockCreateTeam = createTeam as jest.MockedFunction<typeof createTeam>;

const addTeam = (teamName: string) => {
  userEvent.type(screen.getByLabelText("Team Name"), teamName);
  userEvent.click(screen.getByRole("button", {name: /submit/i}));
}

describe("Teams Page", () => {
  describe("when I view teams", () => {
    it("should display existing teams", async () => {
      mockGetTeams.mockResolvedValue(["a team", "a second team"]);

      render(<App/>);

      expect(await screen.findByText("a team")).toBeInTheDocument();
      expect(screen.getByText("a second team")).toBeInTheDocument();
    });
  });

  describe("when I add a new team", () => {
    it("should display the new team in existing teams", async () => {
      mockCreateTeam.mockResolvedValueOnce("a team");
      mockGetTeams.mockResolvedValueOnce([]);
      mockGetTeams.mockResolvedValueOnce(["a team"]);

      render(<App/>);
      addTeam("a team");

      expect(mockCreateTeam).toHaveBeenCalledWith("a team");
      expect(await screen.findByText("a team")).toBeInTheDocument();
    });
  });
});
