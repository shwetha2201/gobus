import React, { useState } from "react";
import Header from "./component/layout/Header";
import Footer from "./component/layout/Footer";
import Home from "./component/Home";
import NotFound from "./component/NotFound";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Buses from "./component/Buses";
import Trips from "./component/Trips";
import Bookings from "./component/Bookings";
import Users from "./component/Users";
import Settings from "./component/Settings";
import Search from "./component/Search";
import SearchResult from "./component/SearchResult";
import BookingDetails from "./component/BookingDetails";
import Ticket from "./component/Ticket";
import useUser from "./component/helpers/useUser";

function App() {
  const { user, setUser } = useUser();

  return (
    <div id="page">
      <Header user={user} setUser={setUser} />

      <BrowserRouter>
        <Routes>
          <Route index element={<Home user={user} setLoginUser={setUser} />} />
          <Route path="/buses" element={<Buses />} />
          <Route path="/trips" element={<Trips />} />
          <Route path="/bookings" element={<Bookings />} />
          <Route path="/users" element={<Users />} />
          <Route path="/settings" element={<Settings />} />
          <Route path="/search" element={<Search />} />
          <Route path="/result" element={<SearchResult />} />
          <Route path="/booking-details" element={<BookingDetails />} />
          <Route path="/ticket" element={<Ticket />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>

      <Footer />
    </div>
  );
}

export default App;
