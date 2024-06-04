import React from "react";

export default function Alert({ error, success }) {
  if (error != null && error != "") {
    return (
      <div className="alert alert-danger alert-dismissible show alert-sm" role="alert">
        {error}
      </div>
    );
  } else if (success != null && success != "") {
    return (
      <div className="alert alert-success alert-dismissible show alert-sm" role="alert">
        {success}
      </div>
    );
  } else {
    return <></>;
  }
}
