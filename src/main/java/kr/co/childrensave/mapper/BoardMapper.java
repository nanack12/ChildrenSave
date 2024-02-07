package kr.co.childrensave.mapper;

import java.util.List;

import kr.co.childrensave.dto.BoardDTO;

public interface BoardMapper {
	
	public List<BoardDTO> getList();
	
	public void insert(BoardDTO boardDTO);
	public void insertSelectKey(BoardDTO boardDTO);
}
