import React from "react";
import MenuBar from "./MenuBar";

export default function Header({ user, setUser }) {
  const userType = user?.user.userType;

  const scrollToBottom = () => {
    window.scroll({
      top: document.body.offsetHeight,
      left: 0,
      behavior: "smooth",
    });
  };

  return (
    <>
      <header>
        <nav className="gtco-nav" role="navigation">
          <div className="gtco-container">
            <div className="row">
              <div className="col-sm-4 col-xs-12">
                <div id="gtco-logo">
                  <a href="/">
                    GoBus <em>.</em>
                  </a>
                </div>
              </div>
              <div className="col-xs-8 text-right menu-1">
                <ul>
                  <li>
                    <a href="/">Home</a>
                  </li>
                  <MenuBar userType={userType} setUser={setUser} />
                  <li>
                    <a className="cursor-pointer" onClick={scrollToBottom}>
                      Contact
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </nav>
      </header>
    </>
  );
}
