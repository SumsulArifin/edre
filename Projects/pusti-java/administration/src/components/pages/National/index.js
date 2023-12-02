import CustomersTableHeader from "components/app/e-commerce/customers/CustomersTableHeader";
import Flex from "components/common/Flex";
import AdvanceTable from "components/common/advance-table/AdvanceTable";
import AdvanceTableWrapper from "components/common/advance-table/AdvanceTableWrapper";
import React, { useEffect, useState } from "react";
import { Button, Card, Dropdown } from "react-bootstrap";
import { useForm } from "react-hook-form";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import LoadingIcon from "helpers/LoadingIcon";
import { Link, useNavigate } from "react-router-dom";
import IconButton from "components/common/IconButton";

const National = () => {
  const [nationalData, setNationalData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();



  // Load All National Data Using Get All National API.

  useEffect(() => {

    const url = process.env.REACT_APP_BASE_URL + "national/getAllNationals";
    setIsLoading(true);
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        let index = 1;
        const result = [];
        if (response.status === 200) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setNationalData(result);
        }
        setIsLoading(false);
      })
      .catch(error => {
        toast.error(error.message);
        setIsLoading(false);
      })
  }, []);



  if (isLoading) {
    return <LoadingIcon />;
  }


  /*******************
   Columns Start.
  *******************/

  const columns = [
    {
      accessor: "index",
      Header: "SL",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { index } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{index}</h5>
            </div>
          </Flex>

        );
      },
    },
    {
      accessor: "nationalName",
      Header: "National Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { nationalName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{nationalName}</h5>
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
        const { nationalId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <IconButton
                  onClick={() => navigate(`/master/national/add/${nationalId}`)}
                  variant="falcon-default"
                  size="sm"
                  icon="edit"
                  transform="shrink-2"
                  iconAlign="middle"
                  className="me-2"
                >
                  <span className="d-none d-xl-inline-block ms-1">Edit</span>
                </IconButton>
                
              </Dropdown.Item>
            </Dropdown.Menu>
          </Dropdown>
        );
      },
    },
  ];

  /*******************
 Columns End.
*******************/


  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={nationalData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="National" newUrl="/master/national/add" isFilter={false} isExport={false} table />
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
        </Card>
      </AdvanceTableWrapper>
    </>
  );
};

export default National;
