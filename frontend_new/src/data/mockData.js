// ---------------------------------------------------------------------------
// Mock data for EVANZOFLOW V1.
// Each export below maps 1:1 to a future Spring Boot REST resource, e.g.
// applications -> GET /api/applications, companies -> GET /api/companies.
// Replace with real API calls once the backend is available.
// ---------------------------------------------------------------------------

export const currentUser = {
  name: 'Aarav Mehta',
  username: 'aarav.mehta',
  email: 'aarav.mehta@evanzoflow.dev',
  location: 'Mumbai, India',
  bio: 'Aspiring Java Full Stack Engineer focused on backend systems and clean architecture.',
  profileCompletion: 78,
  education: {
    school: 'University of Mumbai',
    degree: 'B.E. in Computer Engineering',
    field: 'Computer Engineering',
    graduationYear: 2026,
  },
  skills: ['Java', 'Spring Boot', 'React', 'MySQL', 'Docker', 'REST APIs', 'System Design'],
  interests: ['Backend Engineering', 'Cloud Infrastructure', 'Developer Tools'],
  targetRoles: ['Java Backend Engineer', 'Full Stack Engineer', 'Software Engineer'],
  links: {
    github: 'github.com/aaravmehta',
    linkedin: 'linkedin.com/in/aaravmehta',
    portfolio: 'aaravmehta.dev',
  },
}

export const notifications = [
  { id: 1, title: 'Interview reminder: Razorpay in 2 days', time: '2h ago', unread: true },
  { id: 2, title: 'Application to Zeta moved to Interview', time: '5h ago', unread: true },
  { id: 3, title: 'Your resume "Backend_v3.pdf" was viewed', time: '1d ago', unread: false },
]

export const applications = [
  { id: 1, company: 'Razorpay', role: 'Backend Engineer Intern', status: 'interview', appliedDate: '2026-07-02', location: 'Bengaluru, IN', salary: '₹9 LPA', resume: 'Backend_v3.pdf', notes: 'Round 1 cleared, waiting for system design round.' },
  { id: 2, company: 'Zeta', role: 'Java Full Stack Engineer', status: 'interview', appliedDate: '2026-07-10', location: 'Bengaluru, IN', salary: '₹11 LPA', resume: 'FullStack_v2.pdf', notes: 'Recruiter call scheduled.' },
  { id: 3, company: 'Freshworks', role: 'Software Engineer I', status: 'applied', appliedDate: '2026-07-15', location: 'Chennai, IN', salary: '₹8.5 LPA', resume: 'Backend_v3.pdf', notes: '' },
  { id: 4, company: 'Postman', role: 'Backend Engineer', status: 'rejected', appliedDate: '2026-06-18', location: 'Bengaluru, IN', salary: '₹12 LPA', resume: 'Backend_v2.pdf', notes: 'Rejected after final round.' },
  { id: 5, company: 'Groww', role: 'SDE Intern', status: 'offer', appliedDate: '2026-06-05', location: 'Mumbai, IN', salary: '₹40k/mo', resume: 'FullStack_v2.pdf', notes: 'Offer received, evaluating.' },
  { id: 6, company: 'CRED', role: 'Java Developer', status: 'applied', appliedDate: '2026-07-20', location: 'Bengaluru, IN', salary: '₹10 LPA', resume: 'Backend_v3.pdf', notes: '' },
  { id: 7, company: 'Swiggy', role: 'Backend Engineer', status: 'selected', appliedDate: '2026-06-28', location: 'Bengaluru, IN', salary: '₹13 LPA', resume: 'Backend_v3.pdf', notes: 'Final HR round scheduled.' },
  { id: 8, company: 'Zoho', role: 'Software Developer', status: 'rejected', appliedDate: '2026-06-01', location: 'Chennai, IN', salary: '₹7 LPA', resume: 'Backend_v2.pdf', notes: '' },
]

export const companies = [
  { id: 1, name: 'Razorpay', industry: 'Fintech', location: 'Bengaluru, IN', interest: 'High', openApplications: 1, website: 'razorpay.com', notes: 'Strong backend engineering culture.' },
  { id: 2, name: 'Zeta', industry: 'Fintech', location: 'Bengaluru, IN', interest: 'High', openApplications: 1, website: 'zeta.tech', notes: 'Looking for full stack Java engineers.' },
  { id: 3, name: 'Groww', industry: 'FinTech / Investing', location: 'Mumbai, IN', interest: 'Medium', openApplications: 1, website: 'groww.in', notes: 'Great intern-to-full-time track record.' },
  { id: 4, name: 'Postman', industry: 'Developer Tools', location: 'Bengaluru, IN', interest: 'High', openApplications: 1, website: 'postman.com', notes: 'Dream company — reapply in 6 months.' },
  { id: 5, name: 'CRED', industry: 'Fintech', location: 'Bengaluru, IN', interest: 'Medium', openApplications: 1, website: 'cred.club', notes: '' },
]

export const interviews = [
  { id: 1, company: 'Razorpay', role: 'Backend Engineer Intern', type: 'System Design', date: '2026-07-28', time: '3:00 PM', prep: 'in-progress', notes: 'Focus on rate limiting & caching.' },
  { id: 2, company: 'Zeta', role: 'Java Full Stack Engineer', type: 'Recruiter Call', date: '2026-07-30', time: '11:00 AM', prep: 'not-started', notes: '' },
  { id: 3, company: 'Swiggy', role: 'Backend Engineer', type: 'HR Round', date: '2026-08-02', time: '5:30 PM', prep: 'not-started', notes: 'Discuss compensation expectations.' },
  { id: 4, company: 'Postman', role: 'Backend Engineer', type: 'Final Round', date: '2026-06-25', time: '2:00 PM', prep: 'completed', notes: 'Went well, did not get selected.', past: true },
]

export const resumes = [
  { id: 1, name: 'Backend_v3.pdf', targetRole: 'Java Backend Engineer', lastUpdated: '2026-07-18', type: 'PDF', size: '184 KB' },
  { id: 2, name: 'FullStack_v2.pdf', targetRole: 'Full Stack Engineer', lastUpdated: '2026-07-05', type: 'PDF', size: '201 KB' },
  { id: 3, name: 'Backend_v2.pdf', targetRole: 'Java Backend Engineer', lastUpdated: '2026-05-22', type: 'PDF', size: '176 KB' },
]

export const activity = [
  { id: 1, text: 'Moved Swiggy application to Selected', time: '3h ago' },
  { id: 2, text: 'Scheduled interview with Razorpay', time: '1d ago' },
  { id: 3, text: 'Uploaded resume Backend_v3.pdf', time: '2d ago' },
  { id: 4, text: 'Applied to CRED — Java Developer', time: '4d ago' },
  { id: 5, text: 'Saved Postman to tracked companies', time: '6d ago' },
]

export const statusOptions = [
  { value: 'applied', label: 'Applied' },
  { value: 'interview', label: 'Interview Scheduled' },
  { value: 'selected', label: 'Selected' },
  { value: 'offer', label: 'Offer Received' },
  { value: 'rejected', label: 'Rejected' },
]

export const applicationsOverTime = [
  { month: 'Feb', count: 3 }, { month: 'Mar', count: 5 }, { month: 'Apr', count: 4 },
  { month: 'May', count: 7 }, { month: 'Jun', count: 9 }, { month: 'Jul', count: 8 },
]

export const applicationsByStatus = [
  { label: 'Applied', value: 8, tone: 'info' },
  { label: 'Interview', value: 5, tone: 'warning' },
  { label: 'Selected', value: 2, tone: 'success' },
  { label: 'Offer', value: 1, tone: 'success' },
  { label: 'Rejected', value: 4, tone: 'danger' },
]

export const applicationsByRole = [
  { label: 'Backend Engineer', value: 12 },
  { label: 'Full Stack Engineer', value: 6 },
  { label: 'Software Engineer', value: 4 },
]
