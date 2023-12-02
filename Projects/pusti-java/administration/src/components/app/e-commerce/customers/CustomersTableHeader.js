import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import FalconCloseButton from "components/common/FalconCloseButton";
import IconButton from "components/common/IconButton";
import AdvanceTableSearchBox from "components/common/advance-table/AdvanceTableSearchBox";
import FileUploader from "components/doc-components/FileUploader";
import ExcelDownload from "helpers/ExcelDownload";
import PropTypes from "prop-types";
import React from "react";
import { Button, Col, Dropdown, Form, FormControl, InputGroup, Row } from "react-bootstrap";
import { propTypes } from "react-bootstrap/esm/Image";
import { Link } from "react-router-dom";

const CustomersTableHeader = ({ setSearchText, isSearch = false, isNew = true, isFilter = false, isExport = true, selectedRowIds, handleShow, title, buttonTitle, data, handleShowCSV, newUrl, excelFileName, excelUrl }) => {
  return (
    <Row className="flex-between-center">
      <Col xs={4} sm="auto" className="d-flex align-items-center pe-0">
        <h5 className="fs-0 mb-0 text-nowrap py-2 py-xl-0">{title}</h5>
      </Col>
      <Col xs={8} sm="auto" className="ms-auto text-end ps-0">
        {Object.keys(selectedRowIds).length > 0 ? (
          <div className="d-flex">
            <Form.Select size="sm" aria-label="Bulk actions">
              <option>Bulk Actions</option>
              <option value="refund">Refund</option>
              <option value="delete">Delete</option>
              <option value="archive">Archive</option>
            </Form.Select>
            <Button type="button" variant="falcon-default" size="sm" className="ms-2">
              Apply
            </Button>
          </div>
        ) : (
          <div id="orders-actions">
            {
              isSearch && <IconButton style={{ background: 'none', border: 'none', boxShadow: 'none' }}> <Form className="position-relative" onSubmit={(e) => {
                e.preventDefault();
              }}>

                <Form.Control
                  type="search"
                  placeholder="Search..."
                  aria-label="Search"
                  className="rounded-pill search-input"
                  // value={searchInputValue}
                  onChange={(e) => setSearchText(e.target.value)}
                // onFocus={() => setDropdownOpen(true)}
                // onBlur={() => setDropdownOpen(false)}
                />
              </Form>


              </IconButton>
            }
            {buttonTitle && (
              <IconButton variant="falcon-default" className="me-2" size="sm" icon="plus" transform="shrink-3" onClick={handleShowCSV}>
                <span className="d-none d-sm-inline-block">{buttonTitle}</span>
              </IconButton>
            )}
            {isNew === true ? newUrl ? (
              <Link to={newUrl}>
                <IconButton className="me-2" variant="falcon-default" size="sm" icon="plus" transform="shrink-3">
                  <span className="d-none d-sm-inline-block ms-1">New</span>
                </IconButton>
              </Link>
            ) : (
              <IconButton className="me-2" variant="falcon-default" size="sm" icon="plus" transform="shrink-3" onClick={handleShow}>
                <span className="d-none d-sm-inline-block ms-1">New</span>
              </IconButton>
            ) : ''}
            {isFilter && (

              <Dropdown align="end" className="btn-reveal-trigger d-inline-block">
                <Dropdown.Toggle variant="falcon-default" size="sm" style={{ background: 'none', border: 'none', boxShadow: 'none' }}>
                  <IconButton variant="falcon-default" size="sm" icon="filter">
                    <span className="d-none d-sm-inline-block ms-1">Filter</span>
                  </IconButton>
                  {/* filter */}
                </Dropdown.Toggle>

                <Dropdown.Menu className="border py-0">
                  <div className="py-2">
                    <InputGroup className="position-relative input-search-width">
                      <FormControl
                        size="sm"
                        id="search"
                        type="search"
                        className="shadow-none"
                        placeholder="Search by name"
                        onChange={e => handleTicketsSearch(e.target.value)}
                      />
                      <Button
                        size="sm"
                        variant="outline-secondary"
                        className="border-300 hover-border-secondary"
                      >
                        <FontAwesomeIcon icon="search" className="fs--1" />
                      </Button>
                    </InputGroup>
                    <Dropdown.Item>Export</Dropdown.Item>
                    <Dropdown.Divider />
                    <Dropdown.Item className="text-danger">Remove</Dropdown.Item>
                  </div>
                </Dropdown.Menu>
              </Dropdown>
            )}
            {isExport && (
              <IconButton variant="falcon-default" size="sm" icon="arrow-down" transform="shrink-3">
                <span className="d-none d-sm-inline-block ms-1">{<ExcelDownload url={excelUrl} filename={excelFileName} ></ExcelDownload>}</span>
              </IconButton>
            )}
          </div>
        )}
      </Col>
    </Row >
  );
};

CustomersTableHeader.propTypes = {
  selectedRowIds: PropTypes.object,
  handleShow: propTypes.func,
  title: propTypes.string,
};

export default CustomersTableHeader;
