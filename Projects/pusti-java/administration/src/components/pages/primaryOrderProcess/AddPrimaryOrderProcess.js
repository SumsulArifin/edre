import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import axios from "axios";
import { authHeader } from "utils";
import { Button, Card } from 'react-bootstrap';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Flex from "components/common/Flex";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";

const AddPrimaryOrderProcess = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [committees, setCommittees] = useState([]);
    const [distributor, setDistributors] = useState([]);
    const [allDepot, setAllDepot] = useState([]);
    const [productsCategory, setProductCategory] = useState([]);
    const [products, setProducts] = useState([]);
    const [regions, setRegions] = useState([]);
    const [zones, setZones] = useState([]);
    const [routes, setRoutes] = useState([]);
    const [districts, setDistricts] = useState([{ id: 1, label: "Bagerhat", value: 1 }]);
    const [upazilas, setUpazilas] = useState([{ id: 1, label: "Sadar Upazila", value: 1 }, { id: 1, label: "Sarankhola", value: 2 }]);
    const [banks, setBanks] = useState([]);
    const [educationList, setEducationList] = useState([]);
    const [primaryOrderInfo, setPrimaryOrderInfo] = useState({});
    const [grossAmount, setGrossAmount] = useState(0);
    const [addProduct, setAddProduct] = useState([]);
    const [step, setStep] = useState(1);
    const { updateID } = useParams();
    const navigate = useNavigate();



    // Employee Types
    const approvalStatusTypes = [
        {
            label: 'IN PROGRESS',
            value: 'IN_PROGRESS',
        }
    ]
    // Approval Types
    const approvalTypes = [
        {
            label: 'PRIMARY APPROVAL',
            value: 'PRIMARY_APPROVAL',
        }
    ]


    // Degree

    const education_degrees = [
        { "id": 1, "label": "Primary School Certificate (PSC)", "value": "Primary School Certificate (PSC)" },
        { "id": 2, "label": "Junior School Certificate (JSC)", "value": "Junior School Certificate (JSC)" },
        { "id": 3, "label": "Secondary School Certificate (SSC)", "value": "Secondary School Certificate (SSC)" },
        { "id": 4, "label": "Higher Secondary Certificate (HSC)", "value": "Higher Secondary Certificate (HSC)" },
        { "id": 5, "label": "Bachelor of Arts (BA)", "value": "Bachelor of Arts (BA)" },
        { "id": 6, "label": "Bachelor of Science (BSc)", "value": "Bachelor of Science (BSc)" },
        { "id": 7, "label": "Bachelor of Commerce (BCom)", "value": "Bachelor of Commerce (BCom)" },
        { "id": 8, "label": "Bachelor of Business Administration (BBA)", "value": "Bachelor of Business Administration (BBA)" },
        { "id": 9, "label": "Bachelor of Laws (LLB)", "value": "Bachelor of Laws (LLB)" },
        { "id": 10, "label": "Bachelor of Medicine, Bachelor of Surgery (MBBS)", "value": "Bachelor of Medicine, Bachelor of Surgery (MBBS)" },
        { "id": 11, "label": "Bachelor of Dental Surgery (BDS)", "value": "Bachelor of Dental Surgery (BDS)" },
        { "id": 12, "label": "Bachelor of Engineering (BEng)", "value": "Bachelor of Engineering (BEng)" },
        { "id": 13, "label": "Bachelor of Pharmacy (BPharm)", "value": "Bachelor of Pharmacy (BPharm)" },
        { "id": 14, "label": "Bachelor of Fine Arts (BFA)", "value": "Bachelor of Fine Arts (BFA)" },
        { "id": 15, "label": "Bachelor of Social Science (BSS)", "value": "Bachelor of Social Science (BSS)" },
        { "id": 16, "label": "Bachelor of Education (BEd)", "value": "Bachelor of Education (BEd)" },
        { "id": 17, "label": "Bachelor of Computer Science and Engineering (BSc in CSE)", "value": "Bachelor of Computer Science and Engineering (BSc in CSE)" },
        { "id": 18, "label": "Bachelor of Architecture (BArch)", "value": "Bachelor of Architecture (BArch)" },
        { "id": 19, "label": "Master of Arts (MA)", "value": "Bachelor of Architecture (BArch)" },
        { "id": 20, "label": "Master of Science (MSc)", "value": "Master of Science (MSc)" },
        { "id": 21, "label": "Master of Commerce (MCom)", "value": "Master of Commerce (MCom)" },
        { "id": 22, "label": "Master of Business Administration (MBA)", "value": "Master of Business Administration (MBA)" },
        { "id": 23, "label": "Master of Laws (LLM)", "value": "Master of Laws (LLM)" },
        { "id": 24, "label": "Master of Medicine, Master of Surgery (MMBS)", "value": "Master of Medicine, Master of Surgery (MMBS)" },
        { "id": 25, "label": "Master of Dental Surgery (MDS)", "value": "Master of Dental Surgery (MDS)" },
        { "id": 26, "label": "Master of Engineering (MEng)", "value": "Master of Engineering (MEng)" },
        { "id": 27, "label": "Master of Pharmacy (MPharm)", "value": "Master of Pharmacy (MPharm)" },
        { "id": 28, "label": "Master of Fine Arts (MFA)", "value": "Master of Fine Arts (MFA)" },
        { "id": 29, "label": "Master of Social Science (MSS)", "value": "Master of Social Science (MSS)" },
        { "id": 30, "label": "Master of Education (MEd)", "value": "Master of Education (MEd)" },
        { "id": 31, "label": "Master of Computer Science and Engineering (MSc in CSE)", "value": "Master of Computer Science and Engineering (MSc in CSE)" },
        { "id": 32, "label": "Master of Architecture (MArch)", "value": "Master of Architecture (MArch)" },
        { "id": 33, "label": "Doctor of Philosophy (Ph.D.)", "value": "Doctor of Philosophy (Ph.D.)" },
    ]

    // 
    const UOM_List = [
        {
            label: ' PACKET',
            value: ' PACKET',
        },
        {
            label: ' BAG',
            value: ' BAG',
        }
    ]


    // 
    const DeliveryApprovals = [
        {
            label: ' Yes',
            value: 'true',
        },
        {
            label: ' No',
            value: ' false',
        }
    ]


    //  Date Format
    function formatDateToYYYYMMDD(isoDateString) {
        const date = new Date(isoDateString);

        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0"); // Month is 0-based
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
    }

    // Load create employee field value
    useEffect(() => {
        setIsLoading(true);
        const urls = [
            // process.env.REACT_APP_BASE_URL + 'approvalCommittee/getAll',
            process.env.REACT_APP_BASE_URL + 'distributor/getAllDistributors',
            process.env.REACT_APP_BASE_URL + 'depot/getAllDepots',
            process.env.REACT_APP_BASE_URL + 'category/getAllCategories',
            process.env.REACT_APP_BASE_URL + 'product/getAllProducts',

        ]


        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                console.log('response', response)
                setIsLoading(false);

                // // Committee
                // const modifyCommittee = response[0]?.data?.map(item => {
                //     return {
                //         id: item.committeeId,
                //         label: item.name,
                //         value: item.committeeId

                //     }
                // })
                // setCommittees(modifyCommittee);
                // console.log("commiteee", committees)


                // Distributors
                const modifyDistributors = response[0]?.data?.map(item => {
                    return {
                        id: item.distributorId,
                        label: item.name,
                        value: item.distributorId

                    }
                })
                setDistributors(modifyDistributors);

                // Depot
                const modifyDepots = response[1]?.data?.map(item => {
                    return {
                        id: item.depotId,
                        label: item.name,
                        value: item.depotId

                    }
                })
                setAllDepot(modifyDepots);

                // Product Category
                const modifyProductCategory = response[2]?.data?.map(item => {
                    return {
                        id: item.id,
                        label: item.name,
                        value: item.id

                    }
                })
                setProductCategory(modifyProductCategory);

                // console.log("pro1",response[4]?.data.response)
                // Products
                const modifyProducts = response[3]?.data?.response?.map(item => {
                    return {
                        id: item.productId,
                        name: item.name,
                        label: item.name,
                        value: item.productId,
                        category: item.category.id,
                        name: item.name,
                        skuNumber: item.skuNumber,
                        unitId: item.unitId,
                        distributionPrice: item.distributionPrice


                    }
                })
                setProducts(modifyProducts);

            })
    }, [])



    // load update employee field value
    useEffect(() => {
        setIsLoading(true)
        const urls = [process.env.REACT_APP_BASE_URL + `primaryOrderApprovalsController/getById/${updateID}`]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false)
                setPrimaryOrderInfo(response[0].data);

                console.log(response)

            })
            .catch(error => {
                setIsLoading(false)
                console.log(error)

            })
    }, [])



    // Remove Education List Item
    const removeProduct = (id) => {
        const confirmation = window.confirm("Are Your Sure ?");
        if (confirmation) {
            const filterProduct = addProduct.filter(productItem => productItem.id !== id);
            setAddProduct(filterProduct);
        }
    }


    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }

        setValidated(true);
    };


    const handleProductSubmit = (values, actions) => {

        const primaryOrderInfo = {
            approvalName: values.approvalName,
            offerNote: values.offerNote,
            approvalStatus: values.approvalStatus?.value,
            approvalType: values.approvalType?.value,
            committeeId: values.committeeId?.value,
            distributor: {
                distributorId: values.distributor?.value
            }
        }

        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'primaryOrderApprovalsController/add';
            axios.post(url, primaryOrderInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    // navigate("/master/employee")
                    toast.success(response?.data.message);

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)
                    console.log(error)
                })

            console.log("check1")

        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `employee/updateEmployee/${updateID}`;
            axios.put(updateUrl, primaryOrderInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.data) {
                        navigate("/master/employee")
                        toast.success(response?.data.message);
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)
                    console.log(error)
                })
            console.log("check2")
        }

    }


    const handleNext = () => {
        setStep(step + 1);
    };

    const handlePrev = () => {
        setStep(step - 1);
    };


    isLoading && <LoadingIcon />


    const primaryOrderSchema = Yup.object().shape({

        title: Yup.string().required("Title is required"),
        committeeId: Yup.object().required("Committee is required"),
        approvalStatus: Yup.object().required("Approval Status is required"),
        userId: Yup.number().required("User is required"),
        depotId: Yup.object().required("Depot is required"),
        distributorId: Yup.object().required("Distributor is required"),



    });


    return (
        <>
            <PageHeader
                title="Add Primary Order Process"
                className="mb-3"
            ></PageHeader>

            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{

                            title: primaryOrderInfo.title || "",
                            committeeId: null,
                            distributorId: null,
                            depotId: null,
                            approvalStatus: primaryOrderInfo.approvalStatus || null,
                            approvalType: primaryOrderInfo.approvalType || null,
                            productCategory: null,
                            productId: null,
                            productSkuNumber: null,
                            storageUnit: null,
                            quantity: 0,
                            distributionPrice: 0,
                            grossAmount: 0,
                            netAmount: 0


                        }}
                        validationSchema={primaryOrderSchema}
                        onSubmit={handleProductSubmit}
                        enableReinitialize={true}
                    >
                        {({
                            errors,
                            touched,
                            handleSubmit,
                            handleChange,
                            handleBlur,
                            values,
                            setFieldValue,
                            resetForm
                        }) => {

                            { console.log("values", values) }


                            setGrossAmount(values.quantity * values.productId?.distributionPrice);

                            return (
                                <Form onSubmit={handleSubmit}>

                                    {
                                        step === 1 && <>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Title</Form.Label>
                                                    <Form.Control
                                                        name="title"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Title"
                                                        required
                                                        value={values.title}
                                                    />
                                                    {touched.title && errors.title && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.title}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Committee</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={committees}
                                                        placeholder="Select Committee"
                                                        classNamePrefix="react-select"
                                                        name="committeeId"
                                                        value={values.committeeId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "committeeId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.committeeId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.committeeId}
                                                            </div>
                                                        )}
                                                </Form.Group>

                                            </Row>

                                            <Row className="mb-3">

                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Distributor</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={distributor}
                                                        placeholder="Select Distributor"
                                                        classNamePrefix="react-select"
                                                        name="distributorId"
                                                        value={values.distributorId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "distributorId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.distributorId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.distributorId}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Depot</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={allDepot}
                                                        placeholder="Select Depot"
                                                        classNamePrefix="react-select"
                                                        name="depotId"
                                                        value={values.depotId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "depotId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.depotId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.depotId}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                            </Row>

                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Approval Status</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={approvalStatusTypes}
                                                        placeholder="Select Approval Status"
                                                        classNamePrefix="react-select"
                                                        name="approvalStatus"
                                                        value={values.approvalStatus}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "approvalStatus",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.approvalStatus && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.approvalStatus}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>User</Form.Label>
                                                    <Form.Control
                                                        name="userId"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter User ID"
                                                        required
                                                        value={values.userId}
                                                    />
                                                    {touched.userId && errors.userId && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.userId}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Delivery Approval</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={DeliveryApprovals}
                                                        placeholder="Select Delivery Approval"
                                                        classNamePrefix="react-select"
                                                        name="DeliveryApproval"
                                                        value={values.DeliveryApproval}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "DeliveryApproval",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.DeliveryApproval && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.DeliveryApproval}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                            </Row>
                                            <Button className="me-3 mt-3" onClick={handleNext}>Next</Button>
                                        </>
                                    }


                                    {
                                        step === 2 && <>
                                            <Row className="mb-3 mt-4">
                                                <Card >
                                                    <Card.Header as="h6" className="bg-light">
                                                        Product details :
                                                    </Card.Header>
                                                    <div class="table-responsive scrollbar">
                                                        <table class="table table-sm table-striped h6 my-1 overflow-hidden">
                                                            {/* <thead >
                                                        <tr>
                                                            <th class="  pe-1 align-middle white-space-nowrap" data-sort="name">Product</th>
                                                            <th class=" text-center pe-1 align-middle white-space-nowrap" >Quantity</th>
                                                            <th class="  pe-1 align-middle 
                                                            text-center white-space-nowrap" data-sort="email">Distribution Price</th>
                                                            <th class="align-middle white-space-nowrap text-center " >Net Payment</th>
                                                            <th class=" align-middle white-space-nowrap text-center " >Remove</th>
                                                        </tr>
                                                    </thead> */}
                                                            <tbody class="list" id="table-purchase-body">


                                                                {addProduct?.map((product, index) =>

                                                                (
                                                                    <tr class="btn-reveal-trigger">
                                                                        <th class="align-middle   white-space-nowrap name">{product.productName}</th>
                                                                        <td class="align-middle white-space-nowrap  text-center email">{product.quantity}</td>
                                                                        <td class="align-middle   text-center white-space-nowrap email">{product.distributionPrice}</td>
                                                                        <td class="align-middle text-center fs-0 white-space-nowrap payment">{product.netAmount}</td>
                                                                        <td class="align-middle text-center fs-0 white-space-nowrap payment"><Button
                                                                            variant="link"
                                                                            to="#!"
                                                                            type="button"
                                                                            className="text-danger"
                                                                            size="sm"
                                                                            onClick={() => removeProduct(product.id)}
                                                                        >
                                                                            <FontAwesomeIcon className="fs--1" icon="trash-alt" />
                                                                        </Button></td>

                                                                    </tr>
                                                                ))}
                                                            </tbody>
                                                        </table>

                                                    </div>
                                                    <Card.Body>

                                                        <Row className="mb-2">

                                                            <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                                <Form.Label>Product Category</Form.Label>
                                                                <Select
                                                                    closeMenuOnSelect={true}
                                                                    options={productsCategory}

                                                                    placeholder="Select Category"
                                                                    classNamePrefix="react-select"
                                                                    name="productCategory"

                                                                    value={values.productCategory}
                                                                    onChange={(selectedOption) => {
                                                                        setFieldValue(
                                                                            "productCategory",
                                                                            selectedOption
                                                                        );
                                                                    }}
                                                                    onBlur={handleBlur}
                                                                />

                                                                {
                                                                    errors.productCategory && (
                                                                        <div style={{ color: "red" }}>
                                                                            {errors.productCategory}
                                                                        </div>
                                                                    )}
                                                            </Form.Group>
                                                            <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                                <Form.Label>Products</Form.Label>
                                                                <Select
                                                                    closeMenuOnSelect={true}
                                                                    options={products?.filter(product => product?.category == values?.productCategory?.id)}
                                                                    placeholder="Select Products"
                                                                    classNamePrefix="react-select"
                                                                    name="productId"
                                                                    value={values.productId}
                                                                    onChange={(selectedOption) => {
                                                                        setFieldValue(
                                                                            "productId",
                                                                            selectedOption
                                                                        );
                                                                    }}
                                                                    onBlur={handleBlur}
                                                                />

                                                                {
                                                                    errors.productId && (
                                                                        <div style={{ color: "red" }}>
                                                                            {errors.productId}
                                                                        </div>
                                                                    )}
                                                            </Form.Group>

                                                        </Row>
                                                        {/* <Row className="mb-2">

                                                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                        <Form.Label>Product SKUS</Form.Label>
                                                        <Select
                                                            closeMenuOnSelect={true}
                                                            options={education_degrees}
                                                            placeholder="Select Product SKUS"
                                                            classNamePrefix="react-select"
                                                            name="productSkuNumber"
                                                            value={values.productSkuNumber}
                                                            onChange={(selectedOption) => {
                                                                setFieldValue(
                                                                    "productSkuNumber",
                                                                    selectedOption
                                                                );
                                                            }}
                                                            onBlur={handleBlur}
                                                        />

                                                        {
                                                            errors.productSkuNumber && (
                                                                <div style={{ color: "red" }}>
                                                                    {errors.productSkuNumber}
                                                                </div>
                                                            )}
                                                    </Form.Group>
                                                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                        <Form.Label>UOM</Form.Label>
                                                        <Select
                                                            closeMenuOnSelect={true}
                                                            options={UOM_List}
                                                            placeholder="Select UOM"
                                                            classNamePrefix="react-select"
                                                            name="storageUnit"
                                                            value={values.storageUnit}
                                                            onChange={(selectedOption) => {
                                                                setFieldValue(
                                                                    "storageUnit",
                                                                    selectedOption
                                                                );
                                                            }}
                                                            onBlur={handleBlur}
                                                        />

                                                        {
                                                            errors.storageUnit && (
                                                                <div style={{ color: "red" }}>
                                                                    {errors.storageUnit}
                                                                </div>
                                                            )}
                                                    </Form.Group>

                                                </Row> */}
                                                        <Row className="mb-2">
                                                            <Form.Group as={Col} md="6" controlId="exampleState">
                                                                <Form.Label>Quantity</Form.Label>
                                                                <Form.Control
                                                                    name="quantity"
                                                                    onChange={handleChange}
                                                                    onBlur={handleBlur}
                                                                    type="number"
                                                                    placeholder="Enter Quantity"

                                                                    value={values.quantity}
                                                                />
                                                                {touched.quantity && errors.quantity && (
                                                                    <div style={{ color: "red" }}>
                                                                        {errors.quantity}
                                                                    </div>
                                                                )}
                                                            </Form.Group>
                                                            {/* <Form.Group as={Col} md="6" controlId="exampleState">
                                                        <Form.Label>Distribution price</Form.Label>
                                                        <Form.Control
                                                            name="distributionPrice"
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            type="number"
                                                            placeholder="Enter Distribution price"

                                                            value={values.distributionPrice}
                                                        />
                                                        {touched.distributionPrice && errors.distributionPrice && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.distributionPrice}
                                                            </div>
                                                        )}
                                                    </Form.Group> */}

                                                        </Row >
                                                        <Row className="mb-2">
                                                            <Form.Group as={Col} md="6" controlId="exampleState">
                                                                <Form.Label>Gross amount</Form.Label>
                                                                <Form.Control
                                                                    name="grossAmount"
                                                                    onChange={handleChange}
                                                                    onBlur={handleBlur}
                                                                    type="number"
                                                                    disabled
                                                                    placeholder="Enter Gross amount"

                                                                    value={grossAmount}
                                                                />
                                                                {touched.grossAmount && errors.grossAmount && (
                                                                    <div style={{ color: "red" }}>
                                                                        {errors.grossAmount}
                                                                    </div>
                                                                )}
                                                            </Form.Group>
                                                            <Form.Group as={Col} md="6" controlId="exampleState">
                                                                <Form.Label>Net payment</Form.Label>
                                                                <Form.Control
                                                                    name="netAmount"
                                                                    onChange={handleChange}
                                                                    onBlur={handleBlur}
                                                                    type="number"
                                                                    placeholder="Net payment"
                                                                    disabled
                                                                    value={grossAmount}
                                                                />
                                                                {touched.netAmount && errors.netAmount && (
                                                                    <div style={{ color: "red" }}>
                                                                        {errors.netAmount}
                                                                    </div>
                                                                )}
                                                            </Form.Group>


                                                        </Row >

                                                        <Row className="flex-between-center mt-3">
                                                            <Col md>
                                                                {/* <h5 className="mb-2 mb-md-0">You're almost done</h5> */}
                                                            </Col>
                                                            <Col xs="auto">
                                                                <Button
                                                                    variant="link"
                                                                    className="text-secondary fw-medium p-0 me-3"
                                                                    type="button"
                                                                    onClick={() => setAddProduct([])}
                                                                >
                                                                    Discard
                                                                </Button>
                                                                <Button variant="primary" type="submit" disabled={!values.productId || !values.quantity || !grossAmount} onClick={() => setAddProduct([...addProduct, {
                                                                    id: values.productId.id,
                                                                    productName: values.productId.name, quantity: values.quantity, productSkuNumber: values.productId.skuNumber, storageUnit: values.productId.unitId, distributionPrice: values.productId.distributionPrice,
                                                                    grossAmount: grossAmount, netAmount: grossAmount
                                                                }])}>
                                                                    Add product
                                                                </Button>
                                                            </Col>
                                                        </Row>




                                                    </Card.Body>
                                                </Card>
                                            </Row>
                                            <Button className="me-3" onClick={handlePrev}>Previous</Button>
                                            <Button className="me-3" onClick={handleNext}>Next</Button>
                                        </>
                                    }
                                    {
                                        step === 3 && <>
                                            <Row className="mb-3 mt-4">
                                                <Card >
                                                    <Card.Header as="h6" className="bg-light">
                                                        Payment details :
                                                    </Card.Header>
                                                    <Card.Body>
                                                        {educationList?.map((education, index) => (
                                                            <Row key={index} className="gx-2 flex-between-center mb-3">
                                                                <Col sm={6}>
                                                                    <h6 className="mb-0 text-600">{education.degreeName}</h6>
                                                                </Col>
                                                                <Col sm={3}>
                                                                    <h6 className="mb-0 text-600">{education.passingYear}</h6>
                                                                </Col>
                                                                <Col sm={3}>
                                                                    <Flex justifyContent="between" alignItems="center">
                                                                        <h6 className="mb-0 text-700">{education.result}</h6>
                                                                        <Button
                                                                            variant="link"
                                                                            to="#!"
                                                                            type="button"
                                                                            className="text-danger"
                                                                            size="sm"
                                                                            onClick={() => removeProduct(education.id)}
                                                                        >
                                                                            <FontAwesomeIcon className="fs--1" icon="trash-alt" />
                                                                        </Button>
                                                                    </Flex>
                                                                </Col>
                                                            </Row>
                                                        ))}


                                                        <Row className="mb-2">
                                                            <Form.Group as={Col} md="6" controlId="exampleState">
                                                                <Form.Label>Quantity</Form.Label>
                                                                <Form.Control
                                                                    name="result"
                                                                    onChange={handleChange}
                                                                    onBlur={handleBlur}
                                                                    type="number"
                                                                    placeholder="Enter Quantity"

                                                                    value={values.result}
                                                                />
                                                                {touched.result && errors.result && (
                                                                    <div style={{ color: "red" }}>
                                                                        {errors.result}
                                                                    </div>
                                                                )}
                                                            </Form.Group>
                                                            <Form.Group as={Col} md="6" controlId="exampleState">
                                                                <Form.Label>Distribution price</Form.Label>
                                                                <Form.Control
                                                                    name="result"
                                                                    onChange={handleChange}
                                                                    onBlur={handleBlur}
                                                                    type="number"
                                                                    placeholder="Enter Distribution price"

                                                                    value={values.result}
                                                                />
                                                                {touched.result && errors.result && (
                                                                    <div style={{ color: "red" }}>
                                                                        {errors.result}
                                                                    </div>
                                                                )}
                                                            </Form.Group>




                                                        </Row >

                                                        <Row className="flex-between-center">

                                                            <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                                <Form.Label>Select Bank</Form.Label>
                                                                <Select
                                                                    closeMenuOnSelect={true}
                                                                    options={committees}
                                                                    placeholder="Select Bank"
                                                                    classNamePrefix="react-select"
                                                                    name="bankId"
                                                                    value={values.bankId}
                                                                    onChange={(selectedOption) => {
                                                                        setFieldValue(
                                                                            "bankId",
                                                                            selectedOption
                                                                        );
                                                                    }}
                                                                    onBlur={handleBlur}
                                                                />

                                                                {
                                                                    errors.bankId && (
                                                                        <div style={{ color: "red" }}>
                                                                            {errors.bankId}
                                                                        </div>
                                                                    )}
                                                            </Form.Group>

                                                            <Col xs="auto">
                                                                <Button
                                                                    variant="link"
                                                                    className="text-secondary fw-medium p-0 me-3"
                                                                    type="button"
                                                                >
                                                                    Cancel
                                                                </Button>
                                                                <Button variant="primary" type="submit">
                                                                    Add Payment
                                                                </Button>
                                                            </Col>
                                                        </Row>

                                                    </Card.Body>
                                                </Card>
                                            </Row>




                                            <Button className="me-3" onClick={handlePrev}>Previous</Button>

                                            <IconButton
                                                variant="primary"
                                                className="ms-auto px-5"
                                                type="submit"
                                                iconAlign="right"
                                                transform="down-1 shrink-4"

                                            >
                                                Submit
                                            </IconButton>
                                        </>
                                    }

                                </Form>
                            );
                        }}
                    </Formik>
                </FalconComponentCard.Body>

            </FalconComponentCard>
        </>
    );
};

export default AddPrimaryOrderProcess;


