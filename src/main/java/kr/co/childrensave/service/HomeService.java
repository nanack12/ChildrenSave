package kr.co.childrensave.service;

import java.util.List;

import kr.co.childrensave.dto.CoordinateDTO;
import kr.co.childrensave.dto.HomeDTO;

public interface HomeService {
	
	public List<HomeDTO> viewSchoolData(HomeDTO dto);
	
	public List<CoordinateDTO> viewBadack(CoordinateDTO dto);
}
