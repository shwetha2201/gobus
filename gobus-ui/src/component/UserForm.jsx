import React from "react";

export default function UserForm({ action }) {
  if (action === "") return <></>;
  else
    return (
      <form className="form-inline">
        <div className="form-group mx-sm-3">
          <input
            type="text"
            className="form-control input-md"
            id="fullName"
            placeholder="Full Name"
          />
        </div>
        <div className="form-group mx-sm-3">
          <input
            type="text"
            className="form-control input-md"
            id="email"
            placeholder="Email"
          />
        </div>
        <div className="form-group mx-sm-3">
          <input
            type="text"
            className="form-control input-md"
            id="phone"
            placeholder="Phone #"
          />
        </div>
        <div className="form-group mx-sm-3">
          <input
            type="number"
            className="form-control input-md"
            id="age"
            placeholder="Age"
          />
        </div>
        <div className="form-group mx-sm-3">
          <select
            className="form-control input-md"
            id="gender"
            placeholder="Gender"
          >
            <option value="male">Male</option>
            <option value="female">Female</option>
          </select>
        </div>
        <div className="form-group mx-sm-3">
          {action == "add" ? (
            <button className="btn btn-sm btn-success">Add</button>
          ) : (
            <button className="btn btn-sm btn-warning">Update</button>
          )}
          <button className="btn btn-sm btn-secondary">Cancel</button>
        </div>
      </form>
    );
}
