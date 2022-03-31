import React, {FormEvent, useEffect, useState} from "react";
import {createProduct, getProducts} from "./productsApiClient";
import {Box, Container} from "@mui/material";
import {Product} from "./product";

const App = () => {
    const [products, setProducts] = useState<Product[]>([]);
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
            <h1>Parts Unlimited Inventory</h1>
            <Box display='flex' flexDirection='row'>
                <Box>
                    <h2>Product</h2>
                    {products.map((product, index) => (
                        <div key={index}>{product.name}</div>
                    ))}
                    <form onSubmit={submitForm}>
                        <br/>
                        <label>
                            Product to add
                            <input name="product" type="text" onChange={setProductNameFromInput}/>
                        </label>
                        <button type="submit">Submit</button>
                    </form>
                </Box>
                <Box>
                    <h2>Quantity</h2>
                    {products.map((product, index) => (
                        <div key={index}>{product.quantity}</div>
                    ))}
                </Box>
            </Box>
            <form>
                <select>

                </select>
            </form>
        </Container>
    );
}

export default App;
