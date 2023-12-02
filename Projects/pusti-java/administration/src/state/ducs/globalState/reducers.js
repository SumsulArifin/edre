import * as types from "./types";

const initialState = {
//   activeCategory: {},
//   isLeftMenuActive: true,
//   categories: [],
  showAddToCartModal: false,
};

function globalReducer(state = initialState, action) {
	
  switch (action.type) {
    case types.SHOW_ADD_TO_CART_MODAL:
      return {
        ...state,
        showAddToCartModal: true,
      };

    case types.CLOSE_ADD_TO_CART_MODAL:
      return {
        ...state,
        showAddToCartModal: false,
      };

    default:
      return state;
  }
}

export default globalReducer;
