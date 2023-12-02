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
import LoadingIcon from "helpers/LoadingIcon";
import { Link } from "react-router-dom";
import NoDataFound from "components/errors/NoDataFound";
import IconButton from "components/common/IconButton";

const Divisions = () => {
  const [divisionsData, setDivisionsData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [searchText, setSearchText] = useState("");
  const [searchItems, setSearchItems] = useState([]);
  const [count, setCount] = useState(0);
  ;

  // Search Division Data by Division Name.
  useEffect(() => {

    if (searchText) {
      const url = process.env.REACT_APP_BASE_URL + `division/getDivisionByDivisionName?field=${searchText}`;
      axios
        .get(url, { headers: authHeader() })
        .then((response) => {
          let index = 1;
          const result = [];
          if (response?.data?.recordCount > 0) {
            response?.data?.response?.forEach((element) => {
              const addIndex = { ...element, index };
              result.push(addIndex);
              index++;
            });
            setSearchItems(result);

          }
          else {
            setSearchItems([]);
          }

        })
        .catch(error => {
          setIsLoading(false);
          toast.error(error?.message)
        })
    }
  }, [searchText]);



  // Load All Division Data Using Get All Division API.
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "division/getAllDivisions";
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
          setDivisionsData(result);
        }
        setIsLoading(false);
      })
      .catch(error => {
        setIsLoading(false);
        toast.error(error?.message)
      })
  }, [count]);


  // Delete Division Item Using Division Delete API.
  const handleDeleteDivision = (id) => {
    if (confirm("Are You Sure ?")) {
      setIsLoading(true);
      const url = process.env.REACT_APP_BASE_URL + `division/deleteDivision/${id}`;
      axios
        .delete(url, { headers: authHeader() })
        .then((response) => {
          if (response.status === 200) {
            toast.success("Division Delete Success");
            setCount(count + 1);
          }
          setIsLoading(false);
        })
        .catch(error => {
          toast.error(error?.message)
          setIsLoading(false);
        })
    }
  };


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
        const { national } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{national?.nationalName}</h5>
            </div>
          </Flex>

        );
      },
    },

    {
      accessor: "divisionName",
      Header: "Division Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { divisionName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{divisionName}</h5>
            </div>
          </Flex>

        );
      },
    },

    {
      accessor: "status",
      Header: "Status",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { divisionId, status } = rowData.row.original;

        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={process.env.REACT_APP_BASE_URL + `division/statusChange/${divisionId}`}
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
        const { divisionId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">

                <Link to={`/master/division/add/${divisionId}`}>
                  <IconButton
                    variant="falcon-default"
                    size="sm"
                    icon="edit"
                    transform="shrink-2"
                    iconAlign="middle"
                    className="me-2"
                  >
                    <span className="d-none d-xl-inline-block ms-1">Edit</span>
                  </IconButton>
                </Link>
              </Dropdown.Item>
              <Dropdown.Item href="">
                <IconButton
                  onClick={() => handleDeleteDivision(divisionId)}
                  variant="falcon-default"
                  size="sm"
                  icon="trash-alt"
                  iconAlign="middle"
                  className="d-none d-sm-block me-2"
                >
                  <span className="d-none d-xl-inline-block ms-1">Delete</span>
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
        data={searchText ? searchItems : divisionsData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="Division" newUrl="/master/division/add" setSearchText={setSearchText} isSearch={true} excelUrl="division/excelDownload" excelFileName="Division.xlsx" data={divisionsData} table />
          </Card.Header>
          <Card.Body className="p-0">
            {searchText.length > 0 && searchItems.length === 0 ? (
              <NoDataFound />
            ) : (
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
            )}
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>
    </>
  );

};

export default Divisions;
