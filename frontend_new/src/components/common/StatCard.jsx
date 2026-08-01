import React from 'react'
import SpotlightCard from './SpotlightCard.jsx'
import './StatCard.css'

export default function StatCard({ icon: Icon, label, value, delta, deltaTone = 'success', spotlight = true }) {
  const body = (
    <>
      <div className="stat-top">
        {Icon && (
          <div className="stat-icon">
            <Icon size={17} />
          </div>
        )}
        {delta && <span className={`stat-delta tone-${deltaTone}`}>{delta}</span>}
      </div>
      <div className="stat-value">{value}</div>
      <div className="stat-label">{label}</div>
    </>
  )

  if (spotlight) {
    return <SpotlightCard className="stat-card">{body}</SpotlightCard>
  }
  return <div className="card stat-card stat-card-plain">{body}</div>
}
