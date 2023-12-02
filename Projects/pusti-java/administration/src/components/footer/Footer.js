import React from 'react';
import { Col, Row } from 'react-bootstrap';
import { version } from 'config';

const Footer = () => (
  <footer className="footer">
    <Row className="my-3" style={{textAlign:'center',display:"block"}}>
      <Col sm="auto">
        <p className="mb-0 text-600">
         &copy;{' '}   TK Group All rights reserved. Powered by{' '}
          <span className="d-none d-sm-inline-block">| </span>
          <br className="d-sm-none" /> 
          <a href="https://techknowgram.com" target='_blank' className='text-decoration-none '><strong style={{color:'#483285'}}>TechKnowGram Limited</strong></a>
        </p>
      </Col>
      <Col sm="auto">
        {/* <p className="mb-0 text-600">v{version}</p> */}
      </Col>
    </Row>
  </footer>
);

export default Footer;
