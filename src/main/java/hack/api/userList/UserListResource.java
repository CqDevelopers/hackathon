package hack.api.userList;

import java.io.Serializable;
import java.util.Date;

public class UserListResource implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String userName;
	
	private String location;
	
	private String photoUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
