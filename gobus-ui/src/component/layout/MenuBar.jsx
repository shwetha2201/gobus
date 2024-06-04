import React from "react";
import AdminMenu from "./AdminMenu";
import PassengerMenu from "./PassengerMenu";
import BusOwnerMenu from "./BusOwnerMenu";

export default function MenuBar({ userType, setUser }) {
  const logout = (
    <li>
      <a className="cursor-pointer" onClick={() => setUser(null)}>
        Logout
      </a>
    </li>
  );

  if (userType == "ADMIN") {
    return (
      <>
        <AdminMenu />
        {logout}
      </>
    );
  } else if (userType == "CUSTOMER") {
    return (
      <>
        <PassengerMenu />
        {logout}
      </>
    );
  } else if (userType == "BUS_OWNER") {
    return (
      <>
        <BusOwnerMenu />
        {logout}
      </>
    );
  }
}
