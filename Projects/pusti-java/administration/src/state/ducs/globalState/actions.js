import * as types from './types';



// export const toggleLeftMenuButton = () => ({
// 	type: types.IS_LEFT_MENU_BUTTON_ACTIVE
// });


export const showAddToCartModal= ()=>(
	{
		type: types.SHOW_ADD_TO_CART_MODAL
	}
)

export const closeAddToCartModal= ()=>(
	{
		type: types.CLOSE_ADD_TO_CART_MODAL
	}
)


