import CustomersTableHeader from "components/app/e-commerce/customers/CustomersTableHeader";
import Flex from "components/common/Flex";
import AdvanceTable from "components/common/advance-table/AdvanceTable";
import AdvanceTablePagination from "components/common/advance-table/AdvanceTablePagination";
import AdvanceTableWrapper from "components/common/advance-table/AdvanceTableWrapper";
import React, { useEffect, useState } from "react";
import cloudUpload from "assets/img/icons/cloud-upload.svg";
import { Button, Card, CloseButton, Col, Dropdown, Form, Modal, Row } from "react-bootstrap";
import { useForm } from "react-hook-form";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import { Formik } from "formik";
import ToggleButton from "components/common/Toggle-button/index";
import { Link } from "react-router-dom";
import { authHeaderForm } from "state/ducs/auth/utils";
import { useDropzone } from "react-dropzone";
import FalconCloseButton from "components/common/FalconCloseButton";

const Product = () => {
  const [allProductsData, setAllProductsData] = useState([]);
  const [productItemData, setProductItemData] = useState([]);
  const [allCategorysTypes, setAllCategorysTypes] = useState([]);
  const [allCategorys, setAllCategorys] = useState([]);
  const [categorys, setCategorys] = useState([]);
  const [allBrands, setAllBrands] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [fullscreen, setFullscreen] = useState("xxl-down");
  const [show, setShow] = useState(false);
  const [showUploadCSV, setShowUploadCSV] = useState(false);
  const [formData, setData] = useState({});
  const [showUpdateModal, setShowUpdateModal] = useState(false);
  const [updateId, setUpdateId] = useState(null);
  const [count, setCount] = useState(0);
  const [viewDetails, setViewDetails] = useState(false);
  const [productData, setProductData] = useState(null);


  const handleShow = () => {
    setShow(true);
  };

  // csv

  const handleShowCSV = () => {
    setShowUploadCSV(true);
  };

  const {
    register,
    handleSubmit,
    watch,
    reset,
    setValue,
    formState: { errors },
  } = useForm();

  // Get Single Item
  const handleShowUpdateModal = (id) => {
    if (id) {
      setUpdateId(id);
      setIsLoading(true);
      axios
        .get(process.env.REACT_APP_BASE_URL + `product/getProductBy/${id}`, { headers: authHeader() })
        .then((response) => {
          if (response.data) {
            setProductItemData(response.data);
            setIsLoading(false);
          }
        })
        .catch((error) => {
          setIsLoading(false);
          console.log(error);
        });
    }
    setShowUpdateModal(true);
  };


    // View Details
    const handleViewDetails = (id) => {
        setViewDetails(true);
        const url = process.env.REACT_APP_BASE_URL +`product/getProductBy/${id}`;
        axios
          .get(url, { headers: authHeader() })
          .then((response) => {
            setProductData(response.data);
          })
          .catch((error) => console.log(error));
          
      }

      console.log('all product',productData);
     

      
  const onSubmit = (data) => {
    const productInfo = {
      name: data.productName,
      bengaliName: data.productNameInBangla,
      distributionPrice: data.DP,
      tradePrice: data.TP,
      unitId: data.unitId,
      weight: data.weight,
      piecePerSale: data.piecePerSale,
      piecePerCTN: data.piecePerCTN,
      openingDate: data.openingDate,
      erpid: data.ERP_ID,
      category: {
        id: data.categoryId,
        categoryType: {
          categoryTypeId: data.categoryTypeId,
        },
      },
    };

 

    // Create Product
    if (productInfo) {
      setIsLoading(true);
      const apiUrl = process.env.REACT_APP_BASE_URL + "product/addNewProduct";

      axios
        .post(apiUrl, productInfo, { headers: authHeader() })
        .then((response) => {
          setIsLoading(false);
          setShow(false);
          reset();
          setCount(count + 1);
          toast.success(response.data.message);
        })
        .catch((error) => {
          setIsLoading(false);

          reset();
          console.log(error);
        });
    }

    setData(data);
  };

  // Load All Category type Data
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "categoryType/getAllCategoryTypes";
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        if (response.data) {
          setAllCategorysTypes(response?.data);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + `category/getAllCategories`;
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        if (response.data) {
          setAllCategorys(response?.data);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Filter Category By Id
  const categoryTypeChangeHandler = (id) => {
    const categoryFilter = allCategorys.filter((item) => item.categoryType.categoryTypeId == id);
    setCategorys(categoryFilter);
  };

  //   Get all brands
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "brand/getAllBrands";
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        if (response.data) {
          setAllBrands(response?.data);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, []);

  // Load All Product Info
  useEffect(() => {
    setIsLoading(true);
    const url = process.env.REACT_APP_BASE_URL + "product/getAllProducts";
    axios
      .get(url, { headers: authHeader() })
      .then((response) => {
        console.log("response", response);
        if (response.data) {
          const result = [];
          let index = 1;
          response?.data?.response?.forEach((element) => {
            const allProductsData = { index, ...element };
            result.push(allProductsData);
            index++;
          });
          setAllProductsData(result);
          setIsLoading(false);
        }
      })
      .catch((error) => {
        setIsLoading(false);
        console.log(error);
      });
  }, [count]);

//   console.log("product date", allProductsData);

  // Update Product
  const handleProductUpdate = (values, actions) => {
    const updateProductInfo = {
      name: values.updateProductName,
      bengaliName: values.updateProductNameInBangla,
      distributionPrice: values.updateDP,
      category: {
        id: values.updateCategoryId,
        categoryType: {
          categoryTypeId: values.updateCategoryTypeId,
        },
      },

      openingDate: values.updateOpeningDate,
      piecePerCTN: values.updatePiecePerCTN,
      piecePerSale: values.updatePiecePerSale,
      weight: values.updateWeight,
      unitId: values.updateUnitId,
      erpid: values.updateERP_ID,
      tradePrice: values.updateTP,
    };

    if (updateProductInfo && updateId) {
      setIsLoading(true);
      const url = process.env.REACT_APP_BASE_URL + `product/updateProduct/${updateId}`;
      axios
        .put(url, updateProductInfo, { headers: authHeader() })
        .then((response) => {
          if (response.data) {
            setIsLoading(false);
            setShowUpdateModal(false);
            setCount(count + 1);
            toast.success("Update Success");
          }
        })
        .catch((error) => {
          setIsLoading(false);
          console.log(error);
        });
    }
  };

  // Delete Product2
  const DeleteProduct = (id) => {
    if (confirm("Are You Sure ?")) {
      setIsLoading(true);
      axios
        .delete(process.env.REACT_APP_BASE_URL + `products/delete/${id}`, { headers: authHeader() })
        .then((response) => {
          if (response) {
            setIsLoading(false);
            toast.success("Delete Success");
            setCount(count + 1);
          }
        })
        .catch((error) => {
          console.log(error);
          setIsLoading(false);
        });
    }
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

    const regUrl = process.env.REACT_APP_BASE_URL + "product/csv-upload";

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
      .catch((error) => {
        setIsLoading(false);
        console.log("errors", error);
        if (error) {
          toast.error("Error occurred: ", error.message);
        }
      });
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
      accessor: "index",
      Header: "SN",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { index } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{index}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },
    {
      accessor: "CategoryTypeName",
      Header: "Category Type Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { category } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{category?.categoryType?.name}</h5>
            </div>
          </Flex>
          // </Link>
        );
      },
    },

    {
      accessor: "CategoryName",
      Header: "Category Name",
      headerProps: { className: "pe-1" },
      cellProps: {
        className: "py-2",
      },
      Cell: (rowData) => {
        const { category } = rowData.row.original;
        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <h5 className="mb-0 fs--1">{category?.name}</h5>
            </div>
          </Flex>
          // </Link>
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
        const { productId, status } = rowData.row.original;

        return (
          // <Link to="/e-commerce/customer-details">
          <Flex alignItems="center">
            <div className="flex-1">
              <ToggleButton
                count={count}
                setCount={setCount}
                status={status}
                url={process.env.REACT_APP_BASE_URL + `product/changeStatus/${productId}`}
              ></ToggleButton>
              {/* <h5 className="mb-0 fs--1">{status === true ? "Active" : "InActive"}</h5> */}
            </div>
          </Flex>
          // </Link>
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
        const { productId } = rowData.row.original;
        return (
          <Dropdown className="d-inline mx-2">
            <Dropdown.Toggle id="dropdown-autoclose-true" className=" bg-none">
              ...
            </Dropdown.Toggle>

            <Dropdown.Menu>
              <Dropdown.Item href="">
                <Link to={`/master/product/add/${productId}`}>
                  <Button variant="light" size="sm">
                    Edit
                  </Button>
                </Link>
              </Dropdown.Item>
              <Dropdown.Item href="">
                {" "}
                <Button variant="light" size="sm" onClick={() => handleViewDetails(productId)}>
                  View
                </Button>
              </Dropdown.Item>
              {/* <Dropdown.Item href="">
                                {' '}
                                <Button
                                    variant="light"
                                    size="sm"
                                    onClick={() => DeleteProduct(productId)}
                                >
                                    Delete
                                </Button>
                            </Dropdown.Item> */}
            </Dropdown.Menu>
          </Dropdown>
        );
      },
    },
  ];

  return (
    <>
      <AdvanceTableWrapper
        columns={columns}
        data={allProductsData}
        // selection
        sortable
        pagination
        perPage={10}
      >
        <Card className="mb-3">
          <Card.Header>
            <CustomersTableHeader
              title="Product"
              newUrl="/master/product/add"
              buttonTitle="Upload CSV"
              excelUrl="product/exel-download"
              excelFileName="Product.xlsx"
              handleShowCSV={handleShowCSV}
              handleShow={handleShow}
              data={allProductsData}
              table
            />
          </Card.Header>
          <Card.Body className="p-0">
            <AdvanceTable
              table
              headerClassName="bg-200 text-900 text-nowrap align-middle"
              rowClassName="align-middle white-space-nowrap"
              tableProps={{
                size: "sm",
                striped: true,
                className: "fs--1 mb-0 overflow-hidden",
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
          <Modal.Title>Create Product</Modal.Title>
          <CloseButton className="btn btn-circle btn-sm transition-base p-0" onClick={() => setShow(false)} />
        </Modal.Header>
        <Modal.Body>
          <Form noValidate onSubmit={handleSubmit(onSubmit)}>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                <Form.Label>Select Category Type :</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="text"
                  name="categoryTypeId"
                  onClick={() => categoryTypeChangeHandler(watch().categoryTypeId)}
                  isInvalid={!!errors.categoryTypeId}
                  {...register("categoryTypeId", {
                    required: "Category Type is required",
                  })}
                >
                  <option defaultChecked>Select Category Type</option>
                  {allCategorysTypes?.map((item, i) => (
                    <option key={i} value={item?.categoryTypeId}>
                      {item?.name}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.categoryTypeId && errors.categoryTypeId.message}</Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                <Form.Label>Select Category :</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="text"
                  name="categoryId"
                  isInvalid={!!errors.categoryId}
                  {...register("categoryId", {
                    required: "Category Id is required",
                  })}
                >
                  <option defaultChecked>Select Category</option>
                  {categorys?.map((item, i) => (
                    <option key={i} value={item?.id}>
                      {item?.name}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.categoryId && errors.categoryId.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                <Form.Label>Select Brand :</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="text"
                  name="brandId"
                  isInvalid={!!errors.brandId}
                  {...register("brandId", {
                    required: "Brand is required",
                  })}
                >
                  <option defaultChecked>Select Brand</option>
                  {allBrands?.map((item, i) => (
                    <option key={i} value={item?.brandId}>
                      {item?.brandName}
                    </option>
                  ))}
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.brandId && errors.brandId.message}</Form.Control.Feedback>
              </Form.Group>

              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Product Name :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Product Name"
                  name="productName"
                  isInvalid={!!errors.productName}
                  {...register("productName", {
                    required: "Product Name is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.productName && errors.productName.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Product Name in Bangla :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Product Name in Bangla"
                  name="productNameInBangla"
                  isInvalid={!!errors.productNameInBangla}
                  {...register("productNameInBangla", {
                    required: "Product Name Banla is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.productNameInBangla && errors.productNameInBangla.message}</Form.Control.Feedback>
              </Form.Group>

              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Distribution Price :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Distribution Price"
                  name="DP"
                  isInvalid={!!errors.DP}
                  {...register("DP", {
                    required: "Distribution Price field is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.DP && errors.DP.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Trade Price :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Trade Price"
                  name="TP"
                  isInvalid={!!errors.TP}
                  {...register("TP", {
                    required: "Trade Price field is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.TP && errors.TP.message}</Form.Control.Feedback>
              </Form.Group>

              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>ERP ID :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="number"
                  placeholder="Enter ERP ID"
                  name="ERP_ID"
                  isInvalid={!!errors.ERP_ID}
                  {...register("ERP_ID", {
                    required: "ERP ID is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.ERP_ID && errors.ERP_ID.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                <Form.Label>Unit :</Form.Label>
                <Form.Select
                  aria-label="Default select example"
                  type="text"
                  name="unitId"
                  isInvalid={!!errors.unitId}
                  {...register("unitId", {
                    required: "Unit is required",
                  })}
                >
                  <option defaultChecked>Select Unit</option>
                  <option value={"Box"}>Box</option>
                  <option value={"Jar"}>Jar</option>
                  <option value={"Pcs"}>Pcs</option>
                  <option value={"Pouch"}>Pouch</option>
                </Form.Select>
                <Form.Control.Feedback type="invalid">{errors.unitId && errors.unitId.message}</Form.Control.Feedback>
              </Form.Group>

              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Weight/Pcs (Gram) :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Weight/Pcs"
                  name="weight"
                  isInvalid={!!errors.weight}
                  {...register("weight", {
                    required: "Weight/Pcs is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.weight && errors.weight.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>
            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Pcs/Sales Pack :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Pcs/Sales Pack"
                  name="piecePerSale"
                  isInvalid={!!errors.piecePerSale}
                  {...register("piecePerSale", {
                    required: "Pcs/Sales Pack is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.piecePerSale && errors.piecePerSale.message}</Form.Control.Feedback>
              </Form.Group>
              <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                <Form.Label>Pcs/CTN :</Form.Label>
                <Form.Control
                  aria-label="Default select example"
                  type="text"
                  placeholder="Enter Pcs/CTN"
                  name="piecePerCTN"
                  isInvalid={!!errors.piecePerCTN}
                  {...register("piecePerCTN", {
                    required: "Pcs/CTN Pack is required",
                  })}
                />
                <Form.Control.Feedback type="invalid">{errors.piecePerCTN && errors.piecePerCTN.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>

            <Row>
              <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                <Form.Label>Product Open Date :</Form.Label>
                <Form.Control
                  type="date"
                  name="openingDate"
                  isInvalid={!!errors.openingDate}
                  {...register("openingDate", {
                    required: "Opening Date is required",
                  })}
                ></Form.Control>
                <Form.Control.Feedback type="invalid">{errors.openingDate && errors.openingDate.message}</Form.Control.Feedback>
              </Form.Group>
            </Row>

            <Button
              variant="primary"
              type="submit"
              disabled={
                watch().categoryId == "Select Category" ||
                watch().brandId == "Select Brand" ||
                watch().categoryTypeId == "Select Category Type" ||
                !watch().productName ||
                !watch().productNameInBangla ||
                !watch().DP ||
                !watch().TP ||
                !watch().ERP_ID ||
                !watch().unitId ||
                !watch().weight ||
                !watch().piecePerSale ||
                !watch().piecePerCTN ||
                !watch().openingDate
              }
            >
              Submit
            </Button>
          </Form>
        </Modal.Body>
      </Modal>
      <Modal show={showUpdateModal} fullscreen={fullscreen} onHide={() => setShow(false)}>
        <Modal.Header>
          <Modal.Title>Product Update</Modal.Title>
          <CloseButton className="btn btn-circle btn-sm transition-base p-0" onClick={() => setShowUpdateModal(false)} />
        </Modal.Header>
        <Modal.Body>
          <Formik
            initialValues={{
              updateProductName: productItemData.name,
              updateProductNameInBangla: productItemData.bengaliName,
              updateTP: productItemData.tradePrice,
              updateDP: productItemData.distributionPrice,
              // updateBrandId: productItemData.,
              updateCategoryId: productItemData?.category?.id,
              updateCategoryTypeId: productItemData.category?.categoryType?.categoryTypeId,
              updateOpeningDate: productItemData.openingDate,
              updatePiecePerCTN: productItemData.piecePerCTN,
              updatePiecePerSale: productItemData.piecePerSale,
              updateWeight: productItemData.weight,
              updateUnitId: productItemData.unitId,
              updateERP_ID: productItemData.erpid,
            }}
            // validationSchema={loginSchema}
            onSubmit={handleProductUpdate}
          >
            {({ errors, touched, handleSubmit, handleChange, handleBlur, values }) => {
              return (
                // <Form onSubmit={handleFormSubmit} >

                <Form onSubmit={handleSubmit}>
                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                      <Form.Label>Select Category Type :</Form.Label>
                      <Form.Select
                        aria-label="Default select example"
                        type="text"
                        name="updateCategoryTypeId"
                        required
                        onChange={(e) => {
                          handleChange(e);
                          categoryTypeChangeHandler(e.target.value);
                        }}
                        onBlur={handleBlur}
                        value={values.updateCategoryTypeId}
                      >
                        {allCategorysTypes?.map((item, i) => (
                          <option key={i} value={item?.categoryTypeId}>
                            {item?.name}
                          </option>
                        ))}
                      </Form.Select>
                    </Form.Group>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                      <Form.Label>Select Category :</Form.Label>
                      <Form.Select
                        aria-label="Default select example"
                        type="text"
                        name="updateCategoryId"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateCategoryId}
                      >
                        {categorys?.map((item, i) => (
                          <option key={i} value={item?.id}>
                            {item?.name}
                          </option>
                        ))}
                      </Form.Select>
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                      <Form.Label>Select Brand :</Form.Label>
                      <Form.Select
                        aria-label="Default select example"
                        type="text"
                        name="updateBrandId"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateBrandId}
                      >
                        {allBrands?.map((item, i) => (
                          <option key={i} value={item?.brandId}>
                            {item?.brandName}
                          </option>
                        ))}
                      </Form.Select>
                    </Form.Group>

                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Product Name :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Product Name"
                        name="updateProductName"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateProductName}
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Product Name in Bangla :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Product Name in Bangla"
                        name="updateProductNameInBangla"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateProductNameInBangla}
                      />
                    </Form.Group>

                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Distribution Price :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Distribution Price"
                        name="updateDP"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateDP}
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Trade Price :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Trade Price"
                        name="updateTP"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateTP}
                      />
                    </Form.Group>

                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>ERP ID :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="number"
                        placeholder="Enter ERP ID"
                        name="updateERP_ID"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateERP_ID}
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                      <Form.Label>Unit :</Form.Label>
                      <Form.Select
                        aria-label="Default select example"
                        type="text"
                        name="updateUnitId"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateUnitId}
                      >
                        <option value={"Box"}>Box</option>
                        <option value={"Jar"}>Jar</option>
                        <option value={"Pcs"}>Pcs</option>
                        <option value={"Pouch"}>Pouch</option>
                      </Form.Select>
                    </Form.Group>

                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Weight/Pcs (Gram) :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Weight/Pcs"
                        name="updateWeight"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateWeight}
                      />
                    </Form.Group>
                  </Row>
                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Pcs/Sales Pack :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Pcs/Sales Pack"
                        name="updatePiecePerSale"
                        isInvalid={!!errors.piecePerSale}
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updatePiecePerSale}
                      />
                    </Form.Group>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="email">
                      <Form.Label>Pcs/CTN :</Form.Label>
                      <Form.Control
                        aria-label="Default select example"
                        type="text"
                        placeholder="Enter Pcs/CTN"
                        name="updatePiecePerCTN"
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updatePiecePerCTN}
                      />
                    </Form.Group>
                  </Row>

                  <Row>
                    <Form.Group as={Col} md="6" className="mb-3" controlId="formHookname">
                      <Form.Label>Product Open Date :</Form.Label>
                      <Form.Control
                        type="date"
                        name="updateOpeningDate"
                        isInvalid={!!errors.openingDate}
                        required
                        onChange={handleChange}
                        onBlur={handleBlur}
                        value={values.updateOpeningDate}
                      ></Form.Control>
                    </Form.Group>
                  </Row>

                  <Button variant="primary" type="submit">
                    Submit
                  </Button>
                </Form>
              );
            }}
          </Formik>
        </Modal.Body>
      </Modal>

      <Modal size="lg" show={showUploadCSV} onHide={() => setShowCreateCostCenterModal(false)} aria-labelledby="example-modal-sizes-title-lg">
        <Modal.Header>
          <Modal.Title id="example-modal-sizes-title-lg">Product Upload</Modal.Title>
          <FalconCloseButton onClick={() => setShowUploadCSV(false)} />
        </Modal.Header>
        <Modal.Body>
          <>
            <div {...getRootProps({ className: "dropzone-area py-6" })}>
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


      <Modal size={'lg'} show={viewDetails} onHide={() => setViewDetails(false)}>
              <Modal.Header>
                {/* <Modal.Title className="text-center">Employee Details</Modal.Title> */}
                <CloseButton className="btn btn-circle btn-sm transition-base p-0" onClick={() => setViewDetails(false)} />
              </Modal.Header>
              <Modal.Body>
                <div>
                
                  <div class="table-responsive scrollbar">
                    <table class="table table-bordered  fs--1 mb-0">
                      <thead  class="bg-200">
                        <tr>
                          <th class=" " data-sort="name">Name</th>

                          <th class="" data-sort="age">Value</th>
                        </tr>
                      </thead>
                      <tbody class="list text-black">
                        <tr>
                            <td>Brand Name</td>
                            <td>{productData?.brand.brandName}</td>
                        </tr>
                        <tr>
                            <td>Product ID</td>
                            <td>{productData?.productId}</td>
                        </tr>
                        <tr>
                            <td>Product Name</td>
                            <td>{productData?.name}</td>
                        </tr>
                        <tr>
                            <td>Product Name - Bangla</td>
                            <td>{productData?.bengaliName}</td>
                        </tr>
                        <tr>
                            <td>UNIT</td>
                            <td>{productData?.unitId}</td>
                        </tr>
                        <tr>
                            <td>ERP ID</td>
                            <td>{productData?.erpId}</td>
                        </tr>
                        <tr>
                            <td>Distribution Price</td>
                            <td>{productData?.distributionPrice}</td>
                        </tr>
                        <tr>
                            <td>Trade Price</td>
                            <td>{productData?.tradingPrice}</td>
                        </tr>
                        <tr>
                            <td>Weight/Pcs (Gram)</td>
                            <td>{productData?.weightPerUnit}</td>
                        </tr>
                        <tr>
                            <td>Pcs / Sales_Pack</td>
                            <td>{productData?.sellingPackSize}</td>
                        </tr>
                        <tr>
                            <td>Pcs / CTN</td>
                            <td>{productData?.sellingCartoonSize}</td>
                        </tr>
                        <tr>
                            <td>Product Open Date</td>
                            <td>{productData?.openingDate}</td>
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

export default Product;
