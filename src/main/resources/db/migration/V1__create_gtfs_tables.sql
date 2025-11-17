CREATE TABLE IF NOT EXISTS agency (
    agency_id       VARCHAR(64) PRIMARY KEY,
    agency_name     VARCHAR(255) NOT NULL,
    agency_url      VARCHAR(255) NOT NULL,
    agency_timezone VARCHAR(64)  NOT NULL,
    agency_lang     VARCHAR(16)
);

CREATE TABLE IF NOT EXISTS calendar (
    service_id VARCHAR(64) PRIMARY KEY,
    monday     BOOLEAN NOT NULL,
    tuesday    BOOLEAN NOT NULL,
    wednesday  BOOLEAN NOT NULL,
    thursday   BOOLEAN NOT NULL,
    friday     BOOLEAN NOT NULL,
    saturday   BOOLEAN NOT NULL,
    sunday     BOOLEAN NOT NULL,
    start_date DATE    NOT NULL,
    end_date   DATE    NOT NULL
);

CREATE TABLE IF NOT EXISTS routes (
    route_id         VARCHAR(64) PRIMARY KEY,
    agency_id        VARCHAR(64),
    route_short_name VARCHAR(64),
    route_long_name  VARCHAR(255),
    route_type       VARCHAR(32),
    route_color      VARCHAR(7),
    route_text_color VARCHAR(7),
    CONSTRAINT fk_routes_agency FOREIGN KEY (agency_id) REFERENCES agency (agency_id)
);

CREATE TABLE IF NOT EXISTS trips (
    trip_id       VARCHAR(64) PRIMARY KEY,
    route_id      VARCHAR(64) NOT NULL,
    service_id    VARCHAR(64) NOT NULL,
    trip_headsign VARCHAR(255),
    direction_id  INTEGER     NOT NULL,
    shape_id      VARCHAR(64),
    CONSTRAINT fk_trips_route FOREIGN KEY (route_id) REFERENCES routes (route_id),
    CONSTRAINT fk_trips_calendar FOREIGN KEY (service_id) REFERENCES calendar (service_id)
);

CREATE TABLE IF NOT EXISTS frequencies (
    trip_id      VARCHAR(64) NOT NULL,
    start_time   TIME        NOT NULL,
    end_time     TIME        NOT NULL,
    headway_secs INTEGER     NOT NULL,
    PRIMARY KEY (trip_id, start_time),
    CONSTRAINT fk_frequencies_trip FOREIGN KEY (trip_id) REFERENCES trips (trip_id)
);

CREATE TABLE IF NOT EXISTS shapes (
    shape_id      VARCHAR(64)      NOT NULL,
    sequence      INTEGER          NOT NULL,
    lat           DOUBLE PRECISION NOT NULL,
    lon           DOUBLE PRECISION NOT NULL,
    dist_traveled DOUBLE PRECISION,
    PRIMARY KEY (shape_id, sequence)
);

CREATE TABLE IF NOT EXISTS stops (
    stop_id   VARCHAR(64) PRIMARY KEY,
    stop_name VARCHAR(255)     NOT NULL,
    stop_desc VARCHAR(255),
    stop_lat  DOUBLE PRECISION NOT NULL,
    stop_lon  DOUBLE PRECISION NOT NULL
);

CREATE TABLE IF NOT EXISTS stop_times (
    trip_id        VARCHAR(64) NOT NULL,
    stop_sequence  INTEGER     NOT NULL,
    arrival_time   TIME        NOT NULL,
    departure_time TIME        NOT NULL,
    stop_id        VARCHAR(64) NOT NULL,
    PRIMARY KEY (trip_id, stop_sequence),
    CONSTRAINT fk_stop_times_trip FOREIGN KEY (trip_id) REFERENCES trips (trip_id)
);

CREATE TABLE IF NOT EXISTS fare_attributes (
    fare_id           VARCHAR(64) PRIMARY KEY,
    price             NUMERIC(10, 2) NOT NULL,
    currency_type     VARCHAR(8)     NOT NULL,
    payment_method    INTEGER        NOT NULL,
    transfers         INTEGER,
    transfer_duration INTEGER
);

CREATE TABLE IF NOT EXISTS fare_rules (
    fare_id        VARCHAR(64) NOT NULL,
    route_id       VARCHAR(64),
    origin_id      VARCHAR(64),
    destination_id VARCHAR(64),
    contains_id    VARCHAR(64),
    PRIMARY KEY (fare_id, route_id, origin_id, destination_id, contains_id),
    CONSTRAINT fk_fare_rules_fare FOREIGN KEY (fare_id) REFERENCES fare_attributes (fare_id),
    CONSTRAINT fk_fare_rules_route FOREIGN KEY (route_id) REFERENCES routes (route_id)
);

CREATE TABLE IF NOT EXISTS roles (
    role VARCHAR(64) PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS users (
    id       VARCHAR(64) PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_roles (
    user_id   VARCHAR(64) NOT NULL,
    role_name VARCHAR(64) NOT NULL,
    PRIMARY KEY (user_id, role_name),
    CONSTRAINT fk_users_roles_user FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_roles_role FOREIGN KEY (role_name) REFERENCES roles (role)
);

CREATE TABLE IF NOT EXISTS api_key (
    public_id  VARCHAR(64) PRIMARY KEY,
    secret_hash VARCHAR(255) NOT NULL,
    revoked    BOOLEAN DEFAULT FALSE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    user_id    VARCHAR(64) NOT NULL,
    CONSTRAINT fk_api_key_user FOREIGN KEY (user_id) REFERENCES users (id)
);
