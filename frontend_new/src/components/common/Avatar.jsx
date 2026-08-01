import React from 'react'
import './Avatar.css'

export default function Avatar({ name = 'U', size = 36, src }) {
  const initials = name
    .split(' ')
    .map((p) => p[0])
    .slice(0, 2)
    .join('')
    .toUpperCase()

  if (src) {
    return <img src={src} alt={name} className="avatar-img" style={{ width: size, height: size }} />
  }
  return (
    <div className="avatar-fallback" style={{ width: size, height: size, fontSize: size * 0.38 }}>
      {initials}
    </div>
  )
}
