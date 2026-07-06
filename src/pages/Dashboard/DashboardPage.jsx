import Statcard from "../../components/common/Statcard";
import  "./DashboardPage.css";
function DashboardPage(){
    return (
        <> 
            <div id="dashboard">
                <div className="welcome">
                <a href="https://github.com/Suryansh-Paul" target="_blank" rel="noreferrer">
                 <img src="/logoecf.jpg" alt="EVANZOFLOW Logo" className="dashboard-logo" />
                </a>
                 <h1> Welcome back to EVANZOFLOW</h1>
                   <p>
                    Track, manage, and organize your entire career journey in one place —
                    from applications and interviews to offers and resumes.
                   </p>
                </div>
                <div id="stats">
                    <Statcard title={"Applications"}  value ={"14"}  />
                    <Statcard title={"Interviews"}  value ={"4"}  />
                    <Statcard title={"Offers"}  value ={"2"}  />
                    <Statcard title={"Rejected"}  value ={"10"}  />
                </div>
             
            </div>
         </>
    )
}
export default DashboardPage;