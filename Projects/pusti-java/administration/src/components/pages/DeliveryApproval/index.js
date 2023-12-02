import GreetingsDate from "components/dashboards/crm/greetings/GreetingsDate";
import lineChart from 'assets/img/illustrations/crm-line-chart.png';
import { Button, Card, Col, Form, Row } from "react-bootstrap";
import Flex from 'components/common/Flex';
import { useState } from "react";
import './tableStyles.module.css';

const DeliveryApproval = () => {
    const [testValue, setTestValue] = useState(0);
    const demoData = [
        {
            SL: 1,
            orderName: "First Order",
            approvedProduct: 50,
            alreadyDelivered: 20,
            requiredProduct: 0,


        },
        {
            SL: 2,
            orderName: "First Order",
            approvedProduct: 90,
            alreadyDelivered: 50,
            requiredProduct: 0,


        }
    ]


    const columns = [
        {
            accessor: 'SL',
            Header: 'SL',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { SL } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{SL}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },

        {
            accessor: 'orderName',
            Header: 'Order Name',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { orderName } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{orderName}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },

        {
            accessor: 'approvedProduct',
            Header: 'Approved Product',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { approvedProduct } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            <h5 className="mb-0 fs--1">{approvedProduct}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: 'alreadyDelivered',
            Header: 'Already Delivered',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { alreadyDelivered } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            {/* style={{ wordWrap: 'break-word', whiteSpace: 'pre-wrap', maxWidth: '250px', textAlign: 'justify' }} */}
                            <h5 className="mb-0 fs--1">{alreadyDelivered}</h5>
                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },
        {
            accessor: 'requiredProduct',
            Header: 'Required Product',
            headerProps: { className: 'pe-1' },
            cellProps: {
                className: 'py-2'
            },
            Cell: rowData => {
                const { approvedProduct, alreadyDelivered } = rowData.row.original;
                return (
                    // <Link to="/e-commerce/customer-details">
                    <Flex alignItems="center">
                        <div className="flex-1">
                            {/* style={{ wordWrap: 'break-word', whiteSpace: 'pre-wrap', maxWidth: '250px', textAlign: 'justify' }} */}
                            <input
                                type="number"
                                name="requiredProduct"
                                value={testValue}
                                onChange={(e) => setTestValue(e.target.value)}

                            />
                            <p className="text-danger">{testValue > (approvedProduct - alreadyDelivered) ? "warning" : ""}</p>

                        </div>
                    </Flex>
                    // </Link>
                );
            }
        },

       
    ];



    return <>

        <Card className="bg-100 shadow-none border mb-3 p-3">
            <Card.Body className="py-0">
                <Row className="g-0 justify-content-between">
                    <Col sm="7">
                        <Flex alignItems="center">
                            {/* <img src={barChart} width={90} alt="..." className="ms-n4" /> */}
                            <div>

                                <h4 className="text-black fw-medium mb-0">
                                    Title
                                    {/* <span className="text-info fw-medium"> CRM</span> */}
                                </h4>
                                <h5 className="text-black fw-medium mb-0">
                                    Committee
                                    {/* <span className="text-info fw-medium"> CRM</span> */}
                                </h5>

                                <h6 className="text-primary fs--1 mb-0" >Comment</h6>
                            </div>
                            <img
                                src={lineChart}
                                width={140}
                                alt="..."
                                className="ms-n4 d-md-none d-lg-block"
                            />
                        </Flex>
                    </Col>
                    <Col md="5" className="mb-3 mb-md-0">
                        <Row className="g-3 gy-md-0 h-100 align-items-center">
                            <Col xs="auto">
                                <h6 className="text-700 mb-0 text-nowrap">Delivery Date :</h6>
                            </Col>
                            <Col md="auto">
                                <GreetingsDate />
                            </Col>
                        </Row>
                    </Col>
                </Row>
            </Card.Body>
        </Card>


        <div className="bg-white">

            <div className="table-responsive scrollbar">
                <table className="table table-bordered table-striped fs--1 mb-0">
                    <thead >
                        <tr>
                            <th className="align-middle white-space-nowrap text-center ">Order Name</th>
                            <th className="align-middle white-space-nowrap text-center ">Product Name</th>
                            <th className="align-middle white-space-nowrap text-center ">Approved Product</th>
                            <th className="align-middle white-space-nowrap text-center ">Already Delivered</th>
                            <th className="align-middle white-space-nowrap text-center ">Required Product</th>
                            <th colSpan="2" className="align-middle white-space-nowrap text-center ">Depot</th>
                        </tr>
                    </thead>
                    <tbody className="list" id="table-purchase-body">
                        <tr className="btn-reveal-trigger">
                            <td rowSpan="2" className="align-middle text-center white-space-nowrap ">First Order</td>
                            <td className="align-middle  text-center   white-space-nowrap ">drinks</td>
                            <td className="align-middle  text-center   white-space-nowrap ">60</td>
                            <td className="align-middle  text-center   white-space-nowrap ">5</td>
                            <td className="align-middle  text-center   white-space-nowrap ">
                                <Form.Control type="number" placeholder="Enter Required Number" style={{ border: 'none' }} />
                            </td>
                            <td colSpan="4" className="align-middle  text-center   white-space-nowrap ">
                                <Form.Select aria-label="Default select example" style={{ border: 'none' }}>
                                    <option>Select Factory</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </Form.Select>
                            </td>
                        </tr>
                        <tr>
                            <td className="align-middle  text-center   white-space-nowrap ">drinks</td>
                            <td className="align-middle  text-center   white-space-nowrap ">60</td>
                            <td className="align-middle  text-center   white-space-nowrap ">5</td>
                            <td className="align-middle  text-center   white-space-nowrap ">
                                <Form.Control type="number" placeholder="Enter Required Number" style={{ border: 'none' }} />
                            </td>
                            <td colSpan="4" className="align-middle  text-center   white-space-nowrap ">
                                <Form.Select aria-label="Default select example" style={{ border: 'none' }}>
                                    <option>Select Factory</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </Form.Select>
                            </td>
                        </tr>
                        <tr>
                            <td rowSpan="3">Second Order</td>
                            <td className="align-middle  text-center   white-space-nowrap ">drinks</td>
                            <td className="align-middle  text-center   white-space-nowrap ">60</td>
                            <td className="align-middle  text-center   white-space-nowrap ">5</td>
                            <td className="align-middle  text-center   white-space-nowrap ">
                                <Form.Control type="number" placeholder="Enter Required Number" style={{ border: 'none' }} />
                            </td>
                            <td colSpan="4" className="align-middle  text-center   white-space-nowrap ">
                                <Form.Select aria-label="Default select example" style={{ border: 'none' }}>
                                    <option>Select Factory</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </Form.Select>
                            </td>
                        </tr>
                        <tr>
                            <td className="align-middle  text-center   white-space-nowrap ">drinks</td>
                            <td className="align-middle  text-center   white-space-nowrap ">60</td>
                            <td className="align-middle  text-center   white-space-nowrap ">5</td>
                            <td className="align-middle  text-center   white-space-nowrap ">
                                <Form.Control type="number" placeholder="Enter Required Number" style={{ border: 'none' }} />
                            </td>
                            <td colSpan="4" className="align-middle  text-center   white-space-nowrap ">
                                <Form.Select aria-label="Default select example" style={{ border: 'none' }}>
                                    <option>Select Factory</option>
                                    <option value="1">One</option>
                                    <option value="2">Two</option>
                                    <option value="3">Three</option>
                                </Form.Select>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <Button className="mt-3" variant="primary" type="submit">
           Submit
        </Button>
    </>
}
export default DeliveryApproval;