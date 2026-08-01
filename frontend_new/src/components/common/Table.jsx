import React from 'react'
import './Table.css'

/**
 * columns: [{ key, header, render?(row) }]
 * rows: array of data objects (must include `id`)
 */
export default function Table({ columns, rows, onRowClick }) {
  return (
    <div className="table-wrap">
      <div className="scroll-x">
        <table className="app-table">
          <thead>
            <tr>
              {columns.map((col) => (
                <th key={col.key}>{col.header}</th>
              ))}
            </tr>
          </thead>
          <tbody>
            {rows.map((row) => (
              <tr key={row.id} onClick={() => onRowClick?.(row)} className={onRowClick ? 'clickable' : ''}>
                {columns.map((col) => (
                  <td key={col.key}>{col.render ? col.render(row) : row[col.key]}</td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
