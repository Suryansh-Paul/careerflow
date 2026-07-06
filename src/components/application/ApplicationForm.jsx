function ApplicationForm({ isClose }) {
  return (
    <div className="overlay">
      <div className="modal">
        <h1>Add Application</h1>

        <form>

          <div className="form-group">
            <label htmlFor="company">Company</label>
            <input
              id="company"
              type="text"
              placeholder="Google"
            />
          </div>

          <div className="form-group">
            <label htmlFor="role">Job Role</label>
            <input
              id="role"
              type="text"
              placeholder="Software Engineer"
            />
          </div>

          <div className="form-group">
            <label htmlFor="status">Status</label>
            <select id="status">
              <option>Applied</option>
              <option>Interview Scheduled</option>
              <option>Offer Received</option>
              <option>Selected</option>
              <option>Rejected</option>
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="date">Applied Date</label>
            <input
              id="date"
              type="date"
            />
          </div>

          <div className="form-group">
            <label htmlFor="resume">Resume Used</label>
            <select id="resume">
              <option>AI/ML Resume</option>
              <option>SDE Resume</option>
              <option>Data Science Resume</option>
              <option>Cyber Security Resume</option>
            </select>
          </div>

          <div className="form-group">
            <label htmlFor="location">Location</label>
            <input
              id="location"
              type="text"
              placeholder="Remote"
            />
          </div>

          <div className="form-group">
            <label htmlFor="salary">Expected Salary</label>
            <input
              id="salary"
              type="number"
              placeholder="600000"
              min="0"
              max="100000000"
              step="10000"
            />
          </div>

          <div className="form-group notes-group">
            <label htmlFor="notes">Notes</label>
            <textarea
              id="notes"
              placeholder="Add any notes..."
            ></textarea>
          </div>

          <div className="modal-btn">
            <button
              type="button"
              onClick={isClose}
              className="cancel-btn"
            >
              Cancel
            </button>

            <button
              type="submit"
              className="save-btn"
            >
             Apply Now
            </button>
          </div>

        </form>
      </div>
    </div>
  );
}

export default ApplicationForm;