package car.dch.mapper;

import java.util.Map;

import car.dch.entity.Admin;

public interface AdminMapper {
	public Admin adminLogin(Map<String,String> map);
} 
