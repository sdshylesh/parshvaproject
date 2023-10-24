import React, { useState, useEffect } from 'react';
import axios from 'axios';

import { useNavigate } from 'react-router-dom';
const Home = () => {
    let [supplierMap, setSupplierMap] = useState([]);
    const [supplierList, setSupplierList] = useState([]);
    const [poList, setPoList] = useState([]);
    const navigate = useNavigate();

    let [descriptionMap, setDescriptionMap] = useState([]);
    // let[descriptionList,setDescriptionList]=useState([]);
    let[descriptionListOrder,setDescriptionListOrder]=useState([]);
  
    const [formData, setFormData] = useState({
      name: '',
      startTime: '',
      endTime: '',
      hoursWorked: '',
      ratePerHour: '',
      supplierName: '',
      purchaseOrder: '',
      Description: '',
    });
  
    useEffect(() => {
      axios
        .get('http://localhost:4545/parshva/parshvaProject/primaryDetail')
        .then((response) => {
          const data = response.data[1];
          setSupplierMap(response.data[1]);
          let valuesArray = Object.values(data);
           valuesArray=[...new Set(valuesArray)].sort();
          setSupplierList(valuesArray);
          setDescriptionMap(response.data[0])
      
        })
        .catch((error) => {
          console.error('Error fetching data:', error);
        });
    }, []);

    const handleChange2=(e)=>{
      console.log(e.target.value)
      const { name, value } = e.target;
      setFormData({
        ...formData,
        Description: value, 
         });
      console.log(formData)
    };

    const handleAllDataClick = () => {
      // Use navigate to go to the '/display' route
      navigate('/display');
    };
  
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData({
        ...formData,
        [name]: value,
      });
      console.log(formData)
    // console.log(descriptionMap); 
    // console.log(supplierMap)

    const poNumberList= Object.entries(supplierMap).filter(([purchaseOrder, supplierName]) =>supplierName === value)
 
    setPoList(poNumberList);
    };

    const handleChangeDescription= (e) => {
      const { name, value } = e.target;
      setFormData({
        ...formData,
        [name]: value,
      });
      console.log(e.target.value)
      const descriptionListChoosed=Object.entries(descriptionMap).filter(([purchaseOrder, Description])=>purchaseOrder===value)
      console.log(descriptionListChoosed)
      setDescriptionListOrder(descriptionListChoosed)
   
    };
  
    const handleSubmit = (e) => {
      e.preventDefault();
      const postData = {
        name: formData.name,
        startTime: formData.startTime,
        endTime: formData.endTime,
        noOfHoursWorked: formData.hoursWorked,
        ratePerHour: formData.ratePerHour,
        supplierName: formData.supplierName,
        purchaseOrder: formData.purchaseOrder,
        selectedDescription: formData.Description,
      };

      console.log(postData)
      axios
      .post('http://localhost:4545/parshva/parshvaProject/addingDetails', postData)
      .then((response) => {
        // Handle the response from the server if needed
        console.log('Data submitted successfully:', response.data);
      })
      .catch((error) => {
        console.error('Error submitting data:', error);
      });
      console.log('Form data submitted:', formData);
     
    };
  
    return (
      <div className='form'>
        <h2>Creating a DOCKET</h2>
        <form   className='formwrap' onSubmit={handleSubmit}>
          <div  class="form-group">
            <label>Name:</label>
            <input type="text" name="name" value={formData.name} onChange={handleChange} />
          </div>
          <div class="form-group">
            <label>Start time:</label>
            <input type="text" name="startTime" value={formData.startTime} onChange={handleChange} />
          </div>
          <div class="form-group">
            <label>End time:</label>
            <input type="text" name="endTime" value={formData.endTime} onChange={handleChange} />
          </div>
          <div class="form-group">
            <label>No. of hours worked:</label>
            <input type="text" name="hoursWorked" value={formData.hoursWorked} onChange={handleChange} />
          </div>
          <div class="form-group">
            <label>Rate per hour:</label>
            <input type="text" name="ratePerHour" value={formData.ratePerHour} onChange={handleChange} />
          </div>
          <div class="form-group"> 
            <label>Supplier Name:</label>
            <select name="supplierName" value={formData.supplierName} onChange={handleChange}>
              <option value="">Select a supplier</option>
              
              {supplierList.map((key) => (
                <option key={key} value={key}>
                  {key}
                </option>
              ))}
            </select>
          </div>
          <div  class="form-group">
          <label>Purchase Order:</label>
  <select name="purchaseOrder" value={formData.purchaseOrder} onChange={handleChangeDescription}>
    <option value="">Select a purchase order</option>
    {poList.map(([purchaseOrder, supplierName]) => (
      <option key={purchaseOrder} value={purchaseOrder}>
        {purchaseOrder}
      </option>
    ))}
  </select>
          </div>

      <div class="form-group">
  <label>Description:</label>
  <select name="Description" value={formData.Description} onChange={handleChange2}>
    <option value="">Select a Description</option>
    {descriptionListOrder.map(([description, descriptionArray]) => (
      <React.Fragment key={description}>
        {descriptionArray.map((sentence, index) => (
          <option key={index} value={sentence}>
            {sentence}
          </option>
        ))}
      </React.Fragment>
    ))}
  </select>
</div>
<button type="submit" class="button-53" >Submit</button>
<button onClick={handleAllDataClick}  class="button-53">allData</button>
        </form>
         </div>
         
    );
}
 
export default Home;