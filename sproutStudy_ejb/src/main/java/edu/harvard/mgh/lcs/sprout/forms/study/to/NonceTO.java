package edu.harvard.mgh.lcs.sprout.forms.study.to;

import java.io.Serializable;

public class NonceTO implements Serializable {

	private static final long serialVersionUID = -5808452918661227118L;

	private String nonce;
    private String instanceId;

    public NonceTO(String nonce, String instanceId) {
        this.nonce = nonce;
        this.instanceId = instanceId;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
