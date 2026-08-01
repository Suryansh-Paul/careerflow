import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom'
import { FiArrowRight } from 'react-icons/fi'
import Button from '../../components/common/Button.jsx'
import { Input } from '../../components/forms/Field.jsx'
import PasswordStrength from '../../components/forms/PasswordStrength.jsx'
import { useToast } from '../../context/ToastContext.jsx'
import './Auth.css'

export default function Register() {
  const navigate = useNavigate()
  const toast = useToast()
  const [form, setForm] = useState({ name: '', email: '', password: '', confirm: '' })
  const [errors, setErrors] = useState({})
  const [loading, setLoading] = useState(false)

  const update = (key) => (e) => setForm((f) => ({ ...f, [key]: e.target.value }))

  const validate = () => {
    const next = {}
    if (!form.name.trim()) next.name = 'Enter your full name.'
    if (!form.email.trim()) next.email = 'Enter your email address.'
    else if (!/^\S+@\S+\.\S+$/.test(form.email)) next.email = 'Enter a valid email address.'
    if (!form.password) next.password = 'Create a password.'
    else if (form.password.length < 8) next.password = 'Use at least 8 characters.'
    if (form.confirm !== form.password || !form.confirm) next.confirm = 'Passwords do not match.'
    setErrors(next)
    return Object.keys(next).length === 0
  }

  const handleSubmit = (e) => {
    e.preventDefault()
    if (!validate()) return
    setLoading(true)
    // Integration point: POST /api/auth/register
    setTimeout(() => {
      setLoading(false)
      toast.success('Account created. Let’s set up your workspace.')
      navigate('/onboarding')
    }, 900)
  }

  return (
    <div className="auth-panel">
      <div className="auth-brand">
        <span className="auth-logo">E</span>
        <span className="auth-brand-name">EVANZOFLOW</span>
      </div>

      <h1 className="auth-title">Create your account</h1>
      <p className="auth-subtitle">Start building a career workspace that keeps every opportunity organized.</p>

      <form className="auth-form" onSubmit={handleSubmit} noValidate>
        <Input
          label="Full name"
          placeholder="Aarav Mehta"
          value={form.name}
          onChange={update('name')}
          error={errors.name}
          autoComplete="name"
        />
        <Input
          label="Email"
          type="email"
          placeholder="you@example.com"
          value={form.email}
          onChange={update('email')}
          error={errors.email}
          autoComplete="email"
        />
        <div>
          <Input
            label="Password"
            type="password"
            placeholder="Create a strong password"
            value={form.password}
            onChange={update('password')}
            error={errors.password}
            autoComplete="new-password"
          />
          <PasswordStrength password={form.password} />
        </div>
        <Input
          label="Confirm password"
          type="password"
          placeholder="Re-enter your password"
          value={form.confirm}
          onChange={update('confirm')}
          error={errors.confirm}
          autoComplete="new-password"
        />

        <Button type="submit" specular size="lg" block loading={loading} iconRight={FiArrowRight}>
          Create account
        </Button>
      </form>

      <div className="auth-divider"><span>Already have an account?</span></div>

      <Link to="/login" className="auth-secondary-link">
        Sign in instead
      </Link>

      <p className="auth-footnote">By creating an account you agree to EVANZOFLOW's Terms and Privacy Policy.</p>
    </div>
  )
}
