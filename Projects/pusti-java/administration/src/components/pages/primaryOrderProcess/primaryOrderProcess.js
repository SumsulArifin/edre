import CustomersTableHeader from "components/app/e-commerce/customers/CustomersTableHeader";
import Flex from "components/common/Flex";
import AdvanceTable from "components/common/advance-table/AdvanceTable";
import AdvanceTablePagination from "components/common/advance-table/AdvanceTablePagination";
import AdvanceTableWrapper from "components/common/advance-table/AdvanceTableWrapper";
import React, { useEffect, useState } from "react";
import { Button, Card, Dropdown } from "react-bootstrap";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import ToggleButton from "components/common/Toggle-button/index";
import { Link } from "react-router-dom";
import LoadingIcon from "helpers/LoadingIcon";

const PrimaryOrderProcess = () => {
  const [allEmployee, setAllEmployee] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);

  // Load All Employee Data
  useEffect(() => {
    setIsLoading(true);
    const url =
      process.env.REACT_APP_BASE_URL + "primaryOrderApprovalsController/getAll";
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

    {
      accessor: "approvalName",
      Header: "Approval Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { approvalName } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{approvalName}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "offerNote",
      Header: "Offer Note",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { offerNote } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{offerNote}</h5>
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
        const { approvalType } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{approvalType}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "approvalStatus",
      Header: "Approval Status",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { approvalStatus } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{approvalStatus}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "approvalCommittee",
      Header: "Committee Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { approvalCommittee } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{approvalCommittee?.name}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "distributorId",
      Header: "Distributor ID",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { distributor } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{distributor?.distributorId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "distributorName",
      Header: "Distributor Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { distributor } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{distributor?.name}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "status",
      Header: "Is Approval Required",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { status } = rowData.row.original;

        return (
          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                //   url={
                //     process.env.REACT_APP_BASE_URL +
                //     `distributor/statusChange/${distributorId}`
                //   }
              ></ToggleButton>
            </div>
          </Flex>
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
        const { primaryOrderId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/master/employee/add/${primaryOrderId}`}>
                  <Button variant="light" size="sm">
                    Edit
                  </Button>
                </Link>
              </Dropdown.Item>
              {/* <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteEmployee(employeeId)}
                >
                  Delete
                </Button>
              </Dropdown.Item> */}
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
            <CustomersTableHeader
              title="Primary Order Process"
              newUrl="/primaryOrderProcess/add"
              data={allEmployee}
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
    </>
  );
};

export default PrimaryOrderProcess;
