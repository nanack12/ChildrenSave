package kr.co.childrensave.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.childrensave.dto.CoordinateDTO;
import kr.co.childrensave.dto.HomeDTO;

@Repository
public class HomeDAO {
	
	@Autowired
	private SqlSessionTemplate template;
	
	public List<HomeDTO> homeSchool(HomeDTO dto){
		return template.selectList("mapper.indexSchool");
	}

	public List<CoordinateDTO> badack(CoordinateDTO dto){
		return template.selectList("mapper.selectBadack");
	}
	
}
