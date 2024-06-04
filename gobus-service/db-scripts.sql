CREATE DATABASE gobus_db;

CREATE TABLE gobus_user(
    user_id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    full_name VARCHAR(20) NOT NULL,
    age INTEGER,
    user_type ENUM('CUSTOMER', 'ADMIN', 'BUS_OWNER'),
    email_id VARCHAR(100) NOT NULL,
    phone_number VARCHAR(12),
    gobus_password VARCHAR(100) NOT NULL
);

 CREATE TABLE bus(
     bus_id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
     owner_id INTEGER NOT NULL REFERENCES gobus_user(user_id),
     bus_name VARCHAR(20) NOT NULL,
     bus_type ENUM('AC_SLEEPER', 'NONAC_SLEEPER', 'AC_SEATER', 'NONAC_SEATER'),
     bus_number VARCHAR(15) NOT NULL,
     num_seats INTEGER
  );

 CREATE TABLE payment(
     payment_id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
     payment_mode ENUM('CARD', 'UPI', 'BANK'),
     payment_status ENUM('SUCCESS', 'FAILED'),
     price DOUBLE,
     payment_time TIMESTAMP
 );

 CREATE TABLE review(
      review_id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
      bus_id INTEGER NOT NULL REFERENCES bus(bus_id),
      user_id INTEGER NOT NULL REFERENCES gobus_user(user_id),
      review_comment VARCHAR(500) NOT NULL,
      num_stars INTEGER
 );

CREATE TABLE ticket(
    ticket_id INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    user_id INTEGER NOT NULL REFERENCES gobus_user(user_id),
    trip_id INTEGER NOT NULL REFERENCES trip(trip_id),
    payment_id INTEGER NOT NULL REFERENCES payment(payment_id),
    ticket_status ENUM('CONFIRMED', 'CANCELLED'),
    passenger_name VARCHAR(20) NOT NULL,
    passenger_phoneNumber VARCHAR(15) NOT NULL,
    num_seats_booked INTEGER,
    totalPrice DOUBLE
);

CREATE TABLE trip(
    tripId INTEGER AUTO_INCREMENT PRIMARY KEY NOT NULL,
    bus_id INTEGER NOT NULL REFERENCES bus(bus_id),
    from_place VARCHAR(30) NOT NULL,
    to_place VARCHAR(30) NOT NULL,
    available_seats INTEGER,
    boarding_time DATETIME,
    arrival_time DATETIME,
    driver_phone_number VARCHAR(15) NOT NULL,
    driver_name VARCHAR(20) NOT NULL,
    confirmation_status TINYINT(1),
    seat_price DOUBLE
);