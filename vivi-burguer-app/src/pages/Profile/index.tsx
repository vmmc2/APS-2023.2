import React, { Fragment, useState } from "react";
import DefaultPage from "../DefaultPage";
import { useLogin } from "../../context/LoginContext";
import { CheckIcon, MapPinIcon } from "@heroicons/react/24/outline";
import { Button } from "react-bootstrap";
import { Dialog, Transition } from "@headlessui/react";
import Cards from "react-credit-cards-2";
import "react-credit-cards-2/dist/es/styles-compiled.css";

function Profile() {
  const { login } = useLogin();

  const [openCardRegister, setOpenCardRegister] = useState(false);
  const [cardRegisterState, setCardRegisterState] = useState({
    number: "",
    expiry: "",
    cvc: "",
    name: "",
    focus: "",
  });

  const handleInputChange = (evt) => {
    const { name, value } = evt.target;

    setCardRegisterState((prev) => ({ ...prev, [name]: value }));
  };

  const handleInputFocus = (evt) => {
    setCardRegisterState((prev) => ({ ...prev, focus: evt.target.name }));
  };

  return (
    <>
      <DefaultPage>
        <div className="p-0">
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
                    <div className="rounded-md bg-gray-50 px-6 py-5 sm:flex sm:items-start sm:justify-between">
                      <h4 className="sr-only">Visa</h4>
                      <div className="sm:flex sm:items-start">
                        <MapPinIcon className="w-auto sm:h-6 sm:flex-shrink-0"></MapPinIcon>
                        <div className="mt-3 sm:ml-4 sm:mt-0">
                          <div className="text-sm font-medium text-gray-900">
                            Endereço 01
                          </div>
                          <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                            <div>Rua Mauricea, 474 - 50670-480, Recife-PE </div>
                          </div>
                          <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                            <div>Last updated on 22 Aug 2017 </div>
                          </div>
                        </div>
                      </div>
                      <div className="mt-4 sm:ml-6 sm:mt-0 sm:flex-shrink-0">
                        <button
                          type="button"
                          className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                        >
                          Edit
                        </button>
                      </div>
                    </div>
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
                    <div className="rounded-md bg-gray-50 px-6 py-5 sm:flex sm:items-start sm:justify-between">
                      <h4 className="sr-only">Visa</h4>
                      <div className="sm:flex sm:items-start">
                        <svg
                          className="h-8 w-auto sm:h-6 sm:flex-shrink-0"
                          viewBox="0 0 36 24"
                          aria-hidden="true"
                        >
                          <rect width={36} height={24} fill="#224DBA" rx={4} />
                          <path
                            fill="#fff"
                            d="M10.925 15.673H8.874l-1.538-6c-.073-.276-.228-.52-.456-.635A6.575 6.575 0 005 8.403v-.231h3.304c.456 0 .798.347.855.75l.798 4.328 2.05-5.078h1.994l-3.076 7.5zm4.216 0h-1.937L14.8 8.172h1.937l-1.595 7.5zm4.101-5.422c.057-.404.399-.635.798-.635a3.54 3.54 0 011.88.346l.342-1.615A4.808 4.808 0 0020.496 8c-1.88 0-3.248 1.039-3.248 2.481 0 1.097.969 1.673 1.653 2.02.74.346 1.025.577.968.923 0 .519-.57.75-1.139.75a4.795 4.795 0 01-1.994-.462l-.342 1.616a5.48 5.48 0 002.108.404c2.108.057 3.418-.981 3.418-2.539 0-1.962-2.678-2.077-2.678-2.942zm9.457 5.422L27.16 8.172h-1.652a.858.858 0 00-.798.577l-2.848 6.924h1.994l.398-1.096h2.45l.228 1.096h1.766zm-2.905-5.482l.57 2.827h-1.596l1.026-2.827z"
                          />
                        </svg>
                        <div className="mt-3 sm:ml-4 sm:mt-0">
                          <div className="text-sm font-medium text-gray-900">
                            Ending with 4242
                          </div>
                          <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                            <div>Expires 12/20</div>
                            <span
                              className="hidden sm:mx-2 sm:inline"
                              aria-hidden="true"
                            >
                              &middot;
                            </span>
                            <div className="mt-1 sm:mt-0">
                              Last updated on 22 Aug 2017
                            </div>
                          </div>
                        </div>
                      </div>
                      <div className="mt-4 sm:ml-6 sm:mt-0 sm:flex-shrink-0">
                        <button
                          type="button"
                          className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                        >
                          Edit
                        </button>
                      </div>
                    </div>
                    <div className="rounded-md bg-gray-50 px-6 py-5 mt-2 sm:flex sm:items-start sm:justify-between">
                      <h4 className="sr-only">Visa</h4>
                      <div className="sm:flex sm:items-start">
                        <svg
                          className="h-8 w-auto sm:h-6 sm:flex-shrink-0"
                          viewBox="0 0 36 24"
                          aria-hidden="true"
                        >
                          <rect width={36} height={24} fill="#224DBA" rx={4} />
                          <path
                            fill="#fff"
                            d="M10.925 15.673H8.874l-1.538-6c-.073-.276-.228-.52-.456-.635A6.575 6.575 0 005 8.403v-.231h3.304c.456 0 .798.347.855.75l.798 4.328 2.05-5.078h1.994l-3.076 7.5zm4.216 0h-1.937L14.8 8.172h1.937l-1.595 7.5zm4.101-5.422c.057-.404.399-.635.798-.635a3.54 3.54 0 011.88.346l.342-1.615A4.808 4.808 0 0020.496 8c-1.88 0-3.248 1.039-3.248 2.481 0 1.097.969 1.673 1.653 2.02.74.346 1.025.577.968.923 0 .519-.57.75-1.139.75a4.795 4.795 0 01-1.994-.462l-.342 1.616a5.48 5.48 0 002.108.404c2.108.057 3.418-.981 3.418-2.539 0-1.962-2.678-2.077-2.678-2.942zm9.457 5.422L27.16 8.172h-1.652a.858.858 0 00-.798.577l-2.848 6.924h1.994l.398-1.096h2.45l.228 1.096h1.766zm-2.905-5.482l.57 2.827h-1.596l1.026-2.827z"
                          />
                        </svg>
                        <div className="mt-3 sm:ml-4 sm:mt-0">
                          <div className="text-sm font-medium text-gray-900">
                            Ending with 4242
                          </div>
                          <div className="mt-1 text-sm text-gray-600 sm:flex sm:items-center">
                            <div>Expires 12/20</div>
                            <span
                              className="hidden sm:mx-2 sm:inline"
                              aria-hidden="true"
                            >
                              &middot;
                            </span>
                            <div className="mt-1 sm:mt-0">
                              Last updated on 22 Aug 2017
                            </div>
                          </div>
                        </div>
                      </div>
                      <div className="mt-4 sm:ml-6 sm:mt-0 sm:flex-shrink-0">
                        <button
                          type="button"
                          className="inline-flex items-center rounded-md bg-white px-3 py-2 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50"
                        >
                          Edit
                        </button>
                      </div>
                    </div>
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
                    </form>
                  </div>
                  <div className="mt-5 sm:mt-6">
                    <button
                      type="button"
                      className="inline-flex w-full justify-center rounded-md bg-indigo-600 px-3 py-2 text-sm font-semibold text-white shadow-sm hover:bg-indigo-500 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-indigo-600"
                      onClick={() => setOpenCardRegister(false)}
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
    </>
  );
}

export default Profile;
