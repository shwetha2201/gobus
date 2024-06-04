import React, { useState, useEffect } from "react";
import Alert from "./helpers/Alert";
import config from "../config.json";
import { getUser } from "./helpers/useUser";

async function addBus(busDto, token) {
  try {
    return await fetch(`${config.basePath}/buses`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(busDto),
    });
  } catch (error) {
    console.log("Error occured while adding the bus ", error);
    return { err: "Error occured, please try later!" };
  }
}

async function updateBus(busId, busDto, token) {
  try {
    return await fetch(`${config.basePath}/buses/${busId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(busDto),
    });
  } catch (error) {
    console.log("Error occured while adding the bus ", error);
    return { err: "Error occured, please try later!" };
  }
}

export default function BusForm({ bus, action, onRefresh, onCancel }) {
  const user = getUser();
  const [busNumber, setBusNumber] = useState(bus?.busNumber);
  const [busName, setBusName] = useState(bus?.busName);
  const [busType, setBusType] = useState(bus ? bus.busType : "AC_SLEEPER");
  const [numberOfSeats, setNumberOfSeats] = useState(bus?.numberOfSeats);
  const [error, setError] = useState();
  const [success, setSuccess] = useState();

  useEffect(() => {
    setBusNumber(bus?.busNumber);
    setBusName(bus?.busName);
    setBusType(bus?.busType);
    setNumberOfSeats(bus?.numberOfSeats);
  }, [bus]);

  const prepareBusDto = () => {
    if (busName === "") {
      setError("Please enter bus name");
      return false;
    } else if (busNumber === "") {
      setError("Please enter bus number");
      return false;
    } else if (busType === "") {
      setError("Please select a bus type");
      return false;
    } else if (numberOfSeats === "") {
      setError("Please enter number of seats");
      return false;
    } else {
      var busDto = {
        busName: `${busName}`,
        busType: `${busType}`,
        busNumber: `${busNumber}`,
        numberOfSeats: `${numberOfSeats}`,
        busOwner: {
          userId: getUser().user.userId,
        },
      };
      return busDto;
    }
  };

  const handleAdd = async (e) => {
    e.preventDefault();
    const busDto = prepareBusDto();

    if (!busDto) return;
    const response = await addBus(busDto, user.token);
    if (!response.ok) {
      if (response.status == 400) {
        setError(JSON.stringify(await response.json()));
      } else {
        setError("Something went wrong, please try later");
      }
      return;
    }
    const addBusResponse = await response.json();
    setError("");
    setSuccess("Bus had been added successfully");
    onRefresh();
  };

  const handleUpdate = async (e) => {
    e.preventDefault();
    const busDto = prepareBusDto();

    if (!busDto) return;
    const response = await updateBus(bus.busId, busDto, user.token);
    if (!response.ok) {
      if (response.status == 400) {
        setError(JSON.stringify(await response.json()));
      } else {
        setError("Something went wrong, please try later");
      }
      return;
    }
    const updateBusResponse = await response.json();
    setError("");
    setSuccess("Bus had been updated successfully");
    onRefresh();
  };

  if (action == "") return <></>;
  else
    return (
      <>
        <Alert error={error} success={success} />
        <form className="form-inline" action="POST">
          <div className="form-group mx-sm-3">
            <input
              type="text"
              className="form-control input-md"
              value={busNumber}
              onChange={(e) => setBusNumber(e.target.value)}
              placeholder="Bus #"
            />
          </div>
          <div className="form-group mx-sm-3">
            <input
              type="text"
              className="form-control input-md"
              value={busName}
              onChange={(e) => setBusName(e.target.value)}
              placeholder="Bus Name"
            />
          </div>
          <div className="form-group mx-sm-3">
            <select
              className="form-control input-md"
              placeholder="Bus Type"
              value={busType}
              onChange={(e) => setBusType(e.target.value)}
            >
              <option value="AC_SLEEPER">AC Sleeper</option>
              <option value="NONAC_SLEEPER">Non AC Sleeper</option>
              <option value="AC_SEATER">AC Seater</option>
              <option value="NONAC_SEATER">Non AC Seater</option>
            </select>
          </div>
          <div className="form-group mx-sm-3">
            <input
              type="text"
              className="form-control input-md"
              value={numberOfSeats}
              onChange={(e) => setNumberOfSeats(e.target.value)}
              placeholder="# Seats"
            />
          </div>
          <div className="form-group mx-sm-3">
            {action == "add" ? (
              <button className="btn btn-sm btn-success" onClick={handleAdd}>
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
        </form>
      </>
    );
}
