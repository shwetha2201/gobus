import React, { useState, useEffect } from "react";
import Alert from "./helpers/Alert";
import config from "../config.json";
import { getUser } from "./helpers/useUser";
import Select from "react-select";

async function listBuses(ownerId, token) {
  try {
    return await fetch(`${config.basePath}/buses?ownerId=${ownerId}`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  } catch (error) {
    console.log("Error occured while listing the buses", error);
    return { err: "Error occured, please try later!" };
  }
}

async function addTrip(tripDto, token) {
  try {
    return await fetch(`${config.basePath}/trips`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(tripDto),
    });
  } catch (error) {
    console.log("Error occured while adding the trip ", error);
    return { err: "Error occured, please try later!" };
  }
}

export default function TripForm({ action, onCancel, onRefresh }) {
  const user = getUser();
  const [busId, setBusId] = useState();
  const [price, setPrice] = useState();
  const [from, setFrom] = useState();
  const [to, setTo] = useState();
  const [boardingTime, setBoardingTime] = useState();
  const [arrivalTime, setArrivalTime] = useState();
  const [driverName, setDriverName] = useState();
  const [driverPhoneNumber, setDriverPhoneNumber] = useState();
  const [error, setError] = useState();
  const [success, setSuccess] = useState();
  const [busList, setBusList] = useState();

  useEffect(() => {
    const fetchBuses = async () => {
      const response = await listBuses(user.user.userId, user.token);
      if (response.ok) {
        var buses = await response.json();
        setBusList(
          buses.map((b) => {
            return {
              value: b.busId,
              label: b.busNumber,
            };
          })
        );
      } else {
        console.log("error while fetching buses ", response);
      }
    };
    fetchBuses();
  }, []);

  const populateTripDto = () => {
    if (busId == "") {
      setError("Please select a bus");
      return false;
    } else if (price == "") {
      setError("Please enter price");
      return false;
    } else if (from == "") {
      setError("Please enter from");
      return false;
    } else if (to == "") {
      setError("Please enter to");
      return false;
    } else if (boardingTime == "") {
      setError("Please enter boarding time");
      return false;
    } else if (arrivalTime == "") {
      setError("Please enter arrival time");
      return false;
    } else if (driverName == "") {
      setError("Please enter driver name");
      return false;
    } else if (driverPhoneNumber == "") {
      setError("Please enter driver phone number");
      return false;
    } else {
      return {
        bus: {
          busId: busId,
        },
        from: from,
        to: to,
        boardingTime: boardingTime,
        arrivalTime: arrivalTime,
        driverName: driverName,
        driverPhoneNumber: driverPhoneNumber,
        confirmationStatus: false,
        seatPrice: price,
      };
    }
  };

  const handleAdd = async (e) => {
    e.preventDefault();
    const tripDto = populateTripDto();
    if (!tripDto) return;
    const response = await addTrip(tripDto, user.token);
    if (!response.ok) {
      if (response.status == 400 || response.status == 404) {
        var resJson = await response.json();
        setError(resJson.message);
      } else {
        setError("Something went wrong, please try later");
      }
      return;
    }
    const addTripResponse = await response.json();
    setError("");
    setSuccess("Trip had been added successfully");
    onRefresh();
  };

  const handleUpdate = () => {};

  if (action == "") return <></>;
  else
    return (
      <>
        <Alert error={error} success={success} />
        <form className="row">
          <hr />
          <div className="col-md-6 col-md-offset-3 text-left">
            <div className="row">
              <div className="form-group col-md-4">
                <label htmlFor="bus">Bus</label>
                <Select
                  options={busList}
                  id="bus"
                  className="input-md"
                  placeholder="Bus Number"
                  defaultValue={busId}
                  onChange={({ value }) => setBusId(value)}
                />
              </div>
              <div className="form-group col-md-4">
                <label htmlFor="price">Price</label>
                <input
                  type="number"
                  className="form-control input-md"
                  id="price"
                  placeholder="Price in INR"
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
                />
              </div>
              <div className="form-check form-switch col-md-4">
                <input class="form-check-input" type="checkbox" id="confirmationStatus" />
                <label class="form-check-label" for="confirmationStatus">
                  Active
                </label>
              </div>
            </div>
            <div className="row">
              <div className="form-group col-md-6">
                <label htmlFor="from">From</label>
                <input
                  type="text"
                  className="form-control input-md"
                  id="from"
                  placeholder="From"
                  value={from}
                  onChange={(e) => setFrom(e.target.value)}
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="to">To</label>
                <input
                  type="text"
                  className="form-control input-md"
                  id="to"
                  placeholder="To"
                  value={to}
                  onChange={(e) => setTo(e.target.value)}
                />
              </div>
            </div>
            <div className="row">
              <div className="form-group col-md-6">
                <label htmlFor="inputPassword4">Boarding</label>
                <input
                  type="datetime-local"
                  className="form-control input-md"
                  id="boarding"
                  placeholder="Boarding Time"
                  value={boardingTime}
                  onChange={(e) => setBoardingTime(e.target.value)}
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="arrival">Arrival</label>
                <input
                  type="datetime-local"
                  className="form-control input-md"
                  id="arrival"
                  placeholder="Arrival Time"
                  value={arrivalTime}
                  onChange={(e) => setArrivalTime(e.target.value)}
                />
              </div>
            </div>
            <div className="row">
              <div className="form-group col-md-6">
                <label htmlFor="driverName">Driver Name</label>
                <input
                  type="text"
                  className="form-control input-md"
                  id="driverName"
                  placeholder="Driver Name"
                  value={driverName}
                  onChange={(e) => setDriverName(e.target.value)}
                />
              </div>
              <div className="form-group col-md-6">
                <label htmlFor="driverPhoneNumber">Driver Phone #</label>
                <input
                  type="text"
                  className="form-control input-md"
                  id="driverPhoneNumber"
                  placeholder="Driver Phone Number"
                  value={driverPhoneNumber}
                  onChange={(e) => setDriverPhoneNumber(e.target.value)}
                />
              </div>
            </div>
            <div className="row text-center">
              {action == "add" ? (
                <button className="btn btn-sm btn-primary" onClick={handleAdd}>
                  Add
                </button>
              ) : (
                <button className="btn btn-sm btn-warning" onClick={handleUpdate}>
                  Update
                </button>
              )}
              <button className="btn btn-sm btn-secondary" onClick={onCancel}>
                Cancel
              </button>
            </div>
          </div>
        </form>
      </>
    );
}
