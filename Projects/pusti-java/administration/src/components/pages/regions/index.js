import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useEffect, useState } from 'react';
import {
  Button,
  Card,
  Dropdown,
} from 'react-bootstrap';
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import ToggleButton from 'components/common/Toggle-button/index';
import LoadingIcon from 'helpers/LoadingIcon';
import { Link } from 'react-router-dom';
import NoDataFound from 'components/errors/NoDataFound';
import IconButton from 'components/common/IconButton';

const Regions = () => {
  const [regionsData, setRegionsData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);
  const [searchText, setSearchText] = useState("");
  const [searchItems, setSearchItems] = useState([]);



  // Search Region Data by Region Name.

  useEffect(() => {

    if (searchText) {
      const url = process.env.REACT_APP_BASE_URL + `region/getAllRegionByFieldName?field=${searchText}`;
      axios
        .get(url, { headers: authHeader() })
        .then((response) => {
          console.log("test", response)
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


  // Load All Region Data Using Get All Region API.

  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + 'region/getAllRegions';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        let index = 1;
        const result = [];
        if (response.status === 200) {
          response?.data?.forEach(element => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          })
          setRegionsData(result);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        reset();
        toast.error(error?.message)
      })
  }, [count]);


  //Delete Region Item Using Region Delete API.

  const deleteRegion = id => {
    if (confirm('Are You Sure ?')) {
      if (id) {
        setIsLoading(true);
        const url =
          process.env.REACT_APP_BASE_URL + `region/deleteRegionById/${id}`;
        axios
          .delete(url, { headers: authHeader() })
          .then(response => {
            if (response.status === 200) {
              setIsLoading(false);
              toast.success('Region Delete Success');
              setCount(count + 1);
            }
          })
          .catch(error => {
            setIsLoading(false);
            reset();
            toast.error(error?.message)
          })
      }
    }
  };

  if (isLoading) {
    return <LoadingIcon />
  }


  /*******************
    Columns Start.
   *******************/

  const columns = [
    {
      accessor: 'index',
      Header: 'SL',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { index } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{index}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'nationalName',
      Header: 'National Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { division } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{division?.national?.nationalName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'division',
      Header: 'Division Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { division } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{division?.divisionName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'regionName',
      Header: 'Region Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { regionName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{regionName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'status',
      Header: 'Status',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { regionId, status } = rowData.row.original;

        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `region/statusChange/${regionId}`
                }
              ></ToggleButton>

            </div>
          </Flex>

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
        const { regionId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/master/region/add/${regionId}`}>
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
                  onClick={() => deleteRegion(regionId)}
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
      }
    }
  ];

  /*******************
Columns End.
*******************/

  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={
          searchText ? searchItems : regionsData
        }

        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Regions"
              setSearchText={setSearchText}
              newUrl="/master/region/add"
              data={regionsData}
              isSearch={true}
              excelUrl="region/excelDownload" excelFileName="Region.xlsx"
              table
            />
          </Card.Header>
          <Card.Body className="p-0">
            {
              searchText.length > 0 && searchItems.length === 0 ? <NoDataFound /> : <AdvanceTable
                table
                headerClassName="bg-200 text-900 text-nowrap align-middle"
                rowClassName="align-middle white-space-nowrap"
                tableProps={{
                  size: 'sm',
                  striped: true,
                  className: 'fs--1 mb-0 overflow-hidden'
                }}
              />}
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>

    </>
  );
};

export default Regions;
