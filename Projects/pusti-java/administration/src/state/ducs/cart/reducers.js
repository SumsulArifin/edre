import * as types from "./types";
import * as utils from "./utils";

/* State shape
[
    {
        product,
        quantity,
    }
]
*/


// {quantity: 1,
// product: id,
// variation: selectedVariationId
//   ? selectedVariationId
//   : pricing && pricing[0] && pricing[0]._id,
// name: name,
// cover: cover,
// price: modifiedPrice || price,
// totalPrice: parseInt(modifiedPrice) || parseInt(price) * 1,
// url: url,
// attribute,}

const initialState = [];


function cartReducer(state = initialState, action) {
  switch (action.type) {
    
    case types.TOGGLE: {
      const { cartItem } = action.payload;
      const index = utils.productPositionInCart(state, cartItem);
      if (index === -1) {
        return [cartItem, ...state];
      }

      //increase the quantity of the product if the product already in the cart
      const existingProduct= state[index];
      existingProduct.quantity++;


      const tempArrayWithOutOldProduct = state.filter(
        (product) => product.id !== cartItem.id
      );

      tempArrayWithOutOldProduct.unshift(existingProduct);

      return tempArrayWithOutOldProduct;

    }

    case types.ADD_PRODUCTS_TO_CART: {
      const { cartItems } = action.payload;

      if (cartItems.length > 0) {
        return cartItems;
      } else {
        return [...state];
      }
    }

    case types.SELECT_PRODUCT_FOR_CHECKOUT: {
      const { product } = action.payload;

      const index = utils.productPositionInCart(state, product.product);
      if (index === -1) {
        return [...state, { ...product, isSelectedForCheckout: true }];
      } else if (index !== -1 && !product.isSelectedForCheckout) {
        const tempArrayWithOutOldProduct = state.filter(
          (item) => item.product.id !== product.product.id
        );

        return [
          ...tempArrayWithOutOldProduct,
          { ...product, isSelectedForCheckout: true },
        ];
      }

      const tempArrayWithOutOldProduct = state.filter(
        (item) => item.product.id !== product.product.id
      );

      return [
        ...tempArrayWithOutOldProduct,
        { ...product, isSelectedForCheckout: false },
      ];
    }

    case types.CHANGE_QUANTITY: {
      const { givenCartItem, quantity } = action.payload;
      const index = utils.productPositionInCart(state, givenCartItem);

      const updatedItem = Object.assign({}, state[index], { quantity });
      return [...state.slice(0, index), updatedItem, ...state.slice(index + 1)];
    }

    case types.REMOVE: {
      const { cartItem } = action.payload;
      const index = utils.productPositionInCart(state, cartItem);
      return [...state.slice(0, index), ...state.slice(index + 1)];
    }

    case types.CLEAR:
      return [];

    default:
      return state;
  }
}

export default cartReducer;
