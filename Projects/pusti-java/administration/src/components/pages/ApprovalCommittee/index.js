import CustomersTableHeader from "components/app/e-commerce/customers/CustomersTableHeader";
import Flex from "components/common/Flex";
import AdvanceTable from "components/common/advance-table/AdvanceTable";
import AdvanceTablePagination from "components/common/advance-table/AdvanceTablePagination";
import AdvanceTableWrapper from "components/common/advance-table/AdvanceTableWrapper";
import React, { useEffect, useState } from "react";
import {
  Button,
  Card,
  CloseButton,
  Col,
  Dropdown,
  Form,
  Modal,
  Row,
} from "react-bootstrap";
import { useForm } from "react-hook-form";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import { Formik } from "formik";
import ToggleButton from "components/common/Toggle-button/index";

const Divisions = () => {
  const [ApprovalsData, setApprovalsData] = useState([]);
  const [nationalData, setNationalData] = useState([]);
  const [ApprovalsItemData, setApprovalsItemData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  //   const values = [true, 'sm-down', 'md-down', 'lg-down', 'xl-down', 'xxl-down'];
  const [fullscreen, setFullscreen] = useState("xxl-down");
  const [show, setShow] = useState(false);
  const [formData, setData] = useState({});
  const [mediumScreen, setMediumScreen] = useState("xl-down");
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [count, setCount] = useState(0);
  const [employeeData, setEmployeeData] = useState([]);
  const [employeeData2, setEmployeeData2] = useState([]);
  const employee1ChangeHandler = (event) => {
    // debugger;

    const empType = employeeData.find(
      (itme) => itme.employeeId === +event.target.value
    ).employeeType;
    const employee2Arr = employeeData.filter(
      (item) => item.employeeType === empType
    );

    setEmployeeData2(employee2Arr);
  };

  // Load Employee Name
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + "employee/getAllEmployees", {
        headers: authHeader(),
      })
      .then((response) => {
        if (response.data) {
          setEmployeeData(response?.data);
          setEmployeeData2(response?.data);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  const handleShow = () => {
    setShow(true);
  };

  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors },
  } = useForm();

  // Get Single Item
  const handleShowUpdateModal = (committeeId) => {
    if (committeeId) {
      setUpdateId(committeeId);
      setIsLoading(true);
      axios
        .get(
          process.env.REACT_APP_BASE_URL +
            `approvalCommittee/getBy/${committeeId}`,
          { headers: authHeader() }
        )
        .then((response) => {
          if (response.data) {
            setApprovalsItemData(response.data);
            setIsLoading(false);
          }
        })
        .catch((error) => {
          setIsLoading(false);
          console.log(error);
        });
    }
    setShowUpdateModal(true);
  };

  const onSubmit = (data) => {
    const approvalData = {
      name: data.name,
      approvalCommitteeMemberRequests: [
        {
          sequenceNumber: data.sequenceNumber,
          employee: { employeeId: data.employeeId },
        },
      ],
    };

    // Create Approval Committee
    if (approvalData) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL + "approvalCommittee/save-with-member";
      fetch(url, {
        method: "POST",
        headers: authHeader(),
        body: JSON.stringify(approvalData),
      })
        .then((res) => res.json())
        .then((data) => {
          if (data) {
            setIsLoading(false);
            setShow(false);
            reset();
            setCount(count + 1);
            toast.success(data.message);
          }
        })
        .catch((error) => {
          setIsLoading(false);
          reset();
          console.log(error);
        });
    }

    setData(data);
  };

  // Load All National Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "national/getAllNationals";
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        if (response.data) {
          setNationalData(response?.data);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Load Approval Committee Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "approvalCommittee/getAll";
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        if (response.data) {
          setApprovalsData(response?.data);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

  // Update Division Data
  const handleBankSubmit = (values, actions) => {
    const updateapprovalData = {
      name: values.updateApprovalName,
      approvalCommitteeMemberRequests: [
        {
          sequenceNumber: values.updateSequance,
          employee: { employeeId: values.updateEmployee },
        },
      ],
    };

    if (updateapprovalData && updateapprovalData) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL + `approvalCommittee/update/${updateId}`;
      axios
        .put(url, updateapprovalData, { headers: authHeader() })
        .then((response) => {
          if (response.data) {
            setIsLoading(false);
            setShowUpdateModal(false);
            setCount(count + 1);
            toast.success("Update Success");
          }
        })
        .catch((error) => {
          setIsLoading(false);
          console.log(error);
        });
    }
  };

  // Delete Division
  const DeleteBankItem = (committeeId) => {
    if (confirm("Are You Sure ?")) {
      setIsLoading(true);
      axios
        .delete(
          process.env.REACT_APP_BASE_URL +
            `approvalCommittee/delete/${committeeId}`,
          { headers: authHeader() }
        )
        .then((response) => {
          if (response) {
            setIsLoading(false);
            toast.success("Delete Success");
            setCount(count + 1);
          }
        })
        .catch((error) => {
          console.log(error);
          setIsLoading(false);
        });
    }
  };

  if (isLoading) {
    return <h4>Loading...</h4>;
  }

  /* 
  ..........................
  Columns Data here
  .........................
  */

  const columns = [
    {
      accessor: "committeeId",
      Header: "Approval Committee ID",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { committeeId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{committeeId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },

    {
      accessor: "name",
      Header: "Approval Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { name } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{name}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    // {
    //   accessor: 'employeeId',
    //   Header: 'Employee ID',
    //   headerProps: { className: 'pe-1' },
    //   cellProps: {
    //     className: 'py-2'
    //   },
    //   Cell: rowData => {
    //     const { approvalCommitteeMemberRequests } = rowData.row.original;
    //     return (
    //       // <Link to="/e-commerce/customer-details">
    //       <Flex alignItems="center">
    //         <div className="flex-1">
    //           <h5 className="mb-0 fs--1">{approvalCommitteeMemberRequests?.employee?.employeeId}</h5>
    //         </div>
    //       </Flex>
    //       // </Link>
    //     );
    //   }
    // },
    {
      accessor: "status",
      Header: "Status",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { committeeId, status } = rowData.row.original;

        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `approvalCommittee/statusChange/${committeeId}`
                }
              ></ToggleButton>
              {/* <h5 className="mb-0 fs--1">{status === true ? "Active" : "InActive"}</h5> */}
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "action",
      Header: "Action",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { committeeId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                {" "}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => handleShowUpdateModal(committeeId)}
                >
                  Edit
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {" "}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteBankItem(committeeId)}
                >
                  Delete
                </Button>
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        );
      },
    },
  ];

  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={ApprovalsData}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Approval Committee"
              handleShow={handleShow}
              data={ApprovalsData}
              table
            />
          </Card.Header>
          <Card.Body className="p-0">
            <AdvanceTable
              table
              headerClassName="bg-200 text-900 text-nowrap align-middle"
              rowClassName="align-middle white-space-nowrap"
              tableProps={{
                size: "sm",
                striped: true,
                className: "fs--1 mb-0 overflow-hidden",
              }}
            />
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>

      <Modal show={show} fullscreen={fullscreen} onHide={() => setShow(false)}>
        <Modal.Header>
          <Modal.Title>Create Approval Committee</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShow(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Form noValidate onSubmit={handleSubmit(onSubmit)}>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Approval Name</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Approval Name"
                  name="name"
                  isInvalid={!!errors.name}
                  {...register("name", {
                    required: "Approval Name field is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.name && errors.name.message}
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group
                as={Col}
                md="6"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>Employee Type</Form.Label>
                <Form.Select
                  // aria-label="Default select example"
                  // type="text"
                  // name="employeeId"
                  onChange={employee1ChangeHandler}
                  // isInvalid={!!errors.employeeId}
                  // {...register('employeeId', {
                  //   required: 'Region field is required'
                  // })}
                >
                  <option defaultChecked> Employee Type</option>
                  {employeeData?.map((item, i) => (
                    <option key={i} value={item?.employeeId}>
                      {item?.employeeType}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.employeeId && errors.employeeId.message}
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group
                as={Col}
                md="6"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>Employee Name</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="text"
                  name="employeeId"
                  isInvalid={!!errors.employeeId}
                  {...register("employeeId", {
                    required: "Employee field is required",
                  })}
                >
                  <option defaultChecked> Employee Name </option>
                  {employeeData2?.map((item, i) => (
                    <option key={i} value={item?.employeeId}>
                      {`${item?.firstName} ${
                        item?.middleInitial ? item?.middleInitial : ""
                      } ${item?.lastName ? item?.lastName : ""}`}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.employeeId && errors.employeeId.message}
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Sequence Number </Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Sequence Number"
                  name="sequenceNumber"
                  isInvalid={!!errors.sequenceNumber}
                  {...register("sequenceNumber", {
                    required: "Sequence must be string",
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.sequenceNumber && errors.sequenceNumber.message}
                </Form.Control.Feedback>
              </Form.Group>

              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Description </Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  as="textarea"
                  placeholder="Enter Description"
                  name="description"
                  isInvalid={!!errors.description}
                  {...register("description", {
                    required: "Sequence must be string",
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.description && errors.description.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>

            <Button
              variant="primary"
              type="submit"
              disabled={!watch().name || watch().sequenceNumber == "Select "}
            >
              Submit
            </Button>
          </Form>

          {/* <Col md="auto">
              <h5 className="mt-4">Result</h5>
              <pre>{JSON.stringify(formData, null, 2)}</pre>
            </Col> */}
        </Modal.Body>
      </Modal>

      <Modal
        show={showUpdateModal}
        fullscreen={mediumScreen}
        onHide={() => setShow(false)}
      >
        <Modal.Header>
          <Modal.Title>Approval Update</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShowUpdateModal(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Formik
            initialValues={{
              updateApprovalName: ApprovalsItemData?.approvalCommittee?.name,
              updateSequance: ApprovalsItemData?.sequenceNumber,
              updateEmployee: ApprovalsItemData?.employee?.employeeId,
            }}
            // validationSchema={loginSchema}
            onSubmit={handleBankSubmit}
          >
            {({
              errors,
              touched,
              handleSubmit,
              handleChange,
              handleBlur,
              values,
            }) => (
              // <Form onSubmit={handleFormSubmit} >
              <Form onSubmit={handleSubmit}>
                <Row>
                  <Form.Group as={Col} md="6" className="mb-3" controlId="">
                    <Form.Label>Approval Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateApprovalName"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateApprovalName}
                    />

                    <Form.Control.Feedback type="invalid">
                      {/* {errors.bank_name && errors.bank_name.message} */}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group as={Col} md="6" className="mb-3" controlId="">
                    <Form.Label>Sequence Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateSequance"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateSequance}
                    />

                    <Form.Control.Feedback type="invalid">
                      {/* {errors.bank_name && errors.bank_name.message} */}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group as={Col} md="6" className="mb-3" controlId="">
                    <Form.Label>Employee Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Division Name"
                      name="updateEmployee"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateEmployee}
                    />

                    <Form.Control.Feedback type="invalid">
                      {/* {errors.bank_name && errors.bank_name.message} */}
                    </Form.Control.Feedback>
                  </Form.Group>
                </Row>
                <Button variant="primary" type="submit">
                  Submit
                </Button>
              </Form>
            )}
          </Formik>

          {/* <Col md="auto">
          <h5 className="mt-4">Result</h5>
          <pre>{JSON.stringify(formData, null, 2)}</pre>
        </Col> */}
        </Modal.Body>
      </Modal>
    </>
  );
};

export default Divisions;
