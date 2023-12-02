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
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import { Formik } from 'formik';
import { Link } from 'react-router-dom';
import IconButton from 'components/common/IconButton';

const Banks = () => {
  const [banksData, setBanksData] = useState([]);
  const [allDistributor, setAllDistributor] = useState([]);
  const [bankItemData, setBankItemData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  //   const values = [true, 'sm-down', 'md-down', 'lg-down', 'xl-down', 'xxl-down'];
  const [fullscreen, setFullscreen] = useState('xxl-down');
  const [show, setShow] = useState(false);
  const [formData, setData] = useState({});
  const [mediumScreen, setMediumScreen] = useState('xl-down');
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [count, setCount] = useState(0);


  // Load Bank Data
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + 'bank/getAllBanks', {
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
          setBanksData(result);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        toast.error(error.message);
      });
  }, [count]);


  // Delete Bank Item
  const DeleteBankItem = id => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(process.env.REACT_APP_BASE_URL + `bank/deleteBank/${id}`, {
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

          setIsLoading(false);
          toast.error(error.message);
        });
    }
  };

  if (isLoading) {
    return <h4>Loading...</h4>;
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
      accessor: 'bank_id',
      Header: 'Bank ID',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { bankId } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{bankId}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'bank_name',
      Header: 'Bank Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { bankName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{bankName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'sales_org_id',
      Header: 'Org Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { distributor } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{distributor.name}</h5>
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
        const { bankId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/master/bank/add/${bankId}`}>
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
                  onClick={() => DeleteBankItem(bankId)}
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

  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={banksData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader title="Banks" newUrl="/master/bank/add" isFilter={false} isExport={false} table />
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

export default Banks;
