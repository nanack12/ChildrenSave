package kr.co.childrensave.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDTO {
	private String id;
	private Double lat;
	private Double lon;
	private String cctvid;
	
	private String cctvnm;
	private String dataType;
	private String type;
	
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
	
	// Allime Data
	private Integer allimeid;
	private Integer year;
	private String address;
	private String school;
	
	private String title;
	private String name;
	private String subtitle;
	private String subname;
	
}
