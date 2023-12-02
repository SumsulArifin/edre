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

const NationalForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [nationalInformation, setNationalInformation] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // load update National field value
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `national/getNationalById/${updateID}`
            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setNationalInformation(response.data);
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

        const nationalInfo = {
            nationalName: values.nationalName,

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'national/addNewNational';
            axios.post(url, nationalInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/national")
                        toast.success("National Added Success");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `national/updateNational/${updateID}`;
            axios.put(updateUrl, nationalInfo, { headers: authHeader() })
                .then(response => {

                    if (response.status === 200) {
                        navigate("/master/national")
                        toast.success("National Update Success");
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


    const nationalSchema = Yup.object().shape({
        nationalName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("National Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update National" : "Add National"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            nationalName: nationalInformation?.nationalName || '',
                        }}
                        validationSchema={nationalSchema}
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
                                            <Form.Label>National Name</Form.Label>
                                            <Form.Control
                                                name="nationalName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter National Name"
                                                required
                                                value={values.nationalName}
                                            />
                                            {touched.nationalName && errors.nationalName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.nationalName}
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
                                        onClick={() => navigate("/master/national")}
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

export default NationalForm;


