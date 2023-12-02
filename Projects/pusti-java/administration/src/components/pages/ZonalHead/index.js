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

const ZonalHead = () => {
    const [allZonalHead, setAllZonalHead] = useState([]);
    const [divisionalHead, setDivisionalHead] = useState([]);
    const [regionalHead, setRegionalHead] = useState([]);
    const [zonalHeadItemData, setZonalHeadItemData] = useState([]);
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
                    process.env.REACT_APP_BASE_URL + `zonalhead/getZonalHeadById/${id}`,
                    { headers: authHeader() }
                )
                .then(response => {
                    if (response.data) {
                        setZonalHeadItemData(response.data);
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
        const zonalHeadInfo = {
            name: data.zonalHeadName,
            regionalHead: { regionalHeadId: data.regionalHeadId, divisionalHead: { divisionalHeadId: data.divisionalHeadId } }
        };

        // Create Zonal Head
        if (zonalHeadInfo) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'zonalhead/addNewZonalHead';
            fetch(url, {
                method: 'POST',
                headers: authHeader(),
                body: JSON.stringify(zonalHeadInfo)
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

    // Load All Divisional Head 
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'divisionalHead/getAllDivisionalHeads';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                if (response.data) {
                    setDivisionalHead(response?.data);
                    setIsLoading(false);
                }
            })
            .catch(error => {
                setIsLoading(false);
                console.log(error);
            });
    }, []);


    // Load All Regional Head 
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'regionalhead/getAllRegionalHeads';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                if (response.data) {
                    setRegionalHead(response?.data);
                    setIsLoading(false);
                }
            })
            .catch(error => {
                setIsLoading(false);
                console.log(error);
            });
    }, []);



    // Load Zonal Head Data
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'zonalhead/getAllZonalHeads';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                if (response.data) {
                    setAllZonalHead(response?.data);
                    setIsLoading(false);
                }
            })
            .catch(error => {
                setIsLoading(false);
                console.log(error);
            });
    }, [count]);

    // Update Zonal Head Data
    const handleBankSubmit = (values, actions) => {
        const zonalHeadInfo = {
            name: values.updateZonalHeadName,
            regionalHead: {
                regionalHeadId: values.updateRegionalHeadId, divisionalHead: {
                    divisionalHeadId: values.updateDivisionalHeadId
                }
            }
        };

        if (zonalHeadInfo && updateId) {
            setIsLoading(true);
            const url =
                process.env.REACT_APP_BASE_URL + `zonalhead/updateZonalHead/${updateId}`;
            axios
                .put(url, zonalHeadInfo, { headers: authHeader() })
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

    // Delete Zonal Head
    const DeleteBankItem = id => {
        if (confirm('Are You Sure ?')) {
            setIsLoading(true);
            axios
                .delete(
                    process.env.REACT_APP_BASE_URL + `zonalhead/deleteZonalHeadById/${id}`,
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
            accessor: 'divisionalHeadName',
            Header: 'Divisional Head Name',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { regionalHead } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{regionalHead?.divisionalHead?.name}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: 'regionalHeadName',
            Header: 'Regional Head Name',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { regionalHead } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{regionalHead?.name}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: 'zonalHeadId',
            Header: 'Zonal Head ID',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { zonalHeadId } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{zonalHeadId}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },

        {
            accessor: 'zonalHeadName',
            Header: 'Zonal Head Name',
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
                const { zonalHeadId, status } = rowData.row.original;

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
                                    `zonalhead/statusChange/${zonalHeadId}`
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
                const { zonalHeadId } = rowData.row.original;
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
                                    onClick={() => handleShowUpdateModal(zonalHeadId)}
                                >
                                    Edit
                                </Button>
                            </Dropdown.Item>
                            <Dropdown.Item href="">
                                {' '}
                                <Button
                                    variant="light"
                                    size="sm"
                                    onClick={() => DeleteBankItem(zonalHeadId)}
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
                data={allZonalHead}
                // selection
                sortable
                pagination
                perPage={10}
            >
                <Card className="mb-3">
                    <Card.Header>
                        <CustomersTableHeader
                            title="Zonal Head"
                            handleShow={handleShow}
                            data={allZonalHead}
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
                    <Modal.Title>Create Zonal Head</Modal.Title>
                    <CloseButton
                        className="btn btn-circle btn-sm transition-base p-0"
                        onClick={() => setShow(false)}
                    />
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate onSubmit={handleSubmit(onSubmit)}>
                        <Row>
                            <Form.Group
                                as={Col}
                                md="6"
                                className="mb-3"
                                controlId="formHookname"
                            >
                                <Form.Label>Select DivisionalHead</Form.Label>
                                <Form.Select
                                    aria-label="Default select example"
                                    type="text"
                                    name="divisionalHeadId"
                                    isInvalid={!!errors.divisionalHeadId}
                                    {...register('divisionalHeadId', {
                                        required: 'Divisional Head field is required'
                                    })}
                                >
                                    <option defaultChecked>Select Divisional Head</option>
                                    {divisionalHead?.map((item, i) => (
                                        <option key={i} value={item?.divisionalHeadId}>
                                            {item?.name}
                                        </option>
                                    ))}
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    {errors.divisionalHeadId && errors.divisionalHeadId.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group
                                as={Col}
                                md="6"
                                className="mb-3"
                                controlId="formHookname"
                            >
                                <Form.Label>Select Regional Head</Form.Label>
                                <Form.Select
                                    aria-label="Default select example"
                                    type="text"
                                    name="regionalHeadId"
                                    isInvalid={!!errors.regionalHeadId}
                                    {...register('regionalHeadId', {
                                        required: 'Regional Head field is required'
                                    })}
                                >
                                    <option defaultChecked>Select Regional Head</option>
                                    {regionalHead?.map((item, i) => (
                                        <option key={i} value={item?.regionalHeadId}>
                                            {item?.name}
                                        </option>
                                    ))}
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    {errors.regionalHeadId && errors.regionalHeadId.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                                <Form.Label>Zonal Head Name</Form.Label>
                                <Form.Control
                                    aria-label="Default select example"
                                    type="text"
                                    placeholder="Enter Division Name"
                                    name="zonalHeadName"
                                    isInvalid={!!errors.zonalHeadName}
                                    {...register('zonalHeadName', {
                                        required: 'Zonal Head Name field is required'
                                    })}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.zonalHeadName && errors.zonalHeadName.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Row>

                        <Button
                            variant="primary"
                            type="submit"
                            disabled={
                                !watch().zonalHeadName ||
                                watch().regionalHeadId == 'Select Regional Head' || watch().divisionalHeadId == 'Select Divisional Head'
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
                    <Modal.Title>Zonal Head Update</Modal.Title>
                    <CloseButton
                        className="btn btn-circle btn-sm transition-base p-0"
                        onClick={() => setShowUpdateModal(false)}
                    />
                </Modal.Header>
                <Modal.Body>
                    <Formik
                        initialValues={{
                            updateDivisionalHeadId: zonalHeadItemData?.regionalHead?.divisionalHead?.divisionalHeadId,
                            updateRegionalHeadId: zonalHeadItemData?.regionalHead?.regionalHeadId,
                            updateZonalHeadName: zonalHeadItemData?.name
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
                                        controlId="formHookname"
                                    >
                                        <Form.Label>Select Divisional Head</Form.Label>
                                        <Form.Select
                                            aria-label="Default select example"
                                            type="text"
                                            name="updateDivisionalHeadId"
                                            required

                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateDivisionalHeadId}
                                        >
                                            {divisionalHead?.map((item, i) => (
                                                <option key={i} value={item?.divisionalHeadId}>
                                                    {item?.name}
                                                </option>
                                            ))}
                                        </Form.Select>
                                        <Form.Control.Feedback type="invalid">
                                            {/* {errors.sales_org_id && errors.sales_org_id.message} */}
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                    <Form.Group
                                        as={Col}
                                        md="6"
                                        className="mb-3"
                                        controlId="formHookname"
                                    >
                                        <Form.Label>Select Regional Head</Form.Label>
                                        <Form.Select
                                            aria-label="Default select example"
                                            type="text"
                                            name="updateRegionalHeadId"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateRegionalHeadId}
                                        >
                                            {regionalHead?.map((item, i) => (
                                                <option key={i} value={item?.regionalHeadId}>
                                                    {item?.name}
                                                </option>
                                            ))}
                                        </Form.Select>
                                        <Form.Control.Feedback type="invalid">
                                            {/* {errors.sales_org_id && errors.sales_org_id.message} */}
                                        </Form.Control.Feedback>
                                    </Form.Group>
                                    <Form.Group
                                        as={Col}
                                        md="6"
                                        className="mb-3"
                                        controlId="email"
                                    >
                                        <Form.Label>Zonal Head Name</Form.Label>
                                        <Form.Control
                                            aria-label="Default select example"
                                            type="text"
                                            placeholder="Enter Zonal Head Name"
                                            name="updateZonalHeadName"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateZonalHeadName}
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

export default ZonalHead;
