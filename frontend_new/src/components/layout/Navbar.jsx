import React, { useEffect, useRef, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { FiMenu, FiSearch, FiBell, FiUser, FiSettings, FiLogOut } from 'react-icons/fi'
import { useSidebar } from '../../context/SidebarContext.jsx'
import Avatar from '../common/Avatar.jsx'
import { currentUser, notifications } from '../../data/mockData.js'
import './Navbar.css'

export default function Navbar() {
  const { collapsed, toggle, toggleMobile } = useSidebar()
  const [menuOpen, setMenuOpen] = useState(false)
  const [notifOpen, setNotifOpen] = useState(false)
  const menuRef = useRef(null)
  const notifRef = useRef(null)
  const navigate = useNavigate()

  useEffect(() => {
    function onClick(e) {
      if (menuRef.current && !menuRef.current.contains(e.target)) setMenuOpen(false)
      if (notifRef.current && !notifRef.current.contains(e.target)) setNotifOpen(false)
    }
    document.addEventListener('mousedown', onClick)
    return () => document.removeEventListener('mousedown', onClick)
  }, [])

  const handleToggle = () => {
    toggle()
    toggleMobile()
  }

  return (
    <header className="navbar">
      <div className="navbar-left">
        <button className="navbar-icon-btn" onClick={handleToggle} aria-label="Toggle sidebar">
          <FiMenu size={19} />
        </button>
        <Link to="/dashboard" className="navbar-brand">
          <span className="navbar-logo">E</span>
          <span className="navbar-brand-name hide-mobile">EVANZOFLOW</span>
        </Link>
      </div>

      <div className="navbar-right">
        <button className="navbar-search hide-mobile">
          <FiSearch size={15} />
          <span>Search applications, companies…</span>
          <kbd>⌘K</kbd>
        </button>
        <button className="navbar-icon-btn hide-desktop" aria-label="Search">
          <FiSearch size={18} />
        </button>

        <div className="navbar-popover-wrap" ref={notifRef}>
          <button className="navbar-icon-btn" onClick={() => setNotifOpen((o) => !o)} aria-label="Notifications">
            <FiBell size={18} />
            {notifications.some((n) => n.unread) && <span className="navbar-dot" />}
          </button>
          {notifOpen && (
            <div className="navbar-popover notif-popover">
              <div className="popover-header">Notifications</div>
              {notifications.map((n) => (
                <div key={n.id} className={`notif-item ${n.unread ? 'unread' : ''}`}>
                  <p className="notif-title">{n.title}</p>
                  <p className="notif-time">{n.time}</p>
                </div>
              ))}
            </div>
          )}
        </div>

        <div className="navbar-popover-wrap" ref={menuRef}>
          <button className="navbar-avatar-btn" onClick={() => setMenuOpen((o) => !o)}>
            <Avatar name={currentUser.name} size={32} />
          </button>
          {menuOpen && (
            <div className="navbar-popover user-popover">
              <div className="popover-header">
                <p className="popover-name">{currentUser.name}</p>
                <p className="popover-email">{currentUser.email}</p>
              </div>
              <button className="popover-item" onClick={() => { setMenuOpen(false); navigate('/profile') }}>
                <FiUser size={15} /> Profile
              </button>
              <button className="popover-item" onClick={() => { setMenuOpen(false); navigate('/profile') }}>
                <FiSettings size={15} /> Settings
              </button>
              <div className="popover-divider" />
              <button className="popover-item danger" onClick={() => { setMenuOpen(false); navigate('/login') }}>
                <FiLogOut size={15} /> Log out
              </button>
            </div>
          )}
        </div>
      </div>
    </header>
  )
}
