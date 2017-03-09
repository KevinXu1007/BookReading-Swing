package edu.tamuc.bean;

public class BookInfo {
	private int id;
	private int categoryId;
	private String bookName;
	private String ISBN;
	private String author;
	private int pages;
	private String publisher;
	private String language;
	private String publishDate;
	private String desc;
	private String content;
	private String fileName;
	
	public BookInfo(){
		
	}
	
	public BookInfo(int categoryId, String bookName, String ISBN,
			String author, int pages, String publisher, String language,
			String publishDate, String desc, String content, String fileName) {
		this.categoryId = categoryId;
		this.bookName = bookName;
		this.ISBN = ISBN;
		this.author = author;
		this.pages = pages;
		this.publisher = publisher;
		this.language = language;
		this.publishDate = publishDate;
		this.desc = desc;
		this.content = content;
		this.fileName = fileName;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	

}
