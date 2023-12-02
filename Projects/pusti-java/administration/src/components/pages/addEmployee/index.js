import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import { Formik } from "formik";
import React, { useEffect, useState } from "react";
import { Col, Form, InputGroup, Row } from "react-bootstrap";
import { useNavigate, useParams } from "react-router-dom";
import Select from "react-select";
import DatePicker from "react-datepicker";
import axios from "axios";
import { authHeader } from "utils";
import { Button, Card } from 'react-bootstrap';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import Flex from "components/common/Flex";
import { toast } from "react-toastify";
import * as Yup from "yup";
import LoadingIcon from "helpers/LoadingIcon";

const AddEmployee = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [salesOrganization, setSalesOrganization] = useState([]);
    const [designation, setDesignation] = useState([]);
    const [divisions, setDivisions] = useState([]);
    const [regions, setRegions] = useState([]);
    const [zones, setZones] = useState([]);
    const [routes, setRoutes] = useState([]);
    const [districts, setDistricts] = useState([{ id: 1, label: "Bagerhat", value: 1 }]);
    const [upazilas, setUpazilas] = useState([{ id: 1, label: "Sadar Upazila", value: 1 }, { id: 1, label: "Sarankhola", value: 2 }]);
    const [banks, setBanks] = useState([]);
    const [educationList, setEducationList] = useState([]);
    const [employeeInfo, setEmployeeInfo] = useState({});
    const [step, setStep] = useState(1);
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Employee Types
    const employeeTypes = [
        {
            label: 'Divisional Head',
            value: 'DivisionalHead',
        },
        {
            label: 'Regional Head',
            value: 'RegionalHead',
        },
        {
            label: 'Zonal Head',
            value: 'ZonalHead',
        },
        {
            label: 'SalesOfficer',
            value: 'SalesOfficer',
        }
    ]
    // Blood Groups
    const bloodGroups = [
        {
            label: 'A positive (A+)',
            value: 'A+',
        },
        {
            label: 'A negative (A-)',
            value: 'A-',
        },
        {
            label: 'B positive (B+)',
            value: 'B+',
        },
        {
            label: 'B negative (B-)',
            value: 'B-',
        },
        {
            label: 'AB positive (AB+)',
            value: 'AB+',
        },
        {
            label: 'AB negative (AB-)',
            value: 'AB-',
        },
        {
            label: 'O positive (O+)',
            value: 'O+',
        },
        {
            label: 'O negative (O-)',
            value: 'O-',
        }
    ]


    // Degree

    const education_degrees = [
        { "id": 1, "label": "Primary School Certificate (PSC)", "value": "Primary School Certificate (PSC)" },
        { "id": 2, "label": "Junior School Certificate (JSC)", "value": "Junior School Certificate (JSC)" },
        { "id": 3, "label": "Secondary School Certificate (SSC)", "value": "Secondary School Certificate (SSC)" },
        { "id": 4, "label": "Higher Secondary Certificate (HSC)", "value": "Higher Secondary Certificate (HSC)" },
        { "id": 5, "label": "Bachelor of Arts (BA)", "value": "Bachelor of Arts (BA)" },
        { "id": 6, "label": "Bachelor of Science (BSc)", "value": "Bachelor of Science (BSc)" },
        { "id": 7, "label": "Bachelor of Commerce (BCom)", "value": "Bachelor of Commerce (BCom)" },
        { "id": 8, "label": "Bachelor of Business Administration (BBA)", "value": "Bachelor of Business Administration (BBA)" },
        { "id": 9, "label": "Bachelor of Laws (LLB)", "value": "Bachelor of Laws (LLB)" },
        { "id": 10, "label": "Bachelor of Medicine, Bachelor of Surgery (MBBS)", "value": "Bachelor of Medicine, Bachelor of Surgery (MBBS)" },
        { "id": 11, "label": "Bachelor of Dental Surgery (BDS)", "value": "Bachelor of Dental Surgery (BDS)" },
        { "id": 12, "label": "Bachelor of Engineering (BEng)", "value": "Bachelor of Engineering (BEng)" },
        { "id": 13, "label": "Bachelor of Pharmacy (BPharm)", "value": "Bachelor of Pharmacy (BPharm)" },
        { "id": 14, "label": "Bachelor of Fine Arts (BFA)", "value": "Bachelor of Fine Arts (BFA)" },
        { "id": 15, "label": "Bachelor of Social Science (BSS)", "value": "Bachelor of Social Science (BSS)" },
        { "id": 16, "label": "Bachelor of Education (BEd)", "value": "Bachelor of Education (BEd)" },
        { "id": 17, "label": "Bachelor of Computer Science and Engineering (BSc in CSE)", "value": "Bachelor of Computer Science and Engineering (BSc in CSE)" },
        { "id": 18, "label": "Bachelor of Architecture (BArch)", "value": "Bachelor of Architecture (BArch)" },
        { "id": 19, "label": "Master of Arts (MA)", "value": "Bachelor of Architecture (BArch)" },
        { "id": 20, "label": "Master of Science (MSc)", "value": "Master of Science (MSc)" },
        { "id": 21, "label": "Master of Commerce (MCom)", "value": "Master of Commerce (MCom)" },
        { "id": 22, "label": "Master of Business Administration (MBA)", "value": "Master of Business Administration (MBA)" },
        { "id": 23, "label": "Master of Laws (LLM)", "value": "Master of Laws (LLM)" },
        { "id": 24, "label": "Master of Medicine, Master of Surgery (MMBS)", "value": "Master of Medicine, Master of Surgery (MMBS)" },
        { "id": 25, "label": "Master of Dental Surgery (MDS)", "value": "Master of Dental Surgery (MDS)" },
        { "id": 26, "label": "Master of Engineering (MEng)", "value": "Master of Engineering (MEng)" },
        { "id": 27, "label": "Master of Pharmacy (MPharm)", "value": "Master of Pharmacy (MPharm)" },
        { "id": 28, "label": "Master of Fine Arts (MFA)", "value": "Master of Fine Arts (MFA)" },
        { "id": 29, "label": "Master of Social Science (MSS)", "value": "Master of Social Science (MSS)" },
        { "id": 30, "label": "Master of Education (MEd)", "value": "Master of Education (MEd)" },
        { "id": 31, "label": "Master of Computer Science and Engineering (MSc in CSE)", "value": "Master of Computer Science and Engineering (MSc in CSE)" },
        { "id": 32, "label": "Master of Architecture (MArch)", "value": "Master of Architecture (MArch)" },
        { "id": 33, "label": "Doctor of Philosophy (Ph.D.)", "value": "Doctor of Philosophy (Ph.D.)" },
    ]



    //  Date Format
    function formatDateToYYYYMMDD(isoDateString) {
        const date = new Date(isoDateString);

        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0"); // Month is 0-based
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
    }

    // Load create employee field value
    useEffect(() => {
        setIsLoading(true);
        const urls = [
            process.env.REACT_APP_BASE_URL + 'salesOrganization/getAllSalesOrgs',
            process.env.REACT_APP_BASE_URL + 'designation/getAllDesignations',
            process.env.REACT_APP_BASE_URL + 'division/getAllDivisions',
            process.env.REACT_APP_BASE_URL + 'region/getAllRegions',
            process.env.REACT_APP_BASE_URL + 'bank/getAllBanks',
            process.env.REACT_APP_BASE_URL + 'zone/getAllZones',

        ]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                console.log(response)
                setIsLoading(false);

                // salesOrganization
                const modifySalesOrganization = response[0]?.data?.map(item => {
                    return {
                        id: item.salesOrgId,
                        label: item.salesOrgName,
                        value: item.salesOrgId

                    }
                })
                setSalesOrganization(modifySalesOrganization);

                // designation
                const modifyDesignation = response[1]?.data?.map(item => {
                    return {
                        id: item.designationId,
                        label: item.name,
                        value: item.designationId

                    }
                })
                setDesignation(modifyDesignation);

                // Division
                const modifyDivision = response[2]?.data?.map(item => {
                    return {
                        id: item.divisionId,
                        label: item.divisionName,
                        value: item.divisionId

                    }
                })
                setDivisions(modifyDivision);

                // Region
                const modifyRegion = response[3]?.data?.map(item => {
                    return {
                        id: item.regionId,
                        label: item.regionName,
                        value: item.regionId

                    }
                })
                setRegions(modifyRegion);

                // Bank
                const modifyBanks = response[4]?.data?.map(item => {
                    return {
                        id: item.bankId,
                        label: item.bankName,
                        value: item.bankId

                    }
                })
                setBanks(modifyBanks);

                // Zone
                const modifyZone = response[5]?.data?.map(item => {
                    return {
                        id: item.zoneId,
                        label: item.zoneName,
                        value: item.zoneId

                    }
                })
                setZones(modifyZone);

            })
    }, [])

    // load update employee field value
    useEffect(() => {
        setIsLoading(true)
        const urls = [process.env.REACT_APP_BASE_URL + `employee/getEmployeeDetailsById/${updateID}`, process.env.REACT_APP_BASE_URL + `employee/getEmployeeEducationBy/${updateID}`]


        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false)
                setEmployeeInfo(response[0].data);

                console.log(response)

            })
            .catch(error => {
                setIsLoading(false)
                console.log(error)

            })
    }, [])

    console.log('Employee info', employeeInfo)

    // Remove Education List Item
    const removeEducation = (id) => {
        const confirmation = window.confirm("Are Your Sure ?");
        if (confirmation) {
            const filterEducation = educationList.filter(educationItem => educationItem.id !== id);
            setEducationList(filterEducation);
        }
    }


    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }

        setValidated(true);
    };


    const handleProductSubmit = (values, actions) => {

        const employeeInfo = {
            employeeId: values.employeeId,
            employeeType: values.employeeType.value,
            firstName: values.firstName,
            middleInitial: values.middleInitial,
            lastName: values.lastName,
            dateOfBirth: formatDateToYYYYMMDD(values.dateOfBirth),
            bloodGroup: values.bloodGroup.value,
            nidNumber: values.nidNumber,
            mobileNumber: values.mobileNumber,
            routeId: 0,
            email: values.email,
            address: values.address,
            postalCode: values.postalCode,
            upazilaId: values.upazilaId.value,
            districtId: values.districtId.value,
            dateOfJoining: formatDateToYYYYMMDD(values.dateOfJoining),
            dateOfResignation: formatDateToYYYYMMDD(values.dateOfResignation),
            basicSalary: values.basicSalary,
            houseRent: values.houseRent,
            medicalAllowance: values.medicalAllowance,
            internetBill: values.internetBill,
            mobileBill: values.mobileBill,
            travellingDailyAllowance: values.travellingDailyAllowance,
            meetingTravellingAllowance: values.meetingTravellingAllowance,
            meetingDailyAllowance: values.meetingDailyAllowance,
            cityAllowance: values.cityAllowance,
            otherAllowance: values.otherAllowance,
            contributionPercentage: values.contributionPercentage,
            emergencyContactName: values.emergencyContactName,
            emergencyMobileNumber: values.emergencyMobileNumber,
            emergencyContactRelation: values.emergencyContactRelation,
            backAccNumber: values.backAccNumber,
            zone: {
                zoneId: values.zoneId.value
            },
            region: {
                regionId: values.regionId.value
            },
            designation: {
                designationId: values.districtId.value
            },
            division: {

                divisionId: values.divisionId.value

            },
            bank: {

                bankId: values.bankId.value
            },
            salesOrganization: {

                salesOrgId: 1
            },
            educationList: [
                ...educationList?.map(educationItem => {
                    return {
                        degreeId: educationItem.id,
                        passingYear: educationItem.passingYear,
                        result: educationItem.result
                    }
                })
            ]
        }

        // const employeeInfo = {
        //     employeeId: values.employeeId,
        //     employeeType: values.employeeType.value,
        //     firstName: values.firstName,
        //     middleInitial: values.middleInitial,
        //     lastName: values.lastName,
        //     dateOfBirth: formatDateToYYYYMMDD(values.dateOfBirth),
        //     bloodGroup: values.bloodGroup.value,
        //     nidNumber: values.nidNumber,
        //     salesOrganizationId: values.salesOrganizationId.value,
        //     designationId: values.designationId.value,
        //     mobileNumber: values.mobileNumber,
        //     email: values.email,
        //     address: values.address,
        //     divisionId: values.divisionId.value,
        //     regionId: values.regionId.value,
        //     zoneId: values.zoneId.value,
        //     routeId: 0,
        //     postalCode: values.postalCode,
        //     upazilaId: values.upazilaId.value,
        //     districtId: values.districtId.value,
        //     dateOfJoining: formatDateToYYYYMMDD(values.dateOfJoining),
        //     dateOfResignation: formatDateToYYYYMMDD(values.dateOfResignation),
        //     basicSalary: values.basicSalary,
        //     houseRent: values.houseRent,
        //     medicalAllowance: values.medicalAllowance,
        //     internetBill: values.internetBill,
        //     mobileBill: values.mobileBill,
        //     travellingDailyAllowance: values.travellingDailyAllowance,
        //     meetingTravellingAllowance: values.meetingTravellingAllowance,
        //     meetingDailyAllowance: values.meetingDailyAllowance,
        //     cityAllowance: values.cityAllowance,
        //     otherAllowance: values.otherAllowance,
        //     contributionPercentage: values.contributionPercentage,
        //     emergencyContactName: values.emergencyContactName,
        //     emergencyMobileNumber: values.emergencyMobileNumber,
        //     emergencyContactRelation: values.emergencyContactRelation,
        //     bankId: values.bankId.value,
        //     backAccNumber: values.backAccNumber,
        //     educationalQualificationList: [

        //         ...educationList?.map(educationItem => {
        //             return {
        //                 degreeId: educationItem.id,
        //                 passingYear: educationItem.passingYear,
        //                 result: educationItem.result
        //             }
        //         })

        //     ]
        // }

        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'employee/addNewEmployee';
            axios.post(url, employeeInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    navigate("/master/employee")
                    toast.success(response?.data.message);

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)
                    console.log(error)
                })

            console.log("check1")

        }
        else {
            setIsLoading(true);
            const updateUrl = process.env.REACT_APP_BASE_URL + `employee/updateEmployee/${updateID}`;
            axios.put(updateUrl, employeeInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    if (response.data) {
                        navigate("/master/employee")
                        toast.success(response?.data.message);
                    }

                })
                .catch(error => {
                    setIsLoading(false);
                    toast.error(error.message)
                    console.log(error)
                })
            console.log("check2")
        }

    }


    const handleNext = () => {
        setStep(step + 1);
    };

    const handlePrev = () => {
        setStep(step - 1);
    };


    isLoading && <LoadingIcon />


    const productSchema = Yup.object().shape({
        employeeId: Yup.number().required("Employee ID is required"),
        firstName: Yup.string().required("FirstName is required").test('starts with a number', 'File input should not start with a number', value => {
            return !/^\d/.test(value);
        }),
        middleInitial: Yup.string().required("Mid Name is required").test('starts with a number', 'File input should not start with a number', value => {
            return !/^\d/.test(value);
        }),
        lastName: Yup.string().required("Last Name is required").test('starts with a number', 'File input should not start with a number', value => {
            return !/^\d/.test(value);
        }),
        dateOfBirth: Yup.date().required("D ate Of Birth is required"),
        bloodGroup: Yup.object().nullable(),
        nidNumber: Yup.string().nullable(),
        salesOrganizationId: Yup.object().required("SalesOrganization is required"),
        employeeType: Yup.object().required("EmployeeType is required"),
        designationId: Yup.object().required("Designation is required"),
        mobileNumber: Yup.string().required("Mobile Number is required"),
        email: Yup.string().email('Invalid email').required('Required'),
        address: Yup.string().required("Address is required"),
        divisionId: Yup.object().required("Division is required"),
        regionId: Yup.object().required("Region is required"),
        zoneId: Yup.object().required("Zone is required"),
        routeId: Yup.number().nullable(),
        postalCode: Yup.string().nullable(),
        // districtId: Yup.number().required("District is required"),
        // upazilaId: Yup.number().required("Upazila is required"),
        dateOfJoining: Yup.date().required(" Date Of Joining is required"),
        dateOfResignation: Yup.date().required(" Date Of Resignation is required"),
        basicSalary: Yup.number().required("Basic Salary is required"),
        houseRent: Yup.number().required("House Rent is required"),
        medicalAllowance: Yup.number().required("Medical Allowance is required"),
        internetBill: Yup.number().required("Internet Bill is required"),
        mobileBill: Yup.number().required("MobileBill is required"),
        travellingDailyAllowance: Yup.number().required("Traveling Daily Allowance is required"),
        meetingTravellingAllowance: Yup.number().required("meeting Traveling Allowance is required"),
        meetingDailyAllowance: Yup.number().required("Meeting Daily Allowance is required"),
        cityAllowance: Yup.number().required("City Allowance is required"),
        otherAllowance: Yup.number().required("Other Allowance is required"),
        contributionPercentage: Yup.number().required("Contribution Percentage is required"),
        emergencyContactName: Yup.string().nullable(),
        emergencyMobileNumber: Yup.string().nullable(),
        emergencyContactRelation: Yup.string().nullable(),
        bankId: Yup.object().required("Bank is required"),
        backAccNumber: Yup.string().nullable(),
        // result: Yup.string().nullable(),
        // degreeId: Yup.object().nullable(),
        // passingYear: Yup.string().nullable(),

    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Employee" : "Add New Employee"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            employeeId: employeeInfo.employeeId || 0,
                            employeeType: employeeTypes.find(types => types.value == employeeInfo.employeeType) || null,
                            firstName: employeeInfo.firstName || "",
                            middleInitial: employeeInfo.middleInitial || "",
                            lastName: employeeInfo.lastName || "",
                            dateOfBirth: employeeInfo.dateOfBirth || null,
                            bloodGroup: bloodGroups.find(bloodGroup => bloodGroup.value === employeeInfo.bloodGroup) || null,
                            nidNumber: employeeInfo.nidNumber || "",
                            salesOrganizationId: salesOrganization.find(organization => organization.value == employeeInfo?.salesOrganization?.salesOrgId) || null,
                            designationId: designation.find(designationItem => designationItem.id == employeeInfo?.designation?.designationId) || null,
                            mobileNumber: employeeInfo.mobileNumber || "",
                            email: employeeInfo.email || "",
                            address: employeeInfo.address || "",
                            divisionId: divisions.find(division => division.value == employeeInfo?.division?.divisionId) || null,
                            regionId: regions.find(region => region.value == employeeInfo?.region?.regionId) || null,
                            zoneId: zones.find(zone => zone.value == employeeInfo?.zone?.zoneId) || null,
                            routeId: 0,
                            postalCode: employeeInfo.postalCode || "",
                            upazilaId: upazilas.find(upazila => upazila.value == employeeInfo.upazilaId) || 0,
                            districtId: districts.find(district => district.value == employeeInfo.districtId) || 0,
                            dateOfJoining: employeeInfo.dateOfJoining || null,
                            dateOfResignation: employeeInfo.dateOfResignation || null,
                            basicSalary: employeeInfo.basicSalary || 0,
                            houseRent: employeeInfo.houseRent || 0,
                            medicalAllowance: employeeInfo.medicalAllowance || 0,
                            internetBill: employeeInfo.internetBill || 0,
                            mobileBill: employeeInfo.mobileBill || 0,
                            travellingDailyAllowance: employeeInfo.travellingDailyAllowance || 0,
                            meetingTravellingAllowance: employeeInfo.meetingTravellingAllowance || 0,
                            meetingDailyAllowance: employeeInfo.meetingDailyAllowance || 0,
                            cityAllowance: employeeInfo.cityAllowance || 0,
                            otherAllowance: employeeInfo.otherAllowance || 0,
                            contributionPercentage: employeeInfo.contributionPercentage || 0,
                            emergencyContactName: employeeInfo.emergencyContactName || "",
                            emergencyMobileNumber: employeeInfo.emergencyMobileNumber || "",
                            emergencyContactRelation: employeeInfo.emergencyContactRelation || "",
                            bankId: banks.find(bank => bank.value == employeeInfo?.bank?.bankId) || null,
                            backAccNumber: employeeInfo.backAccNumber || '',
                            degreeId: null,
                            passingYear: 0,
                            result: ""

                        }}
                        validationSchema={productSchema}
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
                            resetForm,
                            validateForm,
                            validateField
                        }) => {

                            return (
                                <Form onSubmit={handleSubmit}>
                                    {
                                        step === 1 && <>


                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>First Name*</Form.Label>
                                                    <Form.Control
                                                        name="firstName"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter First Name"
                                                        required
                                                        value={values.firstName}
                                                    />
                                                    {touched.firstName && errors.firstName && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.firstName}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Middle Name*</Form.Label>
                                                    <Form.Control
                                                        name="middleInitial"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Middle Name"
                                                        required
                                                        value={values.middleInitial}
                                                    />
                                                    {touched.middleInitial && errors.middleInitial && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.middleInitial}
                                                        </div>
                                                    )}
                                                </Form.Group>

                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Last Name*</Form.Label>
                                                    <Form.Control
                                                        name="lastName"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Last Name"
                                                        required
                                                        value={values.lastName}
                                                    />
                                                    {touched.lastName && errors.lastName && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.lastName}
                                                        </div>
                                                    )}
                                                </Form.Group>

                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Employee Id*</Form.Label>
                                                    <Form.Control
                                                        name="employeeId"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Employee ID"
                                                        required
                                                        value={values.employeeId}
                                                    />
                                                    {touched.employeeId && errors.employeeId && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.employeeId}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Employee Type*</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={employeeTypes}
                                                        placeholder="Select Employee Type"
                                                        classNamePrefix="react-select"
                                                        name="employeeType"
                                                        value={values.employeeType}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "employeeType",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.employeeType && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.employeeType}
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
                                                    <Form.Label>Date Of Birth*</Form.Label>

                                                    <Form.Control
                                                        // selected={new Date(values.dateOfBirth)}
                                                        // formatWeekDay={(day) => day.slice(0, 3)}
                                                        // dateFormat="MMMM d, yyyy"
                                                        // minDate={new Date()}
                                                        className="form-control"
                                                        placeholderText="Select Date Of Birth"
                                                        type="date"
                                                        name="dateOfBirth"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        value={values.dateOfBirth}
                                                    />


                                                    {touched.dateOfBirth &&
                                                        errors.dateOfBirth && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.dateOfBirth}
                                                            </div>
                                                        )}
                                                </Form.Group>




                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Blood Group</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={bloodGroups}
                                                        placeholder="Select Blood Group"
                                                        classNamePrefix="react-select"
                                                        name="bloodGroup"
                                                        value={values.bloodGroup}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "bloodGroup",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.bloodGroup && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.bloodGroup}
                                                            </div>
                                                        )}
                                                </Form.Group>


                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>NID Number</Form.Label>
                                                    <Form.Control
                                                        name="nidNumber"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter NID Number"
                                                        required
                                                        value={values.nidNumber}
                                                    />
                                                    {touched.nidNumber && errors.nidNumber && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.nidNumber}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Sales Organization*</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={salesOrganization}
                                                        placeholder="Select salesOrganization"
                                                        classNamePrefix="react-select"
                                                        name="salesOrganizationId"
                                                        value={values.salesOrganizationId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "salesOrganizationId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.salesOrganizationId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.salesOrganizationId}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Designation*</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={designation}
                                                        placeholder="Select Designation"
                                                        classNamePrefix="react-select"
                                                        name="designationId"
                                                        value={values.designationId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "designationId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.designationId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.designationId}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Mobile Number*</Form.Label>
                                                    <Form.Control
                                                        name="mobileNumber"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Mobile Number"
                                                        required
                                                        value={values.mobileNumber}
                                                    />
                                                    {touched.mobileNumber && errors.mobileNumber && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.mobileNumber}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row >
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
                                                    {errors.email && touched.email && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.email}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Address*</Form.Label>
                                                    <Form.Control
                                                        name="address"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        as="textarea" rows={3}
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
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Division*</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={divisions}
                                                        placeholder="Select Division"
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
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Region*</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={regions}
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
                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Zone*</Form.Label>
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
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Route</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={routes}
                                                        placeholder="Select Route"
                                                        classNamePrefix="react-select"
                                                        name="routeId"
                                                        value={values.routeId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "routeId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.routeId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.routeId}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>District</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={districts}
                                                        placeholder="Select District"
                                                        classNamePrefix="react-select"
                                                        name="districtId"
                                                        value={values.districtId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "districtId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.districtId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.districtId}
                                                            </div>
                                                        )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Upazila</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={upazilas.filter(item => item.id == values?.districtId?.id)}
                                                        placeholder="Select Upazila"
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
                                                    <Form.Label>Postal Code</Form.Label>
                                                    <Form.Control
                                                        name="postalCode"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Postal Code"
                                                        required
                                                        value={values.postalCode}
                                                    />
                                                    {touched.postalCode && errors.postalCode && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.postalCode}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Basic Salary*</Form.Label>
                                                    <Form.Control
                                                        name="basicSalary"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Basic Salary"
                                                        required
                                                        value={values.basicSalary}
                                                    />
                                                    {touched.basicSalary && errors.basicSalary && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.basicSalary}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>House Rent*</Form.Label>
                                                    <Form.Control
                                                        name="houseRent"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter houseRent"
                                                        required
                                                        value={values.houseRent}
                                                    />
                                                    {touched.houseRent && errors.houseRent && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.houseRent}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Medical Allowance*</Form.Label>
                                                    <Form.Control
                                                        name="medicalAllowance"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Medical Allowance"
                                                        required
                                                        value={values.medicalAllowance}
                                                    />
                                                    {touched.medicalAllowance && errors.medicalAllowance && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.medicalAllowance}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Mobile Bill*</Form.Label>
                                                    <Form.Control
                                                        name="mobileBill"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Mobile Bill"
                                                        required
                                                        value={values.mobileBill}
                                                    />
                                                    {touched.mobileBill && errors.mobileBill && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.mobileBill}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Internet Bill*</Form.Label>
                                                    <Form.Control
                                                        name="internetBill"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Internet Bill"
                                                        required
                                                        value={values.internetBill}
                                                    />
                                                    {touched.internetBill && errors.internetBill && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.internetBill}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Traveling Daily Allowance*</Form.Label>
                                                    <Form.Control
                                                        name="travellingDailyAllowance"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Travelling Daily Allowance"
                                                        required
                                                        value={values.travellingDailyAllowance}
                                                    />
                                                    {touched.travellingDailyAllowance && errors.travellingDailyAllowance && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.travellingDailyAllowance}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Meeting Traveling Allowance*</Form.Label>
                                                    <Form.Control
                                                        name="meetingTravellingAllowance"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Meeting Traveling Allowance"
                                                        required
                                                        value={values.meetingTravellingAllowance}
                                                    />
                                                    {touched.meetingTravellingAllowance && errors.meetingTravellingAllowance && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.meetingTravellingAllowance}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">

                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Meeting Daily Allowance*</Form.Label>
                                                    <Form.Control
                                                        name="meetingDailyAllowance"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Meeting Daily Allowance"
                                                        required
                                                        value={values.meetingDailyAllowance}
                                                    />
                                                    {touched.meetingDailyAllowance && errors.meetingDailyAllowance && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.meetingDailyAllowance}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>City Allowance*</Form.Label>
                                                    <Form.Control
                                                        name="cityAllowance"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter City Allowance"
                                                        required
                                                        value={values.cityAllowance}
                                                    />
                                                    {touched.cityAllowance && errors.cityAllowance && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.cityAllowance}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                            </Row>
                                            <Row className="mb-3">

                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Contribution Percentage*</Form.Label>
                                                    <Form.Control
                                                        name="contributionPercentage"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="number"
                                                        placeholder="Enter Contribution Percentage"
                                                        required
                                                        value={values.contributionPercentage}
                                                    />
                                                    {touched.contributionPercentage && errors.contributionPercentage && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.contributionPercentage}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                                    <Form.Label>Select Bank</Form.Label>
                                                    <Select
                                                        closeMenuOnSelect={true}
                                                        options={banks}
                                                        placeholder="Select Bank"
                                                        classNamePrefix="react-select"
                                                        name="bankId"
                                                        value={values.bankId}
                                                        onChange={(selectedOption) => {
                                                            setFieldValue(
                                                                "bankId",
                                                                selectedOption
                                                            );
                                                        }}
                                                        onBlur={handleBlur}
                                                    />

                                                    {
                                                        errors.bankId && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.bankId}
                                                            </div>
                                                        )}
                                                </Form.Group>

                                            </Row>
                                            <Row className="mb-3">

                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Bank Acc Number</Form.Label>
                                                    <Form.Control
                                                        name="backAccNumber"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Bank Acc Number"
                                                        required
                                                        value={values.backAccNumber}
                                                    />
                                                    {touched.backAccNumber && errors.backAccNumber && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.backAccNumber}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group
                                                    as={Col}
                                                    md="6"
                                                    controlId="exampleFirstName"
                                                >
                                                    <Form.Label>Date Of Joining*</Form.Label>

                                                    <InputGroup>
                                                        <Form.Control
                                                            // selected={new Date(values.dateOfJoining)}
                                                            // formatWeekDay={(day) => day.slice(0, 3)}
                                                            // dateFormat="MMMM d, yyyy"
                                                            // minDate={new Date()}
                                                            className="form-control"
                                                            placeholderText="Select Date Of Joining"
                                                            type="date"
                                                            name="dateOfJoining"
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            value={values.dateOfJoining}
                                                        />
                                                    </InputGroup>


                                                    {touched.dateOfJoining &&
                                                        errors.dateOfJoining && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.dateOfJoining}
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
                                                    <Form.Label>Date Of Resignation*</Form.Label>
                                                    <InputGroup hasValidation>

                                                        <Form.Control
                                                            // selected={new Date(values.dateOfResignation)}
                                                            // formatWeekDay={(day) => day.slice(0, 3)}
                                                            // dateFormat="MMMM d, yyyy"
                                                            // minDate={new Date()}
                                                            className="form-control"
                                                            placeholderText="Select Date Of Resignation"
                                                            type="date"
                                                            name="dateOfResignation"
                                                            onChange={handleChange}
                                                            onBlur={handleBlur}
                                                            value={values.dateOfResignation}
                                                        />
                                                    </InputGroup>

                                                    {touched.dateOfResignation &&
                                                        errors.dateOfResignation && (
                                                            <div style={{ color: "red" }}>
                                                                {errors.dateOfResignation}
                                                            </div>
                                                        )}
                                                </Form.Group>

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

                                            </Row>
                                            <Row className="mb-3">
                                                <Form.Group as={Col} md="6" controlId="exampleState">
                                                    <Form.Label>Emergency Mobile Number</Form.Label>
                                                    <Form.Control
                                                        name="emergencyMobileNumber"
                                                        onChange={handleChange}
                                                        onBlur={handleBlur}
                                                        type="text"
                                                        placeholder="Enter Emergency Mobile Number"
                                                        required
                                                        value={values.emergencyMobileNumber}
                                                    />
                                                    {touched.emergencyMobileNumber && errors.emergencyMobileNumber && (
                                                        <div style={{ color: "red" }}>
                                                            {errors.emergencyMobileNumber}
                                                        </div>
                                                    )}
                                                </Form.Group>
                                                <Form.Group as={Col} md="6" controlId="exampleState">
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
                                                </Form.Group>
                                            </Row>
                                            {console.log('test', values.firstName, values.middleInitial, values.lastName, values.dateOfBirth, values.salesOrganizationId?.value, values.employeeType?.value, values.designationId?.value, values.mobileNumber, values.address, values.divisionId?.value, values.regionId?.value, values.zoneId?.value, values.dateOfJoining, values.dateOfResignation, values.basicSalary, values.medicalAllowance, values.internetBill, values.mobileBill, values.travellingDailyAllowance, values.meetingTravellingAllowance, values.meetingDailyAllowance, values.cityAllowance, values.otherAllowance, values.contributionPercentage)}
                                            <Button variant="primary" onClick={handleNext} disabled={!values.firstName || !values.middleInitial || !values.lastName || !values.dateOfBirth || !values.salesOrganizationId?.value || !values.employeeType?.value || !values.designationId?.value || !values.mobileNumber || !values.address || !values.divisionId?.value || !values.regionId?.value || !values.zoneId?.value || !values.dateOfJoining || !values.dateOfResignation || !values.basicSalary || !values.medicalAllowance || !values.internetBill || !values.mobileBill || !values.travellingDailyAllowance || !values.meetingTravellingAllowance || !values.meetingDailyAllowance || !values.cityAllowance || !values.contributionPercentage}>Next</Button>
                                        </>
                                    }


                                    {
                                        step === 2 && <>

                                            <Row className="mb-3">
                                                <Card >
                                                    <Card.Header as="h6" className="bg-light">
                                                        Educational Qualification :
                                                    </Card.Header>
                                                    <Card.Body>
                                                        {educationList?.map((education, index) => (
                                                            <Row key={index} className="gx-2 flex-between-center mb-3">
                                                                <Col sm={6}>
                                                                    <h6 className="mb-0 text-600">{education.degreeName}</h6>
                                                                </Col>
                                                                <Col sm={3}>
                                                                    <h6 className="mb-0 text-600">{education.passingYear}</h6>
                                                                </Col>
                                                                <Col sm={3}>
                                                                    <Flex justifyContent="between" alignItems="center">
                                                                        <h6 className="mb-0 text-700">{education.result}</h6>
                                                                        <Button
                                                                            variant="link"
                                                                            to="#!"
                                                                            type="button"
                                                                            className="text-danger"
                                                                            size="sm"
                                                                            onClick={() => removeEducation(education.id)}
                                                                        >
                                                                            <FontAwesomeIcon className="fs--1" icon="trash-alt" />
                                                                        </Button>
                                                                    </Flex>
                                                                </Col>
                                                            </Row>
                                                        ))}
                                                        <Row className="gy-3 gx-2">

                                                            <Form.Group as={Col} md="4" controlId="exampleFirstName">
                                                                <Form.Label>Degree</Form.Label>
                                                                <Select
                                                                    closeMenuOnSelect={true}
                                                                    options={education_degrees}
                                                                    placeholder="Select Degree"
                                                                    classNamePrefix="react-select"
                                                                    name="degreeId"
                                                                    value={values.degreeId}
                                                                    onChange={(selectedOption) => {
                                                                        setFieldValue(
                                                                            "degreeId",
                                                                            selectedOption
                                                                        );
                                                                    }}
                                                                    onBlur={handleBlur}
                                                                />

                                                                {
                                                                    errors.degreeId && (
                                                                        <div style={{ color: "red" }}>
                                                                            {errors.degreeId}
                                                                        </div>
                                                                    )}
                                                            </Form.Group>

                                                            <Form.Group
                                                                as={Col}
                                                                md="3"
                                                                controlId="exampleFirstName"
                                                            >
                                                                <Form.Label>Passing Year</Form.Label>
                                                                <InputGroup hasValidation>

                                                                    <DatePicker
                                                                        selected={values.passingYear}
                                                                        showYearPicker
                                                                        dateFormat="yyyy"
                                                                        className="form-control"
                                                                        placeholderText="Select PassingYear"
                                                                        name="passingYear"
                                                                        onChange={(fieldValue) => setFieldValue('passingYear', fieldValue)}
                                                                        onBlur={handleBlur}
                                                                        value={values.passingYear}
                                                                    />
                                                                </InputGroup>

                                                                {touched.passingYear &&
                                                                    errors.passingYear && (
                                                                        <div style={{ color: "red" }}>
                                                                            {errors.passingYear}
                                                                        </div>
                                                                    )}
                                                            </Form.Group>
                                                            <Form.Group as={Col} md="3" controlId="exampleState">
                                                                <Form.Label>Result</Form.Label>
                                                                <Form.Control
                                                                    name="result"
                                                                    onChange={handleChange}
                                                                    onBlur={handleBlur}
                                                                    type="text"
                                                                    placeholder="Enter Result"

                                                                    value={values.result}
                                                                />
                                                                {touched.result && errors.result && (
                                                                    <div style={{ color: "red" }}>
                                                                        {errors.result}
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
                                                                        !values.degreeId || !values.passingYear || !values.result
                                                                    }
                                                                    onClick={() => {

                                                                        if (educationList.find((educationItem) => educationItem.id == values.degreeId.id)) {
                                                                            return toast.error("Already Added !")
                                                                        }
                                                                        else {
                                                                            setEducationList([...educationList, { id: values.degreeId.id, degreeName: values.degreeId.value, passingYear: values.passingYear.getFullYear(), result: values.result }]);
                                                                            setFieldValue('degreeId', 0);
                                                                            setFieldValue('passingYear', '');
                                                                            setFieldValue('result', '');
                                                                        }

                                                                    }}
                                                                >
                                                                    Add
                                                                </Button>
                                                            </Form.Group>

                                                        </Row>
                                                    </Card.Body>
                                                </Card>
                                            </Row>

                                            <Button className="me-3" onClick={handlePrev}>Previous</Button>
                                            <IconButton
                                                variant="primary"
                                                className="ms-auto px-5"
                                                type="submit"
                                                iconAlign="right"
                                                transform="down-1 shrink-4"

                                            >
                                                Submit
                                            </IconButton>
                                        </>
                                    }








                                </Form>
                            );
                        }}
                    </Formik>
                </FalconComponentCard.Body>
            </FalconComponentCard>
        </>
    );
};

export default AddEmployee;


