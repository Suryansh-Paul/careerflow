import Statcard from "../components/common/Statcard";
function DashboardPage(){
    return (
        <> 
            <div id="dashboard">
                <div className="welcome">
                 <h1>👋 Welcome back to EVANZOFLOW</h1>
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