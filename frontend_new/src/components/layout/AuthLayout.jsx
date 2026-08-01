import React from 'react'
import { Outlet } from 'react-router-dom'
import './AuthLayout.css'

export default function AuthLayout() {
  return (
    <div className="auth-shell">
      <div className="silk-bg" aria-hidden="true">
        <div className="silk-blob silk-blob-a" />
        <div className="silk-blob silk-blob-b" />
        <div className="silk-blob silk-blob-c" />
        <div className="silk-grid" />
      </div>
      <div className="auth-content">
        <Outlet />
      </div>
    </div>
  )
}
