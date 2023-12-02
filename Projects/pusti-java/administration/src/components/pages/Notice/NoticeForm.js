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

const NoticeForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [categoryTypeInformation, setCategoryTypeInformation] = useState({});
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


    // load update Category Type field value
    useEffect(() => {
        setIsLoading(true)
        const urls = [process.env.REACT_APP_BASE_URL + `categoryType/getCategoryTypeById/${updateID}`]

        Promise.all(urls?.map(url => axios.get(url, { headers: authHeader() })))
            .then(response => {
                setIsLoading(false)
                setCategoryTypeInformation(response[0].data);
            })
            .catch(error => {
                setIsLoading(false)
                console.log(error)

            })
    }, [])



    const noticeList = [
        {
            label: "Text",
            value: "Text"
        },
        {
            label: "JPG",
            value: "JPG"
        },
        {
            label: "PDF",
            value: "PDF"
        },

    ]


    const handleSubmit = (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }

        setValidated(true);
    };





    const handleSubmitForm = (values, actions) => {


        // const csvFile = acceptedFiles[0];
        // const formData = new FormData();
        // formData.append("file", csvFile);

        const categoryTypeInfo = {

            noticeType: values.noticeType.value,
            noticeTitle: values.noticeTitle,
            noticeDetails: values.noticeDetails,
            effectiveDate: formatDateToYYYYMMDD(values.effectiveDate),
            expireDate: formatDateToYYYYMMDD(values.expireDate),
            uploadedFile: values.uploadedFile
        }


        if (!updateID) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'notices/addNotice';
            axios.post(url, categoryTypeInfo, { headers: authHeader() })
                .then(response => {
                    setIsLoading(false);
                    // navigate("/master/categoryType")
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
            const updateUrl = process.env.REACT_APP_BASE_URL + `categoryType/updateCategoryType/${updateID}`;
            axios.put(updateUrl, categoryTypeInfo, { headers: authHeader() })
                .then(response => {
                    console.log('check'.response)
                    setIsLoading(false);
                    if (response.data) {
                        navigate("/master/categoryType")
                        toast.success(response?.data.message || 'Category Type Update Success');
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
        noticeType: Yup.object().required("Notice Type is required"),
        noticeTitle: Yup.string().min(2, 'Too Short!')
            .max(100, 'Too Long!').required("Title Name is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            }),
        effectiveDate: Yup.date().required("Effective Date is required"),
        expireDate: Yup.date().required("ExpiryDate is required"),
        noticeDetails: Yup.string().min(5, 'Too Short!')
            .required("Description is required").test('starts with a number', 'Filed input should not start with a number', value => {
                return !/^\d/.test(value);
            }),


    });


    return (
        <>
            <PageHeader
                title={updateID ? "Update Office Notice" : "Add Office Notice"}
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <Formik
                        initialValues={{

                            // title: categoryTypeInformation?.name || '',
                            // effectiveDate: categoryTypeInformation?.description || '',
                            // expiryDate: categoryTypeInformation?.description || '',
                            // attachment: categoryTypeInformation?.description || '',
                            // description: categoryTypeInformation?.description || ''


                        }}
                        validationSchema={productSchema}
                        onSubmit={handleSubmitForm}
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

                           console.log(errors)
                            return (
                                <Form onSubmit={handleSubmit}>

                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleFirstName">
                                            <Form.Label>Notice Type</Form.Label>
                                            <Select
                                                placeholder="Select Notice Type"
                                                closeMenuOnSelect={true}
                                                options={noticeList}
                                                classNamePrefix="react-select"
                                                name="noticeType"
                                                value={values.noticeType}
                                                onChange={(selectedOption) => {
                                                    setFieldValue(
                                                        "noticeType",
                                                        selectedOption
                                                    );
                                                }}
                                                onBlur={handleBlur}
                                            />

                                            {
                                                errors.noticeType && (
                                                    <div style={{ color: "red" }}>
                                                        {errors.noticeType}
                                                    </div>
                                                )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Title</Form.Label>
                                            <Form.Control
                                                name="title"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Title"
                                                required
                                                value={values.noticeTitle}
                                            />
                                            {touched.noticeTitle && errors.noticeTitle && (
                                                <div style={{ color: "red" }}>
                                                    {errors.noticeTitle}
                                                </div>
                                            )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Effective date</Form.Label>
                                            <Form.Control
                                                name="effectiveDate"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="date"
                                                placeholder="Enter Effective Date"
                                                required
                                                value={values.effectiveDate}
                                            />
                                            {touched.effectiveDate && errors.effectiveDate && (
                                                <div style={{ color: "red" }}>
                                                    {errors.effectiveDate}
                                                </div>
                                            )}
                                        </Form.Group>
                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Expiry date</Form.Label>
                                            <Form.Control
                                                name="expireDate"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="date"
                                                placeholder="Enter Expiry date"
                                                required
                                                value={values.expireDate}
                                            />
                                            {touched.expireDate && errors.expireDate && (
                                                <div style={{ color: "red" }}>
                                                    {errors.expireDate}
                                                </div>
                                            )}
                                        </Form.Group>
                                    </Row>
                                    <Row className="mb-3">

                                        <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>Description</Form.Label>
                                            <Form.Control
                                                name="noticeDetails"
                                                as="textarea" rows={3}
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="text"
                                                placeholder="Enter Description"
                                                required
                                                value={values.noticeDetails}
                                            />
                                            {touched.noticeDetails && errors.noticeDetails && (
                                                <div style={{ color: "red" }}>
                                                    {errors.noticeDetails}
                                                </div>
                                            )}
                                        </Form.Group>
                                        {values.noticeType?.value !== "Text" && <Form.Group as={Col} md="6" controlId="exampleState">
                                            <Form.Label>File Attached</Form.Label>
                                            <Form.Control
                                                name="uploadedFile"
                                                onChange={handleChange}
                                                onBlur={handleBlur}
                                                type="file"
                                                placeholder="select File"
                                                required
                                                value={values.uploadedFile}
                                            />
                                            {touched.attachment && errors.attachment && (
                                                <div style={{ color: "red" }}>
                                                    {errors.attachment}
                                                </div>
                                            )}
                                        </Form.Group>}
                                    </Row>



                                    <IconButton
                                        variant="primary"
                                        className="me-2"
                                        type="submit"

                                    >
                                        {updateID ? "Save" : "Submit"}
                                    </IconButton>
                                    <Button
                                        onClick={() => navigate("/master/officenotice")}
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

export default NoticeForm;


