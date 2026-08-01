import React from 'react'
import { FiCalendar, FiClock, FiCheckCircle, FiPlus } from 'react-icons/fi'
import PageHeader from '../../components/common/PageHeader.jsx'
import Button from '../../components/common/Button.jsx'
import EmptyState from '../../components/common/EmptyState.jsx'
import { interviews } from '../../data/mockData.js'
import './Interviews.css'

const PREP_LABEL = {
  'not-started': { label: 'Prep not started', tone: 'muted' },
  'in-progress': { label: 'Prep in progress', tone: 'warning' },
  completed: { label: 'Prep completed', tone: 'success' },
}

function InterviewCard({ iv }) {
  const prep = PREP_LABEL[iv.prep]
  return (
    <div className="card interview-card">
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-sm">
          <div className="company-logo">{iv.company.slice(0, 1)}</div>
          <div>
            <p className="mini-app-role">{iv.role}</p>
            <p className="metadata">{iv.company} · {iv.type}</p>
          </div>
        </div>
        <span className={`insight-tag tone-${prep.tone}`}>{prep.label}</span>
      </div>
      <div className="interview-time">
        <FiClock size={13} /> {iv.date} at {iv.time}
      </div>
      {iv.notes && <p className="company-notes">{iv.notes}</p>}
    </div>
  )
}

export default function Interviews() {
  const upcoming = interviews.filter((i) => !i.past)
  const completed = interviews.filter((i) => i.past)

  return (
    <div>
      <PageHeader
        title="Interview preparation"
        description="Your career preparation workspace — every interview, organized and ready."
        actions={<Button variant="primary" specular icon={FiPlus}>Log interview</Button>}
      />

      <div className="flex-col gap-lg">
        <section>
          <div className="flex items-center gap-sm" style={{ marginBottom: 14 }}>
            <FiCalendar size={15} color="var(--blue-300)" />
            <span className="section-title">Upcoming</span>
          </div>
          {upcoming.length === 0 ? (
            <EmptyState
              icon={FiCalendar}
              title="No interviews scheduled yet."
              description="Once you have an interview, we'll help you keep every detail organized."
            />
          ) : (
            <div className="interview-grid">
              {upcoming.map((iv) => <InterviewCard iv={iv} key={iv.id} />)}
            </div>
          )}
        </section>

        {completed.length > 0 && (
          <section>
            <div className="flex items-center gap-sm" style={{ marginBottom: 14 }}>
              <FiCheckCircle size={15} color="var(--success)" />
              <span className="section-title">Completed</span>
            </div>
            <div className="interview-grid">
              {completed.map((iv) => <InterviewCard iv={iv} key={iv.id} />)}
            </div>
          </section>
        )}
      </div>
    </div>
  )
}
