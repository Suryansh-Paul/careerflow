import React from 'react'
import './Skeleton.css'

export function Skeleton({ width = '100%', height = 14, radius = 6, style = {} }) {
  return <div className="skeleton" style={{ width, height, borderRadius: radius, ...style }} />
}

export function SkeletonCard() {
  return (
    <div className="card skeleton-card">
      <Skeleton width={36} height={36} radius={10} />
      <Skeleton width="60%" height={12} style={{ marginTop: 18 }} />
      <Skeleton width="40%" height={20} style={{ marginTop: 10 }} />
    </div>
  )
}

export function SkeletonRow() {
  return (
    <div className="flex items-center gap-md skeleton-row">
      <Skeleton width={34} height={34} radius={8} />
      <div className="flex-col gap-xs" style={{ flex: 1 }}>
        <Skeleton width="30%" height={11} />
        <Skeleton width="50%" height={13} />
      </div>
      <Skeleton width={90} height={22} radius={999} />
    </div>
  )
}
