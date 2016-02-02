package edu.harvard.mgh.lcs.sprout.forms.study;

import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import junit.framework.Assert;
import org.junit.Test;

public class JSONTest {


	@Test
	public void test() {
        Assert.assertEquals(cleanJSON("1234|{'test': 'yo'}"), "{'test': 'yo'}");
        Assert.assertEquals(cleanJSON("{'test': 'yo'}"), "{'test': 'yo'}");
        Assert.assertEquals(cleanJSON("1234|[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]"), "[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]");
        Assert.assertEquals(cleanJSON("[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]"), "[{\"id\":\"99999990\",\"fullName\":\"Mary Dyer\",\"prettyName\":\"Mary Dyer\",\"firstName\":\"Mary\",\"middleName\":null,\"lastName\":\"Dyer\",\"email\":\"\",\"gender\":\"Male\",\"birthDate\":156056400000,\"addresses\":null,\"identities\":[{\"schema\":\"mgh\",\"id\":\"99999990\"}]}]");
    }

    private String cleanJSON(String response) {
        if (StringUtils.isFull(response)) {
            if (!response.trim().startsWith("{") && !response.trim().startsWith("[") && (response.indexOf("{") >= 0 || response.indexOf("[") >= 0)) {
                String frontierDelimiter = getFrontierDelimiter(response);
                System.out.println("cleanJSON.frontierDelimiter = " + frontierDelimiter);
                if (StringUtils.isFull(frontierDelimiter)) {
                    System.out.println("cleanJSON.response = " + response.substring(response.indexOf(frontierDelimiter)));
                    return(response.substring(response.indexOf(frontierDelimiter)));
                }
            }
            return(response);
        }
        return null;
    }

    private String getFrontierDelimiter(String response) {
        for(char responseChar : response.toCharArray()) {
            if (responseChar == '[') return "[";
            if (responseChar == '{') return "{";
        }
        return null;
    }


}
