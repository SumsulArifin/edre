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

const Zones = () => {
  const [zonesData, setZonesData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);
  const [searchText, setSearchText] = useState("");
  const [searchItems, setSearchItems] = useState([]);

  // Search Zone Data by Zone Name.
  useEffect(() => {
    if (searchText) {
      const url = process.env.REACT_APP_BASE_URL + `zone/getAllZoneByZoneNameOrRegionName?field=${searchText}`;
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


  //  Load All Zone Data Using Get All Zone API.
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + "zone/getAllZones", {
        headers: authHeader(),
      })
      .then((response) => {
        let index = 1;
        const result = [];
        if (response.status === 200) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setZonesData(result);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        toast.error(error?.message)
      })
  }, [count]);



  // Delete Zone Item Using Zone Delete API.
  const handleDeleteZone = (id) => {
    if (confirm("Are You Sure ?")) {
      setIsLoading(true);
      axios
        .delete(process.env.REACT_APP_BASE_URL + `zone/deleteZoneById/${id}`, {
          headers: authHeader(),
        })
        .then((response) => {
          if (response.status === 200) {
            setIsLoading(false);
            toast.success("Delete Success");
            setCount(count + 1);
          }
        })
        .catch(error => {
          setIsLoading(false);
          toast.error(error?.message)
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
      Header: "Sl",
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
      accessor: "regionName",
      Header: "Region Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { region } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{region?.regionName}</h5>
            </div>
          </Flex>

        );
      },
    },
    {
      accessor: "zoneName",
      Header: "Zones Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { zoneName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{zoneName}</h5>
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
        const { zoneId, status } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={process.env.REACT_APP_BASE_URL + `zone/updateStatus/${zoneId}`}
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
        const { zoneId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">



                <Link to={`/master/zone/add/${zoneId}`}>
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
                  onClick={() => handleDeleteZone(zoneId)}
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
        data={searchText ? searchItems : zonesData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="Zones" newUrl="/master/zone/add" setSearchText={setSearchText} isSearch={true} data={zonesData} excelUrl="api/auth/downloadExcel" excelFileName="Zones.xlsx" table />
          </Card.Header>
          <Card.Body className="p-0">
            {searchText.length > 0 && searchItems.length === 0 ? <NoDataFound /> : <AdvanceTable
              table
              headerClassName="bg-200 text-900 text-nowrap align-middle"
              rowClassName="align-middle white-space-nowrap"
              tableProps={{
                size: "sm",
                striped: true,
                className: "fs--1 mb-0 overflow-hidden",
              }}
            />
            }
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>
    </>
  );
};

export default Zones;
