import { useState } from "react";

export default function useUser() {
  const [user, setUser] = useState(getUser());

  const saveUser = (user) => {
    updateUser(user);
    setUser(user);
  };

  return { user, setUser: saveUser };
}

export function getUser() {
  const userStr = sessionStorage.getItem("user");
  return JSON.parse(userStr);
}

function updateUser(user) {
  sessionStorage.setItem("user", JSON.stringify(user));
}

export function updateUserProfile(user) {
  var existing = getUser();
  existing.user = user;
  updateUser(existing);
}
