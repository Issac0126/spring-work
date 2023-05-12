

CREATE TABLE jdbc_board (
    board_no INT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(30) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(2000),
    reg_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM jdbc_board;