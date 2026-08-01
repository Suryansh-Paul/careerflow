import React from 'react'
import { FiTrendingUp, FiPercent } from 'react-icons/fi'
import PageHeader from '../../components/common/PageHeader.jsx'
import SpotlightCard from '../../components/common/SpotlightCard.jsx'
import StatCard from '../../components/common/StatCard.jsx'
import { applicationsOverTime, applicationsByStatus, applicationsByRole } from '../../data/mockData.js'
import './Statistics.css'

function BarChart({ data }) {
  const max = Math.max(...data.map((d) => d.count))
  return (
    <div className="bar-chart">
      {data.map((d) => (
        <div className="bar-col" key={d.month}>
          <div className="bar-track">
            <div className="bar-fill" style={{ height: `${(d.count / max) * 100}%` }} />
          </div>
          <span className="metadata">{d.month}</span>
        </div>
      ))}
    </div>
  )
}

function DonutChart({ data }) {
  const total = data.reduce((s, d) => s + d.value, 0)
  let cumulative = 0
  const radius = 60
  const circumference = 2 * Math.PI * radius
  const colorVar = { info: 'var(--blue-300)', warning: 'var(--warning)', success: 'var(--success)', danger: 'var(--danger)' }

  return (
    <div className="donut-wrap">
      <svg viewBox="0 0 160 160" className="donut-svg">
        <circle cx="80" cy="80" r={radius} fill="none" stroke="var(--surface-2)" strokeWidth="18" />
        {data.map((d) => {
          const fraction = d.value / total
          const dash = fraction * circumference
          const offset = circumference - (cumulative / total) * circumference
          cumulative += d.value
          return (
            <circle
              key={d.label}
              cx="80" cy="80" r={radius}
              fill="none"
              stroke={colorVar[d.tone]}
              strokeWidth="18"
              strokeDasharray={`${dash} ${circumference - dash}`}
              strokeDashoffset={offset}
              transform="rotate(-90 80 80)"
              strokeLinecap="butt"
            />
          )
        })}
        <text x="80" y="76" textAnchor="middle" className="donut-total">{total}</text>
        <text x="80" y="94" textAnchor="middle" className="donut-total-label">applications</text>
      </svg>
      <div className="donut-legend">
        {data.map((d) => (
          <div className="donut-legend-item" key={d.label}>
            <span className="legend-dot" style={{ background: colorVar[d.tone] }} />
            <span>{d.label}</span>
            <span className="metadata">{d.value}</span>
          </div>
        ))}
      </div>
    </div>
  )
}

function RoleBars({ data }) {
  const max = Math.max(...data.map((d) => d.value))
  return (
    <div className="role-bars">
      {data.map((d) => (
        <div key={d.label} className="role-bar-row">
          <span className="role-bar-label">{d.label}</span>
          <div className="role-bar-track">
            <div className="role-bar-fill" style={{ width: `${(d.value / max) * 100}%` }} />
          </div>
          <span className="metadata">{d.value}</span>
        </div>
      ))}
    </div>
  )
}

export default function Statistics() {
  return (
    <div>
      <PageHeader
        title="Statistics"
        description="Understand how your career search is performing over time."
      />

      <div className="stat-grid-4" style={{ marginBottom: 24 }}>
        <StatCard icon={FiPercent} label="Interview conversion" value="36%" delta="+6% this month" deltaTone="success" />
        <StatCard icon={FiPercent} label="Offer conversion" value="7%" delta="+2% this month" deltaTone="success" />
        <StatCard icon={FiPercent} label="Rejection rate" value="29%" delta="-4% this month" deltaTone="success" />
        <StatCard icon={FiTrendingUp} label="Applications this month" value="8" delta="+3 vs last" deltaTone="success" />
      </div>

      <div className="stats-grid">
        <div className="card panel">
          <span className="section-title">Applications over time</span>
          <p className="supporting-text" style={{ marginTop: 4, marginBottom: 20 }}>Your monthly application volume for the last 6 months.</p>
          <BarChart data={applicationsOverTime} />
        </div>

        <div className="card panel">
          <span className="section-title">Applications by status</span>
          <p className="supporting-text" style={{ marginTop: 4, marginBottom: 20 }}>Where your applications currently stand.</p>
          <DonutChart data={applicationsByStatus} />
        </div>
      </div>

      <div className="card panel" style={{ marginTop: 20 }}>
        <span className="section-title">Applications by role</span>
        <p className="supporting-text" style={{ marginTop: 4, marginBottom: 20 }}>
          You apply most frequently to backend engineering roles.
        </p>
        <RoleBars data={applicationsByRole} />
      </div>
    </div>
  )
}
