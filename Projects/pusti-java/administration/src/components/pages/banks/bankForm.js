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
import { PHONE_NUMBER_REGEX } from "utils/validationRegex";

const BankForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [distributorList, setDistributorList] = useState([]);
    const [bankByID, setBankByID] = useState({});
    const { updateID } = useParams();
    const navigate = useNavigate();


    // Bank Get By ID
    useEffect(() => {
        if (updateID) {
            setIsLoading(true)
            const url = process.env.REACT_APP_BASE_URL + `bank/getBankById/${updateID}`
            axios.get(url, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        setBankByID(response.data);
                    }
                    setIsLoading(false)
                })
                .catch(error => {
                    toast.error(error.message);
                    setIsLoading(false);


                })
        }
    }, [])


    // load Distributor Data
    useEffect(() => {
        setIsLoading(true)
        const url = process.env.REACT_APP_BASE_URL + "distributor/getAllDistributors";
        axios.get(url, { headers: authHeader() })
            .then(response => {
                if (response.status === 200) {
                    const modifyDistributor = response?.data?.map(distributor => {
                        return {
                            id: distributor.distributorId,
                            label: distributor.name,
                            value: distributor.distributorId

                        }
                    })

                    setDistributorList(modifyDistributor);
                    setIsLoading(false)
                }
            })
            .catch(error => {
                toast.error(error.message);
                setIsLoading(false)
            })
    }, [])


    const handleFormSubmit = (values, actions) => {

        const bankInfo = {
            bankName: values.bankName,
            accountant: values.accountant,
            contactNumber: values.contactNumber,
            bankAddress: values.bankAddress,
            distributor: {
                distributorId: values.distributorId.value
            }

        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'bank/addNewBank';
            axios.post(url, bankInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 201) {
                        navigate("/master/bank")
                        toast.success("New Bank Added Success");
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `bank/updateBank/${updateID}`;
            axios.put(updateUrl, bankInfo, { headers: authHeader() })
                .then(response => {
                    if (response.status === 200) {
                        navigate("/master/bank");
                        toast.success("Bank Update Success");
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
        distributorId: Yup.object().required("Distributor is required"),
        bankName: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Bank Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            }),
        accountant: Yup.string().min(2, 'Too Short!')
            .max(50, 'Too Long!').required("Accountant Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            }),
        contactNumber: Yup.string().required("Contact Number is required").test('Phone Number Valid', 'Phone Number is Invalid', value => {
            return PHONE_NUMBER_REGEX.test(value);
        }),
        bankAddress: Yup.string().min(5, 'Too Short!')
            .required("Bank Address is required")

    });




    return (
        <>
            <PageHeader
                title={updateID ? "Update Bank" : "Add Bank"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{
                            distributorId: distributorList.find(item => item.id == bankByID?.distributor?.distributorId) || null,
                            bankName: bankByID?.bankName || '',
                            accountant: bankByID?.accountant || "",
                            contactNumber: bankByID?.contactNumber || " ",
                            bankAddress: bankByID?.bankAddress || ""

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
                                            <Form.Label>Select Distributor</Form.Label>
                                            <Select
                                                closeMenuOnSelect={true}
                                                options={distributorList}
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

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Bank Name</Form.Label>
                                            <Form.Control
                                                name="bankName"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Bank Name"
                                                required
                                                value={values.bankName}
                                            />
                                            {touched.bankName && errors.bankName && (
                                                <div style={{ color: "red" }}>
                                                    {errors.bankName}
                                                </div>
                                            )}
                                        </Form.Group>

                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Accountant</Form.Label>
                                            <Form.Control
                                                name="accountant"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Accountant Name"
                                                required
                                                value={values.accountant}
                                            />
                                            {touched.accountant && errors.accountant && (
                                                <div style={{ color: "red" }}>
                                                    {errors.accountant}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Contact Number</Form.Label>
                                            <Form.Control
                                                name="contactNumber"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Contact Number"
                                                required
                                                value={values.contactNumber}
                                            />
                                            {touched.contactNumber && errors.contactNumber && (
                                                <div style={{ color: "red" }}>
                                                    {errors.contactNumber}
                                                </div>
                                            )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Address</Form.Label>
                                            <Form.Control
                                                as="textarea"
                                                name="bankAddress"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Address"
                                                required
                                                value={values.bankAddress}
                                            />
                                            {touched.bankAddress && errors.bankAddress && (
                                                <div style={{ color: "red" }}>
                                                    {errors.bankAddress}
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
                                        onClick={() => navigate("/master/bank")}
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

export default BankForm;


