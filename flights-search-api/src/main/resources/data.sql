--AP.AIRLINES
Insert into AP.AIRLINES(airline_id,airline_code,airline_name) values(100,'EK', 'Emirates');
Insert into AP.AIRLINES(airline_id,airline_code,airline_name) values(101,'QF', 'Qantas');
Insert into AP.AIRLINES(airline_id,airline_code,airline_name) values(102,'CX', 'Cathay Pacific');

--AP.FLIGHT_DETAILS
Insert into AP.FLIGHT_DETAILS(FLIGHT_NUMBER,flight_code,flight_name,airline_id) values(200,'EK01', 'EmiNord',100);
Insert into AP.FLIGHT_DETAILS(FLIGHT_NUMBER,flight_code,flight_name,airline_id) values(201,'QF01', 'QFCity',101);
Insert into AP.FLIGHT_DETAILS(FLIGHT_NUMBER,flight_code,flight_name,airline_id) values(202,'CX01', 'PacificGo',102);


--AP.FLIGHT_SCHEDULE
Insert into AP.FLIGHT_SCHEDULE(flight_number,arrival_Port,departure_port,arrival_ts,departure_ts) values(200,'IND', 'DUB','2022-11-28 09:00:00','2022-11-28 07:00:00');
Insert into AP.FLIGHT_SCHEDULE(flight_number,arrival_Port,departure_port,arrival_ts,departure_ts) values(201,'BAN', 'SLN','2022-11-28 08:00:00','2022-11-28 05:00:00');
Insert into AP.FLIGHT_SCHEDULE(flight_number,arrival_Port,departure_port,arrival_ts,departure_ts) values(202,'SUM', 'SLN','2022-11-29 08:00:00','2022-11-28 05:00:00');