import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useEffect, useState } from 'react';
import {
  Button,
  Card,
  Dropdown
} from 'react-bootstrap';
import { useForm } from 'react-hook-form';
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import ToggleButton from 'components/common/Toggle-button/index';
import { Link } from 'react-router-dom';
import LoadingIcon from 'helpers/LoadingIcon';
import IconButton from 'components/common/IconButton';

const salesOrgs = () => {
  const [salesOrganizationData, setDivisionsData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);


  // Load SalesOrganization Data
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + 'salesOrganization/getAllSalesOrgs', {
        headers: authHeader()
      })
      .then(response => {
        let index = 1;
        const result = [];
        if (response.data) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setDivisionsData(result);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        toast.error(error.message);
      });
  }, [count]);


  // Delete Sales Organization
  const DeleteSalesOrganization = id => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(process.env.REACT_APP_BASE_URL + `salesOrganization/deleteSalesOrganizations/${id}`, {
          headers: authHeader()
        })
        .then(response => {
          if (response.status === 200) {
            toast.success('Delete Success');
            setCount(count + 1);
            setIsLoading(false);
          }
        })
        .catch(error => {
          setIsLoading(false);
          toast.error(error.message);
        });
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
      accessor: 'salesOrgName',
      Header: 'SalesOrg Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { salesOrgName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{salesOrgName}</h5>
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
        const { salesOrgId, status } = rowData.row.original;

        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `salesOrganization/updateSalesOrganizationStatus/${salesOrgId}`
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
        const { salesOrgId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/master/salesorg/add/${salesOrgId}`}>
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
                  onClick={() => DeleteSalesOrganization(salesOrgId)}
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
        data={salesOrganizationData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Sales Organization"
              newUrl="/master/salesorg/add"
              data={salesOrganizationData}
              isFilter={false}
              isExport={false}
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

export default salesOrgs;
