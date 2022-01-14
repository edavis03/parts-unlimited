import React, { FormEvent, useEffect, useState } from "react";
import "./App.css";
import { createTeam, getTeams } from "./teamsApiClient";

function App() {
  const [teams, setTeams] = useState<string[]>([]);
  const [teamName, setTeamName] = useState<string>("");

  const setTeamNameFromInput = (e: FormEvent<HTMLInputElement>) => {
    setTeamName(e.currentTarget.value);
  };

  const submitForm = (e: FormEvent) => {
    e.preventDefault();
    createTeam(teamName);
    setTeams(getTeams());
  };

  useEffect(() => {
    setTeams(getTeams());
  }, []);

  return (
    <>
      <ul>
        {teams.map((team, i) => (
          <li key={i}>{team}</li>
        ))}
      </ul>

      <form onSubmit={submitForm}>
        <label>
          Team Name
          <input name="team-name" type="text" onChange={setTeamNameFromInput} />
        </label>
        <button type="submit">Submit</button>
      </form>
    </>
  );
}

export default App;
