import React from 'react'
import './ProgressBar.css'

export default function ProgressBar({ value = 0, tone = 'accent', height = 8 }) {
  const pct = Math.max(0, Math.min(100, value))
  return (
    <div className="progress-track" style={{ height }}>
      <div className={`progress-fill tone-${tone}`} style={{ width: `${pct}%` }} />
    </div>
  )
}
