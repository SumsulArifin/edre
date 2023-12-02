import { CloseButton } from 'components/common/Toast';
import SettingsPanel from 'components/settings-panel/SettingsPanel';
import AppContext from 'context/Context';
import is from 'is_js';
import PropTypes from 'prop-types';
import React, { useContext, useEffect } from 'react';
import 'react-datepicker/dist/react-datepicker.css';
import { connect } from 'react-redux';
import { BrowserRouter as Router } from 'react-router-dom';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.min.css';
import FalconRoutes from 'routes';
import { authOperations } from './state/ducs/auth';

const App = ({ checkAuth, isAuthenticated, error }) => {
  const HTMLClassList = document.getElementsByTagName('html')[0].classList;
  const {
    config: { navbarPosition }
  } = useContext(AppContext);

  useEffect(() => {
    if (is.windows()) {
      HTMLClassList.add('windows');
    }
    if (is.chrome()) {
      HTMLClassList.add('chrome');
    }
    if (is.firefox()) {
      HTMLClassList.add('firefox');
    }
    if (is.safari()) {
      HTMLClassList.add('safari');
    }
  }, [HTMLClassList]);

  useEffect(() => {
    if (navbarPosition === 'double-top') {
      HTMLClassList.add('double-top-nav-layout');
    }
    return () => HTMLClassList.remove('double-top-nav-layout');
  }, [navbarPosition]);

  useEffect(() => {
    checkAuth();
  }, []);

  useEffect(() => {
    if (error && error.length) {
      alert(error);
    }
  }, [error]);

  console.log('isAuthenticated', isAuthenticated);

  return (
    <Router basename={process.env.PUBLIC_URL}>
      <FalconRoutes isAuthenticated={isAuthenticated} />
      {/* <SettingsToggle /> */}
      <SettingsPanel />
      <ToastContainer
        closeButton={CloseButton}
        icon={false}
        position={toast.POSITION.BOTTOM_LEFT}
      />
    </Router>
  );
};

const mapStateToProps = state => {
  return {
    isAuthenticated: state.auth.token !== null,
    // isAuthenticated: true,
    error: state.auth.error
  };
};

const mapDispatchToProps = {
  checkAuth: authOperations.authStateCheck
};

App.propTypes = {
  error: PropTypes.string,
  isAuthenticated: PropTypes.bool,
  checkAuth: PropTypes.func
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
