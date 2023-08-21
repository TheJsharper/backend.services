package com.jsharper.userservice.utils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.jsharper.userservice.dtos.TransactionRequestDto;
import com.jsharper.userservice.dtos.TransactionResponseDto;
import com.jsharper.userservice.dtos.TransactionStatus;
import com.jsharper.userservice.entities.UserTransaction;

public class EntityDtoUtil {

	public static <T, U> T toDto(U source, Class<T> target) {
		try {
			T dto = target.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, dto);
			return dto;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			return null;
		}
	}

	public static <T, U> U toEntity(T source, Class<U> target) {
		try {
			U dto = target.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(source, dto);
			return dto;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			return null;
		}
	}

	public static UserTransaction toEntity(TransactionRequestDto requestDto) {
		UserTransaction ut = new UserTransaction();
		ut.setId(requestDto.getUserId());
		ut.setAmount(requestDto.getAmount());
		ut.setTransactionDate(LocalDateTime.now());
		return ut;
	}

	public static TransactionResponseDto toDto(TransactionRequestDto requestDto, TransactionStatus status) {
		TransactionResponseDto responseDto = new TransactionResponseDto();
		responseDto.setAmount(requestDto.getAmount());
		responseDto.setUserId(requestDto.getUserId());
		responseDto.setStatus(status);
		return responseDto;
	}

}
