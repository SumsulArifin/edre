import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";

const Add_OfferType = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [offerTypeInformation, setOfferTypeInformation] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // load update Category Type field value
    useEffect(() => {
        setIsLoading(true)
        const urls = [process.env.REACT_APP_BASE_URL + `offerType/getById/${updateID}`]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false)
                setOfferTypeInformation(response[0].data);
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


    const handleProductSubmit = (values, actions) => {


        const offerTypeInfo = {

            name: values.offerTypeName,
            description: values.description

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'offerType/add';
            axios.post(url, offerTypeInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    navigate("/offerType")
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `offerType/update/${updateID}`;
            axios.put(updateUrl, offerTypeInfo, { headers: authHeader() })
                .then(response => {
                    console.log('check'.response)
                    setIsLoading(false);
                    if (response.data) {
                        navigate("/offerType")
                        toast.success(response?.data.message || 'Offer Type Update Success');
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
        offerTypeName: Yup.string().required("Offer Type Name is required"),
        description: Yup.string().required("Description is required"),

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Offer Type" : "Add Offer Type"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{

                            offerTypeName: offerTypeInformation?.name || '',
                            description: offerTypeInformation?.description || ''

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

                        }) => {

                            return (
                                <Form onSubmit={handleSubmit}>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="12" controlId="exampleState">
                                            <Form.Label>Offer Type Name</Form.Label>
                                            <Form.Control
                                                name="offerTypeName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Offer Type Name"
                                                required
                                                value={values.offerTypeName}
                                            />
                                            {touched.offerTypeName && errors.offerTypeName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.offerTypeName}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="12" controlId="exampleState">
                                            <Form.Label>Description</Form.Label>
                                            <Form.Control
                                                name="description"
                                                as="textarea" rows={3}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Description"
                                                required
                                                value={values.description}
                                            />
                                            {touched.description && errors.description && (
                                                <div style={{ color: "red" }}>
                                                    {errors.description}
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

export default Add_OfferType;


