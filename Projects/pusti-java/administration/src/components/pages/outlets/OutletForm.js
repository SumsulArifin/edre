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
import { PHONE_NUMBER_REGEX } from "utils/validationRegex";

const OutletForm = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [routes, setRoute] = useState([]);
  const [distributors, setDistributors] = useState([]);
  const [outletTypes, setOutletTypes] = useState([]);
  const [outletChanel, setOutletChanel] = useState([]);
  const [outletInformation, setOutletInfo] = useState([]);
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

  const keyOutlets = [
    {
      label: "A - 100K Per Month",
      value: "100K Per Month",
    },
    {
      label: "B - 50K to 99K Per Month",
      value: "50K to 99K Per Month",
    },
    {
      label: "C - 1K to 49K Per Month",
      value: "1K to 49K Per Month",
    },
    {
      label: "N/A",
      value: "N/A",
    },
  ];

  const creditSales = [
    {
      label: "Both",
      value: "Both",
    },
    {
      label: "Cash",
      value: "Cash",
    },
    {
      label: "Credit",
      value: "Credit",
    },
    {
      label: "N/A",
      value: "N/A",
    },
  ];

  const shopTypes = [
    {
      label: "Retail",
      value: "Retail",
    },
    {
      label: "Wholesale",
      value: "Wholesale",
    },
    {
      label: "Others",
      value: "Others",
    },
    {
      label: "All",
      value: "All",
    },
    {
      label: "N/A",
      value: "N/A",
    },
  ];
  const hasPc = [
    {
      label: "Yes",
      value: true,
    },
    {
      label: "No",
      value: false,
    },
  ];

  const displayOutlet = [
    {
      label: "Yes",
      value: true,
    },
    {
      label: "No",
      value: false,
    },
  ];
  const displayOutletAmount = [
    {
      label: "Yes",
      value: true,
    },
    {
      label: "No",
      value: false,
    },
  ];

  // Load Distributor field value
  useEffect(() => {
    setIsLoading(true);
    const urls = [
      process.env.REACT_APP_BASE_URL + "route/getAllRoutes",
      process.env.REACT_APP_BASE_URL + "distributor/getAllDistributors",
      process.env.REACT_APP_BASE_URL + "outletType/getAllOutletTypes",
      process.env.REACT_APP_BASE_URL + "outletChannel/getAllOutletChannels",
    ];

    Promise.all(
      urls?.map((url) => axios.get(url, { headers: authHeader() }))
    ).then((response) => {
      setIsLoading(false);

      // Route
      const modifyRoute = response[0]?.data?.map((item) => {
        return {
          id: item.routeId,
          label: item.routeName,
          value: item.routeId,
        };
      });
      setRoute(modifyRoute);

      // Distributor
      const modifyDistributor = response[1]?.data?.map((item) => {
        return {
          id: item.distributorId,
          label: item.name,
          value: item.distributorId,
        };
      });
      setDistributors(modifyDistributor);

      // Outlets
      const modifyOutletTypes = response[2]?.data?.map((item) => {
        return {
          id: item.id,
          label: item.typeName,
          value: item.id,
        };
      });
      setOutletTypes(modifyOutletTypes);

      // Outlets Chanel
      const modifyOutletChanel = response[3]?.data?.map((item) => {
        return {
          id: item.id,
          label: item.channelName,
          value: item.id,
        };
      });
      setOutletChanel(modifyOutletChanel);
    });
  }, []);

  // load update Outlet field value
  useEffect(() => {
    if (updateID) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL +
        `outlet/getById/${updateID}`;
      axios
        .get(url, { headers: authHeader() })
        .then((response) => {
          setIsLoading(false);
          if (response.status === 200) {
            setOutletInfo(response.data);
          }
        })
        .catch((error) => {
          toast.error(error.message);
          setIsLoading(false);

        });
    }
  }, []);

  const handleOutletSubmit = (values, actions) => {
    const outletInformation = {
      outletName: values.outletName,
      address: values.address,
      contactPerson: values.contactPerson,
      mobile: values.mobile,
      salesPerMonth: values.salesPerMonth,
      keyOutlet: values.keyOutlet.value,
      outletType: values.outletType.value,
      outletChannel: values.outletChannel.value,
      displayOutlet: values.displayOutlet.value,
      paidAmount: values.paidAmount,
      shopType: values.shopType.value,
      comments: values.comments,
      salesGroup: values.salesGroup,
      creditSales: values.creditSales.value,
      route: {
        routeId: values.routeId.value,
      },
      distributor: {
        distributorId: values.distributorId.value,
      },
    };


    if (!updateID) {
      setIsLoading(true);
      const url = process.env.REACT_APP_BASE_URL + "outlet/addNewOutlet";
      axios
        .post(url, outletInformation, { headers: authHeader() })
        .then((response) => {
          console.log(response);
          setIsLoading(false);
          if (response.status === 201) {
            navigate("/master/outlet");
            toast.success("New Outlet Added");
          }
        })
        .catch((error) => {
          console.log(error);
          setIsLoading(false);
          toast.error(`! ${error?.response?.data?.errors[0]} . 
                ${error?.message}
                `);
        });
    } else {
      setIsLoading(true);
      const updateUrl =
        process.env.REACT_APP_BASE_URL +
        `outlet/updateOutlet/${updateID}`;
      axios
        .put(updateUrl, outletInformation, { headers: authHeader() })
        .then((response) => {
          setIsLoading(false);
          if (response.status === 200) {
            navigate("/master/outlet");
            toast.success("Outlet Update Success");
          }
        })
        .catch((error) => {
          setIsLoading(false);
          toast.error(`! ${error?.response?.data?.errors[0]} . 
                ${error?.message}
                `);
        });
    }
  };

  isLoading && <LoadingIcon />;

  const productSchema = Yup.object().shape({
    routeId: Yup.object().required("Route is required"),
    distributorId: Yup.object().required("Distributor is required"),
    outletName: Yup.string()
      .min(2, "Too Short!")
      .max(80, "Too Long!")
      .required("Outlet Name is required")
      .test(
        "starts with a number",
        "Filed input should not start with a number",
        (value) => {
          return !/^\d/.test(value);
        }
      ),
    contactPerson: Yup.string()
      .min(2, "Too Short!")
      .max(50, "Too Long!")
      .required("Contact Person Name is required")
      .test(
        "starts with a number",
        "Filed input should not start with a number",
        (value) => {
          return !/^\d/.test(value);
        }
      ),
    mobile: Yup.string()
      .required("Phone Number is Required")
      .test("Phone Number is Valid", "Phone Number is InValid", (value) => {
        return PHONE_NUMBER_REGEX.test(value);
      }),
    salesPerMonth: Yup.number().min(1, 'Value must be greater than 0').required("Sales PerMonth is Required"),
    keyOutlet: Yup.object().required("Key Outlet is Required"),
    salesGroup: Yup.string().test(
      "starts with a number",
      "Filed input should not start with a number",
      (value) => {
        return !/^\d/.test(value);
      }
    ).required("Sales Group is Required"),
    address: Yup.string().min(5, "Too Short!").required("Address is required"),
    outletType: Yup.object().required("Outlet Type is Required"),
    outletChannel: Yup.object().required("Outlet Channel is Required"),
    displayOutlet: Yup.object().required("Display Outlet is Required"),
    paidAmount: Yup.number().min(1, 'Value must be greater than 0').required("Paid Amount is Required"),
    creditSales: Yup.object().required(
      "Credit Sales is Required"
    ),
    displayOutletAmount: Yup.object().required(
      "Display Outlet Amount is Required"
    ),
    shopType: Yup.object().required(
      "ShopType is Required"
    )
  });

  return (
    <>
      <PageHeader
        title={updateID ? "Update Outlet" : "Add New Outlet"}
        className="mb-3"
      ></PageHeader>
      <FalconComponentCard>
        <FalconComponentCard.Header light={false} noPreview />
        <FalconComponentCard.Body>
          <Formik
            initialValues={{
              routeId:
                routes.find(
                  (route) => route.value === outletInformation?.route?.routeId
                ) || null,
              distributorId:
                distributors.find(
                  (distributor) =>
                    distributor.value ===
                    outletInformation?.distributor?.distributorId
                ) || null,
              outletName: outletInformation.outletName || "",
              contactPerson: outletInformation.contactPerson || null,
              mobile: outletInformation.mobile || "",
              salesPerMonth: outletInformation.salesPerMonth || 0,
              salesGroup: outletInformation.salesGroup || "",
              address: outletInformation.address || "",
              keyOutlet:
                keyOutlets.find(
                  (keyOutlet) => keyOutlet.value === outletInformation.keyOutlet
                ) || null,
              outletType:
                outletTypes.find(
                  (outletTypes) =>
                    outletTypes.value === outletInformation.outletType
                ) || null,
              outletChannel:
                outletChanel.find(
                  (outletChanel) =>
                    outletChanel.value === outletInformation.outletChannel
                ) || null,

              displayOutlet:
                displayOutlet.find(
                  (item) => item.value === outletInformation.displayOutlet
                ) || null,
              displayOutletAmount:
                displayOutletAmount.find(
                  (item) => item.value === outletInformation.displayOutletAmount
                ) || null,
              paidAmount: outletInformation.paidAmount || 0,
              creditSales: creditSales.find(item => item.value == outletInformation.creditSales) || "",
              shopType:
                shopTypes.find(
                  (item) => item.value === outletInformation.shopType
                ) || null,
              comments: outletInformation.comments || "",
            }}
            validationSchema={productSchema}
            onSubmit={handleOutletSubmit}
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
            }) => {
              console.log("test", errors)
              return (
                <Form onSubmit={handleSubmit}>
                  <Row className="mb-3">
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
                          setFieldValue("routeId", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.routeId && (
                        <div style={{ color: "red" }}>{errors.routeId}</div>
                      )}
                    </Form.Group>
                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                      <Form.Label>Distributor</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={distributors}
                        placeholder="Select Distributor"
                        classNamePrefix="react-select"
                        name="distributorId"
                        value={values.distributorId}
                        onChange={(selectedOption) => {
                          setFieldValue("distributorId", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.distributorId && (
                        <div style={{ color: "red" }}>
                          {errors.distributorId}
                        </div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">
                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Outlet Name</Form.Label>
                      <Form.Control
                        name="outletName"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        type="text"
                        placeholder="Enter Outlet Name"
                        required
                        value={values.outletName}
                      />
                      {touched.outletName && errors.outletName && (
                        <div style={{ color: "red" }}>{errors.outletName}</div>
                      )}
                    </Form.Group>
                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Contact Person</Form.Label>
                      <Form.Control
                        name="contactPerson"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        type="text"
                        placeholder="Enter Contact Person"
                        required
                        value={values.contactPerson}
                      />
                      {touched.contactPerson && errors.contactPerson && (
                        <div style={{ color: "red" }}>
                          {errors.contactPerson}
                        </div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">

                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Mobile</Form.Label>
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
                        <div style={{ color: "red" }}>{errors.mobile}</div>
                      )}
                    </Form.Group>

                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Sales PerMonth</Form.Label>
                      <Form.Control
                        name="salesPerMonth"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        type="number"
                        placeholder="Enter Sales PerMonth"
                        required
                        value={values.salesPerMonth}
                      />
                      {touched.salesPerMonth && errors.salesPerMonth && (
                        <div style={{ color: "red" }}>
                          {errors.salesPerMonth}
                        </div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">

                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                      <Form.Label>Key Outlet</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={keyOutlets}
                        placeholder="Select Key Outlet"
                        classNamePrefix="react-select"
                        name="keyOutlet"
                        value={values.keyOutlet}
                        onChange={(selectedOption) => {
                          setFieldValue("keyOutlet", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.keyOutlet && (
                        <div style={{ color: "red" }}>{errors.keyOutlet}</div>
                      )}
                    </Form.Group>

                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Sales Group</Form.Label>
                      <Form.Control
                        name="salesGroup"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        type="text"
                        placeholder="Enter Sales Group"
                        required
                        value={values.salesGroup}
                      />
                      {touched.salesGroup && errors.salesGroup && (
                        <div style={{ color: "red" }}>{errors.salesGroup}</div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">
                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                      <Form.Label>Outlet Type</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={outletTypes}
                        placeholder="Select Outlet Type"
                        classNamePrefix="react-select"
                        name="outletType"
                        value={values.outletType}
                        onChange={(selectedOption) => {
                          setFieldValue("outletType", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.outletType && (
                        <div style={{ color: "red" }}>{errors.outletType}</div>
                      )}
                    </Form.Group>
                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                      <Form.Label>Outlet Channel</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={outletChanel}
                        placeholder="Select Outlet Chanel"
                        classNamePrefix="react-select"
                        name="outletChannel"
                        value={values.outletChannel}
                        onChange={(selectedOption) => {
                          setFieldValue("outletChannel", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.outletChannel && (
                        <div style={{ color: "red" }}>
                          {errors.outletChannel}
                        </div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">
                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                      <Form.Label>Display Outlet</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={displayOutlet}
                        placeholder="Select Display Outlet"
                        classNamePrefix="react-select"
                        name="displayOutlet"
                        value={values.displayOutlet}
                        onChange={(selectedOption) => {
                          setFieldValue("displayOutlet", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.displayOutlet && (
                        <div style={{ color: "red" }}>
                          {errors.displayOutlet}
                        </div>
                      )}
                    </Form.Group>
                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Paid Amount</Form.Label>
                      <Form.Control
                        name="paidAmount"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        type="number"
                        placeholder="Enter Paid Amount"
                        required
                        value={values.paidAmount}
                      />
                      {touched.paidAmount && errors.paidAmount && (
                        <div style={{ color: "red" }}>{errors.paidAmount}</div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">
                    <Form.Group as={Col} md="6" controlId="exampleFirstName">
                      <Form.Label>Display Outlet Amount</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={displayOutletAmount}
                        placeholder="Select Display Outlet Amount"
                        classNamePrefix="react-select"
                        name="displayOutletAmount"
                        value={values.displayOutletAmount}
                        onChange={(selectedOption) => {
                          setFieldValue("displayOutletAmount", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.displayOutletAmount && (
                        <div style={{ color: "red" }}>
                          {errors.displayOutletAmount}
                        </div>
                      )}
                    </Form.Group>
                    <Form.Group
                      as={Col}
                      md="6"
                      className="mb-3"
                      controlId="formHookname"
                    >
                      <Form.Label>Credit Sales</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={creditSales}
                        placeholder="Select Credit Sales"
                        classNamePrefix="react-select"
                        name="creditSales"
                        value={values.creditSales}
                        onChange={(selectedOption) => {
                          setFieldValue("creditSales", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.creditSales && (
                        <div style={{ color: "red" }}>{errors.creditSales}</div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">
                    <Form.Group
                      as={Col}
                      md="6"
                      className="mb-3"
                      controlId="formHookname"
                    >
                      <Form.Label>Shope Type</Form.Label>
                      <Select
                        closeMenuOnSelect={true}
                        options={shopTypes}
                        placeholder="Select Shope Type"
                        classNamePrefix="react-select"
                        name="shopType"
                        value={values.shopType}
                        onChange={(selectedOption) => {
                          setFieldValue("shopType", selectedOption);
                        }}
                        onBlur={handleBlur}
                      />

                      {errors.shopType && (
                        <div style={{ color: "red" }}>{errors.shopType}</div>
                      )}
                    </Form.Group>
                  </Row>
                  <Row className="mb-3">
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
                        <div style={{ color: "red" }}>{errors.address}</div>
                      )}
                    </Form.Group>
                    <Form.Group as={Col} md="6" controlId="exampleState">
                      <Form.Label>Comments</Form.Label>
                      <Form.Control
                        name="comments"
                        as="textarea"
                        onChange={handleChange}
                        onBlur={handleBlur}
                        type="text"
                        placeholder="Enter  Comments"
                        required
                        value={values.comments}
                      />
                      {touched.comments && errors.comments && (
                        <div style={{ color: "red" }}>{errors.comments}</div>
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
                    onClick={() => navigate("/master/outlet")}
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

export default OutletForm;
