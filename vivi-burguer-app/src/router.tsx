import { Outlet, RouterProvider, createBrowserRouter } from "react-router-dom";
import React, { useState } from "react";

import Home from "./pages/Home";
import Products from "./pages/Products";
import Checkout from "./pages/Checkout";
import { ShoppingCartProvider } from "./context/ShoppingCartContext";
import ShoppingCartSlideOver from "./components/ShoppingCartSlideOver";
import Login from "./pages/Login";
import Register from "./pages/Register";
import { LoginProvider } from "./context/LoginContext";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import Profile from "./pages/Profile";

const Default = () => {
  const [isCartOpen, setIsCartOpen] = useState(false);
  return (
    <>
      <LoginProvider>
        <ShoppingCartProvider setIsCartOpen={setIsCartOpen}>
          <ToastContainer />
          <ShoppingCartSlideOver isOpen={isCartOpen} />
          <Outlet />
        </ShoppingCartProvider>
      </LoginProvider>
    </>
  );
};

const router = createBrowserRouter(
  [
    {
      path: "/",
      element: <Default />,
      children: [
        { path: "/", element: <Home /> },
        { path: "/login", element: <Login /> },
        { path: "/profile", element: <Profile /> },
        { path: "/register", element: <Register /> },
        { path: "/products", element: <Products /> },
        { path: "/checkout", element: <Checkout /> },
      ],
    },
  ],
  { basename: "/APS-2023.2/" }
);

export default function Router() {
  return <RouterProvider router={router} />;
}
