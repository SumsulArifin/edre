import * as types from "./types";

import { updateObject } from "../../../utils";

const initialState = {
  token: null,
  error: null,
  loading: false,
  authRedirectPath: "/",
  user: {},
};

const authStart = (state, action) => {
  return updateObject(state, { error: null, loading: true });
};

const authSuccess = (state, action) => {
  return updateObject(state, {
    token: action.idToken,
    error: null,
    loading: false,
  });
};

const authUserSet = (state, action) => {
  return updateObject(state, {
    user: action.user,
  });
};

const authUserRemove= (state, action) => {
  return updateObject(state, {
    user: {},
  });
}

const authFail = (state, action) => {
  return updateObject(state, {
    error: action.error,
    loading: false,
  });
};

const authLogout = (state, action) => {
  return updateObject(state, { token: null });
};

const setAuthRedirectPath = (state, action) => {
  return updateObject(state, { authRedirectPath: action.path });
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case types.AUTH_START:
      return authStart(state, action);
    case types.AUTH_SUCCESS:
      return authSuccess(state, action);
    case types.AUTH_USER:
      return authUserSet(state, action);
    case types.AUTH_USER_REMOVE:
        return authUserRemove(state, action);
    case types.AUTH_FAIL:
      return authFail(state, action);
    case types.AUTH_LOGOUT:
      return authLogout(state, action);
    case types.SET_AUTH_REDIRECT_PATH:
      return setAuthRedirectPath(state, action);
    default:
      return state;
  }
};

export default reducer;
