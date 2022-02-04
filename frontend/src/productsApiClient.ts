import axios from "axios";

export async function createProduct(product: string): Promise<string> {
  return (await axios.post<string>("/products", product, {headers: {'Content-Type': 'text/plain'}})).data
}

export async function getProducts(): Promise<string[]> {
  return (await axios.get<string[]>("/products")).data
}
