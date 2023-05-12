package com.spring.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/* 참고용 SQL문
create table scores(
	stu_id INT primary key AUTO_INCREMENT, 
    stu_name varchar(30) NOT NULL,
    kor int default 0,
    eng int default 0,
    math int default 0,
    total int default 0,
    average DECIMAL(5,2)
);  
 */


@Setter @Getter @ToString
@NoArgsConstructor // ->
@AllArgsConstructor // 모든 값을 매개값으로 받을 수 있음. (불확실)
public class ScoreVO {
	
	private int stuId;
	private String stuName;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private double average;
	
	
	//총점, 평균을 구하는 메서드
	public void calcData() {
		this.total = this.kor + this.eng + this.math;
		this.average = Math.round((this.total/3.0)*100)/100.0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
