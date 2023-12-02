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

const index = () => {
  const [NoticeData, setNoticeData] = useState([]);
  const [nationalData, setNationalData] = useState([]);
  const [noticesItemData, setNoticeItemData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
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
          process.env.REACT_APP_BASE_URL + `notices/getById/${id}`,
          { headers: authHeader() }
        )
        .then(response => {
          if (response.data) {
            setNoticeItemData(response.data);
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
    const noticeData =
      {
    
        noticeType: 'Text',
        noticeDetails: data.noticeDetails,
        effectiveDate: data.effectiveDate
        
      };


    // Create Division
    if (noticeData) {
      setIsLoading(true);
      const url = process.env.REACT_APP_BASE_URL + 'notices/add';
      fetch(url, {
        method: 'POST',
        headers: authHeader(),
        body: JSON.stringify(noticeData)
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
    const url = process.env.REACT_APP_BASE_URL + 'notices/getAll';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        let index = 1;
        const result = [];
        if (response.data) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setNoticeData(result);
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
    const noticeData = {
      effectiveDate: values.updateEffictiveDate,
      noticeDetails: values.updateNoticeDetelis,
      noticeType: values.updateNoticeType,
      
    };

    if (noticeData && updateId) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL + `notices/update/${updateId}`;
      axios
        .put(url, noticeData, { headers: authHeader() })
        .then(response => {
          if (response.data) {
            // debugger ;
            setIsLoading(false);
            
            setShowUpdateModal(false);
            setCount(count + 1);
            toast.success('Update Success');
          }
        })
        .catch(error => {
          // setIsLoading(false);
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
          process.env.REACT_APP_BASE_URL + `notices/delete/${id}`,
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
      accessor: 'index',
      Header: 'SL',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { index } = rowData.row.original;
        return (
          
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{index}</h5>
            </div>
          </Flex>
          
        );
      }
    },
    {
      accessor: 'title',
      Header: 'Title',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { title } = rowData.row.original;
        return (
         
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{title}</h5>
            </div>
          </Flex>
         
        );
      }
    },

    {
      accessor: 'description',
      Header: 'Description',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { description } = rowData.row.original;
        return (
         
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{description}</h5>
            </div>
          </Flex>
          
        );
      }
    },
    {
      accessor: 'effective date',
      Header: 'Effective Date ',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { effectiveDate } = rowData.row.original;
        return (
        
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{effectiveDate}</h5>
            </div>
          </Flex>
          
        );
      }
    },
    {
      accessor: 'expiryDate',
      Header: 'Expiry date',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { expiryDate } = rowData.row.original;
        return (
        
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{expiryDate}</h5>
            </div>
          </Flex>
          
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
        const { id } = rowData.row.original;
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
                  onClick={() => handleShowUpdateModal(id)}
                >
                  Edit
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  // onClick={() => DeleteBankItem(id)}
                >
                  View File
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteBankItem(id)}
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
        data={NoticeData}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Office Notice"
              handleShow={handleShow}
              data={NoticeData}
              newUrl='/master/officenotice/add'
              isFilter={false}
              isExport={false}
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
          <Modal.Title>Create Notice</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShow(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Form noValidate onSubmit={handleSubmit(onSubmit)}>
            <Row>
            <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Notice Type</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Division Name"
                  name="noticeType"
                  value='Text'
                  isInvalid={!!errors.noticeType}
                  {...register('noticeType', {
                    required: 'Notice  Type field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.noticeType && errors.noticeType.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" className="mb-3" controlId="">
                <Form.Label>Notice Details </Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Division Name"
                  name="noticeDetails"
                  isInvalid={!!errors.noticeDetails}
                  {...register('noticeDetails', {
                    required: 'Deltails  field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.noticeDetails && errors.noticeDetails.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" className="mb-3" controlId="">
                <Form.Label>Effective Date </Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="date"
                  placeholder="Enter Division Name"
                  name="effectiveDate"
                  isInvalid={!!errors.effectiveDate}
                  {...register('effectiveDate', {
                    required: 'Division Name field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.effectiveDate && errors.effectiveDate.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>

            <Button
              variant="primary"
              type="submit"
              disabled={
                !watch().noticeType ||
                watch().noticeDetails == 'Select National'
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


{/* Update Notices  */}


      <Modal
        show={showUpdateModal}
        fullscreen={mediumScreen}
        onHide={() => setShow(false)}
      >
        <Modal.Header>
          <Modal.Title>Notice Update</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShowUpdateModal(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Formik
            initialValues={{
              updateNoticeType: noticesItemData.noticeType,
              updateNoticeDetelis: noticesItemData?.noticeDetails,
              updateEffictiveDate: noticesItemData?.effectiveDate
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
                    <Form.Label> Update Notice Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateNoticeType"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateNoticeType}
                    />

                    <Form.Control.Feedback type="invalid">
                      {/* {errors.bank_name && errors.bank_name.message} */}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="6"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label> Update Notice Details</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateNoticeDetelis"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateNoticeDetelis}
                    />

                    <Form.Control.Feedback type="invalid">
                      {/* {errors.bank_name && errors.bank_name.message} */}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="6"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label> Update Notice EffectiveDate</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateEffictiveDate"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateEffictiveDate}
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

export default index;
