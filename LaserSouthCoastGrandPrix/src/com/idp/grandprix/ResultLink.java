package com.idp.grandprix;

public class ResultLink {
	protected String year;
	protected String standard;
	protected String radial;
	protected String fourPointSeven;
	
	public ResultLink(String year, String standard, String radial, String fourPointSeven)
	{
		this.year = year;
		this.standard = standard;
		this.radial = radial;
		this.fourPointSeven = fourPointSeven;
	}
	
	public String getYear(){
		return year;
	}
	
	public String getStandard(){
		return standard;
	}
	
	public String getRadial(){
		return radial;
	}
	
	public String getFourPointSeven(){
		return fourPointSeven;
	}

}
