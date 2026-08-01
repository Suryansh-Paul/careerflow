import React from 'react'
import { useNavigate } from 'react-router-dom'
import {
  FiBriefcase, FiCalendar, FiAward, FiXCircle, FiArrowRight, FiTrendingUp, FiClock, FiAlertCircle,
} from 'react-icons/fi'
import Button from '../../components/common/Button.jsx'
import StatCard from '../../components/common/StatCard.jsx'
import SpotlightCard from '../../components/common/SpotlightCard.jsx'
import StatusBadge from '../../components/common/StatusBadge.jsx'
import { currentUser, applications, interviews, activity } from '../../data/mockData.js'
import './Dashboard.css'

export default function Dashboard() {
  const navigate = useNavigate()
  const firstName = currentUser.name.split(' ')[0]
  const upcomingInterviews = interviews.filter((i) => !i.past).slice(0, 3)
  const recentApplications = applications.slice(0, 5)

  return (
    <div className="dashboard">
      <div className="dashboard-hero">
        <div className="dashboard-hero-blobs" aria-hidden="true" />
        <div className="dashboard-hero-content">
          <p className="eyebrow">Career workspace</p>
          <h1 className="dashboard-hero-title">Welcome back, {firstName}</h1>
          <p className="dashboard-hero-desc">
            Track your progress, stay organized, and keep moving closer to your next opportunity.
          </p>
          <Button specular size="lg" icon={FiBriefcase} onClick={() => navigate('/applications')}>
            Apply now
          </Button>
        </div>
      </div>

      <div className="stat-grid">
        <StatCard icon={FiBriefcase} label="Applications" value="14" delta="+3 this month" deltaTone="success" />
        <StatCard icon={FiCalendar} label="Interviews" value="5" delta="+2 this month" deltaTone="success" />
        <StatCard icon={FiAward} label="Offers" value="1" delta="New" deltaTone="info" />
        <StatCard icon={FiXCircle} label="Rejected" value="4" delta="-1 this month" deltaTone="danger" />
      </div>

      <div className="dashboard-grid">
        <div className="flex-col gap-lg">
          <SpotlightCard className="insight-card">
            <div className="flex items-center gap-sm" style={{ marginBottom: 6 }}>
              <FiTrendingUp size={16} color="var(--success)" />
              <span className="section-title">Career progress</span>
            </div>
            <p className="supporting-text">Your application activity is up 24% this month. Interview conversion is trending upward — keep the momentum going.</p>
            <div className="insight-tags">
              <span className="insight-tag tone-success">3 applications need follow-up</span>
              <span className="insight-tag tone-info">Next interview in 2 days</span>
            </div>
          </SpotlightCard>

          <div className="card panel">
            <div className="flex items-center justify-between" style={{ marginBottom: 16 }}>
              <span className="section-title">Recent applications</span>
              <button className="link-btn" onClick={() => navigate('/applications')}>
                View all <FiArrowRight size={13} />
              </button>
            </div>
            <div className="mini-app-list">
              {recentApplications.map((app) => (
                <div className="mini-app-row" key={app.id}>
                  <div className="mini-app-logo">{app.company.slice(0, 1)}</div>
                  <div className="mini-app-info">
                    <p className="mini-app-role">{app.role}</p>
                    <p className="metadata">{app.company} · {app.location}</p>
                  </div>
                  <StatusBadge status={app.status} />
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className="flex-col gap-lg">
          <div className="card panel">
            <div className="flex items-center justify-between" style={{ marginBottom: 16 }}>
              <span className="section-title">Upcoming interviews</span>
              <button className="link-btn" onClick={() => navigate('/interviews')}>
                View all <FiArrowRight size={13} />
              </button>
            </div>
            {upcomingInterviews.length === 0 ? (
              <p className="supporting-text">No interviews scheduled yet.</p>
            ) : (
              <div className="flex-col gap-sm">
                {upcomingInterviews.map((iv) => (
                  <div className="upcoming-interview" key={iv.id}>
                    <div className="upcoming-interview-date">
                      <FiClock size={13} />
                      <span>{iv.date} · {iv.time}</span>
                    </div>
                    <p className="mini-app-role">{iv.role}</p>
                    <p className="metadata">{iv.company} · {iv.type}</p>
                  </div>
                ))}
              </div>
            )}
          </div>

          <div className="card panel">
            <div className="flex items-center gap-sm" style={{ marginBottom: 16 }}>
              <FiAlertCircle size={15} color="var(--blue-300)" />
              <span className="section-title">Recent activity</span>
            </div>
            <div className="activity-list">
              {activity.map((a) => (
                <div className="activity-item" key={a.id}>
                  <span className="activity-dot" />
                  <div>
                    <p className="activity-text">{a.text}</p>
                    <p className="metadata">{a.time}</p>
                  </div>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}
