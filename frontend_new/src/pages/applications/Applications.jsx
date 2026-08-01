import React, { useMemo, useState } from 'react'
import { FiPlus, FiSearch, FiBriefcase, FiEdit2, FiTrash2 } from 'react-icons/fi'
import PageHeader from '../../components/common/PageHeader.jsx'
import Button from '../../components/common/Button.jsx'
import StatCard from '../../components/common/StatCard.jsx'
import StatusBadge from '../../components/common/StatusBadge.jsx'
import Table from '../../components/common/Table.jsx'
import EmptyState from '../../components/common/EmptyState.jsx'
import { Select } from '../../components/forms/Field.jsx'
import AddApplicationModal from './AddApplicationModal.jsx'
import { useToast } from '../../context/ToastContext.jsx'
import { applications as initialApplications, statusOptions } from '../../data/mockData.js'
import './Applications.css'

export default function Applications() {
  const toast = useToast()
  const [apps, setApps] = useState(initialApplications)
  const [query, setQuery] = useState('')
  const [statusFilter, setStatusFilter] = useState('all')
  const [sortBy, setSortBy] = useState('recent')
  const [modalOpen, setModalOpen] = useState(false)
  const [editing, setEditing] = useState(null)

  const filtered = useMemo(() => {
    let list = apps.filter((a) => {
      const matchesQuery = `${a.company} ${a.role}`.toLowerCase().includes(query.toLowerCase())
      const matchesStatus = statusFilter === 'all' || a.status === statusFilter
      return matchesQuery && matchesStatus
    })
    list = [...list].sort((a, b) => {
      if (sortBy === 'recent') return new Date(b.appliedDate) - new Date(a.appliedDate)
      if (sortBy === 'oldest') return new Date(a.appliedDate) - new Date(b.appliedDate)
      if (sortBy === 'company') return a.company.localeCompare(b.company)
      return 0
    })
    return list
  }, [apps, query, statusFilter, sortBy])

  const totals = {
    total: apps.length,
    active: apps.filter((a) => ['applied', 'interview', 'selected'].includes(a.status)).length,
    interviews: apps.filter((a) => a.status === 'interview').length,
    offers: apps.filter((a) => a.status === 'offer').length,
  }

  const openAdd = () => { setEditing(null); setModalOpen(true) }
  const openEdit = (app) => { setEditing(app); setModalOpen(true) }

  const handleSave = (app) => {
    setApps((prev) => {
      const exists = prev.some((a) => a.id === app.id)
      return exists ? prev.map((a) => (a.id === app.id ? app : a)) : [app, ...prev]
    })
    setModalOpen(false)
    toast.success(editing ? 'Application updated.' : 'Application saved.')
  }

  const handleDelete = (id) => {
    setApps((prev) => prev.filter((a) => a.id !== id))
    toast.info('Application deleted.')
  }

  const columns = [
    { key: 'company', header: 'Company', render: (row) => (
      <div className="app-cell-company">
        <div className="app-cell-logo">{row.company.slice(0, 1)}</div>
        <div>
          <p className="app-cell-name">{row.company}</p>
          <p className="metadata">{row.role}</p>
        </div>
      </div>
    ) },
    { key: 'status', header: 'Status', render: (row) => <StatusBadge status={row.status} /> },
    { key: 'appliedDate', header: 'Applied', render: (row) => <span className="metadata">{row.appliedDate}</span> },
    { key: 'location', header: 'Location' },
    { key: 'resume', header: 'Resume', render: (row) => row.resume || '—' },
    { key: 'actions', header: '', render: (row) => (
      <div className="flex gap-xs app-row-actions">
        <button className="icon-action" onClick={(e) => { e.stopPropagation(); openEdit(row) }} aria-label="Edit"><FiEdit2 size={14} /></button>
        <button className="icon-action danger" onClick={(e) => { e.stopPropagation(); handleDelete(row.id) }} aria-label="Delete"><FiTrash2 size={14} /></button>
      </div>
    ) },
  ]

  return (
    <div>
      <PageHeader
        title="Your applications"
        description="Keep every opportunity organized from application to offer."
        actions={<Button variant="primary" specular icon={FiPlus} onClick={openAdd}>Apply</Button>}
      />

      <div className="stat-grid-4" style={{ marginBottom: 24 }}>
        <StatCard icon={FiBriefcase} label="Total applications" value={totals.total} spotlight={false} />
        <StatCard icon={FiBriefcase} label="Active applications" value={totals.active} spotlight={false} />
        <StatCard icon={FiBriefcase} label="Interviews" value={totals.interviews} spotlight={false} />
        <StatCard icon={FiBriefcase} label="Offers" value={totals.offers} spotlight={false} />
      </div>

      <div className="applications-toolbar">
        <div className="search-input">
          <FiSearch size={15} />
          <input
            placeholder="Search by company or role…"
            value={query}
            onChange={(e) => setQuery(e.target.value)}
          />
        </div>
        <div className="flex gap-sm">
          <Select
            value={statusFilter}
            onChange={(e) => setStatusFilter(e.target.value)}
            options={[{ value: 'all', label: 'All statuses' }, ...statusOptions]}
          />
          <Select
            value={sortBy}
            onChange={(e) => setSortBy(e.target.value)}
            options={[
              { value: 'recent', label: 'Sort: Most recent' },
              { value: 'oldest', label: 'Sort: Oldest' },
              { value: 'company', label: 'Sort: Company A–Z' },
            ]}
          />
        </div>
      </div>

      {filtered.length === 0 ? (
        <EmptyState
          icon={FiBriefcase}
          title="Your next opportunity starts here."
          description="Start tracking your applications to see your career pipeline take shape."
          actionLabel="Apply now"
          onAction={openAdd}
        />
      ) : (
        <Table columns={columns} rows={filtered} onRowClick={openEdit} />
      )}

      <AddApplicationModal
        open={modalOpen}
        onClose={() => setModalOpen(false)}
        onSave={handleSave}
        initial={editing}
      />
    </div>
  )
}
