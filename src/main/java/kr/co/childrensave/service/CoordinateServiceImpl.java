package kr.co.childrensave.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import kr.co.childrensave.dao.CoordinateDAO;
import kr.co.childrensave.dto.CoordinateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class CoordinateServiceImpl implements CoordinateService {
	
	@Resource
	private CoordinateDAO coordinateDAO;
	@Override
	public List<CoordinateDTO> selectCoordinate() {
		CoordinateDTO dto = new CoordinateDTO();
		return coordinateDAO.selectCoordinate(dto);
	}



	@Override
	public List<CoordinateDTO> selectToggle(CoordinateDTO dto) {
		List<CoordinateDTO> resultAll = new ArrayList<CoordinateDTO>();
		if(dto.getDataType()!=null) {
		String[] data = dto.getDataType().split(",");
		
			for(String dataType : data) {
				switch (dataType) {
					case "badack":
						resultAll.addAll(coordinateDAO.selectBadack(dto));
					break;
					
					case "child":
						resultAll.addAll(coordinateDAO.selectChild(dto));
					break;
					
					case "accident":
						resultAll.addAll(coordinateDAO.selectAccident(dto));
					break;
				}
				
			}
		}else {
			
		}
		
		return resultAll;
	}



	@Override
	public List<CoordinateDTO> selectToggleOverlay(CoordinateDTO dto) {
		List<CoordinateDTO> resultOverlay = new ArrayList<CoordinateDTO>();
		if(dto.getDataType()!=null) {
			String[] data = dto.getDataType().split(",");
			for(String dataType : data) {
				switch(dataType) {
					case "allime":
						resultOverlay.addAll(coordinateDAO.selectAllime(dto));
					break;
				}
			}
		}
		return resultOverlay;
	}



	
	
}
