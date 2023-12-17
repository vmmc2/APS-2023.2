import { EnvelopeIcon, PhoneIcon, ShoppingCartIcon } from '@heroicons/react/20/solid'
import React from 'react';
import { formatCurrency } from '../../utilities/currencyFormatter';
import { Button } from 'react-bootstrap';
import { CartItem, useShoppingCart } from '../../context/ShoppingCartContext';

export interface ProductsListProps {
    products: CartItem[];
}

function ProductsList({ products }: ProductsListProps): JSX.Element {
    const { getItemQuantity, increaseCartQuantity, decreaseCartQuantity } = useShoppingCart();
    return (
        <ul role="list" className="grid grid-cols-1 gap-6 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4">
            {products.map((product) => (
                <li
                    key={product.id}
                    className="col-span-1 flex flex-col divide-y divide-gray-200 rounded-lg bg-white text-center shadow"
                >
                    <div className="flex flex-1 flex-col p-8">
                        <img className="mx-auto h-32 w-32 flex-shrink-0 rounded-full" src={product.imageUrl} alt="" />
                        <h3 className="mt-6 text-sm font-medium text-gray-900">{product.name}</h3>
                        <dl className="mt-1 flex flex-grow flex-col justify-between">
                            <dd className="text-sm text-gray-500">{product.description}</dd>
                            <dd className="mt-3">
                                <span className="inline-flex items-center rounded-full px-2 py-1 text-base font-bold text-black">
                                    {formatCurrency(product.price)}
                                </span>
                            </dd>
                        </dl>
                    </div>
                    <div>
                        <div className="-mt-px flex divide-x divide-gray-200">
                            {
                                getItemQuantity(product) === 0 ?
                                    <Button onClick={() => increaseCartQuantity(product)} className="flex w-0 flex-1 group hover:bg-[#16a34a] justify-center items-center gap-x-3 py-4 text-sm font-semibold text-gray-900 rounded-b-lg border border-transparent">
                                        <ShoppingCartIcon className="h-5 w-5 text-black group-hover:text-white" aria-hidden="true" />
                                        <span className="flex group-hover:text-white text-black">Add to cart</span>
                                    </Button>
                                    :
                                    <div className="w-0 flex-1 inline-flex items-center justify-center py-2">
                                        <Button onClick={() => decreaseCartQuantity(product)} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                                            -
                                        </Button>
                                        <div>
                                            <span className="p-5 fs-3">{getItemQuantity(product)}</span>
                                        </div>
                                        <Button onClick={() => increaseCartQuantity(product)} className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                                            +
                                        </Button>
                                    </div>
                            }
                        </div>
                    </div>
                </li>
            ))}
        </ul>
    )
}

export default ProductsList;
