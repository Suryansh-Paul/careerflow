import React from 'react'
import Button from './Button.jsx'
import './EmptyState.css'

export default function EmptyState({ icon: Icon, title, description, actionLabel, onAction }) {
  return (
    <div className="empty-state">
      {Icon && (
        <div className="empty-icon">
          <Icon size={26} />
        </div>
      )}
      <h3 className="empty-title">{title}</h3>
      {description && <p className="empty-desc">{description}</p>}
      {actionLabel && onAction && (
        <Button variant="primary" onClick={onAction} className="empty-action">
          {actionLabel}
        </Button>
      )}
    </div>
  )
}
