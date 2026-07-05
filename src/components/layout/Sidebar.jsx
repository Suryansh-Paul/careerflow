import { NavLink } from "react-router-dom";
import {
  FiHome,
  FiFileText,
  FiBriefcase,
  FiCalendar,
  FiClipboard,
  FiBarChart2,
} from "react-icons/fi";

function Sidebar({ isOpen }) {
  return (
    <aside className={isOpen ? "sidebar-open" : "sidebar-close"}>
      <ul>
        <li>
          <NavLink to="/" className={(navData)=>
            navData.isActive ? "active" : ""
          }>
            <FiHome />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Dashboard
            </span>
          </NavLink>
        </li>

        <li>
          <NavLink to="/applications" className={(navData)=>
            navData.isActive ? "active" : ""
          }>
            <FiClipboard />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Applications
            </span>
          </NavLink>
        </li>

        <li>
          <NavLink to="/companies" className={(navData)=>
            navData.isActive ? "active" : ""
          }>
            <FiBriefcase />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Companies
            </span>
          </NavLink>
        </li>

        <li>
          <NavLink to="/interviews" className={(navData)=>
            navData.isActive ? "active" : ""
          }>
            <FiCalendar />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Interviews
            </span>
          </NavLink>
        </li>

        <li>
          <NavLink to="/resumes" className={(navData)=>
            navData.isActive ? "active" : ""
          }>
            <FiFileText />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Resumes
            </span>
          </NavLink>
        </li>

        <li>
          <NavLink to="/statistics" className={(navData)=>
            navData.isActive ? "active" : ""
          }>
            <FiBarChart2 />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Statistics
            </span>
          </NavLink>
        </li>
      </ul>
    </aside>
  );
}

export default Sidebar;