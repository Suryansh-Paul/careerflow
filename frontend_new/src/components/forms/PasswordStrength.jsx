import React, { useMemo } from 'react'
import './PasswordStrength.css'

function scorePassword(pw) {
  if (!pw) return 0
  let score = 0
  if (pw.length >= 8) score++
  if (pw.length >= 12) score++
  if (/[A-Z]/.test(pw) && /[a-z]/.test(pw)) score++
  if (/\d/.test(pw)) score++
  if (/[^A-Za-z0-9]/.test(pw)) score++
  return Math.min(score, 4)
}

const LABELS = ['Too weak', 'Weak', 'Fair', 'Good', 'Strong']
const TONES = ['danger', 'danger', 'warning', 'accent', 'success']

export default function PasswordStrength({ password }) {
  const score = useMemo(() => scorePassword(password), [password])
  if (!password) return null

  return (
    <div className="pw-strength">
      <div className="pw-bars">
        {[0, 1, 2, 3].map((i) => (
          <span key={i} className={`pw-bar ${i < score ? `tone-${TONES[score]}` : ''}`} />
        ))}
      </div>
      <span className={`pw-label tone-${TONES[score]}`}>{LABELS[score]}</span>
    </div>
  )
}
