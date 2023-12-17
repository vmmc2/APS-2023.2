import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";
import React, { useState } from "react";

import Home from "./pages/Home";
import Products from "./pages/Products";
import Checkout from "./pages/Checkout";
import { ShoppingCartProvider } from "./context/ShoppingCartContext";
import ShoppingCartSlideOver from "./components/ShoppingCartSlideOver";

const Default = () => {
  const [isCartOpen, setIsCartOpen] = useState(false);
  return (
    <>
      <ShoppingCartProvider setIsCartOpen={setIsCartOpen}>
        <ShoppingCartSlideOver isOpen={isCartOpen} />
        <Outlet />
      </ShoppingCartProvider>
    </>
  );
};

const router = createBrowserRouter([
  {
    path: '/',
    element: <Default />,
    children: [
      { path: '/', element: <Home /> },
      { path: '/register', element: <Home /> },
      { path: '/products', element: <Products /> },
      { path: '/checkout', element: <Checkout /> },
    ],
  },
],
  { basename: "/APS-2023.2/" }
);

export default function Router() {
  return <RouterProvider router={router} />;
}
