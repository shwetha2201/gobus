import React from "react";
import "bootstrap-icons/font/bootstrap-icons.css";
import Payment from "./Payment";

export default function BookingDetails() {
  return (
    <>
      <Payment />
      <div
        className="modal fade"
        id="bookDetailsModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
      >
        <div className="modal-dialog modal-sm" role="document">
          <div className="modal-content">
            <div className="modal-header">
              <h5 className="modal-title" id="exampleModalLabel">
                Booking Details
              </h5>
              <button
                type="button"
                className="close"
                data-dismiss="modal"
                aria-label="Close"
              >
                <span aria-hidden="true">&times;</span>
              </button>
            </div>

            <div className="modal-body book-cnfrm-body">
              <h4 className="text-center">Boarding & Dropping</h4>
              <div className="book-details-body">
                <div className="left-dots">
                  <div>&#x2022;</div>
                  <div className="vlines"></div>
                  <div>&#x2022;</div>
                </div>
                <div className="book-details-loc">
                  <div className="row">
                    <div className="col-md-8">
                      <h5>Mysore Suburban Bustand</h5>
                    </div>
                    <div className="col-md-4">
                      <strong>
                        26/05/2024 <br />
                        9:00 PM
                      </strong>
                    </div>
                  </div>
                  <div className="row">
                    <div className="col-md-8 line-0">
                      <h5>Mangalore KSRTC Busstand</h5>
                    </div>
                    <div className="col-md-4">
                      <strong>
                        27/05/2024 <br />
                        6:00 AM
                      </strong>
                    </div>
                  </div>
                </div>
              </div>
              <hr />
              <div className="passenger-details-body">
                <h4 className="text-center">Passenger Details</h4>
                <form>
                  <div className="row">
                    <div className="form-group col-md-5 text-right">
                      <label for="passengerName">
                        Primary Passenger Name:{" "}
                      </label>
                    </div>
                    <div className="form-group col-md-7">
                      <input
                        type="text"
                        className="form-control input-sm"
                        id="passengerName"
                      />
                    </div>
                  </div>
                  <div className="row">
                    <div className="form-group col-md-5 text-right">
                      <label for="contactNo">Passenger Contact #: </label>
                    </div>
                    <div className="form-group col-md-7">
                      <input
                        type="text"
                        className="form-control input-sm"
                        id="contactNo"
                      />
                    </div>
                  </div>
                </form>
              </div>
              <hr />
              <h5 className="text-right">
                No of Seats:
                <input
                  type="number"
                  value="2"
                  className="form-control input-sm"
                  id="seats"
                />
              </h5>
              <hr />
              <h5 className="text-right">
                Total Fare: <strong>INR 250.00</strong>
              </h5>
            </div>

            <div className="modal-footer">
              <button
                type="button"
                className="btn btn-warning btn-sm"
                data-toggle="modal"
                data-target="#paymentModal"
                data-dismiss="modal"
              >
                Proceed to Payment...
              </button>
              <button
                type="button"
                className="btn btn-secondary btn-sm"
                data-dismiss="modal"
              >
                Cancel
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
