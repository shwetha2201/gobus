import React from "react";
import Users from "./Users";
import Buses from "./Buses";
import Search from "./Search";
import Default from "./Default";

export default function Home({ user, setLoginUser }) {
  const userType = user?.user.userType;

  if (userType == "ADMIN") {
    return <Users />;
  } else if (userType == "BUS_OWNER") {
    return <Buses />;
  } else if (userType == "CUSTOMER") {
    return <Search />;
  } else {
    return <Default setUser={setLoginUser} />;
  }
}
