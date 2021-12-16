package edu.test.demo.vo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComentVO {
	private int coment_id;
	private int coment_id_to;
	private int coment_id_from;
	private String coment_contents;
	private Timestamp coment_time;
	private boolean coment_access;
	private int coment_status;
}
