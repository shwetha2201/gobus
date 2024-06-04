import React from "react";

export default function Bookings() {
  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <div className="main-container">
        <div className="row">
          <div className="col-md-10 col-md-offset-1 text-center gtco-heading">
            <h2>Your Bookings</h2>
            <p>Upcoming Bookings</p>
            <table className="table text-left table-hover table-sm">
              <thead>
                <tr>
                  <th scope="col">Ticket #</th>
                  <th scope="col">Trip ID</th>
                  <th scope="col">Bus #</th>
                  <th scope="col">Bus Name</th>
                  <th scope="col">Driver Contact #</th>
                  <th scope="col">Passenger Name</th>
                  <th scope="col"># Seats</th>
                  <th scope="col">Boarding Time</th>
                  <th scope="col">Arrival Time</th>
                  <th scope="col">Price</th>
                  <th scope="col">Status</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>6921326</td>
                  <td>100</td>
                  <th scope="row">KA 19 V56123</th>
                  <th scope="row">KSRTC</th>
                  <td>213521232</td>
                  <td>John Luther</td>
                  <td>1</td>
                  <td>2/2/2024 10:10 PM</td>
                  <td>3/2/2024 6:00 AM</td>
                  <td>561.00Rs</td>
                  <td>
                    <span className="badge btn-success">Confirmed</span>
                  </td>
                  <td>
                    <button className="btn-xs btn-danger">Cancel</button>
                  </td>
                </tr>
                <tr>
                  <td>6921326</td>
                  <td>100</td>
                  <th scope="row">KA 19 V56123</th>
                  <th scope="row">KSRTC</th>
                  <td>213521232</td>
                  <td>John Luther</td>
                  <td>1</td>
                  <td>2/2/2024 10:10 PM</td>
                  <td>3/2/2024 6:00 AM</td>
                  <td>561.00Rs</td>
                  <td>
                    <span className="badge btn-success">Confirmed</span>
                  </td>
                  <td>
                    <button className="btn-xs btn-danger">Cancel</button>
                  </td>
                </tr>
                <tr>
                  <td>6921326</td>
                  <td>100</td>
                  <th scope="row">KA 19 V56123</th>
                  <th scope="row">KSRTC</th>
                  <td>213521232</td>
                  <td>John Luther</td>
                  <td>1</td>
                  <td>2/2/2024 10:10 PM</td>
                  <td>3/2/2024 6:00 AM</td>
                  <td>561.00Rs</td>
                  <td>
                    <span className="badge btn-success">Confirmed</span>
                  </td>
                  <td>
                    <button className="btn-xs btn-danger">Cancel</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <div className="row">
          <div className="col-md-10 col-md-offset-1 text-center gtco-heading">
            <p>Past Bookings</p>
            <table className="table text-left table-hover table-sm">
              <thead>
                <tr>
                  <th scope="col">Ticket #</th>
                  <th scope="col">Trip ID</th>
                  <th scope="col">Bus #</th>
                  <th scope="col">Bus Name</th>
                  <th scope="col">Driver Contact #</th>
                  <th scope="col">Passenger Name</th>
                  <th scope="col"># Seats</th>
                  <th scope="col">Boarding Time</th>
                  <th scope="col">Arrival Time</th>
                  <th scope="col">Price</th>
                  <th scope="col">Status</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>6921326</td>
                  <td>100</td>
                  <th scope="row">KA 19 V56123</th>
                  <th scope="row">KSRTC</th>
                  <td>213521232</td>
                  <td>John Luther</td>
                  <td>1</td>
                  <td>2/2/2024 10:10 PM</td>
                  <td>3/2/2024 6:00 AM</td>
                  <td>561.00Rs</td>
                  <td>
                    <span className="badge btn-success">Confirmed</span>
                  </td>
                </tr>
                <tr>
                  <td>6921326</td>
                  <td>100</td>
                  <th scope="row">KA 19 V56123</th>
                  <th scope="row">KSRTC</th>
                  <td>213521232</td>
                  <td>John Luther</td>
                  <td>1</td>
                  <td>2/2/2024 10:10 PM</td>
                  <td>3/2/2024 6:00 AM</td>
                  <td>561.00Rs</td>
                  <td>
                    <span className="badge btn-success">Confirmed</span>
                  </td>
                </tr>
                <tr>
                  <td>6921326</td>
                  <td>100</td>
                  <th scope="row">KA 19 V56123</th>
                  <th scope="row">KSRTC</th>
                  <td>213521232</td>
                  <td>John Luther</td>
                  <td>1</td>
                  <td>2/2/2024 10:10 PM</td>
                  <td>3/2/2024 6:00 AM</td>
                  <td>561.00Rs</td>
                  <td>
                    <span className="badge btn-success">Confirmed</span>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </main>
  );
}
