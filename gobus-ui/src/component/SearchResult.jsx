import React from "react";
import "bootstrap-icons/font/bootstrap-icons.css";
import BookingDetails from "./BookingDetails";

export default function SearchResult() {
  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <BookingDetails />
      <div className="main-container gtco-heading src-result">
        <strong>
          Mysore <i className="icon-arrow-right"></i> Mangalore 25/05/2024
        </strong>
        <hr />

        <div className="row">
          <div className="col-md-2 filter-section">
            <strong>FILTERS</strong>
            <div className="filter-content">
              <strong>Departure Time</strong>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-sunrise"></i> Before 6 am
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-brightness-high"></i> 6 am to 12 pm
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-sunset"></i> 12 pm to 6 pm
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-moon"></i> After 6 pm
              </div>
            </div>
            <div className="filter-content">
              <strong>Bus Type</strong>
              <div className="filter-item">
                <input type="checkbox" />
                AC Sleeper
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                AC Seater
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                Non AC Sleeper
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                Non AC Seater
              </div>
            </div>
            <div className="filter-content">
              <strong>Arrival Time</strong>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-sunrise"></i> Before 6 am
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-brightness-high"></i> 6 am to 12 pm
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-sunset"></i> 12 pm to 6 pm
              </div>
              <div className="filter-item">
                <input type="checkbox" />
                <i className="bi bi-moon"></i> After 6 pm
              </div>
            </div>
          </div>

          <div className="col-md-10">
            <div className="row src-result-header">
              <div className="col-md-3">
                <strong>98 Buses Found</strong>
              </div>
              <div className="col-md-1">Departure</div>
              <div className="col-md-1">Duration</div>
              <div className="col-md-1">Arrival</div>
              <div className="col-md-1">Ratings</div>
              <div className="col-md-2">Price</div>
              <div className="col-md-2">Seats Available</div>
            </div>

            <div className="row src-result-row">
              <div className="col-md-3 result-item">
                <h2>Durgamba Traveller Motors</h2>
                <h4>AC Sleeper</h4>
              </div>
              <div className="col-md-1 result-item">
                <h1>9:00 PM</h1>
                <h4>Sub urban bustand</h4>
              </div>
              <div className="col-md-1 result-item">
                <h4>6 Hours</h4>
              </div>
              <div className="col-md-1 result-item">
                <h2>6:00 AM</h2>
                <h4>Mangalore KSRC Busstand</h4>
              </div>
              <div className="col-md-1 result-item">
                <div className="badge btn-success">
                  <i className="bi bi-star-fill"></i> 4.6
                </div>
                <a href="/reviews">Reviews</a>
              </div>
              <div className="col-md-2 result-item">
                <h2>Starts from</h2>
                <h4>
                  INR <strong>250</strong>
                </h4>
              </div>
              <div className="col-md-2 result-item">
                <h2>16 Seats available</h2>
                <button
                  className="btn btn-info btn-sm"
                  data-toggle="modal"
                  data-target="#bookDetailsModal"
                >
                  Book
                </button>
              </div>
            </div>

            <div className="row src-result-row">
              <div className="col-md-3 result-item">
                <h2>Durgamba Traveller Motors</h2>
                <h4>AC Sleeper</h4>
              </div>
              <div className="col-md-1 result-item">
                <h1>9:00 PM</h1>
                <h4>Sub urban bustand</h4>
              </div>
              <div className="col-md-1 result-item">
                <h4>6 Hours</h4>
              </div>
              <div className="col-md-1 result-item">
                <h2>6:00 AM</h2>
                <h4>Mangalore KSRC Busstand</h4>
              </div>
              <div className="col-md-1 result-item">
                <div className="badge btn-warning">
                  <i className="bi bi-star-fill"></i> 3.6
                </div>
                <a href="/reviews">Reviews</a>
              </div>
              <div className="col-md-2 result-item">
                <h2>Starts from</h2>
                <h4>
                  INR <strong>250</strong>
                </h4>
              </div>
              <div className="col-md-2 result-item">
                <h2>16 Seats available</h2>
                <button className="btn btn-info btn-sm">Book</button>
              </div>
            </div>

            <div className="row src-result-row">
              <div className="col-md-3 result-item">
                <h2>Durgamba Traveller Motors</h2>
                <h4>AC Sleeper</h4>
              </div>
              <div className="col-md-1 result-item">
                <h1>9:00 PM</h1>
                <h4>Sub urban bustand</h4>
              </div>
              <div className="col-md-1 result-item">
                <h4>6 Hours</h4>
              </div>
              <div className="col-md-1 result-item">
                <h2>6:00 AM</h2>
                <h4>Mangalore KSRC Busstand</h4>
              </div>
              <div className="col-md-1 result-item">
                <div className="badge btn-success">
                  <i className="bi bi-star-fill"></i> 4.6
                </div>
                <a href="/reviews">Reviews</a>
              </div>
              <div className="col-md-2 result-item">
                <h2>Starts from</h2>
                <h4>
                  INR <strong>250</strong>
                </h4>
              </div>
              <div className="col-md-2 result-item">
                <h2>16 Seats available</h2>
                <button className="btn btn-info btn-sm">Book</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
