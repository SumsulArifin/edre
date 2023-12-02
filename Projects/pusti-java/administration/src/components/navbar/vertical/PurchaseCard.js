import image from 'assets/img/logos/tkgmis.png';
import React, { useState } from 'react';
import { Button, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import logo from '../../../assets/img/logos/tkgl/techknowgram.fa1a6b163526beb3e5a6.png'

const PurchaseCard = () => {
  const [show, setShow] = useState(true);
  return (
    show && (
      <div className="settings my-2">
        <Card style={{background: "none"}} className="p-0 rounded-2 position-relative">
          <div
            className="position-absolute"
            style={{ right: '6px', top: '3px' }}
          >
            {/* <FalconCloseButton
              size="sm"
              className="fs--2"
              noOutline
              onClick={() => setShow(false)}
            /> */}
          </div>
          <Card.Body className="text-center" style={{paddingBottom:"95px"}} >
            <img src={image} alt="" width={150} />
            <div style={{ position: 'relative' }}>
            <a href='https://techknowgram.com/'><img src={logo} style={{ width: '100%', marginTop: '25px' }}></img></a>
            <div style={{ position: 'absolute', top: '5px', left: '0' }}>
              <small className="text-black bold ">
                Powered By :
              </small>
            </div>
          </div>
          </Card.Body>
        </Card>
      </div>
    )
  );
};

export default PurchaseCard;
