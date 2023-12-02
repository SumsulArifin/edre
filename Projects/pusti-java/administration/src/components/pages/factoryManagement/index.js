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
  Modal,
} from 'react-bootstrap';
import axios from 'axios';
import { authHeader } from 'utils';
import { toast } from 'react-toastify';
import ToggleButton from 'components/common/Toggle-button/index';
import LoadingIcon from 'helpers/LoadingIcon';
import { Link } from 'react-router-dom';
import { useDropzone } from 'react-dropzone';
import FalconCloseButton from 'components/common/FalconCloseButton';
import cloudUpload from 'assets/img/icons/cloud-upload.svg';
import { authHeaderForm } from 'state/ducs/auth/utils';
import IconButton from 'components/common/IconButton';

const index = () => {
  const [factoryData, setFactoryData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [count, setCount] = useState(0);
  const [selectedChoices, setSelectedChoices] = useState([]);
  const [showUploadCSV, setShowUploadCSV] = useState(false);


  const handleDelete = factoryId => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(
          process.env.REACT_APP_BASE_URL + `factory/deleteFactoryById/${factoryId}`,
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
          console.log(error);
          setIsLoading(false);
        });
    }
  };

  // Load All Factory Data
  useEffect(() => {
    setIsLoading(true);
    axios
      .get(process.env.REACT_APP_BASE_URL + 'factory/getAllFactories', {
        headers: authHeader()
      })
      .then(response => {
        console.log(response)
        let index = 1;
        const result = [];
        if (response.data) {
          response?.data?.forEach((element) => {
            const addIndex = { ...element, index };
            result.push(addIndex);
            index++;
          });
        }
        setFactoryData(result);
        setIsLoading(false);

      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);



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

    const regUrl = process.env.REACT_APP_BASE_URL + "factory/uploadCSVWithFactoryAndDepot";

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

  /* 
    ..........................
    Columns Data here
    .........................
    */

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
      accessor: 'factoryName',
      Header: 'Factory Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { factoryName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{factoryName}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'address',
      Header: 'Address',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { address } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{address}</h5>
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
        const { factoryId, status } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `factory/statusChange/${factoryId}`
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
        const { factoryId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/factoryManagement/add/${factoryId}`}>
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
                  onClick={() => handleDelete(factoryId)}
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
        data={factoryData}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Factory Management"
              buttonTitle="Upload CSV"
              handleShowCSV={handleShowCSV}
              newUrl="/factoryManagement/add"
              excelUrl="factory/excelDownload"
              excelFileName="Factory.xlsx"
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

            <Modal
              size="lg"
              show={showUploadCSV}
              onHide={() => setShowCreateCostCenterModal(false)}
              aria-labelledby="example-modal-sizes-title-lg"
            >
              <Modal.Header>
                <Modal.Title id="example-modal-sizes-title-lg">
                  Upload Factory CSV File
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
