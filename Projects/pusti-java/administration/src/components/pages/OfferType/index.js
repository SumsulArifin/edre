import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useEffect, useState } from 'react';
import { Button, Card, CloseButton, Col, Dropdown, Form, Modal, Row } from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import { Formik } from 'formik';
import { Link } from 'react-router-dom';
import ToggleButton from 'components/common/Toggle-button';


const OfferType = () => {
    const [offerTypeData, setOfferTypeData] = useState([]);
    const [singleCategoryType, setSingleCategoryType] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [fullscreen, setFullscreen] = useState('xl-down');
    const [show, setShow] = useState(false);
    const [formData, setData] = useState({});
    const [mediumScreen, setMediumScreen] = useState('xl-down');
    const [showUpdateModal, setShowUpdateModal] = useState(false);
    const [updateId, setUpdateId] = useState(null);
    const [count, setCount] = useState(0);


    // Handle Modal Status
    const handleShow = () => {
        setShow(true);
    };



    // Get Single Item
    const handleShowUpdateModal = id => {
        if (id) {
            setUpdateId(id);
            setIsLoading(true);
            axios
                .get(
                    process.env.REACT_APP_BASE_URL + `categoryType/getCategoryTypeById/${id}`,
                    { headers: authHeader() }
                )
                .then(response => {
                    if (response.data) {
                        setSingleCategoryType(response.data);
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

    // React Hook Form
    const {
        register,
        handleSubmit,
        watch,
        reset,
        formState: { errors }
    } = useForm();



    // Submit Create Form
    const onSubmit = data => {

        const categoryTypeInfo = {
            name: data.categoryTypeName,
            description: data.comments


        };

        // Create Category Type
        if (categoryTypeInfo) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'categoryType/addNewCategoryType';
            fetch(url, {
                method: 'POST',
                headers: authHeader(),
                body: JSON.stringify(categoryTypeInfo)
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


    // Load Category Type 
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'offerType/getAll';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                const result = []
                let index = 1;
                if (response.data) {
                    response?.data?.forEach((element) => {
                        const offerType = { index, ...element }
                        result.push(offerType);
                        index++
                    });
                    setOfferTypeData(result);
                    setIsLoading(false);
                }
            })
            .catch(error => {
                setIsLoading(false);
                console.log(error);
            });
    }, [count]);



    // Update category Type Info
    const handleCategoryUpdate = (values, actions) => {
        const categoryTypeUpdateInfo = {
            name: values.updateCategoryTypeName,
            description: values.updateCategoryTypeDescription
        };

        if (categoryTypeUpdateInfo && updateId) {
            setIsLoading(true);
            const url =
                process.env.REACT_APP_BASE_URL + `categoryType/updateCategoryType/${updateId}`;
            axios
                .put(url, categoryTypeUpdateInfo, { headers: authHeader() })
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



    // Delete Category Type Item
    const DeleteOfferType = id => {
        if (confirm('Are You Sure ?')) {
            setIsLoading(true);
            axios
                .delete(
                    process.env.REACT_APP_BASE_URL + `offerType/delete/${id}`,
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
            accessor: 'SL',
            Header: 'SL',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { index } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{index}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: 'name',
            Header: 'Offer Type Name',
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
            accessor: 'description',
            Header: 'Description',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { description } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1" style={{ wordWrap: 'break-word', whiteSpace: 'pre-wrap', maxWidth: '250px', textAlign: 'justify' }} >{description}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: "status",
            Header: "Status",
            headerProps: { className: "pe-1" },
            cellProps: {
                className: "py-2",
            },
            Cell: (rowData) => {
                const { id, status } = rowData.row.original;

                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <ToggleButton
                                count={count}
                                setCount={setCount}
                                status={status}
                                url={process.env.REACT_APP_BASE_URL + `offerType/statusChange/${id}`}
                            ></ToggleButton>
                        </div>
                    </Flex>
                    // </Link>
                );
            },
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

                                <Link to={`/offerType/add/${id}`}><Button
                                    variant="light"
                                    size="sm"

                                >
                                    Edit
                                </Button></Link>
                            </Dropdown.Item>
                            <Dropdown.Item href="">
                                {' '}
                                <Button
                                    variant="light"
                                    size="sm"
                                    onClick={() => DeleteOfferType(id)}
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
                data={offerTypeData}
                // selection
                sortable
                pagination
                perPage={10}
            >
                <Card className="mb-3">
                    <Card.Header>
                        <CustomersTableHeader
                            title="Offer Type"
                            newUrl='/offerType/add'
                            data={offerTypeData}
                            is
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


        </>
    );
};

export default OfferType;
