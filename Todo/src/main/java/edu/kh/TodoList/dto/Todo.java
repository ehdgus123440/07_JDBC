package edu.kh.TodoList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Todo {
	private int todoNo;
	private String todoTitle;
	private String todoComplate;
	private String todoDate;
	private String detail;
}
