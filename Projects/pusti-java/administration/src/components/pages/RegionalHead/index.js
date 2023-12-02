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

const RegionalHead = () => {
    const [allRegionalHead, setAllRegionalHead] = useState([]);
    const [allDivisionalHead, setAllDivisionalHead] = useState([]);
    const [regionalHeadItem, setRegionalHeadItem] = useState([]);
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
                    process.env.REACT_APP_BASE_URL + `regionalhead/getRegionalHeadById/${id}`,
                    { headers: authHeader() }
                )
                .then(response => {
                    if (response.data) {
                        setRegionalHeadItem(response.data);
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
        const regionalHeadInfo = {
            name: data.regionalHeadName,
            divisionalHead: { divisionalHeadId: data.divisionalHeadId }
        };

        // Create Regional head
        if (regionalHeadInfo) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'regionalhead/addNewRegionalHead';
            fetch(url, {
                method: 'POST',
                headers: authHeader(),
                body: JSON.stringify(regionalHeadInfo)
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
                    setAllDivisionalHead(response?.data);
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
        const url = process.env.REACT_APP_BASE_URL + 'regionalhead/getAllRegionalHeads';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                if (response.data) {
                    setAllRegionalHead(response?.data);
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
        console.log(values)
        const regionalHeadInfo = {
            name: values.updateRegionalHeadName,
            divisionalHead: { divisionalHeadId: values.updateDivisionalHeadId }
        };

        console.log(regionalHeadInfo)

        if (regionalHeadInfo && updateId) {
            setIsLoading(true);
            const url =
                process.env.REACT_APP_BASE_URL + `regionalhead/updateRegionalHead/${updateId}`;
            axios
                .put(url, regionalHeadInfo, { headers: authHeader() })
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
                    process.env.REACT_APP_BASE_URL + `regionalhead/deleteRegionalHeadById/${id}`,
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
                const { divisionalHead } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{divisionalHead?.divisionalHeadId}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },

        {
            accessor: 'divisionalHeadName',
            Header: 'Divisional Head Name',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { divisionalHead } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{divisionalHead?.name}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: 'regionalHeadId',
            Header: 'Regional Head ID',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { regionalHeadId } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{regionalHeadId}</h5>
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
                const { regionalHeadId, status } = rowData.row.original;

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
                                    `regionalhead/statusChange/${regionalHeadId}`
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
                const { regionalHeadId } = rowData.row.original;
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
                                    onClick={() => handleShowUpdateModal(regionalHeadId)}
                                >
                                    Edit
                                </Button>
                            </Dropdown.Item>
                            <Dropdown.Item href="">
                                {' '}
                                <Button
                                    variant="light"
                                    size="sm"
                                    onClick={() => DeleteBankItem(regionalHeadId)}
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
                data={allRegionalHead}
                // selection
                sortable
                pagination
                perPage={10}
            >
                <Card className="mb-3">
                    <Card.Header>
                        <CustomersTableHeader
                            title="Regional Head"
                            handleShow={handleShow}
                            data={allRegionalHead}
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
                    <Modal.Title>Create Regional Head</Modal.Title>
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
                                <Form.Label>Select Divisional Head</Form.Label>
                                <Form.Select
                                    aria-label="Default select example"
                                    type="text"
                                    name="divisionalHeadId"
                                    isInvalid={!!errors.divisionalHeadId}
                                    {...register('divisionalHeadId', {
                                        required: 'Divisional HeadId is required'
                                    })}
                                >
                                    <option defaultChecked>Select Divisional Head</option>
                                    {allDivisionalHead?.map((item, i) => (
                                        <option key={i} value={item?.divisionalHeadId}>
                                            {item?.name}
                                        </option>
                                    ))}
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    {errors.divisionalHeadId && errors.divisionalHeadId.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                                <Form.Label>Regional Head Name</Form.Label>
                                <Form.Control
                                    aria-label="Default select example"
                                    type="text"
                                    placeholder="Enter RegionalHead"
                                    name="regionalHeadName"
                                    isInvalid={!!errors.regionalHeadName}
                                    {...register('regionalHeadName', {
                                        required: 'RegionalHead Name field is required'
                                    })}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.regionalHeadName && errors.regionalHeadName.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Row>

                        <Button
                            variant="primary"
                            type="submit"
                            disabled={
                                !watch().regionalHeadName ||
                                watch().divisionalHeadId == 'Select Divisional Head'
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
                    <Modal.Title>RegionalHead Update</Modal.Title>
                    <CloseButton
                        className="btn btn-circle btn-sm transition-base p-0"
                        onClick={() => setShowUpdateModal(false)}
                    />
                </Modal.Header>
                <Modal.Body>
                    <Formik
                        initialValues={{

                            updateDivisionalHeadId: regionalHeadItem?.divisionalHead?.
                                divisionalHeadId,
                            updateRegionalHeadName: regionalHeadItem?.name
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
                                        // value={values.updateDivisionalHeadId}
                                        >
                                            {allDivisionalHead?.map((item, i) => (
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
                                        controlId="email"
                                    >
                                        <Form.Label>Regional Head Name</Form.Label>
                                        <Form.Control
                                            aria-label="Default select example"
                                            type="text"
                                            placeholder="Enter Regional Head Name"
                                            name="updateRegionalHeadName"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateRegionalHeadName}
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

export default RegionalHead;
