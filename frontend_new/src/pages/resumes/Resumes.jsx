import React from 'react'
import { FiUpload, FiFileText, FiDownload, FiEye, FiZap } from 'react-icons/fi'
import PageHeader from '../../components/common/PageHeader.jsx'
import Button from '../../components/common/Button.jsx'
import SpotlightCard from '../../components/common/SpotlightCard.jsx'
import EmptyState from '../../components/common/EmptyState.jsx'
import { resumes } from '../../data/mockData.js'
import './Resumes.css'

export default function Resumes() {
  return (
    <div>
      <PageHeader
        title="Resume workspace"
        description="Manage every version of your resume and see which one is working."
        actions={<Button variant="primary" specular icon={FiUpload}>Upload resume</Button>}
      />

      <SpotlightCard className="ai-teaser">
        <div className="flex items-center gap-sm">
          <div className="ai-teaser-icon"><FiZap size={16} /></div>
          <div>
            <p className="section-title">AI resume scoring — upcoming</p>
            <p className="supporting-text" style={{ marginTop: 2 }}>
              Soon, EVANZOFLOW will analyze structure, ATS compatibility, and missing keywords against roles you're targeting.
            </p>
          </div>
          <span className="upcoming-pill">Upcoming</span>
        </div>
      </SpotlightCard>

      {resumes.length === 0 ? (
        <EmptyState
          icon={FiFileText}
          title="Build your resume workspace."
          description="Upload your resume to keep every version organized and ready to send."
          actionLabel="Upload resume"
          onAction={() => {}}
        />
      ) : (
        <div className="resume-list">
          {resumes.map((r) => (
            <div className="card resume-row" key={r.id}>
              <div className="resume-icon"><FiFileText size={18} /></div>
              <div className="resume-info">
                <p className="mini-app-role">{r.name}</p>
                <p className="metadata">Target: {r.targetRole} · Updated {r.lastUpdated} · {r.size}</p>
              </div>
              <div className="flex gap-xs">
                <button className="icon-action" aria-label="View"><FiEye size={15} /></button>
                <button className="icon-action" aria-label="Download"><FiDownload size={15} /></button>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  )
}
