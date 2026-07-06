import Navbar from "./Navbar"
import Sidebar from "./Sidebar"
import { Outlet } from "react-router-dom"
import { useState } from "react"


function Layout(){
   
    const[isSidebarOpen, setisSidebarOpen]= useState(false);

    function toggleSidebar() {
    setisSidebarOpen(!isSidebarOpen);
}



    return(
    <>
    <div id="container">
    <Navbar toggleSidebar={toggleSidebar} />
    <div id="main" style={{ display: "flex" }}>
    <Sidebar isOpen={isSidebarOpen}/>
    <div
        id="content"
        style={{
            marginLeft: isSidebarOpen ? "235px" : "75px"
        }}
            >
        <Outlet />
    </div>
    </div>
    </div>
    </>
 
     
     

)
}

export default Layout;