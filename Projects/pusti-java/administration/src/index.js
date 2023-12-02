import 'helpers/initFA';
import React from 'react';
import { createRoot } from 'react-dom/client';
import { Provider as ReduxProvider } from 'react-redux';
import App from './App';
import Main from './Main';

// import store
import { store } from './state/store';

const container = document.getElementById('main');
const root = createRoot(container);

root.render(
  <React.StrictMode>
    <ReduxProvider store={store}>
      <Main>
        <App />
      </Main>
    </ReduxProvider>
  </React.StrictMode>
);
