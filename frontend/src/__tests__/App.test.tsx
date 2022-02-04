import React from "react";
import {render, screen} from "@testing-library/react";
import App from "../App";
import userEvent from "@testing-library/user-event";
import {createProduct, getProducts} from "../productsApiClient";

jest.mock("../productsApiClient");

const mockGetProducts = getProducts as jest.MockedFunction<typeof getProducts>;
const mockCreateProduct = createProduct as jest.MockedFunction<typeof createProduct>;

const addProduct = (product: string) => {
  userEvent.type(screen.getByLabelText("Product"), product);
  userEvent.click(screen.getByRole("button", {name: /submit/i}));
}

describe("inventory", () => {
  describe("when I view the inventory", () => {
    it("should display the products", async () => {
      mockGetProducts.mockResolvedValue(["a product"]);

      render(<App/>);

      expect(screen.getByText("Parts Unlimited Warehouse")).toBeInTheDocument();
      expect(screen.getByText("Product")).toBeInTheDocument();
      expect(await screen.findByText("a product")).toBeInTheDocument();
    });
  });

  describe("when I add a new product", () => {
    it("should display the new product", async () => {
      mockCreateProduct.mockResolvedValueOnce("shiny new product");
      mockGetProducts.mockResolvedValueOnce([]);
      mockGetProducts.mockResolvedValueOnce(["shiny new product"]);

      render(<App/>);
      addProduct("shiny new product");

      expect(mockCreateProduct).toHaveBeenCalledWith("shiny new product");
      expect(await screen.findByText("shiny new product")).toBeInTheDocument();
    });
  });
});
