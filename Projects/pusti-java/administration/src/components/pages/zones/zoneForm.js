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

const ZoneForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [regionList, setRegionList] = useState([]);
    const [regionByID, setZoneByID] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Zone Get By ID
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `zone/getZoneById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setZoneByID(response.data);
                    }
                    setIsLoading(false)
                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);


                })
        }
    }, [])


    // load Region Data
    useEffect(() => {
        setIsLoading(true)
        const url = process.env.REACT_APP_BASE_URL + "region/getAllRegions";
        axios.get(url, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    const modifyRegion = response?.data?.map(region => {
                        return {
                            id: region.regionId,
                            label: region.regionName,
                            value: region.regionId

                        }
                    })

                    setRegionList(modifyRegion);
                    setIsLoading(false)
                }
            })
            .catch(error => {
                toast.error(error.message);
                setIsLoading(false)
            })
    }, [])


    const handleFormSubmit = (values, actions) => {

        const zoneInfo = {
            zoneName: values.zoneName,
            region: {
                regionId: values.regionId.value
            }

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'zone/addNewZone';
            axios.post(url, zoneInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/zone")
                        toast.success("Zone Added Success");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `zone/updateZone/${updateID}`;
            axios.put(updateUrl, zoneInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        navigate("/master/zone");
                        toast.success("Zone Update Success");
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
        regionId: Yup.object().required("Region is required"),
        zoneName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Zone Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })


    });




    return (
        <>
            <PageHeader
                title={updateID ? "Update Zone" : "Add Zone"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            regionId: regionList.find(item => item.id == regionByID?.region?.regionId) || null,
                            zoneName: regionByID?.zoneName || ''

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
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Region</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={regionList}
                                                placeholder="Select Region"
                                                classNamePrefix="react-select"
                                                name="regionId"
                                                value={values.regionId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "regionId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}

                                            />

                                            {
                                                errors.regionId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.regionId}
                                                    </div>
                                                )}
                                        </Form.Group>

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Zone Name</Form.Label>
                                            <Form.Control
                                                name="zoneName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Zone Name"
                                                required
                                                value={values.zoneName}
                                            />
                                            {touched.zoneName && errors.zoneName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.zoneName}
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
                                        onClick={() => navigate("/master/zone")}
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

export default ZoneForm;


