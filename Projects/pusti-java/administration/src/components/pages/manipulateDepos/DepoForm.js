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
import Select from "react-select";
import { EMAIL_REGEX, PHONE_NUMBER_REGEX } from "utils/validationRegex";

const DepotForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [depotsInformation, setDepotsInformation] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Depot Departments List
    const departments = [
        {
            label: 'HUMAN RESOURCE',
            value: 'HUMAN_RESOURCE',
        },
    ]

    // load update Depot field value
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `depot/getDepotById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false)
                    setDepotsInformation(response?.data);
                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(`! ${error?.response?.data?.errors[0]} . 
                ${error?.message}
                `)
                })
        }
    }, [])




    const handleProductSubmit = (values, actions) => {


        const depotInformation = {

            name: values.depotName,
            email: values.email,
            department: values.department.value,
            phone: values.phone

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'depot/addNewDepot';
            axios.post(url, depotInformation, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 201) {
                        navigate("/manipulate")
                        toast.success("New Depot Added Success");
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(`! ${error?.response?.data?.errors[0]} . 
                    ${error?.message}
                    `)
                })



        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `depot/updateDepot/${updateID}`;
            axios.put(updateUrl, depotInformation, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 200) {
                        navigate("/manipulate")
                        toast.success("Depot Update Success");
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(`! ${error?.response?.data?.errors[0]} . 
                    ${error?.message}
                    `)
                })
        }

    }


    isLoading && <LoadingIcon />


    const depotsSchema = Yup.object().shape({
        depotName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Depot Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            }),
        email: Yup.string().test("Email Is valid", "Email Is Invalid", (value => {
            return EMAIL_REGEX.test(value);
        })).required("Email is required"),
        department: Yup.object().required("Department is required"),
        phone: Yup.string().test("Phone Number Valid", "Phone Number Is Invalid", (value => {
            return PHONE_NUMBER_REGEX.test(value);
        })).required("Phone Number is required"),

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Depots" : "Add Depots"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            depotName: depotsInformation?.depotName || '',
                            email: depotsInformation?.email || '',
                            department: departments.find(department => department.value === depotsInformation?.department) || null,
                            phone: depotsInformation?.phone || ''

                        }}
                        validationSchema={depotsSchema}
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


                        }) => {

                            return (
                                <Form onSubmit={handleSubmit}>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Depot Name</Form.Label>
                                            <Form.Control
                                                name="depotName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Depot Name"
                                                required
                                                value={values.depotName}
                                            />
                                            {touched.depotName && errors.depotName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.depotName}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Email</Form.Label>
                                            <Form.Control
                                                name="email"

                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="email"
                                                placeholder="Enter Email"
                                                required
                                                value={values.email}
                                            />
                                            {touched.email && errors.email && (
                                                <div style={{ color: "red" }}>
                                                    {errors.email}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Department</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={departments}
                                                placeholder="Select Department"
                                                classNamePrefix="react-select"
                                                name="department"
                                                value={values.department}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "department",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.department && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.department}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Phone</Form.Label>
                                            <Form.Control
                                                name="phone"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Phone Number"
                                                required
                                                value={values.phone}
                                            />
                                            {touched.phone && errors.phone && (
                                                <div style={{ color: "red" }}>
                                                    {errors.phone}
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
                                        onClick={() => navigate("/manipulate")}
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

export default DepotForm;


