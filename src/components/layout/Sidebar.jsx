import { Link } from "react-router-dom";
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
          <Link to="/">
            <FiHome />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Dashboard
            </span>
          </Link>
        </li>

        <li>
          <Link to="/applications">
            <FiClipboard />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Applications
            </span>
          </Link>
        </li>

        <li>
          <Link to="/companies">
            <FiBriefcase />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Companies
            </span>
          </Link>
        </li>

        <li>
          <Link to="/interviews">
            <FiCalendar />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Interviews
            </span>
          </Link>
        </li>

        <li>
          <Link to="/resumes">
            <FiFileText />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Resumes
            </span>
          </Link>
        </li>

        <li>
          <Link to="/statistics">
            <FiBarChart2 />
            <span className={isOpen ? "sidebar-label show" : "sidebar-label"}>
              Statistics
            </span>
          </Link>
        </li>
      </ul>
    </aside>
  );
}

export default Sidebar;