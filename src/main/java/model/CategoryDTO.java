package model;

public class CategoryDTO {
	private int catNum;
	private String code;
	private String catName;
	
	public CategoryDTO(int catNum, String code, String catName) {
		super();
		this.catNum = catNum;
		this.code = code;
		this.catName = catName;
	}
	
	public int getCatNum() {
		return catNum;
	}
	public void setCatNum(int catNum) {
		this.catNum = catNum;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
}
