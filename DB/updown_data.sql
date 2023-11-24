INSERT INTO user(id, password, nickname, name, email, target_weight, target_time) VALUES
('ssafy', 'ssafy1234!!', '김싸피', '김싸피', 'ssafy2@ssafy.com', 50, 120);

-- INSERT INTO diet(category, food, calorie, img, org_img, reg_date, user_id) VALUES
-- ('아침', '계란후라이', 100, '2023-11-22', 'ssafy'),
-- ('점심', '치킨', 500, '2023-11-23', 'ssafy'),
-- ('저녁', '샐러드', 300, '2023-11-23', 'ssafy'),
-- ('간식', '고구마', 200, '2023-11-23', 'ssafy');

INSERT INTO diet(category, food, calorie, reg_date, img, user_id) VALUES
('아침', '계란후라이', 100, '2023-11-22', '계란후라이.jpg', 'ssafy'),
('점심', '치킨', 500, '2023-11-23', '치킨.jpg','ssafy'),
('저녁', '샐러드', 300, '2023-11-23', '샐러드.jpg','ssafy'),
('간식', '고구마', 200, '2023-11-23', '고구마.jpg','ssafy');

INSERT INTO exercise(type, time, calorie, reg_date, user_id) VALUES
('등산', 120, 1128, '2023-11-21', 'ssafy'),
('줄넘기', 30, 360, '2023-11-22', 'ssafy'),
('배드민턴', 60, 264, '2023-11-23', 'ssafy'),
('축구', 120, 730, '2023-11-23', 'ssafy');

INSERT INTO weight(now_weight, reg_date, user_id) VALUES
(60, '2023-11-16', 'ssafy'),
(59.5, '2023-11-17', 'ssafy'),
(60, '2023-11-18', 'ssafy'),
(61.2, '2023-11-19', 'ssafy'),
(58.7, '2023-11-20', 'ssafy'),
(57.5, '2023-11-21', 'ssafy'),
(55.2, '2023-11-22', 'ssafy'),
(56.4, '2023-11-23', 'ssafy');

INSERT INTO exercise(type, time, calorie, reg_date, user_id) VALUES
('등산', 120, 1128, '2023-11-20', 'ssafy'),
('줄넘기', 30, 360, '2023-11-20', 'ssafy');
SELECT * FROM exercise_info;
delete FROM weight WHERE no=25;