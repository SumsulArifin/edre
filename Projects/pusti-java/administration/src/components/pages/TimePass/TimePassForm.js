import FalconComponentCard from "components/common/FalconComponentCard";
import PageHeader from "components/common/PageHeader";
import IconButton from "components/common/IconButton";
import React, { useEffect, useState } from "react";
import axios from "axios";
import { authHeader } from "utils";
import { toast } from "react-toastify";
import Calendar from 'react-calendar';
// import 'react-calendar/dist/Calendar.css'; //react-calender default style 
import "./timePass.css"
import { Button } from "react-bootstrap";

const TimePassForm = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [rerender, setRerender] = useState(false);
    const [selectedMonth, setSelectedMonth] = useState(null);
    const [selectedYear, setSelectedYear] = useState(null);
    const [selectedDates, setSelectedDates] = useState([]);
    const [holidaysData, setHolidaysData] = useState([]);




    useEffect(() => {
        setSelectedDates([])
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const currentMonth = currentDate.getMonth() + 1;
        setIsLoading(true);
        const url = process.env.REACT_APP_BASE_URL + `getHolidayRecordsByYearMonth/${selectedYear !== null ? selectedYear : currentYear}/${selectedMonth !== null ? selectedMonth : currentMonth}`;
        axios.get(url, { headers: authHeader() })
            .then(response => {
                const result = [];
                if (response?.data) {
                    setIsLoading(false);
                    setHolidaysData(response?.data);

                    response?.data?.forEach(element => {
                        result.push(new Date(element.holidayDate))

                    });
                }

                result.length > 0 && setSelectedDates([...result])
            })
            .catch(error => {
                setIsLoading(false);
                toast.error(error.message)
            }
            )
        setRerender(!rerender);

    }, [selectedYear, selectedMonth])



    const handleDateChange = (date) => {

        // const isDateSelected = selectedDates.some((selectedDate) => {
        //     return selectedDate.getTime() === date.getTime();
        // });
        const isDateSelected = selectedDates?.find((selectedDate) => formatDateToYYYYMMDD(selectedDate) === formatDateToYYYYMMDD(date));
        console.log(isDateSelected)

        if (isDateSelected) {
            console.log("test")
            // If the date is already selected, remove it to deselect
            setSelectedDates(selectedDates?.filter((d) => formatDateToYYYYMMDD(d) !== formatDateToYYYYMMDD(date)));
        } else {
            // If it isn't selected, add it to the array to select
            setSelectedDates([...selectedDates, date]);
        }
        setRerender(!rerender);
    };

    console.log(selectedDates.length)
    console.log(selectedDates)
    // Initialize an object to store class names for selected dates
    const selectedDateClasses = {};
    // Populate the selectedDateClasses object with class names
    selectedDates?.forEach((date) => {
        selectedDateClasses[date?.toDateString()] = 'custom-selected-date';
    });

    // Function to customize the CSS class of dates
    const tileClassName = ({ date }) => {
        // Check if the date has a custom class defined in selectedDateClasses
        return selectedDateClasses[date?.toDateString()] || '';
    };


    // Date Formate
    function formatDateToYYYYMMDD(isoDateString) {
        const date = new Date(isoDateString);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, "0"); // Month is 0-based
        const day = String(date.getDate()).padStart(2, "0");

        return `${year}-${month}-${day}`;
    }



    // create & update holiday
    const handleHolidaySubmit = () => {
        let monthlyHolidays = [];
        selectedDates?.forEach(element => {

            const result = formatDateToYYYYMMDD(element);
            monthlyHolidays.push(result);

        });

        const holidayInfo = {
            yearMonth: `${selectedYear}-${selectedMonth}`,
            year: selectedYear,
            month: selectedMonth,
            monthlyHolidayList: monthlyHolidays

        }

        if (holidaysData?.length > 0) {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'updateMonthlyHolidays';
            axios.put(url, holidayInfo, { headers: authHeader() })
                .then(response => {
                    if (response?.data) {
                        setIsLoading(false);
                        toast.success(response?.data?.message);

                    }
                })
                .catch(error => {
                    setIsLoading(false);
                    console.log(error)
                }
                )

        }
        else {
            setIsLoading(true);
            const url = process.env.REACT_APP_BASE_URL + 'saveMonthHolidays';
            axios.post(url, holidayInfo, { headers: authHeader() })
                .then(response => {
                    if (response?.data) {
                        setIsLoading(false);
                        toast.success(response?.data?.message);

                    }
                })
                .catch(error => {
                    setIsLoading(false);
                    console.log(error)
                }
                )
        }
    }


    const handleChangeMonthYear = ({ date, label, locale, view }) => {

        const selectedMonth = date.getMonth() + 1;
        const selectedYear = date.getFullYear();
        setSelectedMonth(selectedMonth);
        setSelectedYear(selectedYear);

        return label

    };

    isLoading && <>Loading...</>

    return (
        <>
            <PageHeader
                title="Holiday Management"
                className="mb-3"
            ></PageHeader>
            <FalconComponentCard >
                <FalconComponentCard.Header light={false} noPreview />
                <FalconComponentCard.Body>
                    <div>


                        <div className=" d-flex justify-content-center" >
                            <Calendar
                                className="calendar-container"

                                navigationLabel={handleChangeMonthYear}
                                onChange={handleDateChange}
                                value={selectedDates}
                                selectRange={false}
                                selectMultiple={true}
                                tileClassName={tileClassName} // Customize tile class for selected dates
                            />

                        </div>
                        <div className="holiday_legend">
                            <h5>Legend : <span class="badge bg-danger">Holiday</span></h5>
                        </div>

                        <div className="d-flex justify-end holiday_submit_btn">

                            <Button
                                variant="primary"
                                className="ms-auto px-5 justify-end mt-1"
                                type="submit"
                                onClick={handleHolidaySubmit}
                                disable={selectedDates?.length == 0}
                            >
                                Save
                            </Button>
                        </div>
                    </div>
                </FalconComponentCard.Body>
            </FalconComponentCard>
        </>
    );
};

export default TimePassForm;


