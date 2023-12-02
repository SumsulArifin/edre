import axios from 'axios';
import { Formik } from 'formik';
import LoadingIcon from 'helpers/LoadingIcon';
import PropTypes from 'prop-types';
import React, { useEffect, useState } from 'react';
import { Button, Col, Form, Row } from 'react-bootstrap';
import { connect } from 'react-redux';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { authOperations } from 'state/ducs/auth';
import * as Yup from 'yup';

const LoginForm = ({
  hasLabel,
  layout,
  setlogin,
  loading,
  error,
  isAuthenticated,
  authRedirectPath,
  signIn,
  setAuthRedirectPath
}) => {
  // State
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    remember: false
  });

  // Handler
  const handleSubmit = e => {
    e.preventDefault();
    toast.success(`Logged in as ${formData.email}`, {
      theme: 'colored'
    });
  };

  // const handleFormSubmit = e =>{
  //   e.preventDefault();
  //   const email = e.target.name.value;
  //   const password = e.target.password.value;
  //   const remember = e.target.remember.checked;

  //   if(email && password){

  //     signIn(email, password);

  //   const data = {email:email,
  //       password:password}

  //       axios.post("/api/auth/login",data,{
  //         headers: {
  //           'Content-Type': 'application/json',
  //         },
  //       })
  //     .then(response => {
  //       if(response.status === 200){

  //       }
  //       console.log('Login Data',response)
  //     })
  //     .catch(error => console.log(error))
  //   }

  // }

  const handleFieldChange = e => {
    e.preventDefault();
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  const handleLoginSubmit = (values, actions) => {
    console.log('test data', values);
    // debugger;
    signIn(values.userName, values.password);
    actions.resetForm();
  };

  const loginSchema = Yup.object().shape({
    // userName: Yup.string()

    //   // Format Validation
    //   .userName('Invalid email address format')
    //   // Required Field Validation
    //   .required('Email is required'),
    // password: Yup.string()
    //   .min(6, 'Too Short!')
    //   .max(50, 'Too Long!')
    //   .required('Required')
  });
  let testData = null;
  let authRedirect = null;
  if (isAuthenticated) {
    authRedirect = <Navigate to={authRedirectPath} />;
  }

  const navigate = useNavigate();

  useEffect(() => {
    //you can set condition here upon which you want to redirect to which path
    setAuthRedirectPath('/');
  }, []);


  if (loading) {
    return <LoadingIcon />
  }

  return (
    <>
      {authRedirect}

      <Formik
        initialValues={{
          userName: '',
          password: ''
        }}
        validationSchema={loginSchema}
        onSubmit={handleLoginSubmit}
      >
        {({
          errors,
          touched,
          handleSubmit,
          handleChange,
          handleBlur,
          values
        }) => (
          // <Form onSubmit={handleFormSubmit} >
          <Form onSubmit={handleSubmit}>
            {console.log(values, errors)}
            <Form.Group className="mb-3">
              {hasLabel && <Form.Label>User ID</Form.Label>}
              <Form.Control
                placeholder={!hasLabel ? 'User ID' : ''}
                value={values.userName}
                name="userName"
                onChange={handleChange}
                onBlur={handleBlur}
                type="text"
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              {hasLabel && <Form.Label>Password</Form.Label>}
              <Form.Control
                placeholder={!hasLabel ? 'Password' : ''}
                value={values.password}
                name="password"
                onChange={handleChange}
                onBlur={handleBlur}
                type="password"
                required
              />
              <small className="text-danger">{errors.password}</small>
            </Form.Group>

            <Row className="justify-content-between align-items-center">
              <Col xs="auto">
                <Form.Check type="checkbox" id="rememberMe" className="mb-0">
                  <Form.Check.Input
                    type="checkbox"
                    name="remember"
                    checked={formData.remember}
                    onChange={e =>
                      setFormData({
                        ...formData,
                        remember: e.target.checked
                      })
                    }
                  />
                  <Form.Check.Label className="mb-0 text-700">
                    Remember me
                  </Form.Check.Label>
                </Form.Check>
              </Col>

              <Col xs="auto">
                <Link
                  className="fs--1 mb-0"
                  to={`/authentication/${layout}/forgot-password`}
                >
                  Forgot Password?
                </Link>
              </Col>
            </Row>

            <Form.Group>
              <Button
                type="submit"
                color="primary"
                className="mt-3 w-100"
                disabled={!values.userName || !values.password}
              >
                Log in
              </Button>
            </Form.Group>

            {/* <Divider className="mt-4">or log in with</Divider>

      <SocialAuthButtons /> */}
          </Form>
        )}
      </Formik>
    </>
  );
};

LoginForm.propTypes = {
  layout: PropTypes.string,
  hasLabel: PropTypes.bool,
  setlogin: PropTypes.func,
  loading: PropTypes.bool,
  error: PropTypes.string,
  isAuthenticated: PropTypes.bool,
  authRedirectPath: PropTypes.string,
  signIn: PropTypes.func,
  setAuthRedirectPath: PropTypes.func
};

LoginForm.defaultProps = {
  layout: 'simple',
  hasLabel: false
};

const mapStateToProps = state => {
  return {
    loading: state.auth.loading,
    error: state.auth.error,
    isAuthenticated: state.auth.token !== null,
    authRedirectPath: state.auth.authRedirectPath
  };
};

const mapDispatchToProps = {
  signIn: authOperations.auth,
  setAuthRedirectPath: authOperations.setAuthRedirectPath
};

export default connect(mapStateToProps, mapDispatchToProps)(LoginForm);
