import React from 'react'
import './Field.css'

export function FieldWrap({ label, hint, error, optional, children }) {
  return (
    <label className="field-wrap">
      {label && (
        <span className="field-label">
          {label} {optional && <span className="field-optional">(optional)</span>}
        </span>
      )}
      {children}
      {error ? <span className="field-error">{error}</span> : hint ? <span className="field-hint">{hint}</span> : null}
    </label>
  )
}

export function Input({ label, hint, error, optional, className = '', ...rest }) {
  return (
    <FieldWrap label={label} hint={hint} error={error} optional={optional}>
      <input className={`field-input ${error ? 'has-error' : ''} ${className}`} {...rest} />
    </FieldWrap>
  )
}

export function Textarea({ label, hint, error, optional, className = '', ...rest }) {
  return (
    <FieldWrap label={label} hint={hint} error={error} optional={optional}>
      <textarea className={`field-input field-textarea ${error ? 'has-error' : ''} ${className}`} {...rest} />
    </FieldWrap>
  )
}

export function Select({ label, hint, error, optional, options = [], className = '', ...rest }) {
  return (
    <FieldWrap label={label} hint={hint} error={error} optional={optional}>
      <select className={`field-input field-select ${error ? 'has-error' : ''} ${className}`} {...rest}>
        {options.map((opt) => (
          <option key={opt.value ?? opt} value={opt.value ?? opt}>
            {opt.label ?? opt}
          </option>
        ))}
      </select>
    </FieldWrap>
  )
}
