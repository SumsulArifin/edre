import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";


const FactoryManagementForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [allDepot, setAllDepot] = useState([]);
    const [factoryById, setFactoryById] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Load Factory by id
    useEffect(() => {
        if (updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + `factory/getFactoryById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 200) {
                        setFactoryById(response.data);

                    }
                })
        }
    }, [])


    // Load create Factory field value - Depot
    useEffect(() => {
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + 'depot/getAllDepots'

        axios.get(url, { headers: authHeader() })
            .then(response => {
                setIsLoading(false);
                // depot
                if (response.status === 200) {
                    const modifyDepot = response?.data?.map(item => {
                        return {
                            id: item.depotId,
                            label: item.depotName,
                            value: item.depotId,
                            name: item.depotName

                        }
                    })
                    setAllDepot(modifyDepot);
                }
            })
    }, [])

    const handleFactorySubmit = (values, actions) => {

        const factoryInformation =

        {
            factoryName: values.factoryName,
            address: values.address,
            assignDepots: [...values.depotId?.map(depot => {
                return {
                    depot: {
                        depotId: depot.id
                    }
                }
            })]
        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'factory/addNewFactory'
            axios.post(url, factoryInformation, { headers: authHeader() })
                .then(response => {
                    console.log("factory", response)
                    setIsLoading(false);
                    if (response.status === 201) {
                        navigate("/factoryManagements")
                        toast.success("New Factory Added Success");
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message
                    )
                })
        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `factory/updateFactory/${updateID}`;
            axios.put(updateUrl, factoryInformation, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 200) {
                        navigate("/factoryManagements")
                        toast.success("Factory Update Success");
                    }

                })
                .catch(error => {
                    setIsLoading(false);

                    toast.error(
                        error.message
                    )
                })

        }

    }


    isLoading && <LoadingIcon />


    const factorySchema = Yup.object().shape({
        factoryName: Yup.string().required("Factory Name is required"),
        address: Yup.string().required("Address is required"),
        depotId: Yup.array().required("Depot is required"),

    });


    return (
        <>
            <PageHeader
                title={updateID ? 'Update Factory' : 'Add New Factory'}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            factoryName: factoryById.factoryName || "",
                            address: factoryById.address || "",
                            depotId: allDepot.filter(depot => {

                                const result = factoryById?.assignDepots?.find(assignDepo => (assignDepo.depot.depotId) == (depot.id));
                                return result;
                            }) || null


                        }}


                        validationSchema={factorySchema}
                        onSubmit={handleFactorySubmit}
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
                            console.log(values.depotId)
                            return (
                                <Form onSubmit={handleSubmit}>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Factory Name</Form.Label>
                                            <Form.Control
                                                name="factoryName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Factory Name"
                                                required
                                                value={values.factoryName}
                                            />
                                            {touched.factoryName && errors.factoryName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.factoryName}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Depot</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                isMulti={true}
                                                required
                                                options={allDepot}
                                                placeholder="Select Depot"
                                                classNamePrefix="react-select"
                                                name="depotId"
                                                value={values.depotId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "depotId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.depotId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.depotId}
                                                    </div>
                                                )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Address</Form.Label>
                                            <Form.Control
                                                as="textarea"
                                                name="address"
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
                                        onClick={() => navigate("/factoryManagements")}
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

export default FactoryManagementForm;


