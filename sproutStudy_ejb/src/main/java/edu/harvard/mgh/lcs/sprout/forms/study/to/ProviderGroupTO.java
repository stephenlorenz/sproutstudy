package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class ProviderGroupTO implements Serializable {

	private static final long serialVersionUID = -3456928950237497761L;

	private int groupId;
	private int ownerId;
	private String parentGroupName;
    private String ownerFirstName;
    private String ownerLastName;
    private String grandparentGroupName;
    
    public String getGroupOwner() {
    	return String.format("%s:%s", groupId, ownerId);
    }
    
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getParentGroupName() {
		return parentGroupName;
	}
	public void setParentGroupName(String parentGroupName) {
		this.parentGroupName = parentGroupName;
	}
	public String getOwnerFirstName() {
		return ownerFirstName;
	}
	public void setOwnerFirstName(String ownerFirstName) {
		this.ownerFirstName = ownerFirstName;
	}
	public String getOwnerLastName() {
		return ownerLastName;
	}
	public void setOwnerLastName(String ownerLastName) {
		this.ownerLastName = ownerLastName;
	}
	public String getGrandparentGroupName() {
		return grandparentGroupName;
	}
	public void setGrandparentGroupName(String grandparentGroupName) {
		this.grandparentGroupName = grandparentGroupName;
	}
	public String getProviderName() {
		return String.format("%s, %s", ownerLastName, ownerFirstName);
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

}
