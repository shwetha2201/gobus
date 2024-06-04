import React, { useEffect, useState } from "react";
import BusForm from "./BusForm";
import { getUser } from "./helpers/useUser";
import config from "../config.json";

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

async function deleteBusById(busId, token) {
  try {
    return await fetch(`${config.basePath}/buses/${busId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
  } catch (error) {
    console.log("Error occured while deleting the buses", error);
    return { err: "Error occured, please try later!" };
  }
}

export default function Buses() {
  const [refreshed, setRefreshed] = useState(1);
  const user = getUser();
  const [action, setAction] = useState("");
  const [bus, setBus] = useState();

  const refreshHandler = () => {
    setRefreshed(refreshed + 1);
  };

  const addBus = () => {
    setAction("add");
    setBus(null);
  };

  const updateBus = (bus) => {
    setAction("update");
    setBus(bus);
  };

  const deleteBus = async (busId) => {
    const response = await deleteBusById(busId, user.token);
    if (response.ok) {
      refreshHandler();
    } else {
      console.log("Error while deleting bus ", response);
    }
  };

  const [buses, setBuses] = useState();
  const busRows = buses?.map((b) => <BusRow bus={b} onUpdate={updateBus} onDelete={deleteBus} key={b.busId} />);

  useEffect(() => {
    const fetchBuses = async () => {
      const response = await listBuses(user.user.userId, user.token);
      if (response.ok) {
        setBuses(await response.json());
      } else {
        console.log("error while fetching buses ", response);
      }
    };
    fetchBuses();
  }, [refreshed]);

  const handleCancel = () => {
    setBus(null);
    setAction("");
  };

  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <div className="main-container">
        <div className="row">
          <div className="col-md-8 col-md-offset-2 text-center gtco-heading">
            <h2>Your Bus List</h2>
            <p>Manage all your buses here. You may add, update or remove buses.</p>
            <button className="btn btn-md btn-info" onClick={addBus}>
              Add a New Bus
            </button>
            <table className="table text-left table-hover table-sm">
              <thead>
                <tr>
                  <th scope="col">Bus Number</th>
                  <th scope="col">Name</th>
                  <th scope="col">Type</th>
                  <th scope="col"># Seats</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody id="busTableBody">{busRows}</tbody>
            </table>

            <BusForm bus={bus} action={action} onRefresh={refreshHandler} onCancel={handleCancel} />
          </div>
        </div>
      </div>
    </main>
  );
}

function BusRow({ bus, onUpdate, onDelete }) {
  return (
    <tr id={bus.busId}>
      <th scope="row">{bus.busNumber}</th>
      <td>{bus.busName}</td>
      <td>{bus.busType}</td>
      <td>{bus.numberOfSeats}</td>
      <td>
        <button className="btn-xs btn-danger" onClick={() => onDelete(bus.busId)}>
          <i className="icon-trash"></i>
        </button>
        <button className="ml-1 btn-xs btn-warning" onClick={() => onUpdate(bus)}>
          &nbsp;<i className="icon-edit"></i>
        </button>
      </td>
    </tr>
  );
}
