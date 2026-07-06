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
            <div className="page-header">
        <h1>Apply for Your Next Opportunity</h1>
        <p>Organize your job applications, monitor progress, and stay one step closer to landing your dream role</p>
         <button onClick={toggleForm} className="add-btn"> + Add Application</button>
         </div>    
         
        <div className="data">
        { IsFormOpen &&  <ApplicationForm isClose={closeForm}  />}
            <ApplicationList/>
        </div>
        </div>
        
    ) 
}
export default ApplicationsPage;