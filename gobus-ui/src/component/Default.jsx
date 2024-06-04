import React from "react";
import Login from "./Login";

export default function Default({ setUser }) {
  return (
    <>
      <main id="gtco-header" className="gtco-cover gtco-cover-sm main-bg" role="banner">
        <div className="gtco-container">
          <div className="row">
            <div className="col-md-12 col-md-offset-0 text-left">
              <div className="row row-mt-6em">
                <div className="col-md-7 mt-text animated animatedFadeInUp fadeInUp">
                  <h1>Planning Trip To Anywhere in Karnataka?</h1>
                </div>
                <div className="col-md-4 col-md-push-1 animated animatedFadeInUp fadeInUp">
                  <Login option="login" setUser={setUser} />
                </div>
              </div>
            </div>
          </div>
        </div>
      </main>

      <section className="gtco-section">
        <div className="gtco-container">
          <div className="row">
            <div className="col-md-8 col-md-offset-2 text-center gtco-heading">
              <h2>Trip to Your Favourite Destination</h2>
              <p>We travel not to escape life, but for life not to escape us...</p>
            </div>
          </div>
          <div className="row">
            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/mysore.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/mysore.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Mysore, Karnataka</h2>
                  <p>A fusion of history, culture, and architectural wonders...</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>
            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/mangalore.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/mangalore.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Mangaluru, Karnataka</h2>
                  <p>The Majesty of the Coast..</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>
            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/coorg.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/coorg.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Coorg, Karnataka</h2>
                  <p>The Scotland and the coffee capital of India...</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>

            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/chikmagaluru.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/chikmagaluru.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Chikamagaluru, Karnataka</h2>
                  <p>Every mountain top is within reach if you just keep climbing...</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>

            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/jog.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/jog.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Shimoga, Karnataka</h2>
                  <p>A Backpacker's Paradise...</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>

            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/chitradurg.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/chitradurg.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Chitradurga, Karnataka</h2>
                  <p>Far far away,“stone fortress” (Kallina Kote)..</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>

            <div className="col-lg-4 col-md-4 col-sm-6">
              <a href="images/bangalore.jpg" className="fh5co-card-item image-popup">
                <figure>
                  <div className="overlay">
                    <i className="ti-plus"></i>
                  </div>
                  <img src="images/bangalore.jpg" alt="Image" className="img-responsive" />
                </figure>
                <div className="fh5co-text">
                  <h2>Bengaluru, Karnataka</h2>
                  <p>When it rains, Bangalore feels like heaven...</p>
                  <p>
                    <span className="btn btn-primary">Schedule a Trip</span>
                  </p>
                </div>
              </a>
            </div>
          </div>
        </div>
      </section>
      <section id="gtco-counter" className="gtco-section">
        <div className="gtco-container">
          <div className="row">
            <div className="col-md-8 col-md-offset-2 text-center gtco-heading animated animatedFadeInUp fadeInUp">
              <h2 className="successh2">Our Success</h2>
              <p className="successp">
                Dignissimos asperiores vitae velit veniam totam fuga molestias accusamus alias autem provident. Odit ab
                aliquam dolor eius.
              </p>
            </div>
          </div>
          <div className="row">
            <div className="col-md-4 col-sm-6 animated animatedFadeInUp fadeInUp">
              <div className="feature-center">
                <span
                  className="counter js-counter"
                  data-from="0"
                  data-to="196"
                  data-speed="5000"
                  data-refresh-interval="50"
                >
                  1
                </span>
                <span className="counter-label">Destination</span>
              </div>
            </div>
            <div className="col-md-4 col-sm-6 animated animatedFadeInUp fadeInUp">
              <div className="feature-center">
                <span
                  className="counter js-counter"
                  data-from="0"
                  data-to="12402"
                  data-speed="5000"
                  data-refresh-interval="50"
                >
                  1
                </span>
                <span className="counter-label">Travelers</span>
              </div>
            </div>
            <div className="col-md-4 col-sm-6 animated animatedFadeInUp fadeInUp">
              <div className="feature-center">
                <span
                  className="counter js-counter"
                  data-from="0"
                  data-to="12202"
                  data-speed="5000"
                  data-refresh-interval="50"
                >
                  1
                </span>
                <span className="counter-label">Happy Customer</span>
              </div>
            </div>
          </div>
        </div>
      </section>
    </>
  );
}
