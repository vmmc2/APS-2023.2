import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";
import React, { useState } from "react";

import Home from "./pages/Home";
import Products from "./pages/Products";
import Checkout from "./pages/Checkout";
import ShoppingCartSlideOver from "./components/ShoppingCartSlideOver";

const AppLayout = () => {
  const [isCartOpen, setIsCartOpen] = useState(false);
  return (
    <>
      <ShoppingCartSlideOver isOpen={isCartOpen} />
      <Outlet />
    </>
  );
};

const router = createBrowserRouter([
  {
    path: '/',
    element: <AppLayout />,
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
