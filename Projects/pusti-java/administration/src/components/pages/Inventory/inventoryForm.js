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
import LoadingIcon from "helpers/LoadingIcon";

const InventoryForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [inventoryInformation, setInventoryInformation] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // load update Inventory field value
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const urls = [process.env.REACT_APP_BASE_URL + `inventory/getInventoryById/${updateID}`]

            Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
                .then(response => {
                    setIsLoading(false)
                    // if (response.status) {
                    setInventoryInformation(response[0]?.data);
                    // }
                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);

                })
        }
    }, [])



    const handleFormSubmit = (values, actions) => {


        const inventoryInfo = {

            name: values.inventoryName,
            address: values.address

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'inventory/addNewInventory';
            axios.post(url, inventoryInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 201) {
                        navigate("/master/inventory")
                        toast.success("New Inventory Added Success");
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)

                })



        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `inventory/updateInventory/${updateID}`;
            axios.put(updateUrl, inventoryInfo, { headers: authHeader() })
                .then(response => {

                    setIsLoading(false);
                    if (response.status === 200) {
                        navigate("/master/inventory")
                        toast.success('Inventory Update Success');
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)

                })

        }

    }

    console.log("inventoryInformation", inventoryInformation)

    isLoading && <LoadingIcon />


    const productSchema = Yup.object().shape({
        inventoryName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Inventory Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            }),
        address: Yup.string().min(5, 'Too Short!').required("Address is required").test('starts with a number', 'Filed input should not start with a number', value => {
            return !/^\d/.test(value);
        })
        ,

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Inventory" : "Add Inventory"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            inventoryName: inventoryInformation?.name || '',
                            address: inventoryInformation?.address || ''

                        }}
                        validationSchema={productSchema}
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

                        }) => {

                            return (
                                <Form onSubmit={handleSubmit}>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="12" controlId="exampleState">
                                            <Form.Label>Inventory Name</Form.Label>
                                            <Form.Control
                                                name="inventoryName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Inventory Name"
                                                required
                                                value={values.inventoryName}
                                            />
                                            {touched.inventoryName && errors.inventoryName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.inventoryName}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="12" controlId="exampleState">
                                            <Form.Label>Address</Form.Label>
                                            <Form.Control
                                                name="address"
                                                as="textarea" rows={3}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Address"
                                                required
                                                value={values.address}
                                            />
                                            {touched.address && errors.address && (
                                                <div style={{ color: "red" }}>
                                                    {errors.address}
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
                                        onClick={() => navigate("/master/inventory")}
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

export default InventoryForm;


