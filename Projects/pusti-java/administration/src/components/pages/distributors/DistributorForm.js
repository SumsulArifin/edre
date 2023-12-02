import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Button, Col, Form, InputGroup, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";
import { DATE_REGEX, PHONE_NUMBER_REGEX } from "utils/validationRegex";

const DistributorForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [zones, setZones] = useState([]);
    const [depots, setDepots] = useState([]);
    const [upazilas, setUpazilas] = useState([]);
    const [distributorInfo, setDistributorInfo] = useState([]);
    const { updateID } = useParams();
    const navigate = useNavigate();

    //  Date Format
    function formatDateToYYYYMMDD(isoDateString) {
        const date = new Date(isoDateString);

        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0"); // Month is 0-based
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
    }


    const hasPc = [
        {
            label: "Yes",
            value: true
        },
        {
            label: "No",
            value: false
        },

    ]

    const hasPrinter = [
        {
            label: "Yes",
            value: true
        },
        {
            label: "No",
            value: false
        },

    ]

    const hasLiveApp = [
        {
            label: "Yes",
            value: true
        },
        {
            label: "No",
            value: false
        },

    ]

    const hasDirectSale = [
        {
            label: "Yes",
            value: true
        },
        {
            label: "No",
            value: false
        },

    ]

    // Load Distributor field value
    useEffect(() => {
        setIsLoading(true);
        const urls = [
            process.env.REACT_APP_BASE_URL + 'zone/getAllZones',
            process.env.REACT_APP_BASE_URL + 'depot/getAllDepots',
            process.env.REACT_APP_BASE_URL + 'upazila/getAllUpazilas',

        ]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false);

                // Zones
                const modifyZones = response[0]?.data?.map(item => {
                    return {
                        id: item.zoneId,
                        label: item.zoneName,
                        value: item.zoneId

                    }
                })
                setZones(modifyZones);

                // Depot
                const modifyDepot = response[1]?.data?.map(item => {
                    return {
                        id: item.depotId,
                        label: item.depotName,
                        value: item.depotId

                    }
                })
                setDepots(modifyDepot);

                // Upazila
                const modifyUpazilas = response[2]?.data?.map(item => {
                    return {
                        id: item.upazilaId,
                        label: item.upazilaName,
                        value: item.upazilaId

                    }
                })
                setUpazilas(modifyUpazilas);

            })
    }, [])


    // load update Distributor field value
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `distributor/getDistributorById/${updateID}`;
            axios.get(url, { headers: authHeader() })
                .then(response => {
                    console.log("distributro", response)
                    setIsLoading(false)
                    if (response.status === 200) {
                        setDistributorInfo(response.data);
                    }
                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(`! ${error?.response?.data?.errors[0]} . 
            ${error?.message}
            `)
                })
        }
    }, [])


    const handleDistributorSubmit = (values, actions) => {

        const distributorInformation = {
            name: values.distributorName,
            erpId: values.erpId,
            address: values.address,
            mobile: values.mobile,
            proprietorName: values.proprietorName,
            proprietorDob: formatDateToYYYYMMDD(values.proprietorDob),
            hasPrinter: values.hasPrinter.value,
            hasPc: values.hasPc.value,
            hasLiveApp: values.hasLiveApp.value,
            hasDirectSale: values.hasDirectSale.value,
            openingDate: formatDateToYYYYMMDD(values.openingDate),
            emergencyContactName: values.emergencyContactName,
            emergencyContactNumber: values.emergencyContactNumber,
            emergencyContactRelation: values.emergencyContactRelation,
            upazila: { upazilaId: values.upazilaId.value },
            zone: { zoneId: values.zoneId.value },
            depot: { depotId: values.depotId.value }

        }

        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'distributor/addNewDistributor';
            axios.post(url, distributorInformation, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 201) {
                        navigate("/master/distributor")
                        toast.success("New Distributor Added");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `distributor/updateDistributor/${updateID}`;
            axios.put(updateUrl, distributorInformation, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.status === 200) {
                        navigate("/master/distributor")
                        toast.success("Distributor Update Success");
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


    const productSchema = Yup.object().shape({
        distributorName: Yup.string()
            .min(2, "Too Short!")
            .max(80, "Too Long!")
            .required("Distributor Name is required")
            .test(
                "starts with a number",
                "Filed input should not start with a number",
                (value) => {
                    return !/^\d/.test(value);
                }
            ),
        zoneId: Yup.object().required("Zone is required"),
        depotId: Yup.object().required("Depot is required"),
        upazilaId: Yup.object().required("Upazila is required"),
        erpId: Yup.string().required("ERP Id is required"),
        address: Yup.string().min(5, "Too Short").required("Address is required"),
        hasDirectSale: Yup.object().required("hasDirectSale is required"),
        hasLiveApp: Yup.object().required("hasLiveApp is required"),
        hasPrinter: Yup.object().required("hasPrinter is required"),
        hasPc: Yup.object().required("hasPc is required"),
        proprietorName: Yup.string()
            .min(2, "Too Short!")
            .max(80, "Too Long!")
            .required("Proprietor Name is required")
            .test(
                "starts with a number",
                "Filed input should not start with a number",
                (value) => {
                    return !/^\d/.test(value);
                }
            ),
        mobile: Yup.string().test("Number is Valid", "Number is Invalid", (value => {
            return PHONE_NUMBER_REGEX.test(value)
        })).required("Mobile Number is required"),
        proprietorDob: Yup.date().required("Proprietor Dob is required"),
        openingDate: Yup.date().required("Opening Date is required"),
        emergencyContactName: Yup.string()
            .min(2, "Too Short!")
            .max(80, "Too Long!")
            .test(
                "starts with a number",
                "Filed input should not start with a number",
                (value) => {
                    return !/^\d/.test(value);
                }
            ),
        emergencyContactNumber: Yup.string().test("Phone Number Valid", "Phone Number is Invalid", (value => {
            return PHONE_NUMBER_REGEX.test(value);
        })),
        emergencyContactRelation: Yup.string().min(2, "Too Short!")
            .max(80, "Too Long!")
            .test(
                "starts with a number",
                "Filed input should not start with a number",
                (value) => {
                    return !/^\d/.test(value);
                }
            ),


    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Distributor" : "Add New Distributor"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{

                            distributorName: distributorInfo.name || "",
                            zoneId: zones.find(zone => zone.value === distributorInfo.zone?.zoneId) || null,
                            depotId: depots.find(depot => depot.value === distributorInfo.depot?.depotId) || null,
                            upazilaId: upazilas.find(upazila => upazila.value === distributorInfo.upazila?.upazilaId) || null,
                            erpId: distributorInfo.erpId || "",
                            proprietorName: distributorInfo.proprietorName || "",
                            proprietorDob: formatDateToYYYYMMDD(distributorInfo.proprietorDob) || null,
                            openingDate: distributorInfo.openingDate || null,
                            address: distributorInfo.address || "",
                            hasDirectSale: hasDirectSale.find(item => item.value === distributorInfo.hasDirectSale) || null,
                            hasLiveApp: hasLiveApp.find(item => item.value === distributorInfo.hasLiveApp) || null,
                            hasPrinter: hasPrinter.find(item => item.value === distributorInfo.hasPrinter) || null,
                            hasPc: hasPc.find(item => item.value === distributorInfo.hasPc) || null,
                            mobile: distributorInfo.mobile || "",
                            emergencyContactName: distributorInfo.emergencyContactName || "",
                            emergencyContactNumber: distributorInfo.emergencyContactNumber || "",
                            emergencyContactRelation: distributorInfo.emergencyContactRelation || ""

                        }}
                        validationSchema={productSchema}
                        onSubmit={handleDistributorSubmit}
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
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Distributor Name</Form.Label>
                                            <Form.Control
                                                name="distributorName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Distributor Name"
                                                required
                                                value={values.distributorName}
                                            />
                                            {touched.distributorName && errors.distributorName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.distributorName}
                                                </div>
                                            )}
                                        </Form.Group>

                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Zone</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={zones}
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
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Depot</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={depots}
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

                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Select Thana</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={upazilas}
                                                placeholder="Select Thana"
                                                classNamePrefix="react-select"
                                                name="upazilaId"
                                                value={values.upazilaId}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "upazilaId",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.upazilaId && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.upazilaId}
                                                    </div>
                                                )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>ERP ID</Form.Label>
                                            <Form.Control
                                                name="erpId"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter ERP ID"
                                                required
                                                value={values.erpId}
                                            />
                                            {touched.erpId && errors.erpId && (
                                                <div style={{ color: "red" }}>
                                                    {errors.erpId}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Proprietor</Form.Label>
                                            <Form.Control
                                                name="proprietorName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Proprietor Name"
                                                required
                                                value={values.proprietorName}
                                            />
                                            {touched.proprietorName && errors.proprietorName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.proprietorName}
                                                </div>
                                            )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group
                                            as={Col}
                                            md="6"
                                            controlId="exampleFirstName"
                                        >
                                            <Form.Label>Proprietor DOB</Form.Label>
                                            <InputGroup>
                                                <Form.Control
                                                    // selected={new Date(values.dateOfJoining)}
                                                    // formatWeekDay={(day) => day.slice(0, 3)}
                                                    // dateFormat="MMMM d, yyyy"
                                                    // minDate={new Date()}
                                                    className="form-control"
                                                    placeholderText="Select Date Of Birth"
                                                    type="date"
                                                    name="proprietorDob"
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    value={values.proprietorDob}
                                                />
                                            </InputGroup>


                                            {touched.proprietorDob &&
                                                errors.proprietorDob && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.proprietorDob}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group
                                            as={Col}
                                            md="6"
                                            controlId="exampleFirstName"
                                        >
                                            <Form.Label>DB Opening Date</Form.Label>

                                            <InputGroup>
                                                <Form.Control
                                                    // selected={new Date(values.dateOfJoining)}
                                                    // formatWeekDay={(day) => day.slice(0, 3)}
                                                    // dateFormat="MMMM d, yyyy"
                                                    // minDate={new Date()}
                                                    className="form-control"
                                                    placeholderText="Select Date Of Birth"
                                                    type="date"
                                                    name="openingDate"
                                                    onChange={handleChange}
                                                    onBlur={handleBlur}
                                                    value={values.openingDate}
                                                />
                                            </InputGroup>


                                            {touched.openingDate &&
                                                errors.openingDate && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.openingDate}
                                                    </div>
                                                )}
                                        </Form.Group>


                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>hasPc</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={hasPc}
                                                placeholder="Select hasPc"
                                                classNamePrefix="react-select"
                                                name="hasPc"
                                                value={values.hasPc}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "hasPc",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.hasPc && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.hasPc}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>hasPrinter</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={hasPrinter}
                                                placeholder="Select hasPrinter"
                                                classNamePrefix="react-select"
                                                name="hasPrinter"
                                                value={values.hasPrinter}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "hasPrinter",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.hasPrinter && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.hasPrinter}
                                                    </div>
                                                )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>hasLiveApp</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={hasLiveApp}
                                                placeholder="Select hasLiveApp"
                                                classNamePrefix="react-select"
                                                name="hasLiveApp"
                                                value={values.hasLiveApp}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "hasLiveApp",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.hasLiveApp && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.hasLiveApp}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>hasDirectSale</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={hasDirectSale}
                                                placeholder="Select hasDirectSale"
                                                classNamePrefix="react-select"
                                                name="hasDirectSale"
                                                value={values.hasDirectSale}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "hasDirectSale",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.hasDirectSale && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.hasDirectSale}
                                                    </div>
                                                )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Mobile Number*</Form.Label>
                                            <Form.Control
                                                name="mobile"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Mobile Number"
                                                required
                                                value={values.mobile}
                                            />
                                            {touched.mobile && errors.mobile && (
                                                <div style={{ color: "red" }}>
                                                    {errors.mobile}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Address</Form.Label>
                                            <Form.Control
                                                name="address"
                                                as="textarea"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter  Address"
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

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Emergency Contact Name</Form.Label>
                                            <Form.Control
                                                name="emergencyContactName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Emergency Contact Name"
                                                required
                                                value={values.emergencyContactName}
                                            />
                                            {touched.emergencyContactName && errors.emergencyContactName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.emergencyContactName}
                                                </div>
                                            )}
                                        </Form.Group>

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Emergency Mobile Number</Form.Label>
                                            <Form.Control
                                                name="emergencyContactNumber"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Emergency Mobile Number"
                                                required
                                                value={values.emergencyContactNumber}
                                            />
                                            {touched.emergencyContactNumber && errors.emergencyContactNumber && (
                                                <div style={{ color: "red" }}>
                                                    {errors.emergencyContactNumber}
                                                </div>
                                            )}
                                        </Form.Group>



                                    </Row>
                                    <Row className="mb-3"><Form.Group as={Col} md="6" controlId="exampleState">
                                        <Form.Label>Emergency Contact Relation</Form.Label>
                                        <Form.Control
                                            name="emergencyContactRelation"
                                            onChange={handleChange}
                                            onBlur={handleBlur}
                                            type="text"
                                            placeholder="Enter Emergency Contact Relation"
                                            required
                                            value={values.emergencyContactRelation}
                                        />
                                        {touched.emergencyContactRelation && errors.emergencyContactRelation && (
                                            <div style={{ color: "red" }}>
                                                {errors.emergencyContactRelation}
                                            </div>
                                        )}
                                    </Form.Group></Row>

                                    <IconButton
                                        variant="primary"
                                        className="me-2"
                                        type="submit"

                                    >
                                        {updateID ? "Save" : "Submit"}
                                    </IconButton>
                                    <Button
                                        onClick={() => navigate("/master/distributor")}
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

export default DistributorForm;


