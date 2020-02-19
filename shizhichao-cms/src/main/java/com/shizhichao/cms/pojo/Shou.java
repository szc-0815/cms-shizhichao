package com.shizhichao.cms.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
@Document(indexName = "cms_shou",type = "article")
public class Shou implements Serializable{
	
	 @Id
	private Integer id;
	 @Field(index = true,store = true,analyzer = "ik_smart",searchAnalyzer = "ik_smart",type = FieldType.text)
	private String title;
	private String text;
	private String url;
	private Integer user_id;
	private String created;
	
	private Integer articleid;
    private String headimg;
    
    private String nickname;
    
	
	
	public Shou(Integer id, String text, String url, Integer user_id, String created, Integer articleid, String headimg,
			String nickname, String title) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.user_id = user_id;
		this.created = created;
		this.articleid = articleid;
		this.headimg = headimg;
		this.nickname = nickname;
		this.title = title;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Shou(Integer id, String text, String url, Integer user_id, String created, Integer articleid, String headimg,
			String nickname) {
		super();
		this.id = id;
		this.text = text;
		this.url = url;
		this.user_id = user_id;
		this.created = created;
		this.articleid = articleid;
		this.headimg = headimg;
		this.nickname = nickname;
	}
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

	public Shou() {
		super();
	}
	@Override
	public String toString() {
		return "Shou [id=" + id + ", text=" + text + ", url=" + url + ", user_id=" + user_id + ", created=" + created
				+ ", articleid=" + articleid + ", headimg=" + headimg + ", nickname=" + nickname + ", title=" + title
				+ "]";
	}
	
	
	
	
	
}
