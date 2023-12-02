import * as types from './types';

export const addToCart = (cartItem) => ({
  type: types.TOGGLE,
  payload: {
    cartItem
  },
});

export const addProductsToCart = (cartItems) => ({
  type: types.ADD_PRODUCTS_TO_CART,
  payload: {
    cartItems,
  },
});

export const selectProductForCheckout = (product) => ({
  type: types.SELECT_PRODUCT_FOR_CHECKOUT,
  payload: {
    product,
  },
});

export const changeQuantity = (givenCartItem, quantity) => ({
  type: types.CHANGE_QUANTITY,
  payload: {
    givenCartItem,
    quantity,
  },
});

export const removeFromCart = (cartItem) => ({
  type: types.REMOVE,
  payload: {
    cartItem,
  },
});

export const clearCart = () => ({
  type: types.CLEAR,
});

export const setCart = (cart) => ({
  type: types.SET_CART,
  payload: {
    cart,
  },
});
