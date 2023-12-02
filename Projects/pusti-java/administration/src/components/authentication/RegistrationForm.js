import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { Button, Form, Row, Col } from 'react-bootstrap';
import Divider from 'components/common/Divider';
import SocialAuthButtons from './SocialAuthButtons';
import axios from 'axios';


const RegistrationForm = ({ hasLabel }) => {

  // State
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    role: '',
    email: '',
    password: '',
    confirmPassword: '',
    isAccepted: false
  });

  const navigate = useNavigate();

  // Handler
  const handleSubmit = e => {
    e.preventDefault();

    // Post User Data
    if (formData) {

      const userInfo = {
        firstname: formData.firstName,
        lastname: formData.lastName,
        email: formData.email,
        password: formData.password,
        role: formData.role
      }

      axios.post(process.env.REACT_APP_BASE_URL + "api/auth/register", userInfo, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
        .then(response => {
          console.log('register data', response)
          if (response.status === 200) {
            toast.success(`Successfully registered as ${formData.firstName} ${formData.lastName}`, {
              theme: 'colored'
            });
            navigate("/authentication/card/login")
          }
        })
        .catch(error => {
          if (error) {
            toast.error(`Something Went Wrong `, {
              theme: 'colored'
            })
          }
        })
    }


  };

  const handleFieldChange = e => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value
    });
  };

  return (
    <Form onSubmit={handleSubmit}>


      <Row>
        <Form.Group className="mb-3" as={Col} sm={6}>
          {hasLabel && <Form.Label>First Name</Form.Label>}
          <Form.Control
            placeholder={!hasLabel ? 'Name' : ''}
            value={formData.firstName}
            name="firstName"
            onChange={handleFieldChange}
            type="text"
            required
          />
        </Form.Group >
        <Form.Group className="mb-3" as={Col} sm={6}>
          {hasLabel && <Form.Label>Last Name</Form.Label>}
          <Form.Control
            placeholder={!hasLabel ? 'Name' : ''}
            value={formData.lastName}
            name="lastName"
            onChange={handleFieldChange}
            type="text"
            required
          />
        </Form.Group >
      </Row>


      {hasLabel && <Form.Label>Role</Form.Label>}
      <Form.Select aria-label="Default select example" className="mb-3" value={formData.role} name='role' onChange={handleFieldChange} >
        <option>select Role</option>
        <option value="USER">User</option>
        <option value="ADMIN">Admin</option>
        <option value="MANAGER">Manager</option>
      </Form.Select>

      <Form.Group className="mb-3">
        {hasLabel && <Form.Label>Email address</Form.Label>}
        <Form.Control
          placeholder={!hasLabel ? 'Email address' : ''}
          value={formData.email}
          name="email"
          onChange={handleFieldChange}
          type="email"
          required
        />
      </Form.Group>

      <Row className="g-2 mb-3">
        <Form.Group as={Col} sm={6}>
          {hasLabel && <Form.Label>Password</Form.Label>}
          <Form.Control
            placeholder={!hasLabel ? 'Password' : ''}
            value={formData.password}
            name="password"
            onChange={handleFieldChange}
            type="password"
            required
            pattern=".{8,}" title="Password must be at least 8 characters"
          />
        </Form.Group>
        <Form.Group as={Col} sm={6}>
          {hasLabel && <Form.Label>Confirm Password</Form.Label>}
          <Form.Control
            placeholder={!hasLabel ? 'Confirm Password' : ''}
            value={formData.confirmPassword}
            name="confirmPassword"
            onChange={handleFieldChange}
            type="password"
            required
            pattern="^(?=.*\d)(?=.*[A-Z]).{8,}$" title="Password must be at least 8 characters and Include Uppercase Letter and Special Character"
          />
        </Form.Group>
      </Row>

      <Form.Group className="mb-3">
        <Form.Check type="checkbox" id="acceptCheckbox" className="form-check">
          <Form.Check.Input
            type="checkbox"
            name="isAccepted"
            checked={formData.isAccepted}
            onChange={e =>
              setFormData({
                ...formData,
                isAccepted: e.target.checked
              })
            }
          />
          <Form.Check.Label className="form-label">
            I accept the <Link to="#!">terms</Link> and{' '}
            <Link to="#!">privacy policy</Link>
          </Form.Check.Label>
        </Form.Check>
      </Form.Group>

      <Form.Group className="mb-4">
        <Button
          className="w-100"
          type="submit"
          disabled={
            !formData.firstName ||
            !formData.lastName ||
            !formData.email ||
            !formData.password ||
            !formData.confirmPassword ||
            !formData.isAccepted
          }
        >
          Register
        </Button>
      </Form.Group>
      <Divider>or register with</Divider>

      <SocialAuthButtons />
    </Form>
  );
};

RegistrationForm.propTypes = {
  hasLabel: PropTypes.bool
};

export default RegistrationForm;
