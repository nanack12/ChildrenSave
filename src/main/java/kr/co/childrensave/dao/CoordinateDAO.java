package kr.co.childrensave.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import kr.co.childrensave.dto.CoordinateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Repository
public class CoordinateDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	public List<CoordinateDTO> selectCoordinate(CoordinateDTO dto){
		return template.selectList("mapper.selectCoordinate");
	}
	
	public List<CoordinateDTO> selectBadack(CoordinateDTO dto) {
		return template.selectList("mapper.selectBadack");
	}
	
	public List<CoordinateDTO> selectChild(CoordinateDTO dto){
		return template.selectList("mapper.selectChild");
	}
	
	public List<CoordinateDTO> selectAccident(CoordinateDTO dto) {
		return template.selectList("mapper.selectAccident");
	}
	
	public List<CoordinateDTO> selectAllime(CoordinateDTO dto){
		return template.selectList("mapper.selectAllime");
	}
}