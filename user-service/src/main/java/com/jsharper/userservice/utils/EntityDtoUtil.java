package com.jsharper.userservice.utils;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

	public static <T, U> T toDo(U source, Class<T> target) {
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

}
