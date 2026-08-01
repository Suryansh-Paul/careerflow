# EVANZOFLOW — Frontend V1

A modern SaaS-style career management platform frontend, built as a portfolio project for a Java Full Stack Engineer.

## Stack
React + Vite + React Router + plain CSS (design-system driven) + React Icons.

## Getting started
```bash
npm install
npm run dev
```
Visit `http://localhost:5173`. Start at `/login` → `/register` → `/onboarding` → `/dashboard`.

## Project structure
```
src/
  components/
    common/     Button, SpotlightCard, StatusBadge, Modal, Toast, EmptyState,
                Skeleton, PageHeader, StatCard, Table, ProgressBar, Avatar
    layout/     Navbar, Sidebar, AppLayout, AuthLayout
    forms/      Input, Select, Textarea, PasswordStrength
  context/      ToastContext, SidebarContext
  data/         mockData.js — swap for real API calls
  pages/
    auth/           Login, Register
    onboarding/      Multi-step setup flow
    dashboard/       Home
    applications/    Applications tracker + Add/Edit modal
    companies/       Company tracker
    interviews/      Interview prep tracker
    resumes/         Resume workspace (AI features labeled "Upcoming")
    statistics/      Analytics (custom lightweight SVG charts, no chart lib dependency)
    profile/         Career profile
  styles/        variables.css (design tokens), reset, typography, utilities, global
```

## Connecting a Spring Boot backend later
All mock data lives in `src/data/mockData.js`, shaped to mirror future REST resources
(`/api/applications`, `/api/companies`, `/api/interviews`, `/api/resumes`, `/api/profile`).
Each page currently uses local component state seeded from that mock data — swap the
`useState(initialX)` calls for `fetch`/`axios` calls against your API and the UI layer
does not need to change.

Auth screens (`Login.jsx`, `Register.jsx`) have clearly marked integration points
(`// Integration point: POST /api/auth/login`, etc.) where the mocked `setTimeout`
should be replaced with real requests.

## Notes
- Sidebar collapse state and toast notifications are provided via React context and
  work across the whole authenticated app shell (`AppLayout`).
- `SpotlightCard` and the specular `Button` variant are used selectively, per the
  design brief, only on high-value cards and primary actions.
- Verified with `npm run build` — production build succeeds cleanly (94 modules).
