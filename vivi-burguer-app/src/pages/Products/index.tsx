import React from "react";
import ProductsList from "../../components/ProductsList";
import DefaultPage from "../DefaultPage";
import { CartItem } from "../../context/ShoppingCartContext";

function Products() {
  const people: Array<CartItem> = [
    {
      id: 0,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 1,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 2,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 3,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 4,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 5,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 6,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
    {
      id: 7,
      name: 'Classic Burguer',
      price: 5.00,
      description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis varius.',
      imageUrl: 'https://churrasco.coz.br/wp-content/uploads/2021/01/hamburguer-gourmet.jpg',
      quantity: 0
    },
  ]

  return (
    <DefaultPage>
      <ProductsList products={people} />
    </DefaultPage>
  )
}

export default Products;
