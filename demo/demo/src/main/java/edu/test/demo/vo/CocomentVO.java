package edu.test.demo.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CocomentVO {
	private int cocoment_id;
	private int coment_id;
	private int cocoment_id_from;
	private String cocoment_contents;
	private Timestamp cocoment_time;
	private int cocoment_status;
}
