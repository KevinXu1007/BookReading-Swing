package edu.tamuc.bean;

public class BookCategory {
	private int id;  
    private String categoryName;  
    private int pid; //parent id
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}  
    
	@Override public String toString() {
        return categoryName;
    }
}
