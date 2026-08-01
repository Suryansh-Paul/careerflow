import React, { useState } from 'react'
import { FiPlus, FiHome, FiExternalLink, FiMapPin } from 'react-icons/fi'
import PageHeader from '../../components/common/PageHeader.jsx'
import Button from '../../components/common/Button.jsx'
import SpotlightCard from '../../components/common/SpotlightCard.jsx'
import EmptyState from '../../components/common/EmptyState.jsx'
import { companies as initialCompanies } from '../../data/mockData.js'
import './Companies.css'

const INTEREST_TONE = { High: 'success', Medium: 'warning', Low: 'muted' }

export default function Companies() {
  const [companies] = useState(initialCompanies)

  return (
    <div>
      <PageHeader
        title="Companies you're tracking"
        description="Save companies you want to pursue and keep their details close at hand."
        actions={<Button variant="primary" specular icon={FiPlus}>Save company</Button>}
      />

      {companies.length === 0 ? (
        <EmptyState
          icon={FiHome}
          title="Save companies you want to pursue."
          description="Track companies you're interested in, their open roles, and your notes — all in one place."
          actionLabel="Save a company"
          onAction={() => {}}
        />
      ) : (
        <div className="company-grid">
          {companies.map((c) => (
            <SpotlightCard key={c.id} className="company-card">
              <div className="flex items-center justify-between">
                <div className="company-logo">{c.name.slice(0, 1)}</div>
                <span className={`insight-tag tone-${INTEREST_TONE[c.interest]}`}>{c.interest} interest</span>
              </div>
              <h3 className="company-name">{c.name}</h3>
              <p className="metadata">{c.industry}</p>

              <div className="company-meta">
                <span><FiMapPin size={12} /> {c.location}</span>
                <span><FiExternalLink size={12} /> {c.website}</span>
              </div>

              {c.notes && <p className="company-notes">{c.notes}</p>}

              <div className="divider" style={{ margin: '14px 0' }} />
              <div className="flex items-center justify-between">
                <span className="metadata">{c.openApplications} open application{c.openApplications !== 1 ? 's' : ''}</span>
                <Button variant="ghost" size="sm">View details</Button>
              </div>
            </SpotlightCard>
          ))}
        </div>
      )}
    </div>
  )
}
