package com.jsharper.userservice.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@Table("USER_TRANSACTION")
public class UserTransaction {

	@Id
	private Integer id;
	private Integer userId;
	private Integer amount;
	private LocalDateTime transactionDate;
}
