import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import * as Yup from "yup";
import Select from "react-select";
import LoadingIcon from "helpers/LoadingIcon";

const BrandForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [brandByID, setBrandByID] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Brand Get By ID
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `brand/getBrandById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setBrandByID(response.data);
                    }
                    setIsLoading(false)
                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);


                })
        }
    }, [])


    const brandList = [
        {
            id: 1,
            label: " Pusti Happny Time",
            value: "Pusti Happny Time"
        },
        {
            id: 2,
            label: " PCL",
            value: "PCL"
        },
        {
            id: 3,
            label: " Prime Pusti",
            value: "Prime Pusti"
        },

    ]

    const handleFormSubmit = (values, actions) => {

        const brandInfo = {
            brandName: values.brandName,
            brandType: values.brandType.value

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'brand/addNewBrand';
            axios.post(url, brandInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/brand")
                        toast.success("Brand Added Success");
                    }
                    setIsLoading(false);

                })
                .catch(error => {
                    toast.error(error.message)
                    setIsLoading(false);

                })


        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `brand/updateBrand/${updateID}`;
            axios.put(updateUrl, brandInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        navigate("/master/brand");
                        toast.success("Brand Update Success");
                    }
                    setIsLoading(false);

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)

                })

        }

    }


    isLoading && <LoadingIcon />


    const validationSchema = Yup.object().shape({
        brandType: Yup.object().required("Brand Type is required"),
        brandName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Brand Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })


    });



    return (
        <>
            <PageHeader
                title={updateID ? "Update Brand" : "Add Brand"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            brandType: brandList.find(item => item.value == brandByID?.brandType) || null,
                            brandName: brandByID?.brandName || ''

                        }}
                        validationSchema={validationSchema}
                        onSubmit={handleFormSubmit}
                        enableReinitialize={true}
                    >
                        {({
                            errors,
                            touched,
                            handleSubmit,
                            handleChange,
                            handleBlur,
                            values,
                            setFieldValue

                        }) => {

                            return (
                                <Form onSubmit={handleSubmit}>

                                    <Row className="mb-3">


                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Brand Name</Form.Label>
                                            <Form.Control
                                                name="brandName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Brand Name"
                                                required
                                                value={values.brandName}
                                            />
                                            {touched.brandName && errors.brandName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.brandName}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Brand Type</Form.Label>
                                            <Select
                                                placeholder="Select Brand Type"
                                                closeMenuOnSelect={true}
                                                options={brandList}
                                                classNamePrefix="react-select"
                                                name="brandType"
                                                value={values.brandType}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "brandType",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.brandType && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.brandType}
                                                    </div>
                                                )}
                                        </Form.Group>

                                    </Row>

                                    <IconButton
                                        variant="primary"
                                        className="me-2"
                                        type="submit"

                                    >
                                        {updateID ? "Save" : "Submit"}
                                    </IconButton>
                                    <Button
                                        onClick={() => navigate("/master/brand")}
                                        variant="danger" type="Cancel"
                                    >
                                        Cancel
                                    </Button>

                                </Form>
                            );
                        }}
                    </Formik>
                </FalconComponentCard.Body>
            </FalconComponentCard>
        </>
    );
};

export default BrandForm;


