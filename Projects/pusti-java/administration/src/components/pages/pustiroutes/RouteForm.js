import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Col, Form, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import axios from "axios";
import { authHeader } from "utils";
import { Button, Card } from 'react-bootstrap';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";
import educations from "data/educations";

const RouteForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [allDistributor, setAllDistributor] = useState([]);
    const [allZone, setAllZone] = useState([]);
    const [salesOfficer, setSalesOfficer] = useState([]);
    const [route, setRoute] = useState([]);
    const [salesOfficerList, setSalesOfficerList] = useState([]);
    const [employeeInfo, setEmployeeInfo] = useState([]);
    const [checkboxValues, setCheckboxValues] = useState({
        Saturday: false,
        Sunday: false,
        Monday: false,
        Tuesday: false,
        Wednesday: false,
        Thursday: false,
        Friday: false
    });
    const { updateID } = useParams();
    const navigate = useNavigate();


    // create Sales Officer
    const handleCheckboxChange = e => {
        const { name, checked } = event.target;

        // Update state with the new checkbox value
        setCheckboxValues(prevValues => ({
            ...prevValues,
            [name]: checked
        }));
    };


    // Load Single Route Data
    useEffect(() => {
        if (updateID) {
            const url = process.env.REACT_APP_BASE_URL + `route/getRouteById/${updateID}`

            axios.get(url, { headers: authHeader() })
                .then(response => {
                    setRoute(response.data);

                    // Formatting Route Sales Officer Data.
                    const previousSalesOfficer = [];
                    if (response?.data?.routeSalesOfficerList?.length > 0) {
                        response?.data?.routeSalesOfficerList?.map(item => {
                            previousSalesOfficer.push({ id: item?.employee?.employeeId, employeeName: `${item?.employee.firstName} ${item?.employee.middleInitial !== null ? item?.employee.middleInitial : ""} ${item?.employee.lastName !== null ? item?.employee.lastName : ""} `, editPermit: item?.editPermit, addPermit: item?.addPermit, preferredDays: item?.preferredDays, visitingFrequency: item?.visitingFrequency })
                        })
                    }
                    setSalesOfficerList(previousSalesOfficer)

                })
                .catch((error) => console.log(error));
        }

    }, [updateID])


    // Load create Routes field value
    useEffect(() => {
        setIsLoading(true);
        const urls = [
            process.env.REACT_APP_BASE_URL + 'distributor/getAllDistributors',
            process.env.REACT_APP_BASE_URL + 'zone/getAllZones',
            process.env.REACT_APP_BASE_URL + 'employee/getAllEmployees',

        ]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false);

                // allDistributor
                const modifyDistributor = response[0]?.data?.map(item => {
                    return {
                        id: item.distributorId,
                        label: item.name,
                        value: item.distributorId

                    }
                })
                setAllDistributor(modifyDistributor);

                // allZones
                const modifyZone = response[1]?.data?.map(item => {
                    return {
                        id: item.zoneId,
                        label: item.zoneName,
                        value: item.zoneId

                    }
                })
                setAllZone(modifyZone);


                // Employee - Sales Officer
                setEmployeeInfo(response[2]?.data);
                const getSalesOfficer = response[2]?.data?.filter(employee => employee?.employeeType == 'SalesOfficer');

                const modifyEmployee = getSalesOfficer?.map(item => {
                    return {
                        id: item.employeeId,
                        name: `${item.firstName} ${item.middleInitial} ${item.lastName}`,
                        label: `${item.firstName} ${item.middleInitial} ${item.lastName}`,
                        value: item.employeeId

                    }
                })
                setSalesOfficer(modifyEmployee);



            })
    }, [])


    // Remove Sales Officer List Item
    const removeEducation = (index) => {
        const confirmation = window.confirm("Are Your Sure ?");
        if (confirmation) {
            const updatedItems = [...salesOfficerList];
            updatedItems.splice(index, 1);

            setSalesOfficerList(updatedItems);
        }
    }


    const handleRouteSubmit = (values, actions) => {

        const routeInfo =

        {

            routeName: values.routeName,
            zone: {
                zoneId: values.zoneId.value
            },
            distributor: {
                distributorId: values.distributorId.value
            },
            contributionPercentage: values.contributionPercentage,
            routeSalesOfficerList: [
                ...salesOfficerList?.map(salesOfficer => {

                    return {

                        visitingFrequency: salesOfficer.visitingFrequency,
                        preferredDays: salesOfficer.preferredDays,
                        employee: {
                            employeeId: salesOfficer.id
                        },
                        addPermit: values.addPermit,
                        editPermit: values.editPermit
                    }
                })

            ]
        }





        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'route/addNewRoute';
            axios.post(url, routeInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    navigate("/master/route")
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `route/updateRoute/${updateID}`;
            axios.put(updateUrl, routeInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.data) {
                        navigate("/master/route")
                        toast.success(response?.data.message);
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
        distributorId: Yup.object().required("Distributor is required"),
        zoneId: Yup.object().required("Zone is required"),
        routeName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Route Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            })
        ,
        contributionPercentage: Yup.number().required("Contribution % is required"),
        addPermit: Yup.boolean(),
        editPermit: Yup.boolean(),
    });


    return (
        <>
            <PageHeader
                title={updateID ? 'Update Route' : 'Add New Route'}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            distributorId: allDistributor?.find(distributor => distributor.id == route?.distributor?.distributorId) || null,
                            zoneId: allZone?.find(zone => zone.id == route?.zone?.zoneId) || null,
                            routeName: route?.routeName || '',
                            contributionPercentage: route?.contributionPercentage || 0,
                            addPermit: educations.addPermit || false,
                            editPermit: educations.editPermit || false,


                        }}
                        validationSchema={productSchema}
                        onSubmit={handleRouteSubmit}
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

                            return (
                                <Form onSubmit={handleSubmit}>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select DB</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={allDistributor}
                                                placeholder="Select Distributor"
                                                classNamePrefix="react-select"
                                                name="distributorId"
                                                value={values.distributorId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "distributorId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.distributorId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.distributorId}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Zone</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={allZone}
                                                placeholder="Select Zone"
                                                classNamePrefix="react-select"
                                                name="zoneId"
                                                value={values.zoneId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "zoneId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.zoneId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.zoneId}
                                                    </div>
                                                )}
                                        </Form.Group>

                                    </Row>

                                    <Row className="mb-3">

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Route Name</Form.Label>
                                            <Form.Control
                                                name="routeName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Route Name"
                                                required
                                                value={values.routeName}
                                            />
                                            {touched.routeName && errors.routeName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.routeName}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Contribution %</Form.Label>
                                            <Form.Control
                                                name="contributionPercentage"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="number"
                                                placeholder="Enter Contribution %"
                                                required
                                                value={values.contributionPercentage}
                                            />
                                            {touched.contributionPercentage && errors.contributionPercentage && (
                                                <div style={{ color: "red" }}>
                                                    {errors.contributionPercentage}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>

                                    <Row className="mb-3">
                                        <Card >
                                            <Card.Header as="h6" className="bg-light">
                                                Sales Officer / Sales Representative :
                                            </Card.Header>
                                            <Card.Body>


                                                {salesOfficerList.length ? <div className="table-responsive scrollbar">
                                                    <table className="table table-sm table-striped h6 my-1 mb-3 overflow-hidden">
                                                        <thead >
                                                            <tr>
                                                                <th className="  pe-1 align-middle white-space-nowrap" data-sort="name">Name</th>
                                                                <th className=" text-center pe-1 align-middle white-space-nowrap" >Frequency</th>
                                                                <th className="  pe-1 align-middle 
                                                            text-center white-space-nowrap" data-sort="email">Add Permit</th>
                                                                <th className="align-middle white-space-nowrap text-center " >Edit Permit</th>
                                                                <th className="align-middle white-space-nowrap text-center " >Days</th>
                                                                <th className=" align-middle white-space-nowrap text-center " >Remove</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody className="list" id="table-purchase-body">


                                                            {salesOfficerList?.map((education, index) =>

                                                            (
                                                                <tr className="btn-reveal-trigger">
                                                                    <th className="align-middle   white-space-nowrap name">{education?.employee ? `${education.employee.firstName} ${education.employee.middleInitial} ${education.employee.lastName}` : education.employeeName}</th>
                                                                    <td className="align-middle white-space-nowrap  text-center">{education.visitingFrequency}</td>
                                                                    <td className="align-middle   text-center white-space-nowrap">{education.addPermit == true ? "Yes" : "No"}</td>
                                                                    <td className="align-middle text-center white-space-nowrap">{education.editPermit == true ? "Yes" : "No"}</td>
                                                                    <td className="align-middle text-center white-space-nowrap">{education?.preferredDays}</td>
                                                                    <td className="align-middle text-center white-space-nowrap "><Button
                                                                        variant="link"
                                                                        to="#!"
                                                                        type="button"
                                                                        className="text-danger"
                                                                        size="sm"
                                                                        onClick={() => removeEducation(index)}
                                                                    >
                                                                        <FontAwesomeIcon className="fs--1" icon="trash-alt" />
                                                                    </Button></td>

                                                                </tr>
                                                            ))}
                                                        </tbody>
                                                    </table>

                                                </div> : ""}


                                                <Row >
                                                    <Form.Group as={Col} md="5" controlId="exampleFirstName">
                                                        <Form.Label>Select SO/SR</Form.Label>
                                                        <Select
                                                            closeMenuOnSelect={true}
                                                            options={salesOfficer}
                                                            placeholder="Select SO/SR"
                                                            classNamePrefix="react-select"
                                                            name="employeeId"
                                                            value={values.employeeId}
                                                            onChange={(selectedOption) => {
                                                                setFieldValue(
                                                                    "employeeId",
                                                                    selectedOption
                                                                );
                                                            }}
                                                            onBlur={handleBlur}
                                                        />

                                                        {
                                                            errors.employeeId && (
                                                                <div style={{ color: "red" }}>
                                                                    {errors.employeeId}
                                                                </div>
                                                            )}
                                                    </Form.Group>

                                                    <Form.Group as={Col} md="5" controlId="exampleState">
                                                        <Form.Label>Frequency</Form.Label>
                                                        <Form.Control
                                                            name="visitingFrequency"
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            type="number"
                                                            placeholder="Enter Frequency"
                                                            value={values.visitingFrequency}
                                                        />
                                                        {touched.visitingFrequency && errors.visitingFrequency && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.visitingFrequency}
                                                            </div>
                                                        )}
                                                    </Form.Group>


                                                    <Form.Group as={Col} md="2" >

                                                        <Button

                                                            variant="falcon-default"
                                                            className=" "
                                                            type="button"
                                                            style={{ marginTop: '30px' }}
                                                            disabled={
                                                                !values.employeeId || !values.visitingFrequency
                                                            }
                                                            onClick={() => {
                                                                setSalesOfficerList([...salesOfficerList, {
                                                                    id: values.employeeId.id, employeeName: values.employeeId.name,
                                                                    visitingFrequency: values.visitingFrequency,
                                                                    addPermit: values.addPermit, editPermit: values.editPermit, preferredDays: Object.keys(checkboxValues).filter(
                                                                        prop => checkboxValues[prop]).join(' ')
                                                                }]);
                                                                setFieldValue('employeeId', 0);
                                                                setFieldValue('visitingFrequency', 0);
                                                                setFieldValue('editPermit', false);
                                                                setFieldValue('addPermit', false);

                                                                setCheckboxValues({
                                                                    Saturday: false,
                                                                    Sunday: false,
                                                                    Monday: false,
                                                                    Tuesday: false,
                                                                    Wednesday: false,
                                                                    Thursday: false,
                                                                    Friday: false
                                                                })

                                                            }}
                                                        >
                                                            Add
                                                        </Button>
                                                    </Form.Group>

                                                </Row>

                                                <Row >
                                                    {/* <Form.Label>Select Day :</Form.Label> */}
                                                    <div className="my-3 ">
                                                        <Form.Check
                                                            inline
                                                            label="SAT"
                                                            name="Saturday"
                                                            value="Saturday"
                                                            checked={checkboxValues.Saturday}
                                                            type="checkbox"
                                                            id="Saturday"
                                                            onChange={handleCheckboxChange}
                                                        />
                                                        <Form.Check
                                                            inline
                                                            label="SUN"
                                                            name="Sunday"
                                                            value="Sunday"
                                                            checked={checkboxValues.Sunday}
                                                            type="checkbox"
                                                            id="Sunday"
                                                            onChange={handleCheckboxChange}
                                                        />
                                                        <Form.Check
                                                            inline
                                                            label="MON"
                                                            name="Monday"
                                                            value="Monday"
                                                            checked={checkboxValues.Monday}
                                                            type="checkbox"
                                                            id="Monday"
                                                            onChange={handleCheckboxChange}
                                                        />
                                                        <Form.Check
                                                            inline
                                                            label="TUE"
                                                            name="Tuesday"
                                                            value="Tuesday"
                                                            checked={checkboxValues.Tuesday}
                                                            type="checkbox"
                                                            id="Tuesday"
                                                            onChange={handleCheckboxChange}
                                                        />
                                                        <Form.Check
                                                            inline
                                                            label="WED"
                                                            name="Wednesday"
                                                            value="Wednesday"
                                                            checked={checkboxValues.Wednesday}
                                                            type="checkbox"
                                                            id="Tuesday"
                                                            onChange={handleCheckboxChange}
                                                        />
                                                        <Form.Check
                                                            inline
                                                            label="THU"
                                                            name="Thursday"
                                                            value="Thursday"
                                                            type="checkbox"
                                                            id="Thursday"
                                                            onChange={handleCheckboxChange}
                                                            checked={checkboxValues.Thursday}
                                                        />
                                                        <Form.Check
                                                            inline
                                                            label="FRI"
                                                            name="Friday"
                                                            value="Friday"
                                                            type="checkbox"
                                                            id="Friday"
                                                            onChange={handleCheckboxChange}
                                                            checked={checkboxValues.Friday}
                                                        />

                                                    </div>
                                                </Row>
                                                <Row className="my-1">

                                                    <Form.Group as={Col} md="6" controlId="exampleState">
                                                        {/* <Form.Label>Add Permission</Form.Label> */}
                                                        <Form.Check
                                                            name="addPermit"
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            checked={values.addPermit}
                                                            type="checkbox"
                                                            label="Add Permission"
                                                            value={values.addPermit}
                                                        />
                                                        {touched.addPermit && errors.addPermit && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.addPermit}
                                                            </div>
                                                        )}
                                                    </Form.Group>
                                                    <Form.Group as={Col} md="6" controlId="exampleState">
                                                        {/* <Form.Label>Edit Permission</Form.Label> */}
                                                        <Form.Check
                                                            name="editPermit"
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            checked={values.editPermit}
                                                            type="checkbox"
                                                            label="Edit Permission"
                                                            value={values.editPermit}
                                                        />
                                                        {touched.editPermit && errors.editPermit && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.editPermit}
                                                            </div>
                                                        )}
                                                    </Form.Group>

                                                </Row>
                                            </Card.Body>
                                        </Card>
                                    </Row>

                                    <IconButton
                                        variant="primary"
                                        className="me-2"
                                        type="submit"
                                        disabled={!values.distributorId || !values.zoneId || !values.routeName || !values.contributionPercentage || salesOfficerList?.length === 0}

                                    >
                                        {updateID ? "Save" : "Submit"}
                                    </IconButton>
                                    <Button
                                        onClick={() => navigate("/master/route")}
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

export default RouteForm;


