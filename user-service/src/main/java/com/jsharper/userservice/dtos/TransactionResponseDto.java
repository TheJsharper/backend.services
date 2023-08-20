package com.jsharper.userservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
public class TransactionResponseDto {

	private Integer userId;
	private Integer amount;
	private TransactionStatus status;
}
