INSERT INTO user(id, password, nickname, name, email, target_weight, target_time, updown, is_secret) VALUES
('ssafy', 'ssafy1234!!', '김싸피', '김싸피', 'ssafy2@ssafy.com', 50, 120, 'down', false);

INSERT INTO diet(category, food, calorie, reg_date, user_id) VALUES
('아침', '계란후라이', 100, '2023-11-14', 'ssafy'),
('점심', '치킨', 500, '2023-11-14', 'ssafy'),
('저녁', '샐러드', 300, '2023-11-14', 'ssafy'),
('간식', '고구마', 200, '2023-11-14', 'ssafy');

INSERT INTO exercise(type, time, calorie, reg_date, user_id) VALUES
('등산', 120, 1128, '2023-11-14', 'ssafy'),
('줄넘기', 30, 360, '2023-11-14', 'ssafy'),
('배드민턴', 60, 264, '2023-11-15', 'ssafy'),
('축구', 120, 730, '2023-11-15', 'ssafy');

INSERT INTO weight(now_weight, reg_date, user_id) VALUES
(60, '2023-11-14', 'ssafy'),
(59.5, '2023-11-15', 'ssafy'),
(60, '2023-11-16', 'ssafy'),
(60.2, '2023-11-17', 'ssafy');
