function ApplicationForm({isClose}){
    return (
    <div className="overlay">
      <div className="modal">
        <h1>Add Application</h1>
        <form>
            <label >Company: </label>
            <input type="text" placeholder="Google"/>
            <label >Role: </label>
            <input type="text" placeholder="sde,ai,ml..."/>
           
            <label >Status: </label>
             <select>
                <option>Applied</option>
                <option>Selected</option>
                <option>Rejected</option>
                <option>Interview Scheduled</option>
                <option>Offer Recieved</option>
            </select>
            <label >Applied Date: </label>
            <input type="date" />
            <label >Resume Used: </label>
            <select>
                <option>AI ML resume</option>
                <option>SDE resume</option>
                <option>Data scientist resume</option>
                <option>CyberSecurity resume</option>
            </select>
            <label>Location:</label>
            <input type="text" placeholder="Remote"/>
            <label>Expected Salary :</label>
            <input
                        type="number"
                        placeholder="600000"
                        min="0"
                        max="10000000000"
                        step="10000"
            />
            <label >Notes:</label>
            <textarea></textarea>
    <div className="modal-btn">
        <button  type="button" onClick={isClose} className="cancel-btn">Cancel </button>
        <button className="save-btn">Save</button>
    </div>
        </form>

      </div>
    </div>)
}
export default ApplicationForm