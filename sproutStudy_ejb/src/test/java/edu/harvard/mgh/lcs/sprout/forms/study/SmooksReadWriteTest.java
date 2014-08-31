package edu.harvard.mgh.lcs.sprout.forms.study;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.milyn.SmooksException;
import org.milyn.io.StreamUtils;
import org.milyn.javabean.binding.xml.XMLBinding;
import org.milyn.payload.ByteSource;
import org.xml.sax.SAXException;

import edu.harvard.mgh.lcs.sprout.forms.study.to.AssertionTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.IdentityTO;
//import edu.harvard.mgh.lcs.sprout.forms.study.to.ModelTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.ParameterTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.SubmissionTO;

public class SmooksReadWriteTest {

	private static byte[] messageIn = readInputMessage();

	private List<IdentityTO> identities;
	private List<AssertionTO> assertions;
	private List<ParameterTO> parameters;
	private SubmissionTO submission;
//	private ModelTO model;

	@Test
	public void test() throws SmooksException, IOException, SAXException {
//		System.out.println("\n\n");
//		System.out.println("==============Message In==============");
//		System.out.println(new String(messageIn));
//		System.out.println("======================================\n");

		runSmooks();

//		System.out.println("identities.size: " + identities.size());
//		System.out.println("assertions.size: " + (assertions == null ? "null" : assertions.size()));
//		System.out.println("parameters.size: " + parameters.size());
//				
//
//		for (IdentityTO identityTO : identities) {
//			System.out.println(String.format("ID: %s@%s", identityTO.getSchema(), identityTO.getIdentifier()));
//		}
//
//		
//		for (AssertionTO assertionTO : assertions) {
//			System.out.println(String.format("Assertion: %s/%s (mrn: %s)", assertionTO.getName(), assertionTO.getValue(), assertionTO.isIdentity()));
//		}
//
//		for (ParameterTO parameterTO : parameters) {
//			System.out.println(String.format("Parameter: %s/%s", parameterTO.getName(), parameterTO.getValue()));
//		}
//
//		//System.out.println("model: " + model.getModel());
//		System.out.println("Submission: " + submission.getFormTitle());
//		System.out.println("Submission: " + submission.getFormDescription());
//		System.out.println("Submission: " + submission.getInstanceId());
//		System.out.println("Submission: " + submission.getPublicationKey());
//		System.out.println("Submission: " + submission.getTimestamp());

	}




	@SuppressWarnings("unchecked")
	protected void runSmooks() throws IOException, SAXException, SmooksException {

        XMLBinding xmlBinding = new XMLBinding().add("smooks-submission-rw-config.xml");
        xmlBinding.intiailize();

        // Read the order XML into the Order Object model...
//        IdentityTO identityTO = xmlBinding.fromXML(new ByteSource(readInputMessage()), IdentityTO.class);
//        System.out.println("identityTO: " + identityTO);

        List<IdentityTO> identityTOs = xmlBinding.fromXML(new ByteSource(readInputMessage()), ArrayList.class);
        System.out.println("identityTOs: " + identityTOs);
        
        for (IdentityTO identityTO : identityTOs) {
        	System.out.println(String.format("Identity: %s@%s", identityTO.getIdentifier(), identityTO.getSchema()));
        }

        
        
//        IdentityTO identityTO = new IdentityTO();
//        identityTO.setIdentifier("9876");
//        identityTO.setSchema("SCL");
//        identityTO.setScope("domain");
//        
//        identityTOs.add(identityTO);
        
        // Do something with the order....

        // Write the Order object model instance back out to XML...
        String outXML = xmlBinding.toXML(identityTOs);  // (Note: There's also a version of toXML() that takes a Writer)

        // Display read/write info to the example user...
        displayWriteXML(outXML);
    }

    private static void displayInputMessage(String orderXMLMessage) {
        userMessage("\n\n** Press enter to see the Order input sample message (input-message.xml):");
        System.out.println("\n\n");
        System.out.println("==============Source Order XML Message==============");
        System.out.println(new String(orderXMLMessage));
        System.out.println("====================================================");
    }


    private static void displayWriteXML(String xml) {
        userMessage("\n\n** Press enter to see the Order object model serialized back out to XML  (XML writing):");
        System.out.println("==============Serialized Order Bean XML==============");
        System.out.println(xml);
        System.out.println("=====================================================\n");
    }

    private static void userMessage(String message) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("> " + message);
            in.readLine();
        } catch (IOException e) {
        }
        System.out.println("\n");
    }

	private static byte[] readInputMessage() {
		try {
			return StreamUtils.readStream(new FileInputStream("C:/Users/slorenz/workspace/frontOffice/frontOffice/frontoffice_ejb/src/test/resources/submissions/submission1.xml"));
		} catch (IOException e) {
			e.printStackTrace();
			return "<no-message/>".getBytes();
		}
	}



}
