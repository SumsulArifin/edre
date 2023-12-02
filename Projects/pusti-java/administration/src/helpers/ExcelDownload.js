import React from 'react';
import { authHeader } from 'utils';

const ExcelDownload = ({ url, filename }) => {

    // const wb = XLSX.utils.book_new();
    // const ws = XLSX.utils.json_to_sheet(data);
    // XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
    // XLSX.writeFile(wb, `${filename}.xlsx`);
    // console.log("data", data);

    function downloadBinaryFile(url, filename) {
        fetch(url, {
            headers: authHeader()
        })
            .then(response => response.blob())
            .then(blob => {
                const url = window.URL.createObjectURL(blob);
                const a = document.createElement('a');
                a.href = url;
                a.download = filename;
                document.body.appendChild(a);
                a.click();
                document.body.removeChild(a);
                window.URL.revokeObjectURL(url);
            })
            .catch(error => console.error('Error downloading file:', error));
    }

    return (
        <div>
            <span className="d-none d-sm-inline-block ms-1" onClick={() => downloadBinaryFile(process.env.REACT_APP_BASE_URL + url, filename)}>
                Export
            </span>
        </div>
    );
};

export default ExcelDownload;