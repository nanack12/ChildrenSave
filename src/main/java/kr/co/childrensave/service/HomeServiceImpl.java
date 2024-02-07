package kr.co.childrensave.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import kr.co.childrensave.dao.HomeDAO;
import kr.co.childrensave.dto.CoordinateDTO;
import kr.co.childrensave.dto.HomeDTO;

@Service
public class HomeServiceImpl implements HomeService {
	
	@Resource
	private HomeDAO homeDAO;
	
	@Override
	public List<HomeDTO> viewSchoolData(HomeDTO dto) {
		List<HomeDTO> result = new ArrayList<HomeDTO>();
		return result;
	}

	@Override
	public List<CoordinateDTO> viewBadack(CoordinateDTO dto) {
		List<CoordinateDTO> result1 = new ArrayList<CoordinateDTO>();
		return result1;
	}

	

}
