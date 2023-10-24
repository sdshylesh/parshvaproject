import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Display = () => {
  const [completeInfo, setCompleteInfo] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:4545/parshva/parshvaProject/allDetail')
      .then((response) => {
        console.log(response.data);
        setCompleteInfo(response.data);
      })
      .catch((error) => {
        console.error('Error fetching data:', error);
      });
  }, []);

  return (
    <div>
      <h2>Collected Information:</h2>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Hours Worked</th>
            <th>Rate Per Hour</th>
            <th>Supplier Name</th>
            <th>Purchase Order</th>
            <th>Description</th>
          </tr>
        </thead>
        <tbody>
          {completeInfo.map((item) => (
            <tr key={item.parshvaProjectId}>
              <td>{item.parshvaProjectId}</td>
              <td>{item.name}</td>
              <td>{item.startTime}</td>
              <td>{item.endTime}</td>
              <td>{item.noOfHoursWorked}</td>
              <td>{item.ratePerHour}</td>
              <td>{item.supplierName}</td>
              <td>{item.purchaseOrder}</td>
              <td>{item.selectedDescription}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Display;
