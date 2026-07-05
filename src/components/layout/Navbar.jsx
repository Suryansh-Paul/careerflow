import { FiMenu } from "react-icons/fi";
function Navbar({toggleSidebar}) {
  return (
    <nav>
      <div className="nav-left">
        <button onClick={toggleSidebar} className="menu-btn"><FiMenu/></button>
        <div className="nav-brand">
        <img src="/logoecf.jpg" alt="EVANZOFLOW logo" />
        <span>EVANZOFLOW</span>
      </div>
      </div>
    </nav>
  );
}

export default Navbar;