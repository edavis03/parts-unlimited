import React, { FormEvent, useEffect, useState } from "react";
import { createProduct, getProducts } from "./productsApiClient";

const App = () => {
  const [products, setProducts] = useState<string[]>([]);
  const [productName, setProductName] = useState<string>("");

  const setProductNameFromInput = (event: FormEvent<HTMLInputElement>) => {
    setProductName(event.currentTarget.value);
  };

  const submitForm = (event: FormEvent) => {
    event.preventDefault();
    createProduct(productName).then(() => {
      getProducts().then(setProducts);
    });
  };

  useEffect(() => {
    getProducts().then(setProducts);
  }, []);

  return (
    <>
      <ul>
        {products.map((product, index) => (
          <li key={index}>{product}</li>
        ))}
      </ul>

      <form onSubmit={submitForm}>
        <label>
          Product
          <input name="product" type="text" onChange={setProductNameFromInput} />
        </label>
        <button type="submit">Submit</button>
      </form>
    </>
  );
}

export default App;
