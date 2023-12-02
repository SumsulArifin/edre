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

const RegionForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [divisionList, setDivisionList] = useState([]);
    const [regionByID, setRegionByID] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Region Get By ID
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `region/getRegionById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setRegionByID(response.data);
                    }
                    setIsLoading(false)
                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);


                })
        }
    }, [])


    // load Division Data
    useEffect(() => {
        setIsLoading(true)
        const url = process.env.REACT_APP_BASE_URL + "division/getAllDivisions";
        axios.get(url, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    const modifyDivision = response?.data?.map(division => {
                        return {
                            id: division.divisionId,
                            label: division.divisionName,
                            value: division.divisionId

                        }
                    })

                    setDivisionList(modifyDivision);
                    setIsLoading(false)
                }
            })
            .catch(error => {
                toast.error(error.message);
                setIsLoading(false)
            })
    }, [])


    const handleRegionSubmit = (values, actions) => {

        const regionInfo = {
            regionName: values.regionName,
            division: {
                divisionId: values.divisionId.value
            }

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'region/addNewRegion';
            axios.post(url, regionInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/region")
                        toast.success("Region Added Success");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `region/updateRegion/${updateID}`;
            axios.put(updateUrl, regionInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        navigate("/master/region");
                        toast.success("Region Update Success");
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


    const regionSchema = Yup.object().shape({
        divisionId: Yup.object().required("Division is required"),
        regionName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Region Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })


    });



    return (
        <>
            <PageHeader
                title={updateID ? "Update Region" : "Add Region"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            divisionId: divisionList.find(division => division.id == regionByID?.division?.divisionId) || null,
                            regionName: regionByID?.regionName || ''

                        }}
                        validationSchema={regionSchema}
                        onSubmit={handleRegionSubmit}
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
                                            <Form.Label>Select Division</Form.Label>
                                            <Select
                                                placeholder="Select Division"
                                                closeMenuOnSelect={true}
                                                options={divisionList}
                                                classNamePrefix="react-select"
                                                name="divisionId"
                                                value={values.divisionId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "divisionId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.divisionId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.divisionId}
                                                    </div>
                                                )}
                                        </Form.Group>

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Region Name</Form.Label>
                                            <Form.Control
                                                name="regionName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Region Name"
                                                required
                                                value={values.regionName}
                                            />
                                            {touched.regionName && errors.regionName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.regionName}
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
                                        onClick={() => navigate("/master/region")}
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

export default RegionForm;


