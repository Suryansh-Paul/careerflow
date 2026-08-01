import React from 'react'
import { Routes, Route, Navigate } from 'react-router-dom'
import AppLayout from './components/layout/AppLayout.jsx'
import AuthLayout from './components/layout/AuthLayout.jsx'

import Login from './pages/auth/Login.jsx'
import Register from './pages/auth/Register.jsx'
import Onboarding from './pages/onboarding/Onboarding.jsx'
import Dashboard from './pages/dashboard/Dashboard.jsx'
import Applications from './pages/applications/Applications.jsx'
import Companies from './pages/companies/Companies.jsx'
import Interviews from './pages/interviews/Interviews.jsx'
import Resumes from './pages/resumes/Resumes.jsx'
import Statistics from './pages/statistics/Statistics.jsx'
import Profile from './pages/profile/Profile.jsx'

export default function App() {
  return (
    <Routes>
      <Route element={<AuthLayout />}>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
      </Route>

      <Route path="/onboarding" element={<Onboarding />} />

      <Route element={<AppLayout />}>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/applications" element={<Applications />} />
        <Route path="/companies" element={<Companies />} />
        <Route path="/interviews" element={<Interviews />} />
        <Route path="/resumes" element={<Resumes />} />
        <Route path="/statistics" element={<Statistics />} />
        <Route path="/profile" element={<Profile />} />
      </Route>

      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="*" element={<Navigate to="/dashboard" replace />} />
    </Routes>
  )
}
