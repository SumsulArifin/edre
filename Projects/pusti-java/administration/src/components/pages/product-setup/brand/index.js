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
import { Link } from 'react-router-dom';
import IconButton from 'components/common/IconButton';

const Banks = () => {
    const [brandData, setBrandData] = useState([]);
    const [brandItemData, setBrandItemData] = useState([]);
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


    const handleShowUpdateModal = (id) => {

        if (id) {
            setUpdateId(id);
            setIsLoading(true)
            axios.get(process.env.REACT_APP_BASE_URL + `brand/getBrandById/${id}`, { headers: authHeader() })
                .then(response => {
                    if (response.data) {
                        setBrandItemData(response.data);
                        setIsLoading(false)
                    }
                })
                .catch(error => {
                    setIsLoading(false)
                    toast.error(error.message);
                })
        }
        setShowUpdateModal(true);
    }


    const onSubmit = data => {
        const brandData = {
            brandName: data.brand_name,
            brandType: data.brand_type
        }

        // Create Brand
        if (brandData) {
            setIsLoading(true);
            fetch(process.env.REACT_APP_BASE_URL + "brand/addNewBrand", {
                method: 'POST',
                headers: authHeader(),
                body: JSON.stringify(brandData)
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
                    toast.error(error.message);
                })
        }

        setData(data);
    };




    // Load Brand Data
    useEffect(() => {
        setIsLoading(true)
        axios.get(process.env.REACT_APP_BASE_URL + "brand/getAllBrands", { headers: authHeader() })
            .then(response => {

                let index = 1;
                const result = [];
                if (response.data) {
                    response?.data?.forEach((element) => {
                        const addIndex = { ...element, index };
                        result.push(addIndex);
                        index++;
                    });

                    setBrandData(result);
                    setIsLoading(false)
                }
            })
            .catch(error => {
                setIsLoading(false)
                toast.error(error.message);
            })
    }, [count])

    // Update Brand Item
    const handleBankSubmit = (values, actions) => {
        const brandData = {
            brandName: values.update_brand_name,
            brandType: values.update_brand_type,
        }

        if (brandData && updateId) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `brand/updateBrand/${updateId}`
            axios.put(url, brandData, { headers: authHeader() })
                .then(response => {
                    if (response.data) {
                        setIsLoading(false);
                        setShowUpdateModal(false);
                        setCount(count + 1);
                        toast.success("Update Success");
                    }
                })
                .catch(error => {
                    setIsLoading(false)
                    toast.error(error.message);
                })
        }


    }

    // Delete Brand Item
    const DeleteBrandItem = (id) => {

        if (confirm("Are You Sure ?")) {
            setIsLoading(true);
            axios.delete(process.env.REACT_APP_BASE_URL + `brand/deleteBrand/${id}`, { headers: authHeader() })
                .then(response => {
                    if (response) {
                        setIsLoading(false);
                        toast.success("Delete Success");
                        setCount(count + 1);
                    }

                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);
                })

        }




    }

    if (isLoading) {
        return <h4>Loading...</h4>
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
            accessor: 'brand_name',
            Header: 'Brand Name',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { brandName } = rowData.row.original;
                return (

                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{brandName}</h5>
                        </div>
                    </Flex>

                );
            }
        },

        {
            accessor: 'brand_type',
            Header: 'Brand Type',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { brandType } = rowData.row.original;
                return (

                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{brandType}</h5>
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
                const { brandId } = rowData.row.original;
                return (
                    <Dropdown className="d-inline mx-2">
                        <Dropdown.Toggle id="dropdown-autoclose-true" className=' bg-none'>
                            ...
                        </Dropdown.Toggle>

                        <Dropdown.Menu>
                            <Dropdown.Item href=""> <Link to={`/master/brand/add/${brandId}`}>
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
                            </Link></Dropdown.Item>
                            <Dropdown.Item href="">
                                <IconButton
                                    onClick={() => DeleteBrandItem(brandId)}
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
                data={brandData}
                sortable
                pagination
                perPage={10}
            >
                <Card className="mb-3">
                    <Card.Header>
                        <CustomersTableHeader title="Product Brand" newUrl="/master/brand/add" isFilter={false} isExport={false} table />
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

export default Banks;
