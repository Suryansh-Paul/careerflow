import React from 'react'
import { NavLink } from 'react-router-dom'
import {
  FiGrid, FiBriefcase, FiHome, FiCalendar, FiFileText, FiBarChart2,
} from 'react-icons/fi'
import { useSidebar } from '../../context/SidebarContext.jsx'
import './Sidebar.css'

const NAV_ITEMS = [
  { to: '/dashboard', label: 'Dashboard', icon: FiGrid },
  { to: '/applications', label: 'Applications', icon: FiBriefcase },
  { to: '/companies', label: 'Companies', icon: FiHome },
  { to: '/interviews', label: 'Interviews', icon: FiCalendar },
  { to: '/resumes', label: 'Resumes', icon: FiFileText },
  { to: '/statistics', label: 'Statistics', icon: FiBarChart2 },
]

export default function Sidebar() {
  const { collapsed, mobileOpen, setMobileOpen } = useSidebar()

  return (
    <>
      {mobileOpen && <div className="sidebar-scrim hide-desktop" onClick={() => setMobileOpen(false)} />}
      <aside className={`sidebar ${collapsed ? 'is-collapsed' : ''} ${mobileOpen ? 'is-mobile-open' : ''}`}>
        <nav className="sidebar-nav">
          {NAV_ITEMS.map((item) => (
            <NavLink
              key={item.to}
              to={item.to}
              className={({ isActive }) => `sidebar-item ${isActive ? 'is-active' : ''}`}
              onClick={() => setMobileOpen(false)}
              title={collapsed ? item.label : undefined}
            >
              <span className="sidebar-item-icon"><item.icon size={18} /></span>
              <span className="sidebar-item-label">{item.label}</span>
            </NavLink>
          ))}
        </nav>

        <div className="sidebar-footer">
          <div className="sidebar-upgrade">
            <p className="sidebar-upgrade-title">AI features</p>
            <p className="sidebar-upgrade-desc">Resume scoring & job matching — coming soon.</p>
          </div>
        </div>
      </aside>
    </>
  )
}
