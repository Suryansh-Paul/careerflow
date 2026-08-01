import React, { useRef } from 'react'
import './SpotlightCard.css'

/**
 * SpotlightCard — reserved for high-importance content only
 * (dashboard stats, insight cards, resume analysis, AI recommendations).
 * Tracks pointer position to render a soft radial highlight.
 */
export default function SpotlightCard({ children, className = '', ...rest }) {
  const ref = useRef(null)

  const handleMove = (e) => {
    const el = ref.current
    if (!el) return
    const rect = el.getBoundingClientRect()
    el.style.setProperty('--spot-x', `${e.clientX - rect.left}px`)
    el.style.setProperty('--spot-y', `${e.clientY - rect.top}px`)
  }

  return (
    <div
      ref={ref}
      className={`spotlight-card ${className}`}
      onMouseMove={handleMove}
      {...rest}
    >
      <div className="spotlight-glow" />
      <div className="spotlight-content">{children}</div>
    </div>
  )
}
