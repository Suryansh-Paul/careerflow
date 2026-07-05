import { FiMenu } from "react-icons/fi";
function Navbar() {
  return (
    <nav>
      <div className="nav-left">
        <button className="menu-btn"><FiMenu/></button>
        <div className="nav-brand">
        <img src="/logoecf.jpg" alt="EVANZOFLOW logo" />
        <span>EVANZOFLOW</span>
      </div>
      </div>
    </nav>
  );
}

export default Navbar;