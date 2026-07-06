import "./Application.css";
import ApplicationForm  from "../../components/application/ApplicationForm";
import ApplicationList from "../../components/application/ApplicationList";
import { useState } from "react";



function ApplicationsPage(){

   const[IsFormOpen , setIsFormOpen] = useState(false);

   function toggleForm(){
       setIsFormOpen(prev=>!prev);
   }
   function closeForm(){
    setIsFormOpen(false)
   }

    return(
        
        <div id="mainpage">
        <h1>Applications</h1>
        <button onClick={toggleForm} className="add-btn"> + Add Application</button>
        <div className="data">
        { IsFormOpen &&  <ApplicationForm isClose={closeForm}  />}
            <ApplicationList/>
        </div>
        </div>
        
    ) 
}
export default ApplicationsPage;