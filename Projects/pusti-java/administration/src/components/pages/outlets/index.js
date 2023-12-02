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
import { useDropzone } from 'react-dropzone';
import FalconCloseButton from 'components/common/FalconCloseButton';
import cloudUpload from 'assets/img/icons/cloud-upload.svg';
import { authHeaderForm } from 'state/ducs/auth/utils';
import NoDataFound from 'components/errors/NoDataFound';
import IconButton from 'components/common/IconButton';

const index = () => {
  const [outletData, setOutletData] = useState([]);
  const [singleOutletData, setSingleOutletData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [viewDetails, setViewDetails] = useState(false);
  const [showUploadCSV, setShowUploadCSV] = useState(false);
  const [count, setCount] = useState(0);
  const [searchText, setSearchText] = useState("");
  const [searchItems, setSearchItems] = useState([]);


  // csv Modal 
  const handleShowCSV = () => {
    setShowUploadCSV(true);
  };


  // View Details
  const handleViewDetails = (id) => {
    setViewDetails(true);

    // Load Outlet Data.
    const url = process.env.REACT_APP_BASE_URL + `outlet/getById/${id}`;
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        setSingleOutletData(response.data);

      })
      .catch((error) => console.log(error));
  };


  // Search outlet Data by outlet Name.

  useEffect(() => {

    if (searchText) {
      const url = process.env.REACT_APP_BASE_URL + `outlet/getAllOutletByFieldName?field=${searchText}`;
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

  // Load All Outlet Info
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + 'outlet/getAllOutlets';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          const result = []
          let index = 1;
          response?.data?.forEach((element) => {
            const outletData = { index, ...element }
            result.push(outletData);
            index++;
          });
          setOutletData(result)
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

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

    const regUrl = process.env.REACT_APP_BASE_URL + "outlet/csvUpload";

    axios
      .post(regUrl, formData, { headers: authHeaderForm() })
      .then((response) => {

        if (response.status === 201) {
          setIsLoading(false);
          setShowUploadCSV(false);
          setCount((prevState) => prevState + 1);
          toast.success("Csv File Upload Success");
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
          <Link to="/e-commerce/customer-details">
            <Flex alignItems="center">
              <div className="flex-1">
                <h5 className="mb-0 fs--1">{index}</h5>
              </div>
            </Flex>
          </Link>
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
        const { route } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{route?.routeName}</h5>
            </div>
          </Flex>

        );
      }
    },

    {
      accessor: ' outletName',
      Header: 'Outlet Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { outletName } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{outletName}</h5>
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
      accessor: 'contactPerson',
      Header: 'Contact Person',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { contactPerson } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{contactPerson}</h5>
            </div>
          </Flex>

        );
      }
    },
    {
      accessor: 'mobile',
      Header: 'Mobile',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { mobile } = rowData.row.original;
        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{mobile}</h5>
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
        const { outletId, status } = rowData.row.original;

        return (

          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `outlet/statusChangeOutlet/${outletId}`
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
        const { outletId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item >
                <Link to={`/master/outlet/add/${outletId}`}>
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
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => handleViewDetails(outletId)}
                >
                  View Details
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {' '}
                <Link to="/master/outlet/map"><Button
                  variant="light"
                  size="sm"
                >
                  View Map
                </Button></Link>
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
        data={searchText ? searchItems : outletData}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Outlets"
              buttonTitle="Upload CSV"
              handleShowCSV={handleShowCSV}
              isSearch={true}
              setSearchText={setSearchText}
              data={outletData}
              newUrl="/master/outlet/add"
              excelUrl="outlet/excelDownload"
              excelFileName="outlet.xlsx"
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
      {/* View Details Modal */}
      <Modal size={'lg'} show={viewDetails} onHide={() => setViewDetails(false)}>
        <Modal.Header>
          {/* <Modal.Title className="text-center">Employee Details</Modal.Title> */}
          <CloseButton className="btn btn-circle btn-sm transition-base p-0" onClick={() => setViewDetails(false)} />
        </Modal.Header>
        <Modal.Body>
          <div>
            {/* <div class="card mb-3">
                    <div class="card-body position-relative">
                      <div class="row">
                        <div class="col-lg-12">
                          <h4 className="text-center">{`${singleOutletData?.firstName} ${singleOutletData?.middleInitial} ${singleOutletData?.lastName}`}</h4>
                          <p class="mt-2 text-center">{singleOutletData?.employeeType}</p>
                        </div>
                      </div>
                    </div>
                  </div> */}
            <div class="table-responsive scrollbar">
              <table class="table table-bordered  fs--1 mb-0 ">
                <thead class="bg-200">
                  <tr>
                    <th class=" " data-sort="name">Name</th>

                    <th class="" data-sort="age">Value</th>
                  </tr>
                </thead>
                <tbody class="list text-black">
                  <tr>
                    <td class="name">Sales PerMonth</td>

                    <td>{singleOutletData?.salesPerMonth}</td>
                  </tr>
                  <tr>
                    <td class="name">Market Size</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.marketSize}</td>
                  </tr>
                  <tr>
                    <td class="name">keyOutlet</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.keyOutlet}</td>
                  </tr>
                  <tr>
                    <td class="name">Outlet Type</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.outletType}</td>
                  </tr>
                  <tr>
                    <td class="name">Outlet Channel</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.outletChannel}</td>
                  </tr>
                  <tr>
                    <td class="name">Display Outlet</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.displayOutlet === true ? "Yes" : "No"}</td>
                  </tr>
                  <tr>
                    <td class="name">Display Outlet Amount</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.displayOutletAmount === true ? "Yes" : "No"}</td>
                  </tr>
                  <tr>
                    <td class="name">Paid Amount</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.paidAmount}</td>
                  </tr>
                  <tr>
                    <td class="name">ShopType</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.shopType}</td>
                  </tr>
                  <tr>
                    <td class="name">Sales Group</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.salesGroup}</td>
                  </tr>
                  <tr>
                    <td class="name">CreditSales</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.creditSales}</td>
                  </tr>
                  <tr>
                    <td class="name">Comments</td>
                    {/* <td class="email">anna@example.com</td> */}
                    <td>{singleOutletData?.comments}</td>
                  </tr>

                </tbody>
              </table>
            </div>

          </div>

        </Modal.Body>

      </Modal>

      {/* CSV File Upload Modal */}
      <Modal
        size="lg"
        show={showUploadCSV}
        onHide={() => setShowCreateCostCenterModal(false)}
        aria-labelledby="example-modal-sizes-title-lg"
      >
        <Modal.Header>
          <Modal.Title id="example-modal-sizes-title-lg">
            Upload Outlet CSV File
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

export default index;
