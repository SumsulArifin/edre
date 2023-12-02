import axios from 'axios';
import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useEffect, useState } from 'react';
import {
  Button,
  Card,
  CloseButton,
  Col,
  Dropdown,
  Form,
  Modal,
  Row
} from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import { toast } from 'react-toastify';
import { authHeader } from 'utils';

const EmployeeManagement = () => {
  const [userStatus, setUserStatus] = useState(true);
  const [selectedPrivileges, setSelectedPrivileges] = useState([]);
  const [userData, setUserdata] = useState([]);
  const [nationalData, setNationalData] = useState([]);
  const [divisionData, setDivisionData] = useState([]);
  const [registerData,setRegisterData]=useState([]); 
  const [employeeData, setEmployeeData] = useState([]);
  const [employeeData2, setEmployeeData2] = useState([]);
  const [zoneItemData, setZoneItemData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  //   const values = [true, 'sm-down', 'md-down', 'lg-down', 'xl-down', 'xxl-down'];
  const [fullscreen, setFullscreen] = useState('xl-down');
  const [show, setShow] = useState(false);
  const [formData, setData] = useState({});
  const [mediumScreen, setMediumScreen] = useState('xl-down');
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [count, setCount] = useState(0);

  const handleUserStatusChange = (e) => {
    setUserStatus(e.target.checked);
  };

  const handleCheckboxChange = (e) => {
    const { name, checked } = e.target;

    // If the checkbox is checked, add the privilege to the selectedPrivileges array
    if (checked) {
      setSelectedPrivileges((prevSelectedPrivileges) => [
        ...prevSelectedPrivileges,
        name,
      ]);
    } else {
      // If the checkbox is unchecked, remove the privilege from the selectedPrivileges array
      setSelectedPrivileges((prevSelectedPrivileges) =>
        prevSelectedPrivileges.filter((privilege) => privilege !== name)
      );
    }
  };

  console.log("userData", userData);

  const handleShow = () => {
    setShow(true);
    setSelectedPrivileges([]);
  };

  const employee1ChangeHandler=(event)=> {
    // debugger;

    const empType= employeeData.find(itme=> itme.employeeId===+event.target.value).employeeType;
    const employee2Arr= employeeData.filter(item=> item.employeeType=== empType);

    setEmployeeData2(employee2Arr);
  }

  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors }
  } = useForm();

  // Get Single Item
  const handleShowUpdateModal = id => {
    if (id) {
      setUpdateId(id);
      setIsLoading(true);
      axios
        .get(process.env.REACT_APP_BASE_URL + `zone/getById/${id}`, {
          headers: authHeader()
        })
        .then(response => {
          if (response.data) {
            setZoneItemData(response.data);
            setIsLoading(false);
          }
        })
        .catch(error => {
          setIsLoading(false);
          console.log(error);
        });
    }
    setShowUpdateModal(true);
  };

  const onSubmit = data => {
    const userData = {
      employeeId: data.employeeId,
      userName: data.userName,
      role: data.role,
      password: data.password,
      assignedPrivileges: selectedPrivileges.join(','),
      userStatus:userStatus,
     
    };

    // Create user
    if (userData) {
      setIsLoading(true);
      fetch(process.env.REACT_APP_BASE_URL + 'api/auth/register', {
        method: 'POST',
        headers: authHeader(),
        body: JSON.stringify(userData)
      })
        .then(res => res.json())
        .then(data => {
          if (data) {
            setIsLoading(false);
            setShow(false);
            reset();
            selectedPrivileges([]);
            setCount(count + 1);
            toast.success(data.message);
          }
        })
        .catch(error => {
          setIsLoading(false);
          reset();
          console.log(error);
        });
    }

    setData(data);
  };

  // Load National Name
  // useEffect(() => {
  //   setIsLoading(true);
  //   axios
  //     .get(process.env.REACT_APP_BASE_URL + 'national/getAllNationals', {
  //       headers: authHeader()
  //     })
  //     .then(response => {
  //       if (response.data) {
  //         setNationalData(response?.data);
  //         setIsLoading(false);
  //       }
  //     })
  //     .catch(error => {
  //       setIsLoading(false);
  //       console.log(error);
  //     });
  // }, []);

  // Load division Name
  // useEffect(() => {
  //   setIsLoading(true);
  //   axios
  //     .get(process.env.REACT_APP_BASE_URL + 'division/getAllDivisions', {
  //       headers: authHeader()
  //     })
  //     .then(response => {
  //       if (response.data) {
  //         setDivisionData(response?.data);
  //         setIsLoading(false);
  //       }
  //     })
  //     .catch(error => {
  //       setIsLoading(false);
  //       console.log(error);
  //     });
  // }, []);

  // Load Employee Name
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + 'employee/getAllEmployees', {
        headers: authHeader()
      })
      .then(response => {
        if (response.data) {
          setEmployeeData(response?.data);
          setEmployeeData2(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

// Register data load 

// useEffect(()=>{
//   setIsLoading(true);
//   axios
//   .get(process.env.REACT_APP_BASE_URL+'api/auth/register',{
//     headers:authHeader()
//   })
//   .then(response=>{
//     if(response.data){
//       setRegisterData(response?.data);
//       setIsLoading(false);
//     }
//   })
//   .catch(error=>{
//     setIsLoading(false);
//     console.log(error);
//   })
// })




  // Load Usrer Data
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + 'api/auth/getAllUsers', {
        headers: authHeader()
      })
      .then(response => {
        if (response.data) {
          debugger;
          console.log("data",response.data);
          setUserdata(response.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

  // Update zone Item
  const handleBankSubmit = (values, actions) => {
    const zoneData = {
      zoneName: values.updateZoneName,
      region: {
        regionId: values.updateRegionId,
        division: {
          // divisionId: values.updateDivisionId, national: {
          //   nationalId: values.updateNationalId
          // }
        }
      }
    };

    if (zoneData && updateId) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL + `zone/updateZone/${updateId}`;
      axios
        .put(url, zoneData, { headers: authHeader() })
        .then(response => {
          if (response.data) {
            setIsLoading(false);
            setShowUpdateModal(false);
            setCount(count + 1);
            toast.success('Update Success');
          }
        })
        .catch(error => {
          setIsLoading(false);
          console.log(error);
        });
    }
  };

  // Delete zone Item
  const DeleteBankItem = id => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(process.env.REACT_APP_BASE_URL + `api/auth/delete/${id}`, {
          headers: authHeader()
        })
        .then(response => {
          if (response) {
            setIsLoading(false);
            toast.success('Delete Success');
            setCount(count + 1);
          }
        })
        .catch(error => {
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
        accessor: 'id',
        Header: 'SL',
        headerProps: { className: 'pe-1' },
        cellProps: {
          className: 'py-2'
        },
        Cell: rowData => {
          const { id } = rowData.row.original;
          return (
            // <Link to="/e-commerce/customer-details">
            <Flex alignItems="center">
              <div className="flex-1">
                <h5 className="mb-0 fs--1">{id}</h5>
              </div>
            </Flex>
            // </Link>
          );
        }
      },
    {
      accessor: 'username',
      Header: 'User Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { username } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">
                {username}
              </h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },

    {
      accessor: 'assignedPrivilege',
      Header: 'Assigned Privilege',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { assignedPrivilege } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{assignedPrivilege}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'role',
      Header: 'User Type',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { role } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{role}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
  
    {
      accessor: 'action',
      Header: 'Action',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { id } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => handleShowUpdateModal(id)}
                >
                  Edit
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteBankItem(id)}
                >
                  Delete
                </Button>
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        );
      }
    }
  ];

  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={userData}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="User Management" handleShow={handleShow} table />
          </Card.Header>
          <Card.Body className="p-0">
            <AdvanceTable
              table
              headerClassName="bg-200 text-900 text-nowrap align-middle"
              rowClassName="align-middle white-space-nowrap"
              tableProps={{
                size: 'sm',
                striped: true,
                className: 'fs--1 mb-0 overflow-hidden'
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
          <Modal.Title>User Management</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShow(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Form noValidate onSubmit={handleSubmit(onSubmit)}>
            <Row>
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
                  <option defaultChecked > Employee Type</option>
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
                  {...register('employeeId', {
                    required: 'Employee field is required'
                  })}
                >
                  <option defaultChecked > Employee Name </option>
                  {employeeData2?.map((item, i) => (
                    <option key={i} value={item?.employeeId}>
                      {item?.firstName}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.employeeId && errors.employeeId.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>User Name</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter User Name"
                  name="userName"
                  isInvalid={!!errors.userName}
                  {...register('userName', {
                    required: 'userName  field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.userName && errors.userName.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group
                as={Col}
                md="6"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>User Type</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="text"
                  name="role"
                  isInvalid={!!errors.role}
                  {...register('role', {
                    required: 'Rolw field is required'
                  })}
                >
                  <option defaultChecked >Select User Type</option>
                  <option> Super Admin </option>
                  <option> Admin</option>
                  <option>User </option>
                  {/* <option>Sales User</option>
                  <option>Dealer Order maintain</option>
                  <option>ACCT User </option>
                  <option>Super ACCT User </option> */}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.role && errors.role.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="password"
                  // placeholder="Enter User Name"
                  name="password"
                  isInvalid={!!errors.password}
                  {...register('password', {
                    required: 'password  field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.password && errors.password.message}
                </Form.Control.Feedback>
              </Form.Group>
              <label style={{margin:"15px 0px"}}>
          User Status:
          {/* <input
            type="checkbox"
            checked={''}
            onChange={handleUserStatusChange}
          /> */}
        </label>
            </Row>
<Row>
<div>
<label style={{margin:"10px 15px"}}>
        <input
          type="checkbox"
          name="Permit_Master"
          defaultChecked={false}
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_Master')}
        />
        Master settings 
      </label>
      <label style={{margin:"0px 23px"}}>
        <input
          type="checkbox"
          defaultChecked={false}
          name="Permit_Product_Master"
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_Product_Master')}
        />
        Product management
        </label>
        <label style={{margin:"10px 15px"}}>
        <input
          type="checkbox"
          defaultChecked={false}
          name="Permit_product_view"
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_product_view')}
        />
        Product view
        </label>
        <label style={{margin:"0px 40px"}}>
        <input
          type="checkbox"
          defaultChecked={false}
          name="Permit_Route_Register"
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_Route_Register')}
        />
        Route management
        </label>
        <label style={{margin:"0px 15px"}}>
        <input
          type="checkbox"
          defaultChecked={false}
          name="Permit_Dashboard"
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_Dashboard')}
        />
        Dashboard data
        </label>
        <label style={{margin:"0px 15px"}}>
        <input
          type="checkbox"
          defaultChecked={false}
          name="Permit_Dashboard_Top"
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_Dashboard_Top')}
        />
        Dashboard top
        </label>
        <label style={{margin:"10px 40px"}}>
        <input
          type="checkbox"
          defaultChecked={false}
          name="Permit_KPI_Setup"
          onChange={handleCheckboxChange}
          checked={selectedPrivileges.includes('Permit_KPI_Setup')}
        />
        KPI setup.
        </label>
        </div>
</Row>



            <Button
              variant="primary"
              type="submit"
              disabled={
                watch().userName == 'Select National' ||
                watch().employeeId == 'Select Region' ||
                !watch().role
              }
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
     
    </>
  );
};

export default EmployeeManagement;
