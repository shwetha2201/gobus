import React from "react";

export default function Footer() {
  return (
    <>
      <footer id="gtco-footer" role="contentinfo">
        <div className="gtco-container">
          <div className="row row-p b-md">
            <div className="col-md-4">
              <div className="gtco-widget">
                <h3>About Us</h3>
                <p>
                  Gobus is your trusted partner for bus travel. Established in
                  2024, we have been committed to providing comfortable,
                  reliable, and affordable bus services to travelers across
                  Karnataka, India. Our mission is to make bus travel easy,
                  convenient, and enjoyable for everyone.
                </p>
              </div>
            </div>

            <div className="col-md-2 col-md-push-3">
              <div className="gtco-widget">
                <h3>Destination</h3>
                <ul className="gtco-footer-links">
                  <li>
                    <a href="#">Mysore</a>
                  </li>
                  <li>
                    <a href="#">Mangaluru</a>
                  </li>
                  <li>
                    <a href="#">Coorg</a>
                  </li>
                  <li>
                    <a href="#">Chikamagalur</a>
                  </li>
                  <li>
                    <a href="#">Shimoga</a>
                  </li>
                  <li>
                    <a href="#">Chitradurga</a>
                  </li>
                  <li>
                    <a href="#">Bengaluru</a>
                  </li>
                </ul>
              </div>
            </div>

            <div className="col-md-3 col-md-push-4">
              <div className="gtco-widget">
                <h3>Get In Touch</h3>
                <ul className="gtco-quick-contact">
                  <li>
                    <a href="#">
                      <i className="icon-phone"></i> +1 234 567 890
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="icon-mail2"></i> example@example.com
                    </a>
                  </li>
                  <li>
                    <a href="#">
                      <i className="icon-chat"></i> Live Chat
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </footer>
    </>
  );
}
