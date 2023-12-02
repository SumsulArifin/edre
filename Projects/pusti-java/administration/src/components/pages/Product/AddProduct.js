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
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";

const AddProduct = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [categoryTypes, setCategoryTypes] = useState([]);
    const [categorys, setCategorys] = useState([]);
    const [brands, setBrands] = useState([]);
    const [productInfo, setProductInfo] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();



    // Unit
    const units = [
        {
            label: 'Box',
            value: 'Box'
        },
        {
            label: 'Jar',
            value: 'Jar'
        },
        {
            label: 'Pcs',
            value: 'Pcs'
        },
        {
            label: 'Pouch',
            value: 'Pouch'
        },
    ]


    // New Product 

    const newProduct = [{ label: 'Yes', value: true }, { label: 'No', value: false }];

    // OfferRunning
    const offerRunning = [{ label: 'Yes', value: true }, { label: 'No', value: false }];

    // smsActive
    const smsActive = [{ label: 'Yes', value: true }, { label: 'No', value: false }];

    // inStock
    const inStock = [{ label: 'Yes', value: true }, { label: 'No', value: false }];

    // distribution Gift Available
    const distributionGiftAvailable = [{ label: 'Yes', value: true }, { label: 'No', value: false }];

    // productStatus
    const productStatus = [{ label: 'Active', value: true }, { label: 'In Active', value: false }]

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
            process.env.REACT_APP_BASE_URL + 'categoryType/getAllCategoryTypes',
            process.env.REACT_APP_BASE_URL + 'category/getAllCategories',
            process.env.REACT_APP_BASE_URL + 'brand/getAllBrands',


        ]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                console.log(response)
                setIsLoading(false);

                // categoryTypes
                const modifyCategoryTypes = response[0]?.data?.map(item => {
                    return {
                        id: item.categoryTypeId,
                        label: item.name,
                        value: item.categoryTypeId

                    }
                })
                setCategoryTypes(modifyCategoryTypes);

                // categorys
                const modifyCategory = response[1]?.data?.map(item => {
                    return {
                        id: item.id,
                        label: item.name,
                        value: item.id,
                        categoryTypeId: item?.categoryType?.categoryTypeId,
                        categoryTypeName: item?.categoryType?.name

                    }
                })
                setCategorys(modifyCategory);

                // Brand
                const modifyBrand = response[2]?.data?.map(item => {
                    return {
                        id: item.brandId,
                        label: item.brandName,
                        value: item.brandId

                    }
                })
                setBrands(modifyBrand);


            })
    }, [])

    // load update employee field value
    useEffect(() => {
        setIsLoading(true)
        const urls = [process.env.REACT_APP_BASE_URL + `product/getProductBy/${updateID}`]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false)
                setProductInfo(response[0].data);
            })
            .catch(error => {
                setIsLoading(false)
                console.log(error)

            })
    }, [])




    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }

        setValidated(true);
    };


    console.log('product', productInfo.name)

    const handleProductSubmit = (values, actions) => {


        const productInfo = {
            name: values.productName,
            bengaliName: values.bengaliName,
            erpId: values.erpId,
            skuNumber: values.skuNumber,
            unitId: values.unitId.value,
            distributionPrice: values.distributionPrice,
            tradingPrice: values.tradingPrice,
            weightPerUnit: values.weightPerUnit,
            sellingPackSize: values.sellingPackSize,
            sellingCartoonSize: values.sellingCartoonSize,
            openingDate: formatDateToYYYYMMDD(values.openingDate),
            closingDate: formatDateToYYYYMMDD(values.closingDate),
            category: {

                id: values.categoryId.value
            },
            brand: {
                brandId: values.brandId?.value
            },

            active: values.active.value,
            distributionGiftAvailable: values.distributionGiftAvailable.value,
            inStock: values.inStock.value,
            smsactive: values.smsactive.value,
            offerRunning: values.offerRunning.value,
            newProduct: values.newProduct.value

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'product/addNewProduct';
            axios.post(url, productInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    navigate("/master/product")
                    toast.success(response?.data.message);

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)
                    console.log(error)
                })



        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `product/updateProduct/${updateID}`;
            axios.put(updateUrl, productInfo, { headers: authHeader() })
                .then(response => {
                    console.log('check'.response)
                    setIsLoading(false);
                    if (response.data) {
                        navigate("/master/product")
                        toast.success(response?.data.message || 'Product Update Success');
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)
                    console.log(error)
                })
           
        }

    }



    isLoading && <LoadingIcon />


    const productSchema = Yup.object().shape({
        productName: Yup.string().required("Name is required"),
        bengaliName: Yup.string().required("Bengali Name is required"),
        erpId: Yup.string().required("ERP ID is required"),
        unitId: Yup.object().required("Unit is required"),
        distributionPrice: Yup.number().required("Distribution Price is required"),
        tradingPrice: Yup.number().required("Distribution Price is required"),
        weightPerUnit: Yup.number().required("WeightPerUnit is required"),
        sellingPackSize: Yup.number().required("Selling PackSize is required"),
        sellingCartoonSize: Yup.number().required("Selling CartoonSize is required"),
        openingDate: Yup.date().required("Opening Date is required"),
        closingDate: Yup.date().required("Closing Date is required"),
        categoryId: Yup.object().required("Category is required"),
        brandId: Yup.object().required("Brand is required"),
        active: Yup.object().required("Product Active is required"),
        distributionGiftAvailable: Yup.object().required("Distribution GiftAvailable is required"),
        inStock: Yup.object().required("InStock is required"),
        smsactive: Yup.object().required("Sms Active is required"),
        offerRunning: Yup.object().required("Offer Running is required"),
        newProduct: Yup.object().required("New Product is required"),

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Product" : "Add New Product"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{

                            productName: productInfo?.name || "",
                            bengaliName: productInfo.bengaliName || "",
                            erpId: productInfo.erpId || "",
                            skuNumber: productInfo.skuNumber || "",
                            unitId: units.find(unit => unit.value === productInfo.unitId) || null,
                            distributionPrice: productInfo.distributionPrice || 0,
                            tradingPrice: productInfo.tradingPrice || 0,
                            weightPerUnit: productInfo.weightPerUnit || 0,
                            sellingPackSize: productInfo.sellingPackSize || 0,
                            sellingCartoonSize: productInfo.sellingPackSize || 0,
                            openingDate: productInfo.openingDate || null,
                            closingDate: productInfo.closingDate || null,
                            categoryType: categoryTypes.find(type => type.value == productInfo.category?.categoryType?.categoryTypeId) || null,
                            categoryId: categorys.find(category => category?.id === productInfo.category?.id) || null,
                            brandId: brands.find(brand => brand.value == productInfo.brand?.brandId) || null,
                            active: productStatus.find(product => product.value === productInfo.active) || null,
                            distributionGiftAvailable: inStock.find(product => product.value === productInfo.distributionGiftAvailable) || null,
                            inStock: inStock.find(product => product.value === productInfo.inStock) || null,
                            smsactive: smsActive.find(product => product.value === productInfo.smsactive) || null,
                            offerRunning: offerRunning.find(product => product.value === productInfo.offerRunning) || null,
                            newProduct: newProduct.find(product => product.value === productInfo.newProduct) || null

                        }}
                        validationSchema={productSchema}
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
                            console.log("errors", errors)
                            return (
                                <Form onSubmit={handleSubmit}>

                                    <Row className="mb-3">

                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Category Type</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={categoryTypes}
                                                placeholder="Select Category Type"
                                                classNamePrefix="react-select"
                                                name="categoryType"
                                                value={values.categoryType}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "categoryType",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.categoryType && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.categoryType}
                                                    </div>
                                                )}
                                        </Form.Group>

                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Category</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={categorys.filter(category => category.categoryTypeId === values.categoryType?.value)}
                                                placeholder="Select Category"
                                                classNamePrefix="react-select"
                                                name="categoryId"
                                                value={values.categoryId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "categoryId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.categoryId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.categoryId}
                                                    </div>
                                                )}
                                        </Form.Group>


                                    </Row>
                                    <Row className="mb-3">

                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Brand</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={brands}
                                                placeholder="Select Brand"
                                                classNamePrefix="react-select"
                                                name="brandId"
                                                value={values.brandId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "brandId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.brandId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.brandId}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Product Name</Form.Label>
                                            <Form.Control
                                                name="productName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter ProductName"
                                                required
                                                value={values.productName}
                                            />
                                            {touched.productName && errors.productName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.productName}
                                                </div>
                                            )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Product Name in Bangla</Form.Label>
                                            <Form.Control
                                                name="bengaliName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter BengaliName"
                                                required
                                                value={values.bengaliName}
                                            />
                                            {touched.bengaliName && errors.bengaliName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.bengaliName}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Distribution Price</Form.Label>
                                            <Form.Control
                                                name="distributionPrice"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="number"
                                                placeholder="Enter Distribution Price"
                                                required
                                                value={values.distributionPrice}
                                            />
                                            {touched.distributionPrice && errors.distributionPrice && (
                                                <div style={{ color: "red" }}>
                                                    {errors.distributionPrice}
                                                </div>
                                            )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Trade Price</Form.Label>
                                            <Form.Control
                                                name="tradingPrice"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="number"
                                                placeholder="Enter Trade Price"
                                                required
                                                value={values.tradingPrice}
                                            />
                                            {touched.tradingPrice && errors.tradingPrice && (
                                                <div style={{ color: "red" }}>
                                                    {errors.tradingPrice}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>SKU Number</Form.Label>
                                            <Form.Control
                                                name="skuNumber"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter SKU Number"
                                                required
                                                value={values.skuNumber}
                                            />
                                            {touched.skuNumber && errors.skuNumber && (
                                                <div style={{ color: "red" }}>
                                                    {errors.skuNumber}
                                                </div>
                                            )}
                                        </Form.Group>


                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>ERP ID</Form.Label>
                                            <Form.Control
                                                name="erpId"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter ERP ID"
                                                required
                                                value={values.erpId}
                                            />
                                            {touched.erpId && errors.erpId && (
                                                <div style={{ color: "red" }}>
                                                    {errors.erpId}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Unit</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={units}
                                                placeholder="Select Unit"
                                                classNamePrefix="react-select"
                                                name="unitId"
                                                value={values.unitId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "unitId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.unitId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.unitId}
                                                    </div>
                                                )}
                                        </Form.Group>



                                    </Row>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Weight/Pcs (Gram)</Form.Label>
                                            <Form.Control
                                                name="weightPerUnit"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="number"
                                                placeholder="Enter Weight/Pcs"
                                                required
                                                value={values.weightPerUnit}
                                            />
                                            {touched.weightPerUnit && errors.weightPerUnit && (
                                                <div style={{ color: "red" }}>
                                                    {errors.weightPerUnit}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Pcs/Sales Pack</Form.Label>
                                            <Form.Control
                                                name="sellingPackSize"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="number"
                                                placeholder="Enter Pcs/Sales Pack"
                                                required
                                                value={values.sellingPackSize}
                                            />
                                            {touched.sellingPackSize && errors.sellingPackSize && (
                                                <div style={{ color: "red" }}>
                                                    {errors.sellingPackSize}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Pcs/CTN</Form.Label>
                                            <Form.Control
                                                name="sellingCartoonSize"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="number"
                                                placeholder="Enter Selling Cartoon Size"
                                                required
                                                value={values.sellingCartoonSize}
                                            />
                                            {touched.sellingCartoonSize && errors.sellingCartoonSize && (
                                                <div style={{ color: "red" }}>
                                                    {errors.sellingCartoonSize}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Opening Date</Form.Label>
                                            <Form.Control
                                                name="openingDate"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="date"
                                                placeholder="Enter Opening Date"
                                                required
                                                value={values.openingDate}
                                            />
                                            {touched.openingDate && errors.openingDate && (
                                                <div style={{ color: "red" }}>
                                                    {errors.openingDate}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Closing Date</Form.Label>
                                            <Form.Control
                                                name="closingDate"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="date"
                                                placeholder="Enter Closing Date"
                                                required
                                                value={values.closingDate}
                                            />
                                            {touched.closingDate && errors.closingDate && (
                                                <div style={{ color: "red" }}>
                                                    {errors.closingDate}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Product Status</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={productStatus}
                                                placeholder="Select Product Status"
                                                classNamePrefix="react-select"
                                                name="active"
                                                value={values.active}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "active",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.active && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.active}
                                                    </div>
                                                )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Distribution Gift Available</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={distributionGiftAvailable}
                                                placeholder="Distribution Gift Available"
                                                classNamePrefix="react-select"
                                                name="distributionGiftAvailable"
                                                value={values.distributionGiftAvailable}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "distributionGiftAvailable",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.distributionGiftAvailable && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.distributionGiftAvailable}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>InStock</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={inStock}
                                                placeholder="In Stock"
                                                classNamePrefix="react-select"
                                                name="inStock"
                                                value={values.inStock}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "inStock",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.inStock && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.inStock}
                                                    </div>
                                                )}
                                        </Form.Group>

                                    </Row>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Sms Active</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={smsActive}
                                                placeholder="Select Sms Status"
                                                classNamePrefix="react-select"
                                                name="smsactive"
                                                value={values.smsactive}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "smsactive",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.smsactive && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.smsactive}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Offer Running</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={offerRunning}
                                                placeholder="Offer Running"
                                                classNamePrefix="react-select"
                                                name="offerRunning"
                                                value={values.offerRunning}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "offerRunning",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.offerRunning && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.offerRunning}
                                                    </div>
                                                )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>New Product</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={newProduct}
                                                placeholder="Is New Product"
                                                classNamePrefix="react-select"
                                                name="newProduct"
                                                value={values.newProduct}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "newProduct",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.newProduct && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.newProduct}
                                                    </div>
                                                )}
                                        </Form.Group>
                                    </Row>

                                    <IconButton
                                        variant="primary"
                                        className="ms-auto px-5"
                                        type="submit"
                                        iconAlign="right"
                                        transform="down-1 shrink-4"

                                    >
                                        Submit
                                    </IconButton>

                                </Form>
                            );
                        }}
                    </Formik>
                </FalconComponentCard.Body>
            </FalconComponentCard>
        </>
    );
};

export default AddProduct;


