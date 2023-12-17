import { ReactNode, createContext, useContext, useState } from "react";
import React from "react";
import ShoppingCartSlideOver from "../components/ShoppingCartSlideOver";
import { useLocalStorage } from "../hooks/useLocalStorage";

type ShoppingCartProviderProps = {
    children: ReactNode;
    setIsCartOpen: any;
}

export type CartItem = {
    id: number
    name: string;
    price: number;
    description: string;
    imageUrl: string;
    quantity: number
}

type ShoppingCartContext = {
    openCart: () => void
    closeCart: () => void
    getItemQuantity: (cartItem: CartItem) => number
    increaseCartQuantity: (cartItem: CartItem) => void
    decreaseCartQuantity: (cartItem: CartItem) => void
    removeFromCart: (cartItem: CartItem) => void
    getTotalFromCart: () => number
    cartQuantity: number
    cartItems: CartItem[]
}

const ShoppingCartContext = createContext({} as ShoppingCartContext);

export function useShoppingCart() {
    return useContext(ShoppingCartContext);
}

export function ShoppingCartProvider({ children, setIsCartOpen }: ShoppingCartProviderProps) {
    const [cartItems, setCartItems] = useLocalStorage<CartItem[]>("shopping-cart", []);
    const cartQuantity = cartItems.reduce((quantity, item) => item.quantity + quantity, 0)

    const openCart = () => setIsCartOpen(true)
    const closeCart = () => setIsCartOpen(false)

    function getItemQuantity(cartItem: CartItem) {
        return cartItems.find(item => item.id === cartItem.id)?.quantity || 0
    }

    function increaseCartQuantity(cartItem: CartItem) {
        setCartItems(currItems => {
            if (currItems.find(item => item.id === cartItem.id) == null) {
                cartItem.quantity = 1
                return [...currItems, cartItem]
            }
            else {
                return currItems.map(item => {
                    if (item.id === cartItem.id) {
                        return { ...item, quantity: item.quantity + 1 }
                    }
                    else {
                        return item
                    }
                })

            }
        })
    }

    function decreaseCartQuantity(cartItem: CartItem) {
        setCartItems(currItems => {
            if (currItems.find(item => item.id === cartItem.id)?.quantity === 1) {
                return currItems.filter(item => item.id !== cartItem.id);
            }
            else {
                return currItems.map(item => {
                    if (item.id === cartItem.id) {
                        return { ...item, quantity: item.quantity - 1 }
                    }
                    else {
                        return item
                    }
                })

            }
        })
    }

    function removeFromCart(cartItem: CartItem) {
        setCartItems(currItems => {
            return currItems.filter(item => item.id !== cartItem.id)
        })
    }

    function getTotalFromCart() {
        return cartItems.reduce((total, item) => item.price * item.quantity + total, 0)
    }

    return (
        <ShoppingCartContext.Provider value={{ openCart, closeCart, getItemQuantity, increaseCartQuantity, decreaseCartQuantity, removeFromCart, getTotalFromCart, cartQuantity, cartItems }}>
            {children}
        </ShoppingCartContext.Provider >
    )
}