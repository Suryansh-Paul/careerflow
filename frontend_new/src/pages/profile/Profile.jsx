import React, { useState } from 'react'
import {
  FiEdit2, FiMapPin, FiGithub, FiLinkedin, FiGlobe, FiBookOpen, FiTarget, FiCode,
} from 'react-icons/fi'
import Avatar from '../../components/common/Avatar.jsx'
import Button from '../../components/common/Button.jsx'
import ProgressBar from '../../components/common/ProgressBar.jsx'
import Modal from '../../components/common/Modal.jsx'
import { Input, Textarea } from '../../components/forms/Field.jsx'
import { useToast } from '../../context/ToastContext.jsx'
import { currentUser } from '../../data/mockData.js'
import './Profile.css'

export default function Profile() {
  const toast = useToast()
  const [user, setUser] = useState(currentUser)
  const [editOpen, setEditOpen] = useState(false)
  const [draft, setDraft] = useState(currentUser)

  const openEdit = () => { setDraft(user); setEditOpen(true) }
  const saveEdit = () => {
    setUser(draft)
    setEditOpen(false)
    toast.success('Profile updated.')
  }

  return (
    <div>
      <div className="profile-header card">
        <div className="flex items-center gap-md profile-header-main">
          <Avatar name={user.name} size={72} />
          <div>
            <h1 className="page-title" style={{ marginBottom: 2 }}>{user.name}</h1>
            <p className="metadata">@{user.username}</p>
            <p className="profile-location"><FiMapPin size={13} /> {user.location}</p>
          </div>
        </div>
        <Button variant="secondary" icon={FiEdit2} onClick={openEdit}>Edit profile</Button>
      </div>

      <div className="card profile-completion">
        <div className="flex items-center justify-between" style={{ marginBottom: 10 }}>
          <span className="section-title">Your profile is {user.profileCompletion}% complete</span>
        </div>
        <ProgressBar value={user.profileCompletion} />
        <p className="supporting-text" style={{ marginTop: 10 }}>
          Complete your profile to get better career insights.
        </p>
      </div>

      <div className="profile-grid">
        <div className="flex-col gap-lg">
          <section className="card panel">
            <span className="section-title">About</span>
            <p className="supporting-text" style={{ marginTop: 10 }}>{user.bio}</p>
          </section>

          <section className="card panel">
            <div className="flex items-center gap-sm" style={{ marginBottom: 12 }}>
              <FiBookOpen size={15} color="var(--blue-300)" />
              <span className="section-title">Education</span>
            </div>
            <p className="mini-app-role">{user.education.degree}</p>
            <p className="metadata" style={{ marginTop: 3 }}>
              {user.education.school} · {user.education.field} · Class of {user.education.graduationYear}
            </p>
          </section>

          <section className="card panel">
            <div className="flex items-center gap-sm" style={{ marginBottom: 14 }}>
              <FiCode size={15} color="var(--blue-300)" />
              <span className="section-title">Skills</span>
            </div>
            <div className="chip-list">
              {user.skills.map((s) => <span className="chip" key={s}>{s}</span>)}
            </div>
          </section>
        </div>

        <div className="flex-col gap-lg">
          <section className="card panel">
            <div className="flex items-center gap-sm" style={{ marginBottom: 12 }}>
              <FiTarget size={15} color="var(--blue-300)" />
              <span className="section-title">Career preferences</span>
            </div>
            <p className="label" style={{ marginBottom: 8 }}>Target roles</p>
            <div className="chip-list" style={{ marginBottom: 16 }}>
              {user.targetRoles.map((r) => <span className="chip" key={r}>{r}</span>)}
            </div>
            <p className="label" style={{ marginBottom: 8 }}>Interests</p>
            <div className="chip-list">
              {user.interests.map((r) => <span className="chip" key={r}>{r}</span>)}
            </div>
          </section>

          <section className="card panel">
            <span className="section-title">Links</span>
            <div className="profile-links">
              <a href={`https://${user.links.github}`} target="_blank" rel="noreferrer"><FiGithub size={14} /> {user.links.github}</a>
              <a href={`https://${user.links.linkedin}`} target="_blank" rel="noreferrer"><FiLinkedin size={14} /> {user.links.linkedin}</a>
              <a href={`https://${user.links.portfolio}`} target="_blank" rel="noreferrer"><FiGlobe size={14} /> {user.links.portfolio}</a>
            </div>
          </section>
        </div>
      </div>

      <Modal
        open={editOpen}
        onClose={() => setEditOpen(false)}
        title="Edit profile"
        description="Update the details that appear across your career workspace."
        footer={
          <>
            <Button variant="secondary" onClick={() => setEditOpen(false)}>Cancel</Button>
            <Button variant="primary" onClick={saveEdit}>Save changes</Button>
          </>
        }
      >
        <div className="flex-col gap-md">
          <div className="field-row">
            <Input label="Full name" value={draft.name} onChange={(e) => setDraft((d) => ({ ...d, name: e.target.value }))} />
            <Input label="Username" value={draft.username} onChange={(e) => setDraft((d) => ({ ...d, username: e.target.value }))} />
          </div>
          <Input label="Location" value={draft.location} onChange={(e) => setDraft((d) => ({ ...d, location: e.target.value }))} />
          <Textarea label="Bio" value={draft.bio} onChange={(e) => setDraft((d) => ({ ...d, bio: e.target.value }))} />
        </div>
      </Modal>
    </div>
  )
}
