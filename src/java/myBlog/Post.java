
package myBlog;

import java.io.Serializable;
import java.util.Date;

/**
 * This class represents the Post with its specific attributes
 * 
 */

public class Post implements Serializable {

	private String title;
	private String text;
	private Date date;
        private int id;
	
    public Post() {
        title = "Title";
        text = "Insert Text";
    }
    public Post(String title, String text){
        this.title = title;
        this.text = text;
    }
    public Post(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

	
}
