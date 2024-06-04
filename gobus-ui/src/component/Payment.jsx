import React from "react";
import "bootstrap-icons/font/bootstrap-icons.css";

export default function Payment() {
  return (
    <div
      className="modal fade"
      id="paymentModal"
      tabindex="-1"
      role="dialog"
      aria-labelledby="paymentModal"
      aria-hidden="true"
    >
      <div className="modal-dialog modal-sm" role="document">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="paymentModal">
              Payment Details
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
            <div className="passenger-details-body">
              <h4 className="text-center">Card Details</h4>
              <form>
                <div className="row">
                  <div className="form-group col-md-5 text-right">
                    <label for="passengerName">Card Number: </label>
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
                    <label for="contactNo">CVV: </label>
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
              Total Fare: <strong>INR 250.00</strong>
            </h5>
          </div>
          <div className="modal-footer">
            <button type="button" className="btn btn-success btn-sm">
              PAY
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
  );
}
