import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import {
  FiUser, FiBookOpen, FiTarget, FiCode, FiCamera, FiArrowLeft, FiArrowRight, FiCheck,
} from 'react-icons/fi'
import Button from '../../components/common/Button.jsx'
import { Input, Select, Textarea } from '../../components/forms/Field.jsx'
import { useToast } from '../../context/ToastContext.jsx'
import './Onboarding.css'

const STEPS = [
  { key: 'basics', title: 'Basic information', icon: FiUser, tagline: 'Let’s start with the essentials.' },
  { key: 'education', title: 'Education', icon: FiBookOpen, tagline: 'Where has your journey taken you so far?' },
  { key: 'goals', title: 'Career goals', icon: FiTarget, tagline: 'Tell us what you’re aiming for.' },
  { key: 'skills', title: 'Skills & interests', icon: FiCode, tagline: 'What are you bringing to the table?' },
  { key: 'profile', title: 'Profile setup', icon: FiCamera, tagline: 'Finish with a few public details.' },
]

const initialData = {
  fullName: '', username: '', location: '',
  school: '', degree: '', field: '', gradYear: '',
  targetRoles: '', industries: '', preferredLocations: '', workPreference: 'Hybrid',
  skills: '', interests: '',
  github: '', linkedin: '', portfolio: '',
}

export default function Onboarding() {
  const navigate = useNavigate()
  const toast = useToast()
  const [stepIndex, setStepIndex] = useState(0)
  const [data, setData] = useState(initialData)

  const step = STEPS[stepIndex]
  const isLast = stepIndex === STEPS.length - 1

  const update = (key) => (e) => setData((d) => ({ ...d, [key]: e.target.value }))

  const goNext = () => {
    if (isLast) {
      // Integration point: POST /api/onboarding
      toast.success('Your career workspace is ready.')
      navigate('/dashboard')
      return
    }
    setStepIndex((i) => i + 1)
  }

  const goBack = () => setStepIndex((i) => Math.max(0, i - 1))
  const skip = () => goNext()

  return (
    <div className="onboarding-shell">
      <aside className="onboarding-side">
        <div className="onboarding-side-brand">
          <span className="auth-logo">E</span>
          <span className="auth-brand-name">EVANZOFLOW</span>
        </div>
        <h2 className="onboarding-side-title">Let's build your career workspace.</h2>
        <p className="onboarding-side-desc">
          A few quick steps so EVANZOFLOW can organize your applications, interviews, and progress around who you are and where you're headed.
        </p>

        <ol className="onboarding-steps">
          {STEPS.map((s, i) => {
            const StepIcon = s.icon
            const state = i < stepIndex ? 'done' : i === stepIndex ? 'active' : 'upcoming'
            return (
              <li key={s.key} className={`onboarding-step-item state-${state}`}>
                <span className="onboarding-step-icon">
                  {state === 'done' ? <FiCheck size={14} /> : <StepIcon size={14} />}
                </span>
                <span>{s.title}</span>
              </li>
            )
          })}
        </ol>
      </aside>

      <main className="onboarding-main">
        <div className="onboarding-progress">
          <span className="metadata">Step {stepIndex + 1} of {STEPS.length}</span>
          <div className="onboarding-progress-track">
            <div className="onboarding-progress-fill" style={{ width: `${((stepIndex + 1) / STEPS.length) * 100}%` }} />
          </div>
        </div>

        <div className="onboarding-card card">
          <h3 className="section-title">{step.title}</h3>
          <p className="supporting-text" style={{ marginTop: 4, marginBottom: 24 }}>{step.tagline}</p>

          {step.key === 'basics' && (
            <div className="flex-col gap-md">
              <Input label="Full name" placeholder="Aarav Mehta" value={data.fullName} onChange={update('fullName')} />
              <Input label="Username" placeholder="aarav.mehta" value={data.username} onChange={update('username')} />
              <Input label="Location" placeholder="Mumbai, India" value={data.location} onChange={update('location')} optional />
            </div>
          )}

          {step.key === 'education' && (
            <div className="flex-col gap-md">
              <Input label="School / College / University" placeholder="University of Mumbai" value={data.school} onChange={update('school')} />
              <div className="field-row">
                <Input label="Degree" placeholder="B.E. Computer Engineering" value={data.degree} onChange={update('degree')} />
                <Input label="Field of study" placeholder="Computer Engineering" value={data.field} onChange={update('field')} optional />
              </div>
              <Input label="Graduation year" placeholder="2026" value={data.gradYear} onChange={update('gradYear')} optional />
            </div>
          )}

          {step.key === 'goals' && (
            <div className="flex-col gap-md">
              <Input label="Target roles" placeholder="Java Backend Engineer, Full Stack Engineer" value={data.targetRoles} onChange={update('targetRoles')} />
              <Input label="Preferred industries" placeholder="Fintech, Developer Tools" value={data.industries} onChange={update('industries')} optional />
              <Input label="Preferred locations" placeholder="Bengaluru, Mumbai, Remote" value={data.preferredLocations} onChange={update('preferredLocations')} optional />
              <Select
                label="Work preference"
                value={data.workPreference}
                onChange={update('workPreference')}
                options={['On-site', 'Hybrid', 'Remote']}
              />
            </div>
          )}

          {step.key === 'skills' && (
            <div className="flex-col gap-md">
              <Textarea label="Technical skills" placeholder="Java, Spring Boot, React, MySQL, Docker" value={data.skills} onChange={update('skills')} hint="Separate skills with commas." />
              <Textarea label="Areas of interest" placeholder="Backend engineering, cloud infrastructure, system design" value={data.interests} onChange={update('interests')} optional />
            </div>
          )}

          {step.key === 'profile' && (
            <div className="flex-col gap-md">
              <div className="onboarding-photo-row">
                <div className="onboarding-photo-placeholder"><FiCamera size={20} /></div>
                <div>
                  <p style={{ fontSize: 13.5, fontWeight: 600 }}>Profile photo</p>
                  <p className="metadata">Optional — you can add this later from your profile.</p>
                </div>
              </div>
              <Input label="GitHub" placeholder="github.com/username" value={data.github} onChange={update('github')} optional />
              <Input label="LinkedIn" placeholder="linkedin.com/in/username" value={data.linkedin} onChange={update('linkedin')} optional />
              <Input label="Portfolio website" placeholder="yourname.dev" value={data.portfolio} onChange={update('portfolio')} optional />
            </div>
          )}

          <div className="onboarding-actions">
            <div className="flex gap-sm">
              {stepIndex > 0 && (
                <Button variant="ghost" icon={FiArrowLeft} onClick={goBack}>Go back</Button>
              )}
            </div>
            <div className="flex gap-sm">
              {!isLast && <Button variant="ghost" onClick={skip}>Skip for now</Button>}
              <Button variant="primary" specular iconRight={isLast ? FiCheck : FiArrowRight} onClick={goNext}>
                {isLast ? 'Finish setup' : 'Continue'}
              </Button>
            </div>
          </div>
        </div>
      </main>
    </div>
  )
}
