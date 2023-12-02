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
import LoadingIcon from 'helpers/LoadingIcon';
import { Link } from 'react-router-dom';
import IconButton from 'components/common/IconButton';

const Inventory = () => {
    const [inventoryData, setInventoryData] = useState([]);
    const [inventoryItemData, setInventoryItemData] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
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
                    process.env.REACT_APP_BASE_URL + `inventory/getInventoryById/${id}`,
                    { headers: authHeader() }
                )
                .then(response => {
                    if (response.data) {
                        setInventoryItemData(response.data);
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
        const InventoryInfo = {
            name: data.inventoryName,
            address: data.address

        };


        // Create Inventory
        if (InventoryInfo) {
            setIsLoading(true);
            const apiUrl = process.env.REACT_APP_BASE_URL + 'inventory/addNewInventory';

            axios.post(apiUrl, InventoryInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    setShow(false);
                    reset();
                    setCount(count + 1);
                    toast.success(response.data.message);
                })
                .catch(error => {
                    setIsLoading(false);

                    reset();
                    console.log(error);
                });
        }

        setData(data);
    };


    // Update Inventory Item
    const handleInventoryUpdate = (values, actions) => {
        const updateInventoryInfo = {
            name: values.updateInventoryName,
            address: values.updateAddress,

        };



        if (updateInventoryInfo && updateId) {
            setIsLoading(true);
            const url =
                process.env.REACT_APP_BASE_URL + `inventory/updateInventory/${updateId}`;
            axios
                .put(url, updateInventoryInfo, { headers: authHeader() })
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

    // Get All Inventory Data
    useEffect(() => {
        axios
            .get(
                process.env.REACT_APP_BASE_URL + "inventory/getAllInventories",
                { headers: authHeader() }
            )
            .then(response => {
                let index = 1;
                const result = [];
                if (response.status === 200) {
                    response?.data?.forEach((element) => {
                        const addIndex = { ...element, index };
                        result.push(addIndex);
                        index++;
                    });
                    setInventoryData(result);
                    setIsLoading(false);
                }
            })
            .catch(error => {
                setIsLoading(false);
                reset();
                toast.error(`! ${error.response.data.errors[0]}. 
                ${error.message}
                `)
            })
    }, [count])



    // Delete Inventory
    const Delete_Inventory = id => {
        if (confirm('Are You Sure ?')) {
            setIsLoading(true);
            axios
                .delete(
                    process.env.REACT_APP_BASE_URL + `inventory/deleteFactoryById/${id}`,
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
        return <LoadingIcon />
    }



    /*******************
     Columns Start.
    *******************/

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
            accessor: 'inventoryName',
            Header: 'Inventory Name',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { name } = rowData.row.original;
                return (

                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{name}</h5>
                        </div>
                    </Flex>

                );
            }
        },

        {
            accessor: 'address',
            Header: 'Address',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { address } = rowData.row.original;
                return (

                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{address}</h5>
                        </div>
                    </Flex>

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
                const { inventoryId, status } = rowData.row.original;

                return (

                    <Flex alignItems="center">
                        <div className="flex-1">
                            <ToggleButton
                                count={count}
                                setCount={setCount}
                                status={status}
                                url={
                                    process.env.REACT_APP_BASE_URL +
                                    `inventory/statusChange/${inventoryId}`
                                }
                            ></ToggleButton>

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
                const { inventoryId } = rowData.row.original;
                return (
                    <Dropdown className="d-inline mx-2">
                        <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
                            ...
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item href="">
                                <Link to={`/master/inventory/add/${inventoryId}`}>
                                    <IconButton
                                        variant="falcon-default"
                                        size="sm"
                                        icon="edit"
                                        transform="shrink-2"
                                        iconAlign="middle"
                                        className="me-2"
                                    >
                                        <span className="d-none d-xl-inline-block ms-1">Edit</span>
                                    </IconButton>
                                </Link>
                            </Dropdown.Item>
                            <Dropdown.Item href="">
                                <IconButton
                                    onClick={() => Delete_Inventory(inventoryId)}
                                    variant="falcon-default"
                                    size="sm"
                                    icon="trash-alt"
                                    iconAlign="middle"
                                    className="d-none d-sm-block me-2"
                                >
                                    <span className="d-none d-xl-inline-block ms-1">Delete</span>
                                </IconButton>

                            </Dropdown.Item>
                        </Dropdown.Menu>
                    </Dropdown>
                );
            }
        }
    ];

    /*******************
Columns End.
*******************/

    return (
        <>
            <AdvanceTableWrapper
                columns={columns}
                data={inventoryData}
                sortable
                pagination
                perPage={10}
            >
                <Card className="mb-3">
                    <Card.Header>
                        <CustomersTableHeader
                            title="Inventory Management"
                            newUrl="/master/inventory/add"
                            handleShow={handleShow}
                            data={inventoryData}
                            isFilter={false}
                            excelFileName="Inventory.xlsx"
                            excelUrl="inventory/excelDownload"
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

            <Modal show={show} fullscreen={mediumScreen} onHide={() => setShow(false)}>
                <Modal.Header>
                    <Modal.Title>Create Inventory</Modal.Title>
                    <CloseButton
                        className="btn btn-circle btn-sm transition-base p-0"
                        onClick={() => setShow(false)}
                    />
                </Modal.Header>
                <Modal.Body>
                    <Form noValidate onSubmit={handleSubmit(onSubmit)}>

                        <Row>
                            <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                <Form.Label>Inventory Name</Form.Label>
                                <Form.Control
                                    aria-label="Default select example"
                                    type="text"
                                    placeholder="Enter Inventory Name"
                                    name="inventoryName"
                                    isInvalid={!!errors.inventoryName}
                                    {...register('inventoryName', {
                                        required: 'Inventory Name field is required'
                                    })}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.inventoryName && errors.inventoryName.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                <Form.Label>Address</Form.Label>
                                <Form.Control as="textarea" rows={3}
                                    aria-label="Default select example"
                                    type="text"
                                    placeholder="Enter Address "
                                    name="address"
                                    isInvalid={!!errors.address}
                                    {...register('address', {
                                        required: 'Address field is required'
                                    })}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.address && errors.address.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Row>

                        <Button
                            variant="primary"
                            type="submit"
                            disabled={
                                !watch().inventoryName || !watch().address
                            }
                        >
                            Submit
                        </Button>
                    </Form>
                </Modal.Body>
            </Modal>
            <Modal
                show={showUpdateModal}
                fullscreen={mediumScreen}
                onHide={() => setShow(false)}
            >
                <Modal.Header>
                    <Modal.Title>Inventory Update</Modal.Title>
                    <CloseButton
                        className="btn btn-circle btn-sm transition-base p-0"
                        onClick={() => setShowUpdateModal(false)}
                    />
                </Modal.Header>
                <Modal.Body>
                    <Formik

                        initialValues={{
                            updateInventoryName: inventoryItemData.name,
                            updateAddress: inventoryItemData.address,


                        }}

                        onSubmit={handleInventoryUpdate}
                    >
                        {({
                            errors,
                            touched,
                            handleSubmit,
                            handleChange,
                            handleBlur,
                            values
                        }) => (

                            <Form onSubmit={handleSubmit}>

                                <Row>
                                    <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                        <Form.Label>Inventory Name</Form.Label>
                                        <Form.Control
                                            aria-label="Default select example"
                                            type="text"
                                            placeholder="Enter Inventory Name"
                                            name="updateInventoryName"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateInventoryName}
                                        />

                                    </Form.Group>
                                    <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                        <Form.Label>Address</Form.Label>
                                        <Form.Control as="textarea" rows={3}
                                            aria-label="Default select example"
                                            type="text"
                                            placeholder="Enter Address "
                                            name="updateAddress"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateAddress}

                                        />

                                    </Form.Group>
                                </Row>

                                <Button
                                    variant="primary"
                                    type="submit"

                                >
                                    Submit
                                </Button>
                            </Form>
                        )}
                    </Formik>

                </Modal.Body>
            </Modal>
        </>
    );
};

export default Inventory;
