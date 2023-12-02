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

const DivisionForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [nationalList, setNationalList] = useState([]);
    const [divisionByID, setDivisionByID] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Division Get By ID
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `division/getDivisionById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setDivisionByID(response.data);
                    }
                    setIsLoading(false)
                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);


                })
        }
    }, [])


    // load National Data
    useEffect(() => {
        setIsLoading(true)
        const url = process.env.REACT_APP_BASE_URL + "national/getAllNationals";
        axios.get(url, { headers: authHeader() })
            .then(response => {
                console.log(response)
                if (response.status === 200) {
                    const modifyNational = response?.data?.map(national => {
                        return {
                            id: national.nationalId,
                            label: national.nationalName,
                            value: national.nationalId

                        }
                    })

                    setNationalList(modifyNational);
                    setIsLoading(false)
                }
            })
            .catch(error => {
                toast.error(error.message);
                setIsLoading(false)
            })
    }, [])

    console.log(nationalList)

    const handleDivisionSubmit = (values, actions) => {

        const divisionInfo = {
            divisionName: values.divisionName,
            national: {
                nationalId: values.nationalId.value
            }

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'division/addNewDivision';
            axios.post(url, divisionInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/division")
                        toast.success("Division Added Success");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `division/updateDivision/${updateID}`;
            axios.put(updateUrl, divisionInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        navigate("/master/division");
                        toast.success("Division Update Success");
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


    const divisionSchema = Yup.object().shape({
        nationalId: Yup.object().required("National is required"),
        divisionName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Division Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })


    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Division" : "Add Division"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            nationalId: nationalList.find(national => national.id === divisionByID?.national?.nationalId) || null,
                            divisionName: divisionByID?.divisionName || ''

                        }}
                        validationSchema={divisionSchema}
                        onSubmit={handleDivisionSubmit}
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
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select National</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={nationalList}
                                                placeholder="Select National"
                                                classNamePrefix="react-select"
                                                name="nationalId"
                                                value={values.nationalId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "nationalId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}

                                            />

                                            {
                                                errors.nationalId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.nationalId}
                                                    </div>
                                                )}
                                        </Form.Group>

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Division Name</Form.Label>
                                            <Form.Control
                                                name="divisionName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Division Name"
                                                required
                                                value={values.divisionName}
                                            />
                                            {touched.divisionName && errors.divisionName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.divisionName}
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

                                        onClick={() => navigate("/master/division")}
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

export default DivisionForm;


