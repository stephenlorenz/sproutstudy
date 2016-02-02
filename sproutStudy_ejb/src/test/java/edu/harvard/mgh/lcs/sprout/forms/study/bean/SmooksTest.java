package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.container.ExecutionContext;
import org.milyn.io.StreamUtils;
import org.milyn.payload.JavaResult;
import org.xml.sax.SAXException;

import edu.harvard.mgh.lcs.sprout.forms.study.to.AssertionTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.IdentityTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.ParameterTO;
import edu.harvard.mgh.lcs.sprout.forms.study.to.SubmissionTO;

public class SmooksTest {

	private static byte[] messageIn = readInputMessage();

	private List<IdentityTO> identities;
	private List<AssertionTO> assertions;
	private List<ParameterTO> parameters;
	private SubmissionTO submission;

	@Test
	public void test() throws SmooksException, IOException, SAXException {
//		System.out.println("\n\n");
//		System.out.println("==============Message In==============");
//		System.out.println(new String(messageIn));
//		System.out.println("======================================\n");

		runSmooks();

		System.out.println("identities.size: " + identities.size());
		System.out.println("assertions.size: " + (assertions == null ? "null" : assertions.size()));
		System.out.println("parameters.size: " + parameters.size());
				

		for (IdentityTO identityTO : identities) {
			System.out.println(String.format("ID: %s@%s", identityTO.getSchema(), identityTO.getIdentifier()));
		}

		
		for (AssertionTO assertionTO : assertions) {
			System.out.println(String.format("Assertion: %s/%s (mrn: %s)", assertionTO.getName(), assertionTO.getValue(), assertionTO.isIdentity()));
		}

		for (ParameterTO parameterTO : parameters) {
			System.out.println(String.format("Parameter: %s/%s", parameterTO.getName(), parameterTO.getValue()));
		}

		//System.out.println("model: " + model.getModel());
		System.out.println("Submission: " + submission.getFormTitle());
		System.out.println("Submission: " + submission.getFormDescription());
		System.out.println("Submission: " + submission.getInstanceId());
		System.out.println("Submission: " + submission.getPublicationKey());
		System.out.println("Submission: " + submission.getTimestamp());

	}




	@SuppressWarnings("unchecked")
	protected void runSmooks() throws IOException, SAXException, SmooksException {

		// Instantiate Smooks with the config...
		Smooks smooks = new Smooks("smooks-config.xml");

		try {
			// Create an exec context - no profiles....
			ExecutionContext executionContext = smooks.createExecutionContext();
			// The result of this transform is a set of Java objects...
			JavaResult result = new JavaResult();

			// Filter the input message to extract, using the execution context...
			smooks.filterSource(executionContext, new StreamSource(new ByteArrayInputStream(messageIn)), result);

			identities = (List<IdentityTO>) result.getBean("identities");
			assertions = (List<AssertionTO>) result.getBean("assertions");
			parameters = (List<ParameterTO>) result.getBean("parameters");
			submission = (SubmissionTO) result.getBean("submission");

			
			
		} finally {
			smooks.close();
		}
	}

	private static byte[] readInputMessage() {
		try {
			return StreamUtils.readStream(new FileInputStream("C:/Users/slorenz/workspace/frontOffice/frontOffice/frontoffice_ejb/src/test/resources/submission.xml"));
		} catch (IOException e) {
			e.printStackTrace();
			return "<no-message/>".getBytes();
		}
	}



}
