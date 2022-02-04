import React, {FormEvent, useEffect, useState} from "react";
import {createProduct, getProducts} from "./productsApiClient";
import {Container} from "@mui/material";

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
        <Container sx={{mx: 1, my: 1}}>
            <h1>Parts Unlimited Warehouse</h1>
            <h2>Product</h2>
            {products.map((product, index) => (
                <div key={index}>{product}</div>
            ))}
            <form onSubmit={submitForm}>
                <br/>
                <label>
                    Product to add
                    <input name="product" type="text" onChange={setProductNameFromInput}/>
                </label>
                <button type="submit">Submit</button>
            </form>
        </Container>
    );
}

export default App;
