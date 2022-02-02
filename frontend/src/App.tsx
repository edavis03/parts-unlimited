import React, { FormEvent, useEffect, useState } from "react";
import { createTeam, getTeams } from "./teamsApiClient";

const App = () => {
  const [teams, setTeams] = useState<string[]>([]);
  const [teamName, setTeamName] = useState<string>("");

  const setTeamNameFromInput = (event: FormEvent<HTMLInputElement>) => {
    setTeamName(event.currentTarget.value);
  };

  const submitForm = (event: FormEvent) => {
    event.preventDefault();
    createTeam(teamName).then(() => {
      getTeams().then(setTeams);
    });
  };

  useEffect(() => {
    getTeams().then(setTeams);
  }, []);

  return (
    <>
      <ul>
        {teams.map((team, index) => (
          <li key={index}>{team}</li>
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
