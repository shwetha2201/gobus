import React from "react";

import UserForm from "./UserForm";

export default function Users() {
  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <div className="main-container">
        <div className="row">
          <div className="col-md-8 col-md-offset-2 text-center gtco-heading">
            <h2>App Users List</h2>
            <p>Manage all the users here. Here you can add a new admin.</p>
            <button className="btn btn-md btn-info">Add a new Admin</button>
            <table className="table text-left table-hover table-sm">
              <thead>
                <tr>
                  <th scope="col">User #</th>
                  <th scope="col">Name</th>
                  <th scope="col">Email</th>
                  <th scope="col">Phone</th>
                  <th scope="col">Age</th>
                  <th scope="col">Gender</th>
                  <th scope="col">Type</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">56123</th>
                  <td>Mark Travels</td>
                  <td>user@example.com</td>
                  <td>1892532214</td>
                  <td>12</td>
                  <td>M</td>
                  <td>
                    <span className="badge btn-secondary">bus-owner</span>
                  </td>
                  <td>
                    <button className="ml-1 btn-xs btn-warning">
                      &nbsp;<i className="icon-edit"></i>
                    </button>
                  </td>
                </tr>
                <tr>
                  <th scope="row">56123</th>
                  <td>Mark Travels</td>
                  <td>user@example.com</td>
                  <td>1892532214</td>
                  <td>12</td>
                  <td>M</td>
                  <td>
                    <span className="badge btn-secondary">passenger</span>
                  </td>
                  <td>
                    <button className="ml-1 btn-xs btn-warning">
                      &nbsp;<i className="icon-edit"></i>
                    </button>
                  </td>
                </tr>
                <tr>
                  <th scope="row">56123</th>
                  <td>Mark Travels</td>
                  <td>user@example.com</td>
                  <td>1892532214</td>
                  <td>12</td>
                  <td>M</td>
                  <td>
                    <span className="badge btn-secondary">admin</span>
                  </td>
                  <td>
                    <button className="ml-1 btn-xs btn-warning">
                      &nbsp;<i className="icon-edit"></i>
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
            <UserForm />
          </div>
        </div>
      </div>
    </main>
  );
}
