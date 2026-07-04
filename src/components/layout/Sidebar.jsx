import { Link } from "react-router-dom";
function Sidebar(){
    return(
         
        <>
       
        <aside>
         <ul>
         
            <li><Link to="/" > Dashboard </Link></li>
            
         
            <li><Link to="/applications" > Applications </Link></li>
            
            
            <li><Link to="/companies" > Companies </Link></li>
       
          
            <li><Link to="/interviews" > Interviews </Link></li>
         
            
            <li><Link to="/resumes" > Resumes </Link></li>
      
           
            <li><Link to="/statistics" > Statistics </Link></li>
     
            
            
         </ul>
            
        </aside>
      
        </>
    )
}
export default Sidebar;