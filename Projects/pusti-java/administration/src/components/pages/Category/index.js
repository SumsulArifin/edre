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

const Category = () => {
    const [allCategoryData, setAllCategoryData] = useState([]);
    const [allCategoryTypes, setAllCategoryTypes] = useState([]);
    const [categoryItemData, setCategoryItemData] = useState([]);
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
                    process.env.REACT_APP_BASE_URL + `category/getByCategoryId/${id}`,
                    { headers: authHeader() }
                )
                .then(response => {
                    if (response.data) {
                        setCategoryItemData(response.data);
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
        const categoryInfo = {

            name: data.categoryName,
            categoryType: {categoryTypeId:data.categoryTypeId} ,
            description: data.comments


        };

        // Create Category
        if (categoryInfo) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'category/addNewCategory';
            fetch(url, {
                method: 'POST',
                headers: authHeader(),
                body: JSON.stringify(categoryInfo)
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

    // Load All Category Type
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'categoryType/getAllCategoryTypes';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                if (response.data) {
                    setAllCategoryTypes(response?.data);
                    setIsLoading(false);
                }
            })
            .catch(error => {
                setIsLoading(false);
                console.log(error);
            });
    }, []);

    // Load Category Data
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'category/getAllCategories';
        axios
            .get(url, { headers: authHeader() })
            .then(response => {
                const result = []
                let index = 1;
                if (response.data) {
                    response?.data?.forEach((element) => {
                        const outletData = { index, ...element }
                        result.push(outletData);
                        index++
                    });
                    setIsLoading(false);
                }
                setAllCategoryData(result);
            })
            .catch(error => {
                setIsLoading(false);
                console.log(error);
            });
    }, [count]);

    // Update category Info
    const handleCategoryUpdate = (values, actions) => {
        const categoryUpdateInfo = {
            name: values.updateCategoryName,
            categoryTypeId: values.updateCategoryTypeId,
            description: values.updateComments,

        };

        if (categoryUpdateInfo && updateId) {
            setIsLoading(true);
            const url =
                process.env.REACT_APP_BASE_URL + `category/updateCategory/${updateId}`;
            axios
                .put(url, categoryUpdateInfo, { headers: authHeader() })
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

    // Delete Category
     const DeleteCategory = id => {
         if (confirm('Are You Sure ?')) {
             setIsLoading(true);
             axios
                 .delete(
                     process.env.REACT_APP_BASE_URL + `category/deleteCategoryById/${id}`,
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
            accessor: 'categoryTypeId',
            Header: 'Category Type',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { categoryType } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{categoryType?.name}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
      
        {
            accessor: 'categoryName',
            Header: 'Category Name',
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
                                    onClick={() => DeleteCategory(id)}
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
                data={allCategoryData}
                // selection
                sortable
                pagination
                perPage={10}
            >
                <Card className="mb-3">
                    <Card.Header>
                        <CustomersTableHeader
                            title="Products Category"
                            handleShow={handleShow}
                            data={allCategoryData}
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

            <Modal show={show} fullscreen={mediumScreen} onHide={() => setShow(false)}>
                <Modal.Header>
                    <Modal.Title>Create Category</Modal.Title>
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
                                md="12"
                                className="mb-3"
                                controlId="formHookname"
                            >
                                <Form.Label>Select Category Type</Form.Label>
                                <Form.Select
                                    aria-label="Default select example"
                                    type="text"
                                    name="categoryTypeId"
                                    isInvalid={!!errors.categoryTypeId}
                                    {...register('categoryTypeId', {
                                        required: 'Category Type is required'
                                    })}
                                >
                                    <option defaultChecked>Category Type</option>
                                    {allCategoryTypes?.map((item, i) => (
                                        <option key={i} value={item?.categoryTypeId}>
                                            {item?.name}
                                        </option>
                                    ))}
                                </Form.Select>
                                <Form.Control.Feedback type="invalid">
                                    {errors.categoryTypeId && errors.categoryTypeId.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                <Form.Label>Category Name</Form.Label>
                                <Form.Control
                                    aria-label="Default select example"
                                    type="text"
                                    placeholder="Enter Category Name"
                                    name="categoryName"
                                    isInvalid={!!errors.categoryName}
                                    {...register('categoryName', {
                                        required: 'Category Name field is required'
                                    })}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.categoryName && errors.categoryName.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                            <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                <Form.Label>Comments</Form.Label>
                                <Form.Control as="textarea" rows={3}
                                    aria-label="With textarea"
                                    type="text"
                                    placeholder="Enter Comments"
                                    name="comments"
                                    isInvalid={!!errors.comments}
                                    {...register('comments', {
                                        required: 'comments is required'
                                    })}
                                />
                                <Form.Control.Feedback type="invalid">
                                    {errors.comments && errors.comments.message}
                                </Form.Control.Feedback>
                            </Form.Group>
                        </Row>

                        <Button
                            variant="primary"
                            type="submit"
                            disabled={
                                !watch().categoryName ||
                                watch().categoryTypeId == 'Category Type' || !watch().comments
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
                    <Modal.Title>Category Update</Modal.Title>
                    <CloseButton
                        className="btn btn-circle btn-sm transition-base p-0"
                        onClick={() => setShowUpdateModal(false)}
                    />
                </Modal.Header>
                <Modal.Body>
                    <Formik
                        initialValues={{
                            updateCategoryTypeId: categoryItemData?.categoryTypeId,
                            updateCategoryName: categoryItemData?.name,
                            updateComments: categoryItemData.description
                        }}
                        // validationSchema={loginSchema}
                        onSubmit={handleCategoryUpdate}
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
                                    <Form.Group
                                        as={Col}
                                        md="12"
                                        className="mb-3"
                                        controlId="formHookname"
                                    >
                                        <Form.Label>Select Category Type</Form.Label>
                                        <Form.Select
                                            aria-label="Default select example"
                                            type="text"
                                            name="updateCategoryTypeId"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateCategoryTypeId}
                                        >
                                            {allCategoryTypes?.map((item, i) => (
                                                <option key={i} value={item?.categoryId}>
                                                    {item?.name}
                                                </option>
                                            ))}
                                        </Form.Select>

                                    </Form.Group>
                                    <Form.Group
                                        as={Col}
                                        md="12"
                                        className="mb-3"
                                        controlId="email"
                                    >
                                        <Form.Label>Category Name</Form.Label>
                                        <Form.Control
                                            aria-label="Default select example"
                                            type="text"
                                            placeholder="Enter Category Name"
                                            name="updateCategoryName"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateCategoryName}
                                        />


                                    </Form.Group>
                                    <Form.Group as={Col} md="12" className="mb-3" controlId="email">
                                        <Form.Label>Comments</Form.Label>
                                        <Form.Control as="textarea" rows={3}
                                            aria-label="With textarea"
                                            type="text"
                                            placeholder="Enter Comments"
                                            name="updateComments"
                                            required
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            value={values.updateComments}

                                        />

                                    </Form.Group>
                                </Row>
                                <Button variant="primary" type="submit">
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

export default Category;
