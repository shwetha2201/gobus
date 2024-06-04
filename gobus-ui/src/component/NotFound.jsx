import React from "react";

export default function NotFound() {
  return (
    <main
      id="gtco-header"
      className="gtco-cover gtco-cover-sm"
      role="banner"
      style={{ backgroundImage: "url('images/404.gif')" }}
    >
      <div className="overlay"></div>
      <div className="gtco-container">
        <div className="row">
          <div className="col-md-12 col-md-offset-0 text-center">
            <div className="row row-mt-6em">
              <div className="col-md-12 mt-text animated animatedFadeInUp fadeInUp">
                <h1>404 Not Found</h1>
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  );
}
