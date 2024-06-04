import React, { useState } from "react";
import Alert from "./helpers/Alert";
import config from "../config.json";

async function loginUser(email, password) {
  try {
    return await fetch(`${config.basePath}/users/login?email=${email}&pwd=${password}`);
  } catch (error) {
    console.log("Error occured while login ", error);
    return { err: "Error occured, please try later!" };
  }
}

async function registerUser(userDto) {
  try {
    return await fetch(`${config.basePath}/users`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userDto),
    });
  } catch (error) {
    console.log("Error occured while register ", error);
    return { err: "Error occured, please try later!" };
  }
}

export default function Login({ option, setUser }) {
  const [view, setView] = useState(option);
  const [error, setError] = useState();

  const handleLogin = async (e) => {
    e.preventDefault();
    var data = new FormData(e.target.form);
    var email = data.get("email");
    var password = data.get("password");

    if (email == "" && password == "") {
      setError("Please enter your email and password");
    } else if (email == "") {
      setError("Please enter your email");
    } else if (password == "") {
      setError("Please enter your password");
    }
    await doLogin(email, password);
  };

  const doLogin = async (email, password) => {
    const response = await loginUser(email, password);
    if (!response.ok) {
      if (response.status == 400) {
        setError("Invalid email/password");
      } else {
        setError("Something went wrong, please try later");
      }
      return;
    }
    const loginResponse = await response.json();
    setUser(loginResponse);
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    var data = new FormData(e.target.form);
    var fullName = data.get("fullName");
    var email = data.get("email");
    var phone = data.get("phone");
    var password = data.get("password");
    var password2 = data.get("password2");
    var loginAs = data.get("loginAs");

    if (fullName === "") {
      setError("Please enter your full name");
    } else if (password != password2) {
      setError("Passwords must match");
    } else if (password2 === "") {
      setError("Please enter password");
    } else if (loginAs === "") {
      setError("Please select login as");
    } else if (email === "") {
      setError("Please enter your email id");
    }

    var userDto = {
      fullName: `${fullName}`,
      userType: `${loginAs}`,
      emailId: `${email}`,
      password: `${password}`,
      phoneNumber: `${phone}`,
    };
    const response = await registerUser(userDto);
    if (!response.ok) {
      if (response.status == 400) {
        setError(JSON.stringify(await response.json()));
      } else {
        setError("Something went wrong, please try later");
      }
      return;
    }
    const registerResponse = await response.json();
    doLogin(email, password);
  };

  if (view == "login") {
    return (
      <div className="form-wrap">
        <div className="tab">
          <div className="tab-content">
            <div className="tab-content-inner active" data-content="signup">
              <h3>Sign In to start your journey</h3>
              <Alert error={error} />
              <form action="#">
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="email">Email ID</label>
                    <input type="text" id="email" name="email" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <input type="submit" className="btn btn-primary btn-block" value="Sign In" onClick={handleLogin} />
                  </div>
                </div>
              </form>

              <div className="text-center">
                <a onClick={() => setView("signUp")} className="cursor-pointer">
                  Sign Up
                </a>
              </div>
              <div className="text-center">
                <a onClick={() => setView("forgotPass")} className="cursor-pointer">
                  Forgot Password?
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  } else if (view === "signUp") {
    return (
      <div className="form-wrap">
        <div className="tab">
          <div className="tab-content">
            <div className="tab-content-inner active" data-content="signup">
              <h3>Create an Account</h3>
              <Alert error={error} />
              <form action="#">
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="fullName">Full Name</label>
                    <input type="text" name="fullName" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="email">Email ID</label>
                    <input type="text" name="email" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="phone">Phone Number</label>
                    <input type="text" name="phone" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="password">Password</label>
                    <input type="password" name="password" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="password2">Re-Enter Password</label>
                    <input type="password" name="password2" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="form-check col-md-6">
                    <input type="radio" name="loginAs" value="CUSTOMER" checked className="form-check-input" />
                    <label className="form-check-label" for="passenger">
                      Passenger
                    </label>
                  </div>
                  <div className="form-check col-md-6">
                    <input type="radio" name="loginAs" value="BUS_OWNER" className="form-check-input" />
                    <label className="form-check-label" for="bus-manager">
                      Bus Owner
                    </label>
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <input
                      type="submit"
                      className="btn btn-primary btn-block"
                      value="Sign Up"
                      onClick={handleRegister}
                    />
                  </div>
                </div>
              </form>
              <div className="text-center">
                <a onClick={() => setView("login")} className="cursor-pointer">
                  Sign In
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  } else {
    return (
      <div className="form-wrap">
        <div className="tab">
          <div className="tab-content">
            <div className="tab-content-inner active" data-content="signup">
              <h3>Forgot Password</h3>
              <form action="#">
                <div className="row form-group">
                  <div className="col-md-12">
                    <label for="email">Email ID</label>
                    <input type="text" id="email" name="email" className="form-control" />
                  </div>
                </div>
                <div className="row form-group">
                  <div className="col-md-12">
                    <input type="submit" className="btn btn-primary btn-block" value="Submit" />
                  </div>
                </div>
              </form>
              <div className="text-center">
                <a onClick={() => setView("login")} className="cursor-pointer">
                  Sign In
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
