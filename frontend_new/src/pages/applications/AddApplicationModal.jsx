import React, { useState, useEffect } from 'react'
import Modal from '../../components/common/Modal.jsx'
import Button from '../../components/common/Button.jsx'
import { Input, Select, Textarea } from '../../components/forms/Field.jsx'
import { statusOptions, resumes } from '../../data/mockData.js'

const empty = {
  company: '', role: '', status: 'applied', appliedDate: '', location: '', salary: '', resume: '', notes: '',
}

export default function AddApplicationModal({ open, onClose, onSave, initial }) {
  const [form, setForm] = useState(empty)
  const [errors, setErrors] = useState({})

  useEffect(() => {
    setForm(initial ? { ...initial } : empty)
    setErrors({})
  }, [initial, open])

  const update = (key) => (e) => setForm((f) => ({ ...f, [key]: e.target.value }))

  const validate = () => {
    const next = {}
    if (!form.company.trim()) next.company = 'Company name is required.'
    if (!form.role.trim()) next.role = 'Role is required.'
    if (!form.appliedDate) next.appliedDate = 'Applied date is required.'
    setErrors(next)
    return Object.keys(next).length === 0
  }

  const handleSave = () => {
    if (!validate()) return
    onSave({ ...form, id: initial?.id ?? Date.now() })
  }

  return (
    <Modal
      open={open}
      onClose={onClose}
      title={initial ? 'Edit application' : 'Add application'}
      description="Keep every opportunity organized from application to offer."
      footer={
        <>
          <Button variant="secondary" onClick={onClose}>Cancel</Button>
          <Button variant="primary" onClick={handleSave}>{initial ? 'Save changes' : 'Add application'}</Button>
        </>
      }
    >
      <div className="flex-col gap-md">
        <div className="field-row">
          <Input label="Company" placeholder="Razorpay" value={form.company} onChange={update('company')} error={errors.company} />
          <Input label="Role" placeholder="Backend Engineer" value={form.role} onChange={update('role')} error={errors.role} />
        </div>
        <div className="field-row">
          <Select label="Status" value={form.status} onChange={update('status')} options={statusOptions} />
          <Input label="Applied date" type="date" value={form.appliedDate} onChange={update('appliedDate')} error={errors.appliedDate} />
        </div>
        <div className="field-row">
          <Input label="Location" placeholder="Bengaluru, IN" value={form.location} onChange={update('location')} optional />
          <Input label="Expected salary" placeholder="₹10 LPA" value={form.salary} onChange={update('salary')} optional />
        </div>
        <Select
          label="Resume used"
          value={form.resume}
          onChange={update('resume')}
          optional
          options={[{ value: '', label: 'Select a resume' }, ...resumes.map((r) => ({ value: r.name, label: r.name }))]}
        />
        <Textarea label="Notes" placeholder="Interview stage details, follow-up reminders…" value={form.notes} onChange={update('notes')} optional />
      </div>
    </Modal>
  )
}
