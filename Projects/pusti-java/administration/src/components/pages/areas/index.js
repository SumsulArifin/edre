import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useState } from 'react';
import {
  Button,
  Card,
  CloseButton,
  Col,
  Form,
  Modal,
  Row
} from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { Link } from 'react-router-dom';
import { areaData } from './areaData';

const columns = [
  {
    accessor: 'region_id',
    Header: 'Zone Name',
    headerProps: { className: 'pe-1' },
    cellProps: {
      className: 'py-2'
    },
    Cell: rowData => {
      const { region_id } = rowData.row.original;
      return (
        <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{region_id}</h5>
            </div>
          </Flex>
        </Link>
      );
    }
  },

  {
    accessor: 'area_id',
    Header: 'Region Name',
    headerProps: { className: 'pe-1' },
    cellProps: {
      className: 'py-2'
    },
    Cell: rowData => {
      const { area_id } = rowData.row.original;
      return (
        <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{area_id}</h5>
            </div>
          </Flex>
        </Link>
      );
    }
  },

  {
    accessor: 'teritorry_id',
    Header: 'Area ID',
    headerProps: { className: 'pe-1' },
    cellProps: {
      className: 'py-2'
    },
    Cell: rowData => {
      const { teritorry_id } = rowData.row.original;
      return (
        <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{teritorry_id}</h5>
            </div>
          </Flex>
        </Link>
      );
    }
  },

  {
    accessor: 'teritorry_name',
    Header: 'Area Name',
    headerProps: { className: 'pe-1' },
    cellProps: {
      className: 'py-2'
    },
    Cell: rowData => {
      const { teritorry_name } = rowData.row.original;
      return (
        <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{teritorry_name}</h5>
            </div>
          </Flex>
        </Link>
      );
    }
  },

  {
    accessor: 'total_db',
    Header: 'Total Distributor',
    headerProps: { className: 'pe-1' },
    cellProps: {
      className: 'py-2'
    },
    Cell: rowData => {
      const { total_db } = rowData.row.original;
      return (
        <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{total_db}</h5>
            </div>
          </Flex>
        </Link>
      );
    }
  },

  {
    accessor: 'status',
    Header: 'Status',
    headerProps: { className: 'pe-1' },
    cellProps: {
      className: 'py-2'
    },
    Cell: rowData => {
      const { status } = rowData.row.original;
      return (
        <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{status}</h5>
            </div>
          </Flex>
        </Link>
      );
    }
  }
];

const Areas = () => {
  const [areas] = useState(areaData);
  //   const values = [true, 'sm-down', 'md-down', 'lg-down', 'xl-down', 'xxl-down'];
  const [fullscreen, setFullscreen] = useState('xxl-down');
  const [show, setShow] = useState(false);

  const handleShow = () => {
    setShow(true);
  };

  const {
    register,
    handleSubmit,
    watch,
    formState: { errors }
  } = useForm();

  const [formData, setData] = useState({});

  const onSubmit = data => {
    setData(data);
  };
  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={areas}
        selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="Areas" handleShow={handleShow} table />
          </Card.Header>
          <Card.Body className="p-0">
            <AdvanceTable
              table
              headerClassName="bg-200 text-900 text-nowrap align-middle"
              rowClassName="align-middle white-space-nowrap"
              tableProps={{
                size: 'sm',
                striped: true,
                className: 'fs--1 mb-0 overflow-hidden'
              }}
            />
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>

      <Modal show={show} fullscreen={fullscreen} onHide={() => setShow(false)}>
        <Modal.Header>
          <Modal.Title>Create Area</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShow(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Row>
            <Col md={12}>
              <Form noValidate onSubmit={handleSubmit(onSubmit)}>
                <Form.Group className="mb-3" controlId="formHookname">
                  <Form.Label>Zone</Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    placeholder="Select Zone"
                    isInvalid={!!errors.name}
                    {...register('name', {
                      required: 'Zone field is required'
                    })}
                  />
                  <Form.Control.Feedback type="invalid">
                    {errors.name && errors.name.message}
                  </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formHookname">
                  <Form.Label>Region</Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    placeholder="Select Region"
                    isInvalid={!!errors.name}
                    {...register('name', {
                      required: 'Region field is required'
                    })}
                  />
                  <Form.Control.Feedback type="invalid">
                    {errors.name && errors.name.message}
                  </Form.Control.Feedback>
                </Form.Group>
                <Form.Group className="mb-3" controlId="email">
                  <Form.Label>Area</Form.Label>
                  <Form.Control
                    type="text"
                    name="email"
                    placeholder="Enter Area Name"
                    isInvalid={!!errors.email}
                    {...register('email', {
                      required: 'Area Name field is required'
                    })}
                  />

                  <Form.Control.Feedback type="invalid">
                    {errors.email && errors.email.message}
                  </Form.Control.Feedback>
                </Form.Group>

                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </Form>
            </Col>
            {/* <Col md="auto">
              <h5 className="mt-4">Result</h5>
              <pre>{JSON.stringify(formData, null, 2)}</pre>
            </Col> */}
          </Row>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default Areas;
