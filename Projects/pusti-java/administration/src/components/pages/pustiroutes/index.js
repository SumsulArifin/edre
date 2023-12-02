import axios from 'axios';
import CustomersTableHeader from 'components/app/e-commerce/customers/CustomersTableHeader';
import Flex from 'components/common/Flex';
import ToggleButton from 'components/common/Toggle-button/index';
import AdvanceTable from 'components/common/advance-table/AdvanceTable';
import AdvanceTablePagination from 'components/common/advance-table/AdvanceTablePagination';
import AdvanceTableWrapper from 'components/common/advance-table/AdvanceTableWrapper';
import React, { useEffect, useState } from 'react';
import {
  Button,
  Card,
  Dropdown,
  Modal,
} from 'react-bootstrap';
import { Link } from 'react-router-dom';
import { useDropzone } from 'react-dropzone';
import cloudUpload from 'assets/img/icons/cloud-upload.svg';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import FalconCloseButton from 'components/common/FalconCloseButton';
import { authHeaderForm } from 'state/ducs/auth/utils';
import LoadingIcon from 'helpers/LoadingIcon';
import NoDataFound from 'components/errors/NoDataFound';
import IconButton from 'components/common/IconButton';

const PustiRoute = () => {
  const [routeData, setRouteData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [showUploadCSV, setShowUploadCSV] = useState(false);
  const [searchText, setSearchText] = useState("");
  const [searchItems, setSearchItems] = useState([]);
  const [count, setCount] = useState(0);


  // Search Route Data
  useEffect(() => {
    // setIsLoading(true);
    if (searchText) {
      const url = process.env.REACT_APP_BASE_URL + `route/getAllRouteByFieldName?field=${searchText}`;
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



  // Load All Route Data
  useEffect(() => {
    setIsLoading(true)
    const url = process.env.REACT_APP_BASE_URL + "route/getAllRoutes";
    axios.get(url, { headers: authHeader() })
      .then(response => {

        let index = 1;
        const result = [];
        if (response.data) {
          response?.data?.forEach(element => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;

          });
          setRouteData(result);
          setIsLoading(false)

        }
      })
      .catch(error => {
        setIsLoading(false)

      })
  }, [count])


  // csv Modal 
  const handleShowCSV = () => {
    setShowUploadCSV(true);
  };

  /* 
  ...........................
          CSV FILE UPLOAD
   ..........................
  */
  const { acceptedFiles, getRootProps, getInputProps } = useDropzone();

  const files = acceptedFiles.map((file) => (
    <li key={file.path}>
      {file.path} - {file.size} bytes
    </li>
  ));


  const handleCSVUpload = () => {

    const csvFile = acceptedFiles[0];
    const formData = new FormData();
    formData.append("file", csvFile);

    setIsLoading(true);

    const regUrl = process.env.REACT_APP_BASE_URL + "route/csvUpload";

    axios
      .post(regUrl, formData, { headers: authHeaderForm() })
      .then((response) => {

        if (response.data) {
          setIsLoading(false);
          setShowUploadCSV(false);
          setCount((prevState) => prevState + 1);
          toast.success(response?.data?.message);
        }
      })
      .catch(error => {
        setIsLoading(false);
        toast.error(error?.message)
      })

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
      accessor: 'divisionName',
      Header: 'Division Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { zone } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{zone?.region?.division?.divisionName}</h5>
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
        const { zone } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{zone?.region?.regionName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'zoneName',
      Header: 'Zone Name',
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
      accessor: 'DB Name',
      Header: 'DB Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { distributor } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{distributor?.name}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'routeName',
      Header: 'Route Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { routeName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{routeName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: 'status',
      Header: 'status',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { status, routeId } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={process.env.REACT_APP_BASE_URL + `route/statusChange/${routeId}`}
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
        const { routeId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                {' '}
                <Link to={`/master/route/add/${routeId}`}>
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
              {/* <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteRouteItem(routeId)}
                >
                  Delete
                </Button>
              </Dropdown.Item> */}

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
        data={searchText ? searchItems : routeData}
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Route"
              buttonTitle="Upload CSV"
              handleShowCSV={handleShowCSV}
              newUrl="/master/route/add"
              isSearch={true}
              setSearchText={setSearchText}
              excelUrl="route/excelDownload"
              excelFileName="Route.xlsx"
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

      <Modal
        size="lg"
        show={showUploadCSV}
        onHide={() => setShowCreateCostCenterModal(false)}
        aria-labelledby="example-modal-sizes-title-lg"
      >
        <Modal.Header>
          <Modal.Title id="example-modal-sizes-title-lg">
            Upload Route CSV File
          </Modal.Title>
          <FalconCloseButton
            onClick={() => setShowUploadCSV(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <>
            <div
              {...getRootProps({ className: "dropzone-area py-6" })}
            >
              <input {...getInputProps({ multiple: false })} />
              <Flex justifyContent="center">
                <img src={cloudUpload} alt="" width={25} className="me-2" />
                <p className="fs-0 mb-0 text-700">Drop your file here</p>
              </Flex>
            </div>
            <div className="mt-3">
              {acceptedFiles.length > 0 && (
                <>
                  <h6>File</h6>
                  <ul>{files}</ul>
                </>
              )}
            </div>
          </>

          <Button variant="primary" onClick={handleCSVUpload}>
            Submit
          </Button>
        </Modal.Body>
      </Modal>
    </>
  );
};

export default PustiRoute;
