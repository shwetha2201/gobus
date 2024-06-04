import React from "react";
import Barcode from "react-barcode";

export default function Ticket() {
  const print = () => {
    window.print();
  };

  return (
    <main className="gtco-cover gtco-cover-sm main-bg">
      <div className="ticket-container" id="ticket-container">
        <h1>GoBus</h1>
        <div className="ticket-header">
          <h4>Your Ticket</h4>
          <h4>Thank you, your booking is confirmed.</h4>
          <p>
            <strong>
              Please show your ticket to the driver when boarding your bus.
            </strong>
          </p>
          <p className="light-txt">We hope you enjoy your journey.</p>
          <hr />
        </div>
        <div className="ticket-passenger-details">
          <div className="row">
            <div className="col-md-2">
              <p className="light-txt">Lead Passenger:</p>
            </div>
            <div className="col-md-6">
              <h5>Mr N Kaul</h5>
              <p className="light-txt">1 Adult</p>
              <p>
                <strong>Contact: +91 9561353923</strong>
              </p>
            </div>
            <div className="col-md-3">
              <Barcode
                value="GoBus: ETPA016"
                width="1"
                height="60"
                fontSize="10"
              />
            </div>
          </div>

          <div className="row">
            <div className="col-md-2">
              <p className="light-txt">Ticket Number:</p>
            </div>
            <div className="col-md-8">
              <h5>ETPA016</h5>
              <p className="light-txt">
                An email has been sent to <strong>example@example.com</strong>
              </p>
              <p className="light-txt">
                With detailed confirmation of all items in your booking
              </p>
            </div>
            <div className="col-md-3"></div>
          </div>
        </div>
        <hr />

        <div className="ticket-journey-details">
          <h2>Mysore to Mangalore</h2>
          <p className="light-txt">
            Duration: <strong>6h 30M</strong> Trip: <strong>210</strong>
          </p>
          <div className="row journey-header">
            <div className="col-md-2">Date of travel</div>
            <div className="col-md-1">Departure</div>
            <div className="col-md-1">Arrival</div>
            <div className="col-md-3">From</div>
            <div className="col-md-3">To</div>
          </div>
          <div className="row journey-detail">
            <div className="col-md-2">Wed 13 Apr 2011</div>
            <div className="col-md-1">10:30 AM</div>
            <div className="col-md-1">1:00 PM</div>
            <div className="col-md-3">Suburban Bus stand Mysore</div>
            <div className="col-md-3">Mangalore KSRTC Busstand</div>
          </div>
        </div>
        <hr />
        <div className="ticket-payment-details">
          <div className="row">
            <div className="col-md-9">
              <h4>Payment taken from your account</h4>
              <p className="light-txt">card ************5549</p>
            </div>
            <div className="col-md-3">
              <div className="row">
                <div className="col-md-6 light-txt">Ticket:</div>
                <div className="col-md-6 light-txt">INR 250.00</div>
              </div>
              <div className="row">
                <div className="col-md-6 light-txt">Tax:</div>
                <div className="col-md-6 light-txt">INR 0.00</div>
              </div>
              <div className="row">
                <div className="col-md-6">
                  <h4>Total: </h4>
                </div>
                <div className="col-md-6">
                  <h4>INR 0.00</h4>
                </div>
              </div>
            </div>
          </div>
        </div>
        <hr />
        <div className="ticket-important-details">
          <h3>Important Information</h3>
          <div className="ticket-imp-info-section">
            <h5>Ticket and ID Card</h5>
            <p>
              Please bring your ticket along with your government ID proof with
              you. Show it to the driver before you board the bus.
            </p>
          </div>
          <div className="ticket-imp-info-section">
            <h5>Amendments and Refunds</h5>
            <p>
              Amendments or the validation of an open return ticket can be made
              to tickets at any time prior to the journey.
            </p>
          </div>
        </div>
        <hr />
        <div className="ticket-buttons">
          <button className="btn btn-sm btn-secondary">Download PDF</button>
          <button className="btn btn-sm btn-warning" onClick={print}>
            Print
          </button>
          <button className="btn btn-sm btn-success">Email</button>
          <button className="btn btn-sm btn-info">SMS</button>
        </div>
      </div>
    </main>
  );
}
