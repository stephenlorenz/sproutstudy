package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PublicationTO implements Serializable, Comparable<PublicationTO> {

	private static final long serialVersionUID = 8109728591315653877L;

    private int id;
    private String key;
    private String title;
    private String description;
    private String publicationDate;
    private List<ProviderTO> providers;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<ProviderTO> getProviders() {
        return providers;
    }

    public void setProviders(List<ProviderTO> providers) {
        this.providers = providers;
    }
    public void addProvider(ProviderTO providerTO) {
        if (providers == null) providers = new ArrayList<ProviderTO>();
        providers.add(providerTO);
    }

    @Override
    public int compareTo(PublicationTO publicationTO) {
        return publicationTO.getId() > this.getId() ? 0 : 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
