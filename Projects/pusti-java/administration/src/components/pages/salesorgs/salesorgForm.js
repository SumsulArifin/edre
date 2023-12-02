import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";

const SalesOrgForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [salesOrgInformation, setSalesOrgInformation] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // load update Sales org field value
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `salesOrganization/getSalesOrganizationById/${updateID}`
            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setSalesOrgInformation(response.data);
                        setIsLoading(false)
                    }
                })
                .catch(error => {
                    alert.error(error.message)
                    setIsLoading(false)

                })
        }
    }, [])


    const handleNationalSubmit = (values, actions) => {

        const salesOrgInfo = {
            salesOrgName: values.salesOrgName,

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'salesOrganization/addSalesOrganizations';
            axios.post(url, salesOrgInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/salesorg")
                        toast.success("Sales Org Added Success");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `salesOrganization/updateSalesOrganization/${updateID}`;
            axios.put(updateUrl, salesOrgInfo, { headers: authHeader() })
                .then(response => {

                    if (response.status === 200) {
                        navigate("/master/salesorg")
                        toast.success("Sales Org Update Success");
                    }
                    setIsLoading(false);

                })
                .catch(error => {
                    toast.error(error.message)
                    setIsLoading(false);

                })

        }

    }


    isLoading && <LoadingIcon />


    const validationSchema = Yup.object().shape({
        salesOrgName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Sales Org Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update SalesOrg" : "Add SalesOrg"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            salesOrgName: salesOrgInformation?.salesOrgName || '',
                        }}
                        validationSchema={validationSchema}
                        onSubmit={handleNationalSubmit}
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
                                        <Form.Group as={Col} md="10" controlId="exampleState">
                                            <Form.Label>SalesOrg Name</Form.Label>
                                            <Form.Control
                                                name="salesOrgName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Sales Org Name"
                                                required
                                                value={values.salesOrgName}
                                            />
                                            {touched.salesOrgName && errors.salesOrgName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.salesOrgName}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>

                                    <Button
                                        className="me-2"
                                        variant="primary" type="submit"
                                    >
                                        {updateID ? "Save" : "Submit"}
                                    </Button>
                                    <Button
                                        onClick={() => navigate("/master/salesorg")}
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

export default SalesOrgForm;


