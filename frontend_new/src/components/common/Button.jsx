import React from 'react'
import './Button.css'

/**
 * Generic button. variant: primary | secondary | ghost | danger | outline
 * Use `specular` for the one or two highest-value actions on a page.
 */
export default function Button({
  children,
  variant = 'secondary',
  size = 'md',
  specular = false,
  loading = false,
  icon: Icon,
  iconRight: IconRight,
  block = false,
  className = '',
  ...rest
}) {
  const sizeClass = size === 'sm' ? 'btn-sm' : size === 'lg' ? 'btn-lg' : ''

  if (specular) {
    return (
      <button
        className={`btn btn-specular ${sizeClass} ${block ? 'btn-block' : ''} ${loading ? 'btn-loading' : ''} ${className}`}
        disabled={loading || rest.disabled}
        {...rest}
      >
        <span>
          {loading ? <i className="btn-spinner" /> : Icon ? <Icon size={16} /> : null}
          {children}
          {IconRight && !loading ? <IconRight size={16} /> : null}
        </span>
      </button>
    )
  }

  return (
    <button
      className={`btn btn-${variant} ${sizeClass} ${block ? 'btn-block' : ''} ${loading ? 'btn-loading' : ''} ${className}`}
      disabled={loading || rest.disabled}
      {...rest}
    >
      {loading ? <i className="btn-spinner" /> : Icon ? <Icon size={16} /> : null}
      {children}
      {IconRight && !loading ? <IconRight size={16} /> : null}
    </button>
  )
}
