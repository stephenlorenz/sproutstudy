package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;

public class ProviderTO implements Serializable {
	
	private static final long serialVersionUID = -3424638600632016492L;

	private String name;
	private String id;

    public ProviderTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrettyName() {
		if (!StringUtils.isEmpty(name)) {
			if (name.indexOf(",") > 0) {
				String lastName = name.substring(0, name.lastIndexOf(",")).trim();
				String firstName = name.substring(name.lastIndexOf(",") + 1).trim();
				return String.format("%s %s", firstName, lastName);
			}
		}
		return name;
	}

	public ProviderTO() {
		super();
	}

	public ProviderTO(String name) {
		super();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
