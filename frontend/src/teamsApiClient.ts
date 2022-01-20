import axios from "axios";

export async function createTeam(teamName: string): Promise<string> {
  return (await axios.post<string>("/team", teamName, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function getTeams(): Promise<string[]> {
  return (await axios.get<string[]>("/teams")).data
}
