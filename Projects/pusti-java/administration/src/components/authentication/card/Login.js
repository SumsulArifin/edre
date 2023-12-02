import LoginForm from 'components/authentication/LoginForm';
import React from 'react';
import logo from '../../../assets/img/logos/tkgl/techknowgram.fa1a6b163526beb3e5a6.png'
import AuthCardLayout from 'layouts/AuthCardLayout';


const Login = () => {
  return (
    <AuthCardLayout
      leftSideContent={
        <>
          <div style={{ position: 'relative' }}>
            <a href='https://techknowgram.com/'><img src={logo} style={{ width: '50%', marginTop: '10px' }}></img></a>
            <div style={{ position: 'absolute', top: '0', left: '45px' }}>
              <strong className="text-white bold">
                Powered By :
              </strong>
            </div>
          </div>
        </>
      }
    >
      <h3>User Login</h3>
      <LoginForm layout="card" hasLabel />
    </AuthCardLayout>
  );
};

export default Login;
