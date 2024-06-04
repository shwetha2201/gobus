import React, { useState, useEffect } from "react";
import config from "../config.json";
import { getUser, updateUserProfile } from "./helpers/useUser";
import Alert from "./helpers/Alert";

async function updateUser(userDto, token) {
  try {
    return await fetch(`${config.basePath}/users/${userDto.userId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(userDto),
    });
  } catch (error) {
    console.log("Error occured while updating the user", error);
    return { err: "Error occured, please try later!" };
  }
}

async function resetPassword(request, userId, token) {
  try {
    return await fetch(`${config.basePath}/users/${userId}/action/reset-password`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(request),
    });
  } catch (error) {
    console.log("Error occured while resetting the password", error);
    return { err: "Error occured, please try later!" };
  }
}

export default function Settings() {
  const user = getUser();

  const [fullName, setFullName] = useState(user?.user.fullName);
  const [age, setAge] = useState(user?.user.age);
  const [phone, setPhone] = useState(user?.user.phoneNumber);
  const [email, setEmail] = useState(user?.user.emailId);
  const [curPass, setCurPass] = useState();
  const [newPass, setNewPass] = useState();
  const [rePass, setRePass] = useState();
  const [error, setError] = useState();
  const [success, setSuccess] = useState();
  const [passError, setPassError] = useState();
  const [passSuccess, setPassSuccess] = useState();

  const handleUpdate = async (e) => {
    e.preventDefault();
    var userDto = {
      userId: user.user.userId,
      fullName: fullName,
      age: age,
      phoneNumber: phone,
      emailId: email,
      userType: user.user.userType,
    };

    const response = await updateUser(userDto, user.token);
    if (!response.ok) {
      if (response.status == 400) {
        const resJson = await response.json();
        setError(resJson.message);
      } else {
        setError("Something went wrong, please try later");
      }
      return;
    }
    const updateUserRespons = await response.json();
    setError("");
    updateUserProfile(userDto);
    setSuccess("Profile has been update successfully");
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    if (curPass == "") {
      setPassError("Please enter your current password");
    } else if (newPass == "") {
      setPassError("Please enter a new password");
    } else if (newPass != rePass) {
      setPassError("New Password and Re-Password must be same");
    } else {
      const resetPasswordRequest = {
        currentPassword: curPass,
        newPassword: newPass,
      };
      const response = await resetPassword(resetPasswordRequest, user.user.userId, user.token);
      if (!response.ok) {
        if (response.status == 400) {
          const resJson = await response.json();
          setPassError(resJson.message);
        } else {
          setPassError("Something went wrong, please try later");
        }
        return;
      }
      setPassError("");
      setPassSuccess("Password has been reset successfully");
    }
  };

  return (
    <main className="gtco-cover gtco-cover-sm main-bg" role="banner">
      <div className="main-container">
        <div className="row">
          <div className="col-md-9 col-md-offset-2 text-center gtco-heading">
            <h2>Account Settings</h2>
            <p>Manager your user account</p>

            <div className="row">
              <div className="col-md-4">
                <h4>Profile Details</h4>
                <Alert error={error} success={success} />
                <form action="#" className="text-right">
                  <div className="form-group row">
                    <label for="fullname" className="col-sm-4 col-form-label input-sm">
                      Full Name
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="text"
                        className="input-sm form-control"
                        id="fullname"
                        value={fullName}
                        onChange={(e) => setFullName(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label for="age" className="col-sm-4 col-form-label input-sm">
                      Age
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="number"
                        className="input-sm form-control"
                        id="age"
                        value={age}
                        onChange={(e) => setAge(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label for="phone" className="col-sm-4 col-form-label input-sm">
                      Phone
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="text"
                        className="input-sm form-control"
                        id="phone"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label for="email" className="col-sm-4 col-form-label input-sm">
                      Email
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="text"
                        className="input-sm form-control-plaintext"
                        id="email"
                        value={email}
                        readonly
                      />
                    </div>
                  </div>
                  <div className="row form-group text-center">
                    <div className="col-md-9 col-md-offset-1">
                      <input type="submit" className="btn btn-sm btn-primary" value="Update" onClick={handleUpdate} />
                    </div>
                  </div>
                </form>
              </div>

              <div className="col-md-4 col-md-offset-3 text-center">
                <h4>Reset Password</h4>
                <Alert error={passError} success={passSuccess} />
                <form action="#" className="text-right">
                  <div className="form-group row">
                    <label for="currentPassword" className="col-sm-4 col-form-label input-sm">
                      Current Password
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="password"
                        readonly
                        className="input-sm form-control"
                        id="currentPassword"
                        value={curPass}
                        onChange={(e) => setCurPass(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label for="newPassword" className="col-sm-4 col-form-label input-sm">
                      New Password
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="password"
                        readonly
                        className="input-sm form-control"
                        id="newPassword"
                        value={newPass}
                        onChange={(e) => setNewPass(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="form-group row">
                    <label for="newPassword2" className="col-sm-4 col-form-label input-sm">
                      Re-Password
                    </label>
                    <div className="col-sm-8">
                      <input
                        type="password"
                        readonly
                        className="input-sm form-control"
                        id="newPassword2"
                        value={rePass}
                        onChange={(e) => setRePass(e.target.value)}
                      />
                    </div>
                  </div>
                  <div className="row form-group text-center">
                    <div className="col-md-9 col-md-offset-1">
                      <input
                        type="submit"
                        className="btn btn-sm btn-primary"
                        value="Reset"
                        onClick={handleResetPassword}
                      />
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
