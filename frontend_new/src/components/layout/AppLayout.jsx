import React from 'react'
import { Outlet } from 'react-router-dom'
import Navbar from './Navbar.jsx'
import Sidebar from './Sidebar.jsx'
import { useSidebar } from '../../context/SidebarContext.jsx'
import './AppLayout.css'

export default function AppLayout() {
  const { collapsed } = useSidebar()

  return (
    <div className="app-shell">
      <Navbar />
      <Sidebar />
      <main className={`app-main ${collapsed ? 'is-collapsed' : ''}`}>
        <div className="container-page">
          <Outlet />
        </div>
      </main>
    </div>
  )
}
