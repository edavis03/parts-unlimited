import axios from "axios";

export async function createProduct(teamName: string): Promise<string> {
  return (await axios.post<string>("/products", teamName, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function getProducts(): Promise<string[]> {
  return (await axios.get<string[]>("/products")).data
}
