import { Link } from "react-router-dom";
import {
  FiHome,
  FiFileText,
  FiBriefcase,
  FiCalendar,
  FiClipboard,
  FiBarChart2,
} from "react-icons/fi";
function Sidebar(){
    return(
         
        <>
       
        <aside>
         <ul>
         
            <li><Link to="/" > <FiHome/> Dashboard </Link></li>
            
         
            <li><Link to="/applications" > <FiClipboard/>  Applications </Link></li>
            
            
            <li><Link to="/companies" > <FiBriefcase/> Companies</Link></li>
       
          
            <li><Link to="/interviews" >  <FiCalendar/> Interviews</Link></li>
         
            
            <li><Link to="/resumes" > <FiFileText/> Resumes</Link></li>
      
           
            <li><Link to="/statistics" > <FiBarChart2/> Statistics </Link></li>
     
            
            
         </ul>
            
        </aside>
      
        </>
    )
}
export default Sidebar;