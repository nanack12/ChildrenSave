package kr.co.childrensave.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeDTO {
	private Integer schoolid;
	private String kind;
	private String school;
	private String address;
	private Double lat;
	private Double lon;
	
	// Ajax
	private String title;
	private String type;
	private String latlng2;
	// Accident Data
	private Integer accnum;
	private String acctype;
	private String accloc;
	private String acccase;
	private Integer accdeath;
	private Integer accjs;
	private Integer accgs;
	private Integer accbs;
	private Double acclat;
	private Double acclon;
	
	//badack
	private Integer id;
	
	// Allime Data
	private Integer allimeid;
	private Integer year;
	private String name;
	private String subtitle;
	private String subname;

	
	
}
