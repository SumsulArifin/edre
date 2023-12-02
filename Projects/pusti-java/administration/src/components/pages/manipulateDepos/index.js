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
import IconButton from 'components/common/IconButton';

const index = () => {
  const [depotData, setDepotsData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);



  const deleteDepot = depotId => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(
          process.env.REACT_APP_BASE_URL + `depot/deleteDepot/${depotId}`,
          {
            headers: authHeader()
          }
        )
        .then(response => {
          if (response) {
            setIsLoading(false);
            toast.success('Delete Success');
            setCount(count + 1);
          }
        })
        .catch(error => {
          setIsLoading(false);
          reset();
          toast.error(`${error?.response?.data?.errors[0]}
          ${error.message}
          `)
        })
    }
  };


  // Load All Depots Data Using Get All Depots API.

  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + 'depot/getAllDepots', {
        headers: authHeader()
      })
      .then(response => {
        let index = 1;
        const result = [];
        if (response.status === 200) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setDepotsData(result);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        reset();
        toast.error(`${error?.response?.data?.errors[0]}
        ${error.message}
        `)
      })
  }, [count]);



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
      accessor: 'name',
      Header: 'Depots Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { depotName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{depotName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'email',
      Header: 'Email',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { email } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{email}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'phone',
      Header: 'Phone Number',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { phone } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{phone}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'department',
      Header: 'Department',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { department } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{department}</h5>
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
        const { depotId, status } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `depot/statusChangeDepot/${depotId}`
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
        const { depotId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item >
                <Link to={`/depot/add/${depotId}`}>
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
                  onClick={() => deleteDepot(depotId)}
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
        data={depotData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Depots"
              isFilter={false}
              isExport={false}
              newUrl="/depot/add/"
              table
            />
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

    </>
  );
};

export default index;
