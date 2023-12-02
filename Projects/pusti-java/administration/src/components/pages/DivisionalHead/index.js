import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useEffect, useState } from 'react';
import {
  Button,
  Card,
  CloseButton,
  Col,
  Dropdown,
  Form,
  Modal,
  Row
} from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import { Formik } from 'formik';
import ToggleButton from 'components/common/Toggle-button/index';

const DivisionalHead = () => {
  const [allDivisionalHead, setAllDivisionalHead] = useState([]);
  const [nationalData, setNationalData] = useState([]);
  const [divisionalItemData, setDivisionalItemData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  //   const values = [true, 'sm-down', 'md-down', 'lg-down', 'xl-down', 'xxl-down'];
  const [fullscreen, setFullscreen] = useState('xl-down');
  const [show, setShow] = useState(false);
  const [formData, setData] = useState({});
  const [mediumScreen, setMediumScreen] = useState('xl-down');
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [count, setCount] = useState(0);

  const handleShow = () => {
    setShow(true);
  };

  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors }
  } = useForm();

  // Get Single Item
  const handleShowUpdateModal = id => {
    if (id) {
      setUpdateId(id);
      setIsLoading(true);
      axios
        .get(
          process.env.REACT_APP_BASE_URL + `divisionalHead/getDivisionHeadById/${id}`,
          { headers: authHeader() }
        )
        .then(response => {
          if (response.data) {
            setDivisionalItemData(response.data);
            setIsLoading(false);
          }
        })
        .catch(error => {
          setIsLoading(false);
          console.log(error);
        });
    }
    setShowUpdateModal(true);
  };

  const onSubmit = data => {
    const divisionalHeadData = {
      name: data.divisionalHeadName,
     
    };
    console.log(divisionalHeadData)

    // Create Division
    if (divisionalHeadData) {
      setIsLoading(true);
      const url = process.env.REACT_APP_BASE_URL + 'divisionalHead/addNewDivisionHead';
      fetch(url, {
        method: 'POST',
        headers: authHeader(),
        body: JSON.stringify(divisionalHeadData)
      })
        .then(res => res.json())
        .then(data => {
          if (data) {
            setIsLoading(false);
            setShow(false);
            reset();
            setCount(count + 1);
            toast.success(data.message);
          }
        })
        .catch(error => {
          setIsLoading(false);
          reset();
          console.log(error);
        });
    }

    setData(data);
  };

  // Load All National Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + 'national/getAllNationals';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          setNationalData(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Load Division Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + 'divisionalHead/getAllDivisionalHeads';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          setAllDivisionalHead(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

  // Update Division Data
  const handleBankSubmit = (values, actions) => {
    const divisionalHeadInfo = {
      name: values.updateDivisionalHeadName,
      
    };

    if (divisionalHeadInfo && updateId) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL + `divisionalHead/updateDivisionHead/${updateId}`;
      axios
        .put(url, divisionalHeadInfo, { headers: authHeader() })
        .then(response => {
          if (response.data) {
            setIsLoading(false);
            setShowUpdateModal(false);
            setCount(count + 1);
            toast.success('Update Success');
          }
        })
        .catch(error => {
          setIsLoading(false);
          console.log(error);
        });
    }
  };

  // Delete Division
  const DeleteBankItem = id => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(
          process.env.REACT_APP_BASE_URL + `divisionalHead/deleteDivisionalHead/${id}`,
          { headers: authHeader() }
        )
        .then(response => {
          if (response) {
            setIsLoading(false);
            toast.success('Delete Success');
            setCount(count + 1);
          }
        })
        .catch(error => {
          console.log(error);
          setIsLoading(false);
        });
    }
  };

  if (isLoading) {
    return <h4>Loading...</h4>;
  }

  /* 
  ..........................
  Columns Data here
  .........................
  */

  const columns = [
    {
      accessor: 'divisionalHeadId',
      Header: 'Divisional Head ID',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { divisionalHeadId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{divisionalHeadId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },

    {
      accessor: 'name',
      Header: 'Divisional Head Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { name } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{name}</h5>
            </div>
          </Flex>
          // </Link>
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
        const { divisionalHeadId, status } = rowData.row.original;

        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `divisionalHead/statusChange/${divisionalHeadId}`
                }
              ></ToggleButton>
              {/* <h5 className="mb-0 fs--1">{status === true ? "Active" : "InActive"}</h5> */}
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'action',
      Header: 'Action',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { divisionalHeadId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => handleShowUpdateModal(divisionalHeadId)}
                >
                  Edit
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteBankItem(divisionalHeadId)}
                >
                  Delete
                </Button>
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        );
      }
    }
  ];

  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={allDivisionalHead}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Divisional Head"
              handleShow={handleShow}
              data={allDivisionalHead}
              table
            />
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
          <Modal.Title>Create Divisional Head</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShow(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Form noValidate onSubmit={handleSubmit(onSubmit)}>
            <Row>
             
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Divisional Head Name</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Divisional Head Name"
                  name="divisionalHeadName"
                  isInvalid={!!errors.divisionalHeadName}
                  {...register('divisionalHeadName', {
                    required: 'Divisional Head Name field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.divisionalHeadName && errors.divisionalHeadName.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>

            <Button
              variant="primary"
              type="submit"
              disabled={
                !watch().divisionalHeadName
               
              }
            >
              Submit
            </Button>
          </Form>

          {/* <Col md="auto">
              <h5 className="mt-4">Result</h5>
              <pre>{JSON.stringify(formData, null, 2)}</pre>
            </Col> */}
        </Modal.Body>
      </Modal>
      <Modal
        show={showUpdateModal}
        fullscreen={mediumScreen}
        onHide={() => setShow(false)}
      >
        <Modal.Header>
          <Modal.Title>Division Update</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShowUpdateModal(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Formik
            initialValues={{
             
              updateDivisionalHeadName: divisionalItemData?.divisionName
            }}
            // validationSchema={loginSchema}
            onSubmit={handleBankSubmit}
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
                <Row>
                  
                  <Form.Group
                    as={Col}
                    md="6"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Divisional Head Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateDivisionalHeadName"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateDivisionalHeadName}
                    />

                    <Form.Control.Feedback type="invalid">
                      {/* {errors.bank_name && errors.bank_name.message} */}
                    </Form.Control.Feedback>
                  </Form.Group>
                </Row>
                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </Form>
            )}
          </Formik>

          {/* <Col md="auto">
          <h5 className="mt-4">Result</h5>
          <pre>{JSON.stringify(formData, null, 2)}</pre>
        </Col> */}
        </Modal.Body>
      </Modal>
    </>
  );
};

export default DivisionalHead;
