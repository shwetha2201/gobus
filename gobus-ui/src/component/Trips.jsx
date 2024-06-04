import React, { useState, useEffect } from "react";
import TripForm from "./TripForm";
import { getUser } from "./helpers/useUser";
import config from "../config.json";

async function listTrips(ownerId, token) {
  try {
    return await fetch(`${config.basePath}/trips/listByOwner?ownerId=${ownerId}`, {
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

async function deleteTripById(tripId, token) {
  try {
    return await fetch(`${config.basePath}/trips/${tripId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  } catch (error) {
    console.log("Error occured while deleting the trip", error);
    return { err: "Error occured, please try later!" };
  }
}

export default function Trips() {
  const user = getUser();
  const [action, setAction] = useState("");
  const [refreshed, setRefreshed] = useState(1);
  const [trips, setTrips] = useState();
  const [trip, setTrip] = useState();

  useEffect(() => {
    const fetchTrips = async () => {
      const response = await listTrips(user.user.userId, user.token);
      if (response.ok) {
        setTrips(await response.json());
      } else {
        console.log("error while fetching trips ", response);
      }
    };
    fetchTrips();
  }, [refreshed]);

  const addTrip = () => {
    setAction("add");
    setTrip(null);
  };

  const handleCancel = () => {
    setTrip(null);
    setAction("");
  };

  const updateTrip = (trip) => {
    setAction("update");
    setTrip(trip);
  };

  const deleteTrip = async (tripId) => {
    const response = await deleteTripById(tripId, user.token);
    if (response.ok) {
      handleRefresh();
    } else {
      console.log("Error while deleting trip ", response);
    }
  };

  const handleRefresh = () => {
    setRefreshed(refreshed + 1);
  };

  const tripRows = trips?.map((t) => <TripRow trip={t} onUpdate={updateTrip} onDelete={deleteTrip} key={t.tripId} />);

  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <div className="main-container">
        <div className="row">
          <div className="col-md-10 col-md-offset-1 text-center gtco-heading">
            <h2>Your Trip List</h2>
            <p>Manage all your Trips here. You may add, update or remove trips.</p>
            <button className="btn btn-md btn-info" onClick={addTrip}>
              Add a New Trip
            </button>
            <table className="table text-left table-hover table-sm">
              <thead>
                <tr>
                  <th scope="col">Bus #</th>
                  <th scope="col">Trip ID</th>
                  <th scope="col">From</th>
                  <th scope="col">To</th>
                  <th scope="col">Available Seats</th>
                  <th scope="col">Boarding Time</th>
                  <th scope="col">Arrival Time</th>
                  <th scope="col">Driver Phone #</th>
                  <th scope="col">Driver Name</th>
                  <th scope="col">Seat Price</th>
                  <th scope="col">Status</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>{tripRows}</tbody>
            </table>
            <TripForm action={action} onCancel={handleCancel} onRefresh={handleRefresh} />
          </div>
        </div>
      </div>
    </main>
  );
}

function TripRow({ trip, onUpdate, onDelete }) {
  return (
    <tr id={trip.tripId}>
      <th scope="row">{trip?.bus?.busNumber}</th>
      <td>{trip?.tripId}</td>
      <td>{trip?.from}</td>
      <td>{trip?.to}</td>
      <td>{trip?.availableSeats}</td>
      <td>{trip?.boardingTime}</td>
      <td>{trip?.arrivalTime}</td>
      <td>{trip?.driverPhoneNumber}</td>
      <td>{trip?.driverName}</td>
      <td>{trip?.seatPrice}</td>
      <td>
        {trip?.confirmationStatus ? (
          <span className="badge btn-success">Confirmed</span>
        ) : (
          <span className="badge btn-warning">Pending</span>
        )}
      </td>
      <td>
        <button className="btn-xs btn-danger" onClick={() => onDelete(trip?.tripId)}>
          <i className="icon-trash"></i>
        </button>
        <button className="ml-1 btn-xs btn-warning" onClick={() => onUpdate(trip)}>
          &nbsp;<i className="icon-edit"></i>
        </button>
        <button className="btn-xs btn-success" onClick={() => onDelete(trip?.tripId)}>
          <i className="icon-trash"></i>
        </button>
      </td>
    </tr>
  );
}
