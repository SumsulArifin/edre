import CustomersTableHeader from "components/app/e-commerce/customers/CustomersTableHeader";
import Flex from "components/common/Flex";
import AdvanceTable from "components/common/advance-table/AdvanceTable";
import AdvanceTablePagination from "components/common/advance-table/AdvanceTablePagination";
import AdvanceTableWrapper from "components/common/advance-table/AdvanceTableWrapper";
import React, { useEffect, useState } from "react";
import { Button, Card, CloseButton, Dropdown, Modal, Table } from "react-bootstrap";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import ToggleButton from "components/common/Toggle-button/index";
import { Link } from "react-router-dom";
import LoadingIcon from "helpers/LoadingIcon";
import Background from "components/common/Background";

const Employee = () => {
  const [allEmployee, setAllEmployee] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);
  const [viewDetails, setViewDetails] = useState(false);
  const [fullscreen, setFullscreen] = useState([]);
  const [employeeData, setEmployeeData] = useState(null);

  // Load All Employee Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "employee/getAllEmployees";
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        let index = 1;
        const result = [];
        if (response.data) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setAllEmployee(result);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        toast.error(error.message);
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

  // Delete Employee..
  /*   const DeleteEmployee = id => {
      if (confirm('Are You Sure ?')) {
        setIsLoading(true);
        axios
          .delete(
            process.env.REACT_APP_BASE_URL + `${id}`,
            { headers: authHeader() }
          )
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
    }; */

  // View Details
  const handleViewDetails = (id) => {
    setViewDetails(true);

    // Load Employee Data.
    const url = process.env.REACT_APP_BASE_URL + `employee/getEmployeeDetailsById/${id}`;
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        setEmployeeData(response.data);

      })
      .catch((error) => console.log(error));
  };

  if (isLoading) {
    return <LoadingIcon />;
  }

  /* 
  ..........................
  Columns Data here
  .........................
  */

 const columns = [
    {
      accessor: "index",
      Header: "SN",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { index } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{index}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },

    /*
    {
      accessor: 'employeeId',
      Header: 'Employee ID',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { employeeId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{employeeId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    */
    {
      accessor: "employeeType",
      Header: "Employee Type",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { employeeType } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{employeeType}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "name",
      Header: "Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { firstName, middleInitial, lastName } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{`${firstName} ${middleInitial} ${lastName}`}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    // {
    //   accessor: "dateOfBirth",
    //   Header: "Date Of Birth",
    //   headerProps: { className: "pe-1" },
    //   cellProps: {
    //     className: "py-2",
    //   },
    //   Cell: (rowData) => {
    //     const { dateOfBirth } = rowData.row.original;
    //     return (
    //       // <Link to="/e-commerce/customer-details">
    //       <Flex alignItems="center">
    //         <div className="flex-1">
    //           <h5 className="mb-0 fs--1">{dateOfBirth}</h5>
    //         </div>
    //       </Flex>
    //       // </Link>
    //     );
    //   },
    // },
    // {
    //   accessor: "bloodGroup",
    //   Header: "Blood Group",
    //   headerProps: { className: "pe-1" },
    //   cellProps: {
    //     className: "py-2",
    //   },
    //   Cell: (rowData) => {
    //     const { bloodGroup } = rowData.row.original;
    //     return (
    //       // <Link to="/e-commerce/customer-details">
    //       <Flex alignItems="center">
    //         <div className="flex-1">
    //           <h5 className="mb-0 fs--1">{bloodGroup}</h5>
    //         </div>
    //       </Flex>
    //       // </Link>
    //     );
    //   },
    // },
    // {
    //   accessor: "nidNumber",
    //   Header: "Nid Number",
    //   headerProps: { className: "pe-1" },
    //   cellProps: {
    //     className: "py-2",
    //   },
    //   Cell: (rowData) => {
    //     const { nidNumber } = rowData.row.original;
    //     return (
    //       // <Link to="/e-commerce/customer-details">
    //       <Flex alignItems="center">
    //         <div className="flex-1">
    //           <h5 className="mb-0 fs--1">{nidNumber}</h5>
    //         </div>
    //       </Flex>
    //       // </Link>
    //     );
    //   },
    // },
    /*
    {
      accessor: "salesOrganizationId",
      Header: "SalesOrganization",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { salesOrganizationId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{salesOrganizationId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "designationId",
      Header: "Designation",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { designationId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{designationId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    */
    {
      accessor: "mobileNumber",
      Header: "Mobile Number",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { mobileNumber } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{mobileNumber}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "email",
      Header: "Email",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { email } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{email}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    /*
    {
      accessor: "address",
      Header: "Address",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { address } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{address}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "divisionId",
      Header: "Division",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { divisionId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{divisionId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "regionId",
      Header: "Region",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { regionId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{regionId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "zoneId",
      Header: "Zone",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { zoneId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{zoneId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "routeId",
      Header: "Route",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { routeId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{routeId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "postalCode",
      Header: "Postal",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { postalCode } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{postalCode}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "districtId",
      Header: "District",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { districtId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{districtId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "dateOfJoining",
      Header: "Date Of Joining",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { dateOfJoining } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{dateOfJoining}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "dateOfResignation",
      Header: "Date Of Resignation",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { dateOfResignation } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{dateOfResignation}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "basicSalary",
      Header: "Basic Salary",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { basicSalary } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{basicSalary}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "houseRent",
      Header: "House Rent",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { houseRent } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{houseRent}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "medicalAllowance",
      Header: "Medical Allowance",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { medicalAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{medicalAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "internetBill",
      Header: "Internet Bill",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { internetBill } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{internetBill}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "mobileBill",
      Header: "Mobile Bill",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { mobileBill } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{mobileBill}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "travellingDailyAllowance",
      Header: "Travelling Daily Allowance",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { travellingDailyAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{travellingDailyAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "meetingTravellingAllowance",
      Header: "Meeting Travelling Allowance",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { meetingTravellingAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{meetingTravellingAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "meetingDailyAllowance",
      Header: "Meeting Daily Allowance",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { meetingDailyAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{meetingDailyAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "cityAllowance",
      Header: "City Allowance",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { cityAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{cityAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "otherAllowance",
      Header: "Other Allowance",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { otherAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{otherAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "contributionPercentage",
      Header: "Contribution Percentage",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { contributionPercentage } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{contributionPercentage}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "bankId",
      Header: "Bank",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { bankId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{bankId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    */
    // {
    //   accessor: "backAccNumber",
    //   Header: "bank Account Number",
    //   headerProps: { className: "pe-1" },
    //   cellProps: {
    //     className: "py-2",
    //   },
    //   Cell: (rowData) => {
    //     const { backAccNumber } = rowData.row.original;
    //     return (
    //       // <Link to="/e-commerce/customer-details">
    //       <Flex alignItems="center">
    //         <div className="flex-1">
    //           <h5 className="mb-0 fs--1">{backAccNumber}</h5>
    //         </div>
    //       </Flex>
    //       // </Link>
    //     );
    //   },
    // },
    /*
    {
      accessor: "emergencyContactName",
      Header: "Emergency Contact Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { emergencyContactName } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{emergencyContactName}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "emergencyMobileNumber",
      Header: "Emergency Mobile Number",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { emergencyMobileNumber } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{emergencyMobileNumber}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "emergencyContactRelation",
      Header: "Emergency Contact Relation",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { emergencyContactRelation } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{emergencyContactRelation}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
*/
    {
      accessor: "status",
      Header: "Status",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { employeeId, status } = rowData.row.original;

        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={process.env.REACT_APP_BASE_URL + `employee/statusChange/${employeeId}`}
              ></ToggleButton>
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
        const { employeeId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/master/employee/add/${employeeId}`}>
                  <Button variant="light" size="sm">
                    Edit
                  </Button>
                </Link>
              </Dropdown.Item>
              <Dropdown.Item href="">
               
                  <Button variant="light" size="sm" onClick={() => handleViewDetails(employeeId)}>
                    View Details
                  </Button>
               
              </Dropdown.Item>
              <Dropdown.Item href="">
                {" "}
                <Button variant="light" size="sm" onClick={() => DeleteEmployee(employeeId)}>
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
        data={allEmployee}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="Employee" newUrl="/master/employee/add" data={allEmployee} isFilter={false} isExport={false} table />
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
            <Modal size={'lg'} show={viewDetails} onHide={() => setViewDetails(false)}>
              <Modal.Header>
                {/* <Modal.Title className="text-center">Employee Details</Modal.Title> */}
                <CloseButton className="btn btn-circle btn-sm transition-base p-0" onClick={() => setViewDetails(false)} />
              </Modal.Header>
              <Modal.Body>
                <div>
                  {/* <div class="card mb-3">
                    <div class="card-body position-relative">
                      <div class="row">
                        <div class="col-lg-12">
                          <h4 className="text-center">{`${employeeData?.firstName} ${employeeData?.middleInitial} ${employeeData?.lastName}`}</h4>
                          <p class="mt-2 text-center">{employeeData?.employeeType}</p>
                        </div>
                      </div>
                    </div>
                  </div> */}
                  <div class="table-responsive scrollbar">
                    <table class="table table-bordered  fs--1 mb-0">
                      <thead  class="bg-200">
                        <tr>
                          <th class=" " data-sort="name">Name</th>

                          <th class="" data-sort="age">Value</th>
                        </tr>
                      </thead>
                      <tbody class="list text-black">
                        <tr>
                          <td class="name">Nid Number</td>

                          <td>{employeeData?.nidNumber}</td>
                        </tr>
                        <tr>
                          <td class="name">Back Account Number</td>

                          <td>{employeeData?.backAccNumber}</td>
                        </tr>
                        <tr>
                          <td class="name">Blood Group</td>

                          <td>{employeeData?.bloodGroup}</td>
                        </tr>
                        <tr>
                          <td class="name">Date Of Birth</td>

                          <td>{employeeData?.dateOfBirth}</td>
                        </tr>
                        <tr>
                          <td class="name">Designation</td>

                          <td>{employeeData?.designation?.name}</td>
                        </tr>
                        <tr>
                          <td class="name">Sales Organization</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.salesOrganization?.salesOrgName}</td>
                        </tr>
                        <tr>
                          <td class="name">Address</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.address}</td>
                        </tr>
                        <tr>
                          <td class="name">Division</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.division?.divisionName}</td>
                        </tr>
                        <tr>
                          <td class="name">Zone</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.zone?.zoneName}</td>
                        </tr>
                        <tr>
                          <td class="name">Route</td>
                          {/* <td class="email">anna@example.com</td> */}
                          {/* <td>{employeeData?.routeId}</td> */}
                        </tr>
                        <tr>
                          <td class="name">Region</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.region?.regionName}</td>
                        </tr>
                        <tr>
                          <td class="name">Postal Code</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.postalCode}</td>
                        </tr>
                        <tr>
                          <td class="name">District</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.districtId}</td>
                        </tr>
                        <tr>
                          <td class="name">Date Of Joining</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.dateOfJoining}</td>
                        </tr>
                        <tr>
                          <td class="name">Date Of Resignation</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.dateOfResignation}</td>
                        </tr>
                        <tr>
                          <td class="name">Basic Salary</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.basicSalary}</td>
                        </tr>
                        <tr>
                          <td class="name">House Rent</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.houseRent}</td>
                        </tr>
                        <tr>
                          <td class="name">Medical Allowance </td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.medicalAllowance}</td>
                        </tr>
                        <tr>
                          <td class="name">Internet Bill </td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.internetBill}</td>
                        </tr>
                        <tr>
                          <td class="name">Mobile Bill </td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.mobileBill}</td>
                        </tr>
                        <tr>
                          <td class="name">Travelling Daily Allowance</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.travellingDailyAllowance}</td>
                        </tr>
                        <tr>
                          <td class="name">Meeting Travelling Allowance</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.meetingTravellingAllowance}</td>
                        </tr>
                        <tr>
                          <td class="name">City Allowance</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.cityAllowance}</td>
                        </tr>
                        <tr>
                          <td class="name">Other Allowance</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.otherAllowance}</td>
                        </tr>
                        <tr>
                          <td class="name">Contribution Percentage </td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.contributionPercentage}</td>
                        </tr>
                        <tr>
                          <td class="name">Bank </td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.bank?.bankName}</td>
                        </tr>
                        <tr>
                          <td class="name">Emergency Contact Name </td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.emergencyContactName}</td>
                        </tr>
                        <tr>
                          <td class="name">Emergency Mobile Number</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.emergencyMobileNumber}</td>
                        </tr>
                        <tr>
                          <td class="name">Emergency Contact Relation</td>
                          {/* <td class="email">anna@example.com</td> */}
                          <td>{employeeData?.emergencyContactRelation}</td>
                        </tr>

                      </tbody>
                    </table>
                  </div>

                </div>

              </Modal.Body>

            </Modal>
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>
    </>
  );
};

export default Employee;
