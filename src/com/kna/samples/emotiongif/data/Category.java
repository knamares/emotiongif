package com.kna.samples.emotiongif.data;


public class Category {
	private Integer	id;
	private String	name;
	private String	url;

	
	
	public Category(Integer id, String name, String url) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
