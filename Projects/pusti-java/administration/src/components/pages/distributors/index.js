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
  Dropdown,
  Modal,

} from 'react-bootstrap';
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import ToggleButton from 'components/common/Toggle-button/index';
import LoadingIcon from 'helpers/LoadingIcon';
import { Link } from 'react-router-dom';
import NoDataFound from 'components/errors/NoDataFound';
import IconButton from 'components/common/IconButton';

const Distributors = () => {
  const [allDistributor, setAllDistributor] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);
  const [viewDetails, setViewDetails] = useState(false);
  const [distrbutorData, setDistrbutorData] = useState(null);
  const [searchText, setSearchText] = useState("");
  const [searchItems, setSearchItems] = useState([]);



  // View Details
  const handleViewDetails = (id) => {
    setViewDetails(true);
    const url = process.env.REACT_APP_BASE_URL + `distributor/getDistributorById/${id}`;
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        if (response.status === 200) {
          setDistrbutorData(response.data);
        }
      })
      .catch(error => {
        setIsLoading(false);
        reset();
        toast.error(`${error?.response?.data?.errors[0]}
        ${error?.message}
        `)
      })

  }

  // Search Distributor Data by Zone Name.
  useEffect(() => {

    const url = process.env.REACT_APP_BASE_URL + `distributor/getDistributorsByDistributorName?field=${searchText}`;
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
        reset();
        toast.error(error?.message)
      })
  }, [searchText]);

  // Load All Distributors Data
  useEffect(() => {
    setIsLoading(true);
    const url =
      process.env.REACT_APP_BASE_URL + 'distributor/getAllDistributors';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        let index = 1;
        const result = [];
        if (response.status === 200) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
          setAllDistributor(result);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        reset();
        toast.error(`${error?.response?.data?.errors[0]}
        ${error?.message}
        `)
      })
  }, [count]);


  // Delete Distributor
  const handleDeleteDistributor = id => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(
          process.env.REACT_APP_BASE_URL +
          `distributor/deleteDistributorById/${id}`,
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
          setIsLoading(false);
          reset();
          toast.error(`${error?.response?.data?.errors[0]}
          ${error?.message}
          `)
        })
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
      accessor: 'name',
      Header: 'DB Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { name } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{name}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'zoneName',
      Header: 'Zone',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { zone } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{zone?.zoneName}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'proprietorName',
      Header: 'Proprietor Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { proprietorName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{proprietorName}</h5>
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
        const { distributorId, status } = rowData.row.original;

        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `distributor/statusChange/${distributorId}`
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
        const { distributorId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item >
                <Link to={`/master/distributor/add/${distributorId}`}>
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
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => handleViewDetails(distributorId)}
                >
                  View
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
              <IconButton
                  onClick={() => handleDeleteDistributor(distributorId)}
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
        data={searchItems ? searchItems : allDistributor}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Distributor"
              isExport={false}
              isSearch={true}
              setSearchText={setSearchText}
              newUrl="/master/distributor/add"
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
              />
            }
          </Card.Body>
          <Card.Footer>
            <AdvanceTablePagination table />
          </Card.Footer>
        </Card>
      </AdvanceTableWrapper>
      <Modal size={'lg'} show={viewDetails} onHide={() => setViewDetails(false)}>
        <Modal.Header>
          {/* <Modal.Title className="text-center">Employee Details</Modal.Title> */}
          <CloseButton className="btn btn-circle btn-sm transition-base p-0" onClick={() => setViewDetails(false)} />
        </Modal.Header>
        <Modal.Body>
          <div>

            <div class="table-responsive scrollbar">
              <table class="table table-bordered  fs--1 mb-0">
                <thead class="bg-200">
                  <tr>
                    <th class=" " data-sort="name">Name</th>

                    <th class="" data-sort="age">Value</th>
                  </tr>
                </thead>
                <tbody class="list text-black">
                  <tr>
                    <td>Depot</td>
                    <td>{distrbutorData?.depot?.name}</td>
                  </tr>
                  <tr>
                    <td>ERP ID </td>
                    <td>{distrbutorData?.erpId}</td>
                  </tr>
                  <tr>
                    <td>Upazila </td>
                    <td>{distrbutorData?.upazila?.upazilaName}</td>
                  </tr>
                  <tr>
                    <td>Address</td>
                    <td>{distrbutorData?.address}</td>
                  </tr>
                  <tr>
                    <td>Mobile</td>
                    <td>{distrbutorData?.mobile}</td>
                  </tr>
                  <tr>
                    <td>Proprietor DOB</td>
                    <td>{distrbutorData?.proprietorDob}</td>
                  </tr>
                  <tr>
                    <td>Printer</td>
                    <td>{distrbutorData?.hasPrinter === true ? 'Yes' : 'No'}</td>
                  </tr>
                  <tr>
                    <td>Pc</td>
                    <td>{distrbutorData?.hasPc === true ? 'Yes' : 'No'}</td>
                  </tr>
                  <tr>
                    <td>Live App</td>
                    <td>{distrbutorData?.hasLiveApp === true ? 'Yes' : 'No'}</td>
                  </tr>
                  <tr>
                    <td>Direct Sale</td>
                    <td>{distrbutorData?.hasDirectSale === true ? 'Yes' : 'No'}</td>
                  </tr>
                  <tr>
                    <td>Opening Date</td>
                    <td>{distrbutorData?.openingDate}</td>
                  </tr>
                  <tr>
                    <td>Emergency Person</td>
                    <td>{distrbutorData?.emergencyContactName}</td>
                  </tr>
                  <tr>
                    <td>Emergency Mobile</td>
                    <td>{distrbutorData?.emergencyContactNumber}</td>
                  </tr>
                  <tr>
                    <td>Relation With Owner</td>
                    <td>{distrbutorData?.emergencyContactRelation}</td>
                  </tr>

                </tbody>
              </table>
            </div>

          </div>

        </Modal.Body>

      </Modal>
    </>
  );
};

export default Distributors;
