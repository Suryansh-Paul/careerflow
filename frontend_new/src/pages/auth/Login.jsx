import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { FiArrowRight } from 'react-icons/fi'
import Button from '../../components/common/Button.jsx'
import { Input } from '../../components/forms/Field.jsx'
import { useToast } from '../../context/ToastContext.jsx'
import './Auth.css'

export default function Login() {
  const navigate = useNavigate()
  const toast = useToast()
  const [form, setForm] = useState({ email: '', password: '' })
  const [remember, setRemember] = useState(true)
  const [errors, setErrors] = useState({})
  const [loading, setLoading] = useState(false)

  const update = (key) => (e) => setForm((f) => ({ ...f, [key]: e.target.value }))

  const validate = () => {
    const next = {}
    if (!form.email.trim()) next.email = 'Enter your email address.'
    else if (!/^\S+@\S+\.\S+$/.test(form.email)) next.email = 'Enter a valid email address.'
    if (!form.password) next.password = 'Enter your password.'
    setErrors(next)
    return Object.keys(next).length === 0
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    if (!validate()) return
    setLoading(true)
    // Integration point: POST /api/auth/login
    setTimeout(() => {
      setLoading(false)
      toast.success('Signed in successfully.')
      navigate('/dashboard')
    }, 900)
  }

  return (
    <div className="auth-panel">
      <div className="auth-brand">
        <span className="auth-logo">E</span>
        <span className="auth-brand-name">EVANZOFLOW</span>
      </div>

      <h1 className="auth-title">Welcome back</h1>
      <p className="auth-subtitle">Your career journey, organized for momentum.</p>

      <form className="auth-form" onSubmit={handleSubmit} noValidate>
        <Input
          label="Email"
          type="email"
          placeholder="you@example.com"
          value={form.email}
          onChange={update('email')}
          error={errors.email}
          autoComplete="email"
        />
        <Input
          label="Password"
          type="password"
          placeholder="Enter your password"
          value={form.password}
          onChange={update('password')}
          error={errors.password}
          autoComplete="current-password"
        />

        <div className="auth-row">
          <label className="auth-checkbox">
            <input type="checkbox" checked={remember} onChange={() => setRemember((r) => !r)} />
            <span>Remember me</span>
          </label>
          <Link to="/login" className="auth-link">Forgot password?</Link>
        </div>

        <Button type="submit" specular size="lg" block loading={loading} iconRight={FiArrowRight}>
          Sign in
        </Button>
      </form>

      <div className="auth-divider"><span>New to EVANZOFLOW?</span></div>

      <Link to="/register" className="auth-secondary-link">
        Create an account
      </Link>
    </div>
  )
}
