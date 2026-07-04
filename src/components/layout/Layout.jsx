import Navbar from "./Navbar"
import Sidebar from "./Sidebar"
import { Outlet } from "react-router-dom"
function Layout(){
    return(
    <>
    <div id="container">
    <Navbar/>
    <div id="main" style={{ display: "flex" }}>
    <Sidebar/>
    <Outlet/>
    </div>
    </div>
    </>
 
     
     

)
}

export default Layout;