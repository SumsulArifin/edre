export function productPositionInCart(cart, givenCartItem) {
  const index = cart.findIndex(cartItem => cartItem.id === givenCartItem.id 
    // && cartItem.variation === givenCartItem.variation
    
    );
  return index;
}

export function newCartItem(id, quantity, isSelectedForCheckout) {
  return {
    id,
    quantity,
    isSelectedForCheckout,
  };
}
