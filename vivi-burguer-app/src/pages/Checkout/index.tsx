/*
  This example requires some changes to your config:
  
  ```
  // tailwind.config.js
  module.exports = {
    // ...
    plugins: [
      // ...
      require('@tailwindcss/forms'),
      require(' '),
    ],
  }
  ```
*/
import React from "react";
import { useState } from "react";
import { RadioGroup } from "@headlessui/react";
import { CheckCircleIcon, TrashIcon } from "@heroicons/react/20/solid";
import DefaultPage from "../DefaultPage";
import { formatCurrency } from "../../utilities/currencyFormatter";
import { useShoppingCart } from "../../context/ShoppingCartContext";

const products = [
  {
    id: 1,
    title: "Basic Tee",
    href: "#",
    price: "$32.00",
    color: "Black",
    size: "Large",
    imageSrc:
      "https://tailwindui.com/img/ecommerce-images/checkout-page-02-product-01.jpg",
    imageAlt: "Front of men's Basic Tee in black.",
  },
  // More products...
];

const deliveryAddresses = [
  {
    id: 1,
    name: "Nome do Endereço 01",
    address: "Rua Frederico Moura, Franca - SP",
    complement: "Complemento do Endereço 01",
    zipcode: "14401-150",
  },
];

const paymentCards = [
  {
    id: 1,
    title: "Card 01",
    type: "credit",
    issuer: "mastercard",
    number: "**** 0001",
  },
  {
    id: 2,
    title: "Card 02",
    type: "debit",
    issuer: "visa",
    number: "**** 0002",
  },
];

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export default function Checkout() {
  const [selectedDeliveryAddress, setSelectedDeliveryAddress] = useState(
    deliveryAddresses[0]
  );
  const [selectedPaymentCard, setSelectedPaymentCard] = useState(
    paymentCards[0]
  );
  const { cartItems, removeFromCart, closeCart, getTotalFromCart } =
    useShoppingCart();

  return (
    <DefaultPage>
      <div className="bg-gray-50">
        <main className="mx-auto max-w-7xl px-4 pb-24 pt-16 sm:px-6 lg:px-8">
          <div className="mx-auto max-w-2xl lg:max-w-none">
            <h1 className="sr-only">Checkout</h1>

            <form className="lg:grid lg:grid-cols-2 lg:gap-x-12 xl:gap-x-16">
              <div>
                {/* Delivery address */}
                <div>
                  <RadioGroup
                    value={selectedDeliveryAddress}
                    onChange={setSelectedDeliveryAddress}
                  >
                    <RadioGroup.Label className="text-lg font-medium text-gray-900">
                      Delivery address
                    </RadioGroup.Label>

                    <div className="mt-4 grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-4">
                      {deliveryAddresses.map((deliveryAddress) => (
                        <RadioGroup.Option
                          key={deliveryAddress.id}
                          value={deliveryAddress}
                          className={({ checked, active }) =>
                            classNames(
                              checked
                                ? "border-transparent"
                                : "border-gray-300",
                              active ? "ring-2 ring-indigo-500" : "",
                              "relative flex cursor-pointer rounded-lg border bg-white p-4 shadow-sm focus:outline-none"
                            )
                          }
                        >
                          {({ checked, active }) => (
                            <>
                              <span className="flex flex-1">
                                <span className="flex flex-col">
                                  <RadioGroup.Label
                                    as="span"
                                    className="block text-sm font-medium text-gray-900"
                                  >
                                    {deliveryAddress.name}
                                  </RadioGroup.Label>
                                  <RadioGroup.Description
                                    as="span"
                                    className="mt-1 flex items-center text-sm text-gray-500"
                                  >
                                    {deliveryAddress.address}
                                  </RadioGroup.Description>
                                  <RadioGroup.Description
                                    as="span"
                                    className="mt-1 flex items-center text-sm text-gray-500"
                                  >
                                    {deliveryAddress.complement}
                                  </RadioGroup.Description>
                                  <RadioGroup.Description
                                    as="span"
                                    className="mt-1 flex items-center text-sm text-gray-500"
                                  >
                                    {deliveryAddress.zipcode}
                                  </RadioGroup.Description>
                                  <RadioGroup.Description
                                    as="span"
                                    className="mt-6 text-sm font-medium text-gray-900"
                                  >
                                    {formatCurrency(0)}
                                  </RadioGroup.Description>
                                </span>
                              </span>
                              {checked ? (
                                <CheckCircleIcon
                                  className="h-5 w-5 text-indigo-600"
                                  aria-hidden="true"
                                />
                              ) : null}
                              <span
                                className={classNames(
                                  active ? "border" : "border-2",
                                  checked
                                    ? "border-indigo-500"
                                    : "border-transparent",
                                  "pointer-events-none absolute -inset-px rounded-lg"
                                )}
                                aria-hidden="true"
                              />
                            </>
                          )}
                        </RadioGroup.Option>
                      ))}
                    </div>
                  </RadioGroup>
                </div>

                {/* Payment */}
                <div className="mt-10 border-t border-gray-200 pt-10">
                  <h2 className="text-lg font-medium text-gray-900">Payment</h2>

                  <RadioGroup
                    value={selectedPaymentCard}
                    onChange={setSelectedPaymentCard}
                  >
                    <div className="mt-4 grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-4">
                      {paymentCards.map((paymentCard) => (
                        <RadioGroup.Option
                          key={paymentCard.id}
                          value={paymentCard}
                          className={({ checked, active }) =>
                            classNames(
                              checked
                                ? "border-transparent"
                                : "border-gray-300",
                              active ? "ring-2 ring-indigo-500" : "",
                              "relative flex cursor-pointer rounded-lg border bg-white p-4 shadow-sm focus:outline-none"
                            )
                          }
                        >
                          {({ checked, active }) => (
                            <>
                              <span className="flex flex-1">
                                <span className="flex flex-col">
                                  <RadioGroup.Label
                                    as="span"
                                    className="block text-sm font-medium text-gray-900"
                                  >
                                    {paymentCard.title}
                                  </RadioGroup.Label>
                                  <RadioGroup.Description
                                    as="span"
                                    className="mt-1 flex items-center text-sm text-gray-500"
                                  >
                                    {paymentCard.type === "credit"
                                      ? "Credit Card"
                                      : "Debit Card"}
                                  </RadioGroup.Description>
                                  <RadioGroup.Description
                                    as="span"
                                    className="mt-1 flex items-center text-sm text-gray-500"
                                  >
                                    {paymentCard.number}
                                  </RadioGroup.Description>
                                  <RadioGroup.Label>
                                    <svg
                                      className="absolute h-8 w-auto sm:h-6 sm:flex-shrink-0 bottom-2 right-2"
                                      viewBox="0 0 36 24"
                                      aria-hidden="true"
                                    >
                                      <rect
                                        width={36}
                                        height={24}
                                        fill="#224DBA"
                                        rx={4}
                                      />
                                      <path
                                        fill="#fff"
                                        d="M10.925 15.673H8.874l-1.538-6c-.073-.276-.228-.52-.456-.635A6.575 6.575 0 005 8.403v-.231h3.304c.456 0 .798.347.855.75l.798 4.328 2.05-5.078h1.994l-3.076 7.5zm4.216 0h-1.937L14.8 8.172h1.937l-1.595 7.5zm4.101-5.422c.057-.404.399-.635.798-.635a3.54 3.54 0 011.88.346l.342-1.615A4.808 4.808 0 0020.496 8c-1.88 0-3.248 1.039-3.248 2.481 0 1.097.969 1.673 1.653 2.02.74.346 1.025.577.968.923 0 .519-.57.75-1.139.75a4.795 4.795 0 01-1.994-.462l-.342 1.616a5.48 5.48 0 002.108.404c2.108.057 3.418-.981 3.418-2.539 0-1.962-2.678-2.077-2.678-2.942zm9.457 5.422L27.16 8.172h-1.652a.858.858 0 00-.798.577l-2.848 6.924h1.994l.398-1.096h2.45l.228 1.096h1.766zm-2.905-5.482l.57 2.827h-1.596l1.026-2.827z"
                                      />
                                    </svg>
                                  </RadioGroup.Label>
                                </span>
                              </span>
                              {checked ? (
                                <CheckCircleIcon
                                  className="h-5 w-5 text-indigo-600"
                                  aria-hidden="true"
                                />
                              ) : null}
                              <span
                                className={classNames(
                                  active ? "border" : "border-2",
                                  checked
                                    ? "border-indigo-500"
                                    : "border-transparent",
                                  "pointer-events-none absolute -inset-px rounded-lg"
                                )}
                                aria-hidden="true"
                              />
                            </>
                          )}
                        </RadioGroup.Option>
                      ))}
                    </div>
                  </RadioGroup>
                </div>
              </div>

              {/* Order summary */}
              <div className="mt-10 lg:mt-0">
                <h2 className="text-lg font-medium text-gray-900">
                  Order summary
                </h2>

                <div className="mt-4 rounded-lg border border-gray-200 bg-white shadow-sm">
                  <h3 className="sr-only">Items in your cart</h3>
                  <ul role="list" className="divide-y divide-gray-200">
                    {cartItems.map((product) => (
                      <li key={product.id} className="flex px-4 py-6 sm:px-6">
                        <div className="flex-shrink-0">
                          <img
                            src={product.imageUrl}
                            className="w-20 rounded-md"
                          />
                        </div>

                        <div className="ml-6 flex flex-1 flex-col">
                          <div className="flex">
                            <div className="min-w-0 flex-1">
                              <h4 className="text-sm">
                                <a className="font-medium text-gray-700 hover:text-gray-800">
                                  {product.name}
                                </a>
                              </h4>
                              <p className="mt-1 text-sm text-gray-500">
                                x{product.quantity}
                              </p>
                            </div>

                            <div className="ml-4 flow-root flex-shrink-0">
                              <button
                                onClick={() => removeFromCart(product)}
                                type="button"
                                className="-m-2.5 flex items-center justify-center bg-white p-2.5 text-gray-400 hover:text-gray-500"
                              >
                                <span className="sr-only">Remove</span>
                                <TrashIcon
                                  className="h-5 w-5"
                                  aria-hidden="true"
                                />
                              </button>
                            </div>
                          </div>

                          <div className="flex flex-1 items-end justify-between pt-2">
                            <p className="mt-1 text-sm font-medium text-gray-900">
                              {formatCurrency(product.price * product.quantity)}
                            </p>
                          </div>
                        </div>
                      </li>
                    ))}
                  </ul>
                  <dl className="space-y-6 border-t border-gray-200 px-4 py-6 sm:px-6">
                    <div className="flex items-center justify-between">
                      <dt className="text-sm">Subtotal</dt>
                      <dd className="text-sm font-medium text-gray-900">
                        {formatCurrency(getTotalFromCart())}
                      </dd>
                    </div>
                    <div className="flex items-center justify-between">
                      <dt className="text-sm">Taxes</dt>
                      <dd className="text-sm font-medium text-gray-900">
                        {formatCurrency(0)}
                      </dd>
                    </div>
                    <div className="flex items-center justify-between border-t border-gray-200 pt-6">
                      <dt className="text-base font-medium">Total</dt>
                      <dd className="text-base font-medium text-gray-900">
                        {formatCurrency(getTotalFromCart())}
                      </dd>
                    </div>
                  </dl>

                  <div className="border-t border-gray-200 px-4 py-6 sm:px-6">
                    <button
                      type="submit"
                      disabled={cartItems.length === 0}
                      className="w-full rounded-md border border-transparent bg-indigo-600 px-4 py-3 text-base font-medium text-white shadow-sm hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2 focus:ring-offset-gray-50 disabled:opacity-50 disabled:cursor-not-allowed"
                    >
                      Confirm order
                    </button>
                  </div>
                </div>
              </div>
            </form>
          </div>
        </main>
      </div>
    </DefaultPage>
  );
}
