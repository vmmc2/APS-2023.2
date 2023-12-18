import React, { Fragment, useState, useRef, useEffect } from "react";
import DefaultPage from "../DefaultPage";
import { useLogin } from "../../context/LoginContext";
import {
  CheckCircleIcon,
  CheckIcon,
  CreditCardIcon,
  ExclamationTriangleIcon,
  MapPinIcon,
  TrashIcon,
} from "@heroicons/react/24/outline";
import { Button } from "react-bootstrap";
import { Dialog, RadioGroup, Transition } from "@headlessui/react";
import Cards from "react-credit-cards-2";
import "react-credit-cards-2/dist/es/styles-compiled.css";
import { deliveryAddresses } from "../../mock";
import api from "../../services/api";
import { showPromiseToast } from "../../utilities/toastWindow";
import { useNavigate } from "react-router-dom";
import { PaymentCard } from "../../types";
import { useQueries, useQuery } from "@tanstack/react-query";

function classNames(...classes) {
  return classes.filter(Boolean).join(" ");
}

export const paymentMethods = ["Credit", "Debit"];

function Profile() {
  const navigate = useNavigate();
  const { login, setLogin } = useLogin();

  const [state, setState] = useState<PaymentCard[]>([]);
  const { data, isLoading } = useQuery<any>({
    queryKey: [
      "cartao/get",
      "GET",
      { headers: { Authorization: login.token }, params: { cpf: login.cpf } },
    ],
    retry: false,
  });

  useEffect(() => {
    if (!isLoading) {
      let paymentCards: PaymentCard[] = [];
      data.forEach((element) => {
        paymentCards.push({
          name: element["titular"],
          type: element["tipoCartao"],
          lastNumbers: element["numero"].slice(-4),
          issuer: element["bandeira"],
        });
      });
      setState(paymentCards);
    }
  }, [data]);

  const [openCardRegister, setOpenCardRegister] = useState(false);
  const [cardRegisterState, setCardRegisterState] = useState({
    number: "",
    expiry: "",
    cvc: "",
    name: "",
    focus: "",
  });

  const [openDeleteAccount, setOpenDeleteAccount] = useState(false);
  const deleteAccountCancelButtonRef = useRef(null);

  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState(
    paymentMethods[0]
  );

  const handleInputChange = (evt) => {
    const { name, value } = evt.target;

    setCardRegisterState((prev) => ({ ...prev, [name]: value }));
  };

  const handleInputFocus = (evt) => {
    setCardRegisterState((prev) => ({ ...prev, focus: evt.target.name }));
  };

  const [form, setForm] = useState({
    senha: "",
  });
  function tryDeleteAccount() {
    const promise = api
      .delete("conta/remover", {
        params: {
          email: login.email,
          senha: form["senha"],
        },
        headers: {
          Authorization: login.token,
        },
      })
      .then((response) => {
        setLogin({
          email: "",
          nome: "",
          token: "",
        });
        navigate("/");
      });
    showPromiseToast(
      {
        pending: "Trying to delete account...",
        success: "Successfully deleted account",
        error: "Delete account attempt failed",
      },
      promise
    );
  }
  const changeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
    setForm({ ...form, [event.currentTarget.name]: event.currentTarget.value });
  };

  function tryRegisterCard() {
    const promise = api
      .post(
        "cartao/adicionar",
        {
          numero: cardRegisterState.number,
          cvv: cardRegisterState.cvc,
          titular: cardRegisterState.name,
          bandeira: "mastercard",
          dataValidade: cardRegisterState.expiry,
          tipoCartao: selectedPaymentMethod,
          cpf: login.cpf,
        },
        {
          headers: {
            Authorization: login.token,
          },
        }
      )
      .then((response) => {
        console.log(response);
      });
    showPromiseToast(
      {
        pending: "Trying to register card...",
        success: "Successfully registered card",
        error: "Register card failed",
      },
      promise
    );
  }

  return (
    <>
      <DefaultPage>
        <div className="p-0">
          <Button
            className="absolute bg-red-700 hover:bg-red-800 text-white font-bold py-1 px-3 rounded btn btn-primary right-0 mr-20"
            onClick={() => {
              setOpenDeleteAccount(true);
            }}
          >
            Delete account
          </Button>
          <div className="p-8 bg-white shadow mt-24">
            <div className="grid grid-cols-1 md:grid-cols-3">
              <div className="grid grid-cols-3 text-center order-last md:order-first mt-20 md:mt-0"></div>
              <div className="relative">
                <div className="w-36 h-36 bg-indigo-100 mx-auto rounded-full shadow-2xl absolute inset-x-0 top-0 -mt-24 flex items-center justify-center text-indigo-500">
                  <svg
                    xmlns="http://www.w3.org/2000/svg"
                    className="h-24 w-24"
                    viewBox="0 0 20 20"
                    fill="currentColor"
                  >
                    <path
                      fill-rule="evenodd"
                      d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z"
                      clip-rule="evenodd"
                    />
                  </svg>
                </div>
              </div>
            </div>

            <div className="mt-20 text-center border-b pb-12">
              <h1 className="text-4xl font-medium text-gray-700">
                {login.nome}
              </h1>
            </div>

            <div className="mt-8 flex flex-col justify-center">
              <div className="bg-white shadow sm:rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <h3 className="flex text-base font-semibold leading-6 text-gray-900">
                    Addresses
                    <div className="flex w-full justify-end mr-8">
                      <Button className="bg-blue-700 hover:bg-blue-800 text-white font-bold py-1 px-3 rounded btn btn-primary">
                        +
                      </Button>
                    </div>
                  </h3>
                  <div className="mt-5">
                    {deliveryAddresses.map((address) => (
                      <div className="rounded-md bg-gray-50 px-6 py-5 sm:flex sm:items-start sm:justify-between">
                        <h4 className="sr-only">Visa</h4>
                        <div className="sm:flex sm:items-start">
                          <MapPinIcon
                            className="h-8 w-auto sm:h-6 sm:flex-shrink-0"
                            viewBox="0 0 36 24"
                            aria-hidden="true"
                          ></MapPinIcon>
                          <div className="mt-3 sm:ml-4 sm:mt-0">
                            <div className="text-sm font-medium text-gray-900">
                              {address.name}
                            </div>
                            <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                              <div>{address.address}</div>
                            </div>
                            <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                              <div>{address.complement}</div>
                            </div>
                            <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                              <div>{address.zipcode}</div>
                            </div>
                          </div>
                        </div>
                        <div className="mt-4 sm:ml-6 sm:mt-0 sm:flex-shrink-0">
                          <button
                            type="button"
                            className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                          >
                            <TrashIcon className="h-5 w-5" aria-hidden="true" />
                          </button>
                        </div>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </div>

            <div className="mt-6 flex flex-col justify-center">
              <div className="bg-white shadow sm:rounded-lg">
                <div className="px-4 py-5 sm:p-6">
                  <h3 className="flex text-base font-semibold leading-6 text-gray-900">
                    Payment
                    <div className="flex w-full justify-end mr-8">
                      <Button
                        className="bg-blue-700 hover:bg-blue-800 text-white font-bold py-1 px-3 rounded btn btn-primary"
                        onClick={() => {
                          setOpenCardRegister(true);
                          setCardRegisterState({
                            number: "",
                            expiry: "",
                            cvc: "",
                            name: "",
                            focus: "",
                          });
                        }}
                      >
                        +
                      </Button>
                    </div>
                  </h3>
                  <div className="mt-5">
                    {isLoading ? (
                      <div>Loading...</div>
                    ) : (
                      state.map((card) => (
                        <div className="rounded-md bg-gray-50 px-6 py-5 mt-2 sm:flex sm:items-start sm:justify-between">
                          <h4 className="sr-only">{card.issuer}</h4>
                          <div className="sm:flex sm:items-start">
                            <CreditCardIcon
                              className="h-8 w-auto sm:h-6 sm:flex-shrink-0"
                              viewBox="0 0 36 24"
                              aria-hidden="true"
                            ></CreditCardIcon>
                            <div className="mt-3 sm:ml-4 sm:mt-0">
                              <div className="text-sm font-medium text-gray-900">
                                {card.name}
                              </div>
                              <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                                <div>
                                  {card.type === "credit"
                                    ? "Credit Card"
                                    : "Debit Card"}
                                </div>
                              </div>
                              <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                                <div>Ends in {card.lastNumbers}</div>
                              </div>
                            </div>
                          </div>
                          <div className="mt-4 sm:ml-6 sm:mt-0 sm:flex-shrink-0">
                            <button
                              type="button"
                              className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                            >
                              <TrashIcon
                                className="h-5 w-5"
                                aria-hidden="true"
                              />
                            </button>
                          </div>
                        </div>
                      ))
                    )}
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </DefaultPage>
      <Transition.Root show={openCardRegister} as={Fragment}>
        <Dialog
          as="div"
          className="relative z-10"
          onClose={setOpenCardRegister}
        >
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
          </Transition.Child>

          <div className="fixed inset-0 z-10 w-screen overflow-y-auto">
            <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                enterTo="opacity-100 translate-y-0 sm:scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 translate-y-0 sm:scale-100"
                leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              >
                <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white px-4 pb-4 pt-5 text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-sm sm:p-6">
                  <div>
                    <div>
                      <Cards
                        number={cardRegisterState.number}
                        expiry={cardRegisterState.expiry}
                        cvc={cardRegisterState.cvc}
                        name={cardRegisterState.name}
                        focused={cardRegisterState.focus}
                      />
                    </div>
                    <form className="mt-2">
                      <div>
                        <label
                          htmlFor="creditCardNumber"
                          className="block text-sm font-medium text-gray-600"
                        >
                          Card Number
                        </label>
                        <input
                          type="text"
                          name="number"
                          className="mt-1 p-2 border rounded-md w-full"
                          placeholder="1234 5678 9012 3456"
                          value={cardRegisterState.number}
                          onChange={handleInputChange}
                          onFocus={handleInputFocus}
                        />
                      </div>
                      <div className="mt-2">
                        <label
                          htmlFor="creditCardUserName"
                          className="block text-sm font-medium text-gray-600"
                        >
                          Name
                        </label>
                        <input
                          type="text"
                          name="name"
                          className="mt-1 p-2 border rounded-md w-full"
                          placeholder="John Doe"
                          value={cardRegisterState.name}
                          onChange={handleInputChange}
                          onFocus={handleInputFocus}
                        />
                      </div>
                      <div className="container mx-auto mt-2">
                        <div className="flex flex-col lg:flex-row items-center">
                          <div className="mb-4 lg:mb-0 lg:mr-4">
                            <label
                              htmlFor="expiryDate"
                              className="block text-sm font-medium text-gray-600"
                            >
                              Expiry Date
                            </label>
                            <input
                              type="text"
                              name="expiry"
                              className="mt-1 p-2 border rounded-md w-full"
                              placeholder="MM/YYYY"
                              value={cardRegisterState.expiry}
                              onChange={handleInputChange}
                              onFocus={handleInputFocus}
                            />
                          </div>
                          <div>
                            <label
                              htmlFor="cvv"
                              className="block text-sm font-medium text-gray-600"
                            >
                              CVV
                            </label>
                            <input
                              type="text"
                              name="cvc"
                              className="mt-1 p-2 border rounded-md w-full"
                              placeholder="CVC"
                              value={cardRegisterState.cvc}
                              onChange={handleInputChange}
                              onFocus={handleInputFocus}
                            />
                          </div>
                        </div>
                      </div>
                      <RadioGroup
                        value={selectedPaymentMethod}
                        onChange={setSelectedPaymentMethod}
                      >
                        <div className="mt-4 grid grid-cols-1 gap-y-6 sm:grid-cols-2 sm:gap-x-4">
                          {paymentMethods.map((paymentMethod, index) => (
                            <RadioGroup.Option
                              key={index}
                              value={paymentMethod}
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
                                        {paymentMethod} Card
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
                    </form>
                  </div>
                  <div className="mt-5 sm:mt-6">
                    <button
                      type="button"
                      className="inline-flex w-full justify-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                      onClick={() => {
                        tryRegisterCard();
                        setOpenCardRegister(false);
                      }}
                    >
                      Register card
                    </button>
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition.Root>

      <Transition.Root show={openDeleteAccount} as={Fragment}>
        <Dialog
          as="div"
          className="relative z-10"
          initialFocus={deleteAccountCancelButtonRef}
          onClose={setOpenDeleteAccount}
        >
          <Transition.Child
            as={Fragment}
            enter="ease-out duration-300"
            enterFrom="opacity-0"
            enterTo="opacity-100"
            leave="ease-in duration-200"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity" />
          </Transition.Child>

          <div className="fixed inset-0 z-10 w-screen overflow-y-auto">
            <div className="flex min-h-full items-end justify-center p-4 text-center sm:items-center sm:p-0">
              <Transition.Child
                as={Fragment}
                enter="ease-out duration-300"
                enterFrom="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
                enterTo="opacity-100 translate-y-0 sm:scale-100"
                leave="ease-in duration-200"
                leaveFrom="opacity-100 translate-y-0 sm:scale-100"
                leaveTo="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
              >
                <Dialog.Panel className="relative transform overflow-hidden rounded-lg bg-white text-left shadow-xl transition-all sm:my-8 sm:w-full sm:max-w-lg">
                  <div className="bg-white px-4 pb-4 pt-5 sm:p-6 sm:pb-4">
                    <div className="sm:flex sm:items-start">
                      <div className="mx-auto flex h-12 w-12 flex-shrink-0 items-center justify-center rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10">
                        <ExclamationTriangleIcon
                          className="h-6 w-6 text-red-600"
                          aria-hidden="true"
                        />
                      </div>
                      <div className="mt-3 text-center sm:ml-4 sm:mt-0 sm:text-left">
                        <Dialog.Title
                          as="h3"
                          className="text-base font-semibold leading-6 text-gray-900"
                        >
                          Delete account
                        </Dialog.Title>
                        <div className="mt-2">
                          <p className="text-sm text-gray-500">
                            Are you sure you want to delete your account? All of
                            your data will be permanently removed. This action
                            cannot be undone.
                          </p>
                        </div>
                        <div className="mt-2">
                          <form>
                            <label
                              htmlFor="creditCardNumber"
                              className="block text-sm font-medium text-gray-600"
                            >
                              Type your password
                            </label>
                            <input
                              type="password"
                              name="senha"
                              className="mt-1 p-2 border rounded-md w-full"
                              onChange={changeHandler}
                            />
                          </form>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div className="bg-gray-50 px-4 py-3 sm:flex sm:flex-row-reverse sm:px-6">
                    <button
                      type="button"
                      className="inline-flex w-full justify-center rounded-md bg-red-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-red-500 sm:ml-3 sm:w-auto"
                      onClick={() => {
                        setOpenDeleteAccount(false);
                        tryDeleteAccount();
                      }}
                    >
                      Deactivate
                    </button>
                    <button
                      type="button"
                      className="mt-3 inline-flex w-full justify-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 sm:mt-0 sm:w-auto"
                      onClick={() => setOpenDeleteAccount(false)}
                      ref={deleteAccountCancelButtonRef}
                    >
                      Cancel
                    </button>
                  </div>
                </Dialog.Panel>
              </Transition.Child>
            </div>
          </div>
        </Dialog>
      </Transition.Root>
    </>
  );
}

export default Profile;
