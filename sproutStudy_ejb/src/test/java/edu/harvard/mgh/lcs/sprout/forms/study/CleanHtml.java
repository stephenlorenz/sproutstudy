package edu.harvard.mgh.lcs.sprout.forms.study;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import junit.framework.Assert;
import org.junit.Test;

public class CleanHtml {


	@Test
	public void test() {
        Assert.assertEquals(cleanHtml("<span>test span</span>&nbsp;space&nbsp;space"), "test span space space");


//        Assert.assertEquals(cleanJSON("1234|{'test': 'yo'}"), "{'test': 'yo'}");
//        Assert.assertEquals(cleanJSON("{'test': 'yo'}"), "{'test': 'yo'}");
//        Assert.assertEquals(cleanJSON("1234|[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]"), "[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]");
//        Assert.assertEquals(cleanJSON("[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]"), "[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]");
    }

    private String cleanHtml(String narrative) {
        if (StringUtils.isFull(narrative)) {

            narrative = narrative.replaceAll("<span[^>]*>", "").replaceAll("</span[^>]*>", "").replaceAll("&nbsp;", " ");

            return(narrative);
        }
        return null;
    }


}
