import axios from 'axios';
import { authHeader } from '../../../utils';
import * as types from './types';

export const authStart = () => {
  return {
    type: types.AUTH_START
  };
};

export const authSuccess = token => {
  return {
    type: types.AUTH_SUCCESS,
    idToken: token
  };
};

export const setAuthUser = user => {
  return {
    type: types.AUTH_USER,
    user: user
  };
};

export const removeAuthUser = () => {
  return {
    type: types.AUTH_USER_REMOVE
  };
};

export const authFail = error => {
  return {
    type: types.AUTH_FAIL,
    error: error
  };
};

export const logout = () => {
  localStorage.removeItem('token');
  localStorage.removeItem('user');
  localStorage.removeItem('expirationDate');
  return {
    type: types.AUTH_LOGOUT
  };
};

export const checkAuthTimeout = expirationTime => {
  return dispatch => {
    setTimeout(() => {
      dispatch(logout());
    }, expirationTime * 1000);
  };
};

export const auth = (email, password) => {

  return dispatch => {
    dispatch(authStart());
    const authData = {
      userName: email,
      password: password
    };

    console.log('Auth Data', authData)
    const url = process.env.REACT_APP_BASE_URL + 'api/auth/login';

    console.log(url)
    axios
      .post(url, authData, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
      .then(response => {
        console.log("response", response)
        const expires_in = 3600
        const expirationDate = new Date(
          new Date().getTime() + expires_in * 1000
        );
        localStorage.setItem('token', response.data.access_token);
        // localStorage.setItem('user', JSON.stringify(response.data.data.user));
        localStorage.setItem('expirationDate', expirationDate);
        dispatch(authSuccess(response.data.access_token));
        // dispatch(setAuthUser(response.data.data.user));
        dispatch(checkAuthTimeout(expires_in));
      })
      .catch(err => {
        dispatch(
          authFail('Login failed. Try again with correct username & password')
        );
      });
  };
};

export const setAuthRedirectPath = path => {
  return {
    type: types.SET_AUTH_REDIRECT_PATH,
    path: path
  };
};

export const authStateCheck = () => {
  return dispatch => {
    const token = localStorage.getItem('token');
    const user = localStorage.getItem('user');
    if (!token) {
      dispatch(logout());
    } else {
      dispatch(authSuccess(token));
      dispatch(setAuthUser(JSON.parse(user)));
    }
  };
};

export const authUserCheck = () => {
  return dispatch => {
    const url = '/api/auth/me';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        localStorage.setItem('user', JSON.stringify(response.data.data));
        dispatch(setAuthUser(response.data.data));
      })
      .catch(err => {
        dispatch(
          authFail('Login failed. Try again with correct username & password')
        );
      });
  };
};
