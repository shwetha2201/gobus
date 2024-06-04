import React from "react";

export default function Search() {
  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <div className="main-container">
        <div className="row">
          <div className="col-md-8 col-md-offset-2 text-center gtco-heading">
            <h2>India's No. 1 Online Bus Ticket Booking Site</h2>

            <form className="form-inline src-form col-xs-offset-1">
              <div className="src-box-shadow">
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control"
                    id="from"
                    placeholder="From"
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control"
                    id="to"
                    placeholder="To"
                  />
                </div>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control"
                    id="date"
                    placeholder="Date"
                  />
                </div>
                <div className="form-group">
                  <input
                    type="number"
                    className="form-control"
                    id="numberSeats"
                    placeholder="# Seats"
                  />
                </div>
                <div className="form-group">
                  <button className="btn btn-success">Search</button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
    </main>
  );
}
