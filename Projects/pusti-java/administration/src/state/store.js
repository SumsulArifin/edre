// import { configureStore } from '@reduxjs/toolkit';
import { applyMiddleware, combineReducers, compose, createStore } from 'redux';
import { persistReducer, persistStore } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import * as reducers from './ducs';
// import { apiService } from "./middlewares";
import thunk from 'redux-thunk';

const rootReducer = combineReducers(reducers);

const persistConfig = {
  key: 'fizainc',
  storage: storage,
  whitelist: ['lingual', 'cart'] // session removed from array
};

const pReducer = persistReducer(persistConfig, rootReducer);

function configureStore(initialState) {
  return createStore(
    pReducer,
    initialState,
    compose(
      // applyMiddleware(apiService),
      applyMiddleware(thunk),
      window.__REDUX_DEVTOOLS_EXTENSION__
        ? window.__REDUX_DEVTOOLS_EXTENSION__()
        : f => f
    )
  );
}

// const preloadedState={};

// const debounceNotify = _.debounce(notify => notify());

// const store = configureStore({
//   pReducer,
//   middleware: (getDefaultMiddleware) => getDefaultMiddleware().concat(apiService),
//   devTools: process.env.NODE_ENV !== 'production',
//   preloadedState,
//   enhancers: [batchedSubscribe(debounceNotify)],
// })

const store = configureStore({});

const persistor = persistStore(store);

export { persistor, store };

