import { BrowserRouter, Routes, Route } from "react-router-dom";

import DashboardPage from "../pages/Dashboard/DashboardPage";
import ApplicationsPage from "../pages/ApplicationsPage";
import CompaniesPage from "../pages/CompaniesPage";
import InterviewsPage from "../pages/InterviewsPage";
import ResumesPage from "../pages/ResumesPage";
import StatisticsPage from "../pages/StatisticsPage";
import NotFoundPage from "../pages/NotFoundPage";
import Layout from "../components/layout/Layout";

function AppRoutes(){
return (
<BrowserRouter>
<Routes>
<Route path="/" element={<Layout/>}>
<Route index element={<DashboardPage/>}  />
<Route path="/applications" element={<ApplicationsPage/>}  />
<Route path="/companies" element={<CompaniesPage/>}  />
<Route path="/interviews" element={<InterviewsPage/>}  />
<Route path="/resumes" element={<ResumesPage/>}  />
<Route path="/statistics" element={<StatisticsPage/>}  />
<Route path="*" element={<NotFoundPage/>}  />
</Route>

</Routes>
</BrowserRouter>
)
}
export default AppRoutes;