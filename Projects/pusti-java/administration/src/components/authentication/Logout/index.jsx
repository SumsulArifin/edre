import PropTypes from 'prop-types';
import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Navigate } from 'react-router';
import { authOperations } from 'state/ducs/auth';

const Logout = ({ logout, removeUser }) => {
  useEffect(() => {
    logout();
    removeUser();
  }, []);

  return <Navigate to={'/'} />;
};

const mapStateToProps = state => {
  return {};
};

const mapDispatchToProps = {
  logout: authOperations.logout,
  removeUser: authOperations.removeAuthUser
};

Logout.propTypes = {
  logout: PropTypes.func,
  removeUser: PropTypes.func
};

export default connect(mapStateToProps, mapDispatchToProps)(Logout);
