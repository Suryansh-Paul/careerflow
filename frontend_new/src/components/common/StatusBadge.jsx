import React from 'react'
import './StatusBadge.css'

const MAP = {
  applied: { label: 'Applied', tone: 'info' },
  interview: { label: 'Interview Scheduled', tone: 'warning' },
  selected: { label: 'Selected', tone: 'success' },
  offer: { label: 'Offer Received', tone: 'success' },
  rejected: { label: 'Rejected', tone: 'danger' },
  scheduled: { label: 'Scheduled', tone: 'warning' },
  completed: { label: 'Completed', tone: 'success' },
  pending: { label: 'Pending', tone: 'muted' },
  active: { label: 'Active', tone: 'info' },
}

export default function StatusBadge({ status, label, tone }) {
  const config = MAP[status] || { label: label || status, tone: tone || 'muted' }
  return <span className={`status-badge tone-${config.tone}`}>{label || config.label}</span>
}
