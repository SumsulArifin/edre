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
import ToggleButton from 'components/common/Toggle-button/index';

const SalesOfficer = () => {
  const [allSalesOfficer, setAllSalesOfficer] = useState([]);
  const [allDistributor, setAllDistributor] = useState([]);
  const [salesOfficerItemData, setSalesOfficerItemData] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  //   const values = [true, 'sm-down', 'md-down', 'lg-down', 'xl-down', 'xxl-down'];
  const [fullscreen, setFullscreen] = useState('xxl-down');
  const [show, setShow] = useState(false);
  const [formData, setData] = useState({});
  const [mediumScreen, setMediumScreen] = useState('xl-down');
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [designation, setDesignation] = useState([]);
  const [count, setCount] = useState(0);
  const [bank, setBank] = useState([]);
  const bangladeshDistricts = [
    { districtId: 1, districtName: 'Dhaka' },
    { districtId: 2, districtName: 'Chittagong' },
    { districtId: 3, districtName: 'Rajshahi' },
    { districtId: 4, districtName: 'Khulna' },
    { districtId: 5, districtName: 'Barishal' },
    { districtId: 6, districtName: 'Sylhet' },
    { districtId: 7, districtName: 'Rangpur' },
    { districtId: 8, districtName: 'Mymensingh' },
    { districtId: 9, districtName: 'Comilla' },
    { districtId: 10, districtName: 'Narayanganj' },
    { districtId: 11, districtName: 'Gazipur' },
    { districtId: 12, districtName: 'Narsingdi' },
    { districtId: 13, districtName: 'Jhenaidah' },
    { districtId: 14, districtName: 'Jessore' },
    { districtId: 15, districtName: 'Satkhira' },
    { districtId: 16, districtName: 'Kushtia' },
    { districtId: 17, districtName: 'Magura' },
    { districtId: 18, districtName: 'Meherpur' },
    { districtId: 19, districtName: 'Narail' },
    { districtId: 20, districtName: 'Chuadanga' },
    { districtId: 21, districtName: 'Bagerhat' },
    { districtId: 22, districtName: 'Khagrachari' },
    { districtId: 23, districtName: 'Bandarban' },
    { districtId: 24, districtName: "Cox's Bazar" },
    { districtId: 25, districtName: 'Feni' },
    { districtId: 26, districtName: 'Noakhali' },
    { districtId: 27, districtName: 'Lakshmipur' },
    { districtId: 28, districtName: 'Chandpur' },
    { districtId: 29, districtName: 'Brahmanbaria' },
    { districtId: 30, districtName: 'Rangamati' },
    { districtId: 31, districtName: 'Patuakhali' },
    { districtId: 32, districtName: 'Barguna' },
    { districtId: 33, districtName: 'Bhola' },
    { districtId: 34, districtName: 'Pirojpur' },
    { districtId: 35, districtName: 'Barisal' },
    { districtId: 36, districtName: 'Bogra' },
    { districtId: 37, districtName: 'Joypurhat' },
    { districtId: 38, districtName: 'Naogaon' },
    { districtId: 39, districtName: 'Natore' },
    { districtId: 40, districtName: 'Nawabganj' },
    { districtId: 41, districtName: 'Pabna' },
    { districtId: 42, districtName: 'Sirajganj' },
    { districtId: 43, districtName: 'Dinajpur' },
    { districtId: 44, districtName: 'Thakurgaon' },
    { districtId: 45, districtName: 'Panchagarh' },
    { districtId: 46, districtName: 'Kurigram' },
    { districtId: 47, districtName: 'Lalmonirhat' },
    { districtId: 48, districtName: 'Nilphamari' },
    { districtId: 49, districtName: 'Rangpur' },
    { districtId: 50, districtName: 'Gaibandha' },
    { districtId: 51, districtName: 'Kishoreganj' },
    { districtId: 52, districtName: 'Netrakona' },
    { districtId: 53, districtName: 'Jamalpur' },
    { districtId: 54, districtName: 'Sherpur' },
    { districtId: 55, districtName: 'Sunamganj' },
    { districtId: 56, districtName: 'Habiganj' },
    { districtId: 57, districtName: 'Moulvibazar' },
    { districtId: 58, districtName: 'Feni' },
    { districtId: 59, districtName: 'Chapainawabganj' },
    { districtId: 60, districtName: 'Gopalganj' },
    { districtId: 61, districtName: 'Madaripur' },
    { districtId: 62, districtName: 'Shariatpur' },
    { districtId: 63, districtName: 'Tangail' },
    { districtId: 64, districtName: 'Kishoreganj' },
    { districtId: 65, districtName: 'Faridpur' },
    { districtId: 66, districtName: 'Gazipur' },
    { districtId: 67, districtName: 'Manikganj' },
    { districtId: 68, districtName: 'Narayanganj' },
    { districtId: 69, districtName: 'Rajbari' },
    { districtId: 70, districtName: 'Munshiganj' },
    { districtId: 71, districtName: 'Gopalganj' },
    { districtId: 72, districtName: 'Bhola' },
    { districtId: 73, districtName: 'Patuakhali' },
    { districtId: 74, districtName: 'Barguna' },
    { districtId: 75, districtName: 'Jhalokati' },
    { districtId: 76, districtName: 'Barisal' },
    { districtId: 77, districtName: 'Bagerhat' },
    { districtId: 78, districtName: 'Khulna' },
    { districtId: 79, districtName: 'Satkhira' },
    { districtId: 80, districtName: 'Magura' },
    { districtId: 81, districtName: 'Meherpur' },
    { districtId: 82, districtName: 'Chuadanga' },
    { districtId: 83, districtName: 'Kushtia' },
    { districtId: 84, districtName: 'Jhenaidah' },
    { districtId: 85, districtName: 'Jessore' },
    { districtId: 86, districtName: 'Narail' }
  ];

  const handleShow = () => {
    setShow(true);
  };

  const {
    register,
    handleSubmit,
    watch,
    reset,
    formState: { errors }
  } = useForm();

  // Get Single Item
  const handleShowUpdateModal = id => {
    if (id) {
      setUpdateId(id);
      setIsLoading(true);
      axios
        .get(
          process.env.REACT_APP_BASE_URL +
          `salesOfficer/getSalesOfficerById/${id}`,
          { headers: authHeader() }
        )
        .then(response => {
          if (response.data) {
            setSalesOfficerItemData(response.data);
            setIsLoading(false);
          }
        })
        .catch(error => {
          setIsLoading(false);
          console.log(error);
        });
    }
    setShowUpdateModal(true);
  };

  const onSubmit = data => {
   
    const salesOfficerData = {
      name: data.srName,
      dob: data.dateOfBirth,
      bloodGroup: data.bloodGroup,
      districtId: data.district,
      email: "string",
      companyId: data.company,
      joiningDate: data.joiningDate,
      designationId: data.designation,
      basicSalary: data.basicSalary,
      houseRent: data.houseRent,
      medicalAllowance: data.medical,
      otherAllowance: data.others,
      travellingDailyAllowance: data.TA_DA,
      phoneBill: data.phoneBill,
      meetingTravellingAllowance: data.meeting_TA,
      meetingJoiningAllowance: 0,
      mobileNumber: data.mobileNo,
      sscPassingYear: data.sscYear,
      highestDegreeId: data.H_Degree,
      bankId: data.bank,
      bankAccountNumber: data.bankA_C,
      contributionPercentage: data.contribution,
      "pcId": "string",
      ecName: data.name,
      ecPhone: data.mobileNumber,
      ecRelation: data.relation,
      distributor: {
        distributorId: data.dealerId
      },
      pustiOfficer: data.isOfficer

    };

    
    // Create Sales Officer
    if (salesOfficerData) {
      setIsLoading(true);
      const apiUrl = process.env.REACT_APP_BASE_URL + 'salesOfficer/addSalesOfficer';

      axios.post(apiUrl, salesOfficerData, { headers: authHeader() })
          .then(response => {
              setIsLoading(false);
              setShow(false);
              reset();
              setCount(count + 1);
              toast.success(response.data.message);
          })
          .catch(error => {
              setIsLoading(false);

              reset();
              console.log(error);
          });
  }

    // if (salesOfficerData) {
    //   setIsLoading(true);
    //   const url = process.env.REACT_APP_BASE_URL + 'salesOfficer/addSalesOfficer';
    //   fetch(url, {
    //     method: 'POST',
    //     headers: authHeader(),
    //     body: JSON.stringify(salesOfficerData)
    //   })
    //     .then(res => res.json())
    //     .then(data => {
    //       if (data) {
    //         setIsLoading(false);
    //         setShow(false);
    //         reset();
    //         setCount(count + 1);
    //         toast.success(data.message);
    //       }
    //     })
    //     .catch(error => {
    //       setIsLoading(false);
    //       reset();
    //       console.log(error);
    //     });
    // }

    setData(data);
  };

  // Load All National Data
  useEffect(() => {
    setIsLoading(true);
    const url =
      process.env.REACT_APP_BASE_URL + 'distributor/getAllDistributors';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          setAllDistributor(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Load All Bank Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + 'bank/getAllBanks';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          setBank(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Load All National Data
  useEffect(() => {
    setIsLoading(true);
    const url =
      process.env.REACT_APP_BASE_URL + 'designation/getAllDesignations';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          setDesignation(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Load Division Data
  useEffect(() => {
    setIsLoading(true);
    const url =
      process.env.REACT_APP_BASE_URL + 'salesOfficer/getAllSalesOfficers';
    axios
      .get(url, { headers: authHeader() })
      .then(response => {
        if (response.data) {
          setAllSalesOfficer(response?.data);
          setIsLoading(false);
        }
      })
      .catch(error => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

  // Update Division Data
  const handleBankSubmit = (values, actions) => {
    const salesOfficerUpdateInfo = {
      distributor: { distributorId: values.updateDealerId },
      name: values.updateSrName,
      dob: values.updateDateOfBirth,
      bloodGroup: values.updateBloodGroup,
      districtId: values.updateDistrict,
      email: values.updateMail,
      companyId: values.updateCompany,
      joiningDate: values.updateJoiningDate,
      designationId: values.updateDesignation,
      basicSalary: values.updateBasicSalary,
      houseRent: values.updateHouseRent,
      medicalAllowance: values.updateMedical,
      otherAllowance: values.updateOthers,
      travellingDailyAllowance: values.updateTA_DA,
      phoneBill: values.updatePhoneBill,
      meetingTravellingAllowance: values.updateMeeting_TA,
      mobileNumber: values.updateMobileNo,
      sscPassingYear: values.updateSscYear,
      highestDegreeId: values.updateH_Degree,
      pustiOfficer: values.updateIsOfficer,
      bankId: values.updateBank,
      bankAccountNumber: values.updateBankA_C,
      contributionPercentage: values.updateContribution,
      textDataID: values.updateTextDataID,
      ecName: values.updateName,
      ecPhone: values.updateMobileNumber,
      ecRelation: values.updateRelation
    };

    console.log(salesOfficerUpdateInfo);

    if (salesOfficerUpdateInfo && updateId) {
      setIsLoading(true);
      const url =
        process.env.REACT_APP_BASE_URL +
        `salesOfficer/updateSalesOfficer/${updateId}`;
      axios
        .put(url, salesOfficerUpdateInfo, { headers: authHeader() })
        .then(response => {
          console.log(response)
          if (response.data) {
            setIsLoading(false);
            setShowUpdateModal(false);
            setCount(count + 1);
            toast.success('Update Success');
          }
        })
        .catch(error => {
          setIsLoading(false);
          console.log(error);
        });
    }
  };

  // Delete Division
  const DeleteBankItem = id => {
    if (confirm('Are You Sure ?')) {
      setIsLoading(true);
      axios
        .delete(
          process.env.REACT_APP_BASE_URL +
          `salesOfficer/deleteSalesOfficerById/${id}`,
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
          console.log(error);
          setIsLoading(false);
        });
    }
  };

  if (isLoading) {
    return <h4>Loading...</h4>;
  }

  /* 
  ..........................
  Columns Data here
  .........................
  */

  const columns = [
    {
      accessor: 'districtId',
      Header: 'District ID',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { districtId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{districtId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },

    {
      accessor: 'designationId',
      Header: 'Designation',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { designationId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{designationId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'name',
      Header: 'Name',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { name } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{name}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'companyId',
      Header: 'Company',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { companyId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{companyId}</h5>
            </div>
          </Flex>
          // </Link>
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
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{email}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'pustiOfficer',
      Header: 'Officer',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { pustiOfficer } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">
                {pustiOfficer === true ? 'Yes' : 'No'}
              </h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'joiningDate',
      Header: 'Joining Date',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { joiningDate } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{joiningDate}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'basicSalary',
      Header: 'Basic Salary',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { basicSalary } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{basicSalary}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'houseRent',
      Header: 'House Rent',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { houseRent } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{houseRent}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'medicalAllowance',
      Header: 'Medical Allowance',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { medicalAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{medicalAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'otherAllowance',
      Header: 'Other Allowance',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { otherAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{otherAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'travellingDailyAllowance',
      Header: 'Traveling Daily Allowance',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { travellingDailyAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{travellingDailyAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'phoneBill',
      Header: 'Phone Bill',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { phoneBill } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{phoneBill}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'meetingJoiningAllowance',
      Header: 'Meeting Joining Allowance',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { meetingJoiningAllowance } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{meetingJoiningAllowance}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'mobileNumber',
      Header: 'Mobile Number',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { mobileNumber } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{mobileNumber}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'sscPassingYear',
      Header: 'SSC Passing Year',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { sscPassingYear } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{sscPassingYear}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'highestDegreeId',
      Header: 'Highest Degree Id',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { highestDegreeId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{highestDegreeId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'bankId',
      Header: 'Bank Id',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { bankId } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{bankId}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'bankAccountNumber',
      Header: 'Bank Account Number',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { bankAccountNumber } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{bankAccountNumber}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'contributionPercentage',
      Header: 'Contribution Percentage',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { contributionPercentage } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{contributionPercentage}</h5>
            </div>
          </Flex>
          // </Link>
        );
      }
    },
    {
      accessor: 'distributor',
      Header: 'Distributor',
      headerProps: { className: 'pe-1' },
      cellProps: {
        className: 'py-2'
      },
      Cell: rowData => {
        const { distributor } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{distributor?.name}</h5>
            </div>
          </Flex>
          // </Link>
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
        const { distributor } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{distributor?.mobile}</h5>
            </div>
          </Flex>
          // </Link>
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
        const { salesOfficerId, status } = rowData.row.original;

        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={
                  process.env.REACT_APP_BASE_URL +
                  `salesOfficer/statusChange/${salesOfficerId}`
                }
              ></ToggleButton>
              {/* <h5 className="mb-0 fs--1">{status === true ? "Active" : "InActive"}</h5> */}
            </div>
          </Flex>
          // </Link>
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
        const { salesOfficerId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => handleShowUpdateModal(salesOfficerId)}
                >
                  Edit
                </Button>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {' '}
                <Button
                  variant="light"
                  size="sm"
                  onClick={() => DeleteBankItem(salesOfficerId)}
                >
                  Delete
                </Button>
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
        data={allSalesOfficer}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Sales Officer"
              handleShow={handleShow}
              data={allSalesOfficer}
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

      <Modal show={show} fullscreen={fullscreen} onHide={() => setShow(false)}>
        <Modal.Header>
          <Modal.Title>Create SO/SR</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShow(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Form noValidate onSubmit={handleSubmit(onSubmit)}>
            <Row>
              <Form.Group
                as={Col}
                md="5"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>Select Dealer</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="select"
                  name="dealerId"
                  isInvalid={!!errors.dealerId}
                  {...register('dealerId', {
                    required: 'Dealer field is required'
                  })}
                >
                  <option defaultChecked>Select Dealer</option>
                  {allDistributor?.map((item, i) => (
                    <option key={i} value={item?.distributorId}>
                      {item?.name}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.dealerId && errors.dealerId.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="4" className="mb-3" controlId="email">
                <Form.Label>SR Name</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter SR Name"
                  name="srName"
                  isInvalid={!!errors.srName}
                  {...register('srName', {
                    required: 'SR Name field is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.srName && errors.srName.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Joining Date</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="date"
                  placeholder="Enter joining Date"
                  name="joiningDate"
                  isInvalid={!!errors.joiningDate}
                  {...register('joiningDate', {
                    required: 'Joining Date is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.joiningDate && errors.joiningDate.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group
                as={Col}
                md="3"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>Select Designation</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="select"
                  name="designation"
                  isInvalid={!!errors.designation}
                  {...register('designation', {
                    required: 'Designation field is required'
                  })}
                >
                  <option defaultChecked>Select Designation</option>
                  {designation?.map((item, i) => (
                    <option key={i} value={item?.designationId}>
                      {item?.name}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.designation && errors.designation.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Basic Salary</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Basic Salary"
                  name="basicSalary"
                  isInvalid={!!errors.basicSalary}
                  {...register('basicSalary', {
                    required: 'Basic Salary is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.basicSalary && errors.basicSalary.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>House Rent</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter House Rent"
                  name="houseRent"
                  isInvalid={!!errors.houseRent}
                  {...register('houseRent', {
                    required: 'House Rent is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.houseRent && errors.houseRent.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Medical</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Medical"
                  name="medical "
                  isInvalid={!!errors.medical}
                  {...register('medical', {
                    required: 'Medical is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.medical && errors.medical.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Others</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Others "
                  name="others"
                  isInvalid={!!errors.others}
                  {...register('others', {
                    required: 'Others is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.others && errors.others.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>TA/DA</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter TA/DA "
                  name="TA_DA"
                  isInvalid={!!errors.TA_DA}
                  {...register('TA_DA', {
                    required: 'TA/DA is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.TA_DA && errors.TA_DA.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Phone Bill </Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Phone Bill  "
                  name="phoneBill"
                  isInvalid={!!errors.phoneBill}
                  {...register('phoneBill', {
                    required: 'Phone Bill  is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.phoneBill && errors.phoneBill.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Meeting TA</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Meeting TA"
                  name="meeting_TA"
                  isInvalid={!!errors.meeting_TA}
                  {...register('meeting_TA', {
                    required: 'Meeting TA  is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.meeting_TA && errors.meeting_TA.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Mobile No</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Mobile No"
                  name="mobileNo"
                  isInvalid={!!errors.mobileNo}
                  {...register('mobileNo', {
                    required: 'Mobile No  is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.mobileNo && errors.mobileNo.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>SSC Year</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter SSC Year"
                  name="sscYear"
                  isInvalid={!!errors.sscYear}
                  {...register('sscYear', {
                    required: 'SSC Year  is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.sscYear && errors.sscYear.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>H Degree:</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter H Degree:"
                  name="H_Degree:"
                  isInvalid={!!errors.H_Degree}
                  {...register('H_Degree', {
                    required: 'H Degree: Year  is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.H_Degree && errors.H_Degree.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Date of Birth</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="date"
                  placeholder="Enter Date of Birth"
                  name="dateOfBirth"
                  isInvalid={!!errors.H_Degree}
                  {...register('dateOfBirth', {
                    required: 'Date of Birth  is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.dateOfBirth && errors.dateOfBirth.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Blood Group</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Blood Group"
                  name="bloodGroup:"
                  isInvalid={!!errors.bloodGroup}
                  {...register('bloodGroup', {
                    required: 'Blood Group is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.bloodGroup && errors.bloodGroup.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group
                as={Col}
                md="3"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>Select District</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="select"
                  name="district"
                  isInvalid={!!errors.district}
                  {...register('district', {
                    required: 'District is required'
                  })}
                >
                  <option defaultChecked>Select District</option>
                  {bangladeshDistricts?.map((item, i) => (
                    <option key={i} value={item.districtId}>
                      {item.districtName}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.district && errors.district.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Mail</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="email"
                  placeholder="Enter Your Mail"
                  name="mail"
                  isInvalid={!!errors.mail}
                  {...register('mail', {
                    required: 'Mail is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.mail && errors.mail.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Company</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Your Company"
                  name="company"
                  isInvalid={!!errors.company}
                  {...register('company', {
                    required: 'Company is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.company && errors.company.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Pusti Officer</Form.Label>
                <Form.Check
                  label="Is Pusti Officer"
                  type="checkbox"
                  name="isOfficer"
                  {...register('isOfficer')}
                />
              </Form.Group>
              <Form.Group
                as={Col}
                md="3"
                className="mb-3"
                controlId="formHookname"
              >
                <Form.Label>Select Bank</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="select"
                  name="bank"
                  isInvalid={!!errors.bank}
                  {...register('bank', {
                    required: 'Bank is required'
                  })}
                >
                  <option defaultChecked>Select Bank</option>
                  {bank?.map((item, i) => (
                    <option key={i} value={item?.bankId}>
                      {item?.bankName}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">
                  {errors.bank && errors.bank.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Bank A/C No</Form.Label>
                <Form.Control
                  type="text"
                  placeholder="Enter Bank A/C No"
                  name="bankA_C"
                  isInvalid={!!errors.bankA_C}
                  {...register('bankA_C', {
                    required: 'Bank A/C No is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.bankA_C && errors.bankA_C.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Contribution %</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Contribution %"
                  name="contribution:"
                  isInvalid={!!errors.contribution}
                  {...register('contribution', {
                    required: 'Contribution is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.contribution && errors.contribution.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Text Data ID</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Text Data ID"
                  name="textDataID:"
                  isInvalid={!!errors.textDataID}
                  {...register('textDataID', {
                    required: 'Text Data ID is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.textDataID && errors.textDataID.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Label>Emergency Contact Details :</Form.Label>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Name</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Your Name"
                  name="name"
                  isInvalid={!!errors.name}
                  {...register('name', {
                    required: 'Name is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.name && errors.name.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Mobile</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter Your Number"
                  name="mobileNumber"
                  isInvalid={!!errors.mobileNumber}
                  {...register('mobileNumber', {
                    required: 'Mobile Number is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.mobileNumber && errors.mobileNumber.message}
                </Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="3" className="mb-3" controlId="email">
                <Form.Label>Relation</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Relation"
                  name="relation"
                  isInvalid={!!errors.relation}
                  {...register('relation', {
                    required: 'Relation is required'
                  })}
                />
                <Form.Control.Feedback type="invalid">
                  {errors.relation && errors.relation.message}
                </Form.Control.Feedback>
              </Form.Group>
            </Row>

            <Button
              variant="primary"
              type="submit"
            //   disabled={
            //     !watch().divisionName ||
            //     watch().nationalName == 'Select National'
            //   }
            >
              Submit
            </Button>
          </Form>

          {/* <Col md="auto">
              <h5 className="mt-4">Result</h5>
              <pre>{JSON.stringify(formData, null, 2)}</pre>
            </Col> */}
        </Modal.Body>
      </Modal>
      <Modal
        show={showUpdateModal}
        fullscreen={fullscreen}
        onHide={() => setShow(false)}
      >
        <Modal.Header>
          <Modal.Title>Sales Officer Update</Modal.Title>
          <CloseButton
            className="btn btn-circle btn-sm transition-base p-0"
            onClick={() => setShowUpdateModal(false)}
          />
        </Modal.Header>
        <Modal.Body>
          <Formik
            initialValues={{
              updateDealerId: salesOfficerItemData?.distributor?.distributorId,
              updateSrName: salesOfficerItemData.name,
              updateDateOfBirth: salesOfficerItemData.dob,
              updateBloodGroup: salesOfficerItemData.bloodGroup,
              updateDistrict: salesOfficerItemData.districtId,
              updateMail: salesOfficerItemData.email,
              updateCompany: salesOfficerItemData.companyId,
              updateJoiningDate: salesOfficerItemData.joiningDate,
              updateDesignation: salesOfficerItemData.designationId,
              updateBasicSalary: salesOfficerItemData.basicSalary,
              updateHouseRent: salesOfficerItemData.houseRent,
              updateMedical: salesOfficerItemData.medicalAllowance,
              updateOthers: salesOfficerItemData.otherAllowance,
              updateTA_DA: salesOfficerItemData.travellingDailyAllowance,
              updatePhoneBill: salesOfficerItemData.phoneBill,
              updateMeeting_TA: salesOfficerItemData.meetingTravellingAllowance,
              updateMobileNo: salesOfficerItemData.mobileNumber,
              updateSscYear: salesOfficerItemData.sscPassingYear,
              updateH_Degree: salesOfficerItemData.highestDegreeId,
              updateIsOfficer: salesOfficerItemData.pustiOfficer,
              updateBank: salesOfficerItemData.bankId,
              updateBankA_C: salesOfficerItemData.bankAccountNumber,
              updateContribution: salesOfficerItemData.contributionPercentage,
              updateTextDataID: salesOfficerItemData.textDataID,
              updateName: salesOfficerItemData.ecName,
              updateMobileNumber: salesOfficerItemData.ecPhone,
              updateRelation: salesOfficerItemData.ecRelation
            }}
            // validationSchema={loginSchema}
            onSubmit={handleBankSubmit}
          >
            {({
              errors,
              touched,
              handleSubmit,
              handleChange,
              handleBlur,
              values
            }) => (
              // <Form onSubmit={handleFormSubmit} >
              <Form noValidate onSubmit={handleSubmit}>
                <Row>
                  <Form.Group
                    as={Col}
                    md="5"
                    className="mb-3"
                    controlId="formHookname"
                  >
                    <Form.Label>Select Dealer</Form.Label>
                    <Form.Select
                      aria-label="Default select example"
                      type="select"
                      name="updateDealerId"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateDealerId}
                    >
                      <option defaultChecked>Select Dealer</option>
                      {allDistributor?.map((item, i) => (
                        <option key={i} value={item?.distributorId}>
                          {item?.name}
                        </option>
                      ))}
                    </Form.Select>
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="4"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>SR Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter SR Name"
                      name="updateSrName"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateSrName}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Joining Date</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="date"
                      placeholder="Enter joining Date"
                      name="updateJoiningDate"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateJoiningDate}
                    />
                  </Form.Group>
                </Row>
                <Row>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="formHookname"
                  >
                    <Form.Label>Select Designation</Form.Label>
                    <Form.Select
                      aria-label="Default select example"
                      type="select"
                      name="updateDesignation"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateDesignation}
                    >
                      <option defaultChecked>Select Designation</option>
                      {designation?.map((item, i) => (
                        <option key={i} value={item?.designationId}>
                          {item?.name}
                        </option>
                      ))}
                    </Form.Select>
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Basic Salary</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Basic Salary"
                      name="updateBasicSalary"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateBasicSalary}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>House Rent</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter House Rent"
                      name="updateHouseRent"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateHouseRent}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Medical</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Medical"
                      name="updateMedical "
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateMedical}
                    />
                  </Form.Group>
                </Row>
                <Row>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Others</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Others "
                      name="updateOthers"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateOthers}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>TA/DA</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter TA/DA "
                      name="updateTA_DA"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateTA_DA}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Phone Bill </Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Phone Bill  "
                      name="updatePhoneBill"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updatePhoneBill}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Meeting TA</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Meeting TA"
                      name="updateMeeting_TA"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateMeeting_TA}
                    />
                  </Form.Group>
                </Row>
                <Row>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Mobile No</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Mobile No"
                      name="updateMobileNo"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateMobileNo}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>SSC Year</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter SSC Year"
                      name="updateSscYear"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateSscYear}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>H Degree:</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter H Degree:"
                      name="updateH_Degree"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateH_Degree}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Date of Birth</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="date"
                      placeholder="Enter Date of Birth"
                      name="updateDateOfBirth"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateDateOfBirth}
                    />
                  </Form.Group>
                </Row>
                <Row>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Blood Group</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Blood Group"
                      name="updateBloodGroup"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateBloodGroup}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="formHookname"
                  >
                    <Form.Label>Select District</Form.Label>
                    <Form.Select
                      aria-label="Default select example"
                      type="select"
                      name="updateDistrict"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateDistrict}
                    >
                      <option defaultChecked>Select District</option>
                      {bangladeshDistricts?.map((item, i) => (
                        <option key={i} value={item.districtId}>
                          {item.districtName}
                        </option>
                      ))}
                    </Form.Select>
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Mail</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="email"
                      placeholder="Enter Your Mail"
                      name="updateMail"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateMail}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Company</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Your Company"
                      name="updateCompany"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateCompany}
                    />
                  </Form.Group>
                </Row>
                <Row>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Pusti Officer</Form.Label>
                    <Form.Check
                      label="Is Pusti Officer"
                      type="checkbox"
                      name="updateIsOfficer"
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateIsOfficer}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="formHookname"
                  >
                    <Form.Label>Select Bank</Form.Label>
                    <Form.Select
                      aria-label="Default select example"
                      type="select"
                      name="updateBank"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateBank}
                    >
                      <option defaultChecked>Select Bank</option>
                      {bank?.map((item, i) => (
                        <option key={i} value={item?.bankId}>
                          {item?.bankName}
                        </option>
                      ))}
                    </Form.Select>
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Bank A/C No</Form.Label>
                    <Form.Control
                      type="number"
                      placeholder="Enter Bank A/C No"
                      name="updateBankA_C"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateBankA_C}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Contribution %</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Contribution %"
                      name="updateContribution"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateContribution}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Text Data ID</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Text Data ID"
                      name="updateTextDataID"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateTextDataID}
                    />
                  </Form.Group>
                </Row>
                <Row>
                  <Form.Label>Emergency Contact Details :</Form.Label>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Your Name"
                      name="updateName"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateName}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Mobile</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="number"
                      placeholder="Enter Your Number"
                      name="updateMobileNumber"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateMobileNumber}
                    />
                  </Form.Group>
                  <Form.Group
                    as={Col}
                    md="3"
                    className="mb-3"
                    controlId="email"
                  >
                    <Form.Label>Relation</Form.Label>
                    <Form.Control
                      aria-label="Default select example"
                      type="text"
                      placeholder="Enter Relation"
                      name="updateRelation"
                      required
                      onChange={handleChange}
                      onBlur={handleBlur}
                      value={values.updateRelation}
                    />
                  </Form.Group>
                </Row>

                <Button
                  variant="primary"
                  type="submit"
                //   disabled={
                //     !watch().divisionName ||
                //     watch().nationalName == 'Select National'
                //   }
                >
                  Submit
                </Button>
              </Form>
            )}
          </Formik>

          {/* <Col md="auto">
          <h5 className="mt-4">Result</h5>
          <pre>{JSON.stringify(formData, null, 2)}</pre>
        </Col> */}
        </Modal.Body>
      </Modal>
    </>
  );
};

export default SalesOfficer;

// required
// onChange={handleChange}
// onBlur={handleBlur}
// value={values.updateNationalId}
