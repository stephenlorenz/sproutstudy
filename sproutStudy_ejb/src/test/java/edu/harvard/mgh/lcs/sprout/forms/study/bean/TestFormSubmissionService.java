package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.UserTransaction;

import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.converters.ErrorReporter;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.core.BaseException;
import com.thoughtworks.xstream.core.util.CustomObjectOutputStream;
import com.thoughtworks.xstream.io.AbstractDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.DocumentReader;
import com.thoughtworks.xstream.io.xml.SaxWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import edu.harvard.mgh.lcs.ihealthspace.enrollment.model.ReenrollPatientData;
import edu.harvard.mgh.lcs.partnersPatientLookup.core.beaninterface.PartnersPatientLookupService;
import edu.harvard.mgh.lcs.partnersPatientLookup.to.PatientTO;
import edu.harvard.mgh.lcs.sprout.forms.core.ejb.beaninterface.InvalidNameException;
import edu.harvard.mgh.lcs.sprout.study.model.cas.ApplicationAuthorityEntity;
import edu.harvard.mgh.lcs.sprout.study.model.formSubmission.SubmissionEntity;
import edu.harvard.mgh.lcs.sprout.study.model.oncall.IHealthSpaceEnrollmentSvpEntity;
import edu.harvard.mgh.lcs.sprout.study.model.registrycommon.PatientNameEntity;
import org.jaxen.saxpath.SAXPathException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.harvard.mgh.lcs.sprout.forms.study.bean.FormSubmissionServiceImpl;
import edu.harvard.mgh.lcs.sprout.forms.study.beaninterface.FormSubmissionService;
import edu.harvard.mgh.lcs.sprout.forms.study.to.PracticeTO;
import edu.harvard.mgh.lcs.sprout.forms.study.util.StringUtils;
import org.milyn.Smooks;
import org.milyn.cdr.SmooksConfigurationException;
import org.milyn.cdr.TokenizedStringParameterDecoder;
import org.milyn.cdr.xpath.SelectorStep;
import org.milyn.container.ExecutionContext;
import org.milyn.delivery.ContentHandler;
import org.milyn.delivery.dom.DOMVisitBefore;
import org.milyn.delivery.java.XStreamXMLReader;
import org.milyn.delivery.ordering.Producer;
import org.milyn.delivery.replay.SAXEventReplay;
import org.milyn.delivery.sax.SAXElementVisitor;
import org.milyn.event.ExecutionEvent;
import org.milyn.expression.ExpressionEvaluator;
import org.milyn.io.NullWriter;
import org.milyn.profile.ProfileSet;
import org.milyn.resource.ContainerResourceLocator;
import org.milyn.xml.SmooksXMLReader;
import org.milyn.xml.hierarchy.HierarchyChangeListener;
import org.springframework.asm.Opcodes;
import org.springframework.asm.commons.EmptyVisitor;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.beans.support.ArgumentConvertingMethodInvoker;
import org.springframework.context.ApplicationContext;
import org.springframework.core.NestedRuntimeException;
import org.springframework.core.convert.ConversionException;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.NamingException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.util.ReflectionUtils;

@RunWith(Arquillian.class)
public class TestFormSubmissionService {

	@Deployment
	public static Archive<?>createDeployment(){
		File[] libs = DependencyResolvers
				.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml")
				.includeDependenciesFromPom("pom.xml")
				.exclusion("org.hibernate:hibernate-core")
				.exclusion("edu.harvard.mgh.lcs.sprout.study:frontoffice_jpa")
				.resolveAsFiles();

		return ShrinkWrap.create(WebArchive.class, "testFrontOffice.war")
				.addPackage(FormSubmissionService.class.getPackage())
				.addPackage(FormSubmissionServiceImpl.class.getPackage())
                .addPackage(InvalidNameException.class.getPackage())
                .addPackage(IHealthSpaceEnrollmentSvpEntity.class.getPackage())
                .addPackage(SubmissionEntity.class.getPackage())
                .addPackage(ApplicationContext.class.getPackage())
                .addPackage(EnvironmentCapable.class.getPackage())
                .addPackage(ListableBeanFactory.class.getPackage())
                .addPackage(ResourcePatternResolver.class.getPackage())
                .addPackage(ResourceLoader.class.getPackage())
                .addPackage(NameNotFoundException.class.getPackage())
                .addPackage(NamingException.class.getPackage())
                .addPackage(NestedRuntimeException.class.getPackage())
                .addPackage(LdapTemplate.class.getPackage())
                .addPackage(ApplicationAuthorityEntity.class.getPackage())
                .addPackage(PartnersPatientLookupService.class.getPackage())
                .addPackage(PatientTO.class.getPackage())
                .addPackage(PatientNameEntity.class.getPackage())
                .addPackage(ReenrollPatientData.class.getPackage())
				.addPackage(PracticeTO.class.getPackage())
                .addPackage(FatalBeanException.class.getPackage())
                .addPackage(ReflectionUtils.class.getPackage())
                .addPackage(EmptyVisitor.class.getPackage())
                .addPackage(Opcodes.class.getPackage())
                .addPackage(ConversionException.class.getPackage())
                .addPackage(ConfigurableConversionService.class.getPackage())
                .addPackage(ConverterFactory.class.getPackage())
                .addPackage(AutowireCapableBeanFactory.class.getPackage())
                .addPackage(ArgumentConvertingMethodInvoker.class.getPackage())
                .addPackage(SAXPathException.class.getPackage())
                .addPackage(SmooksConfigurationException.class.getPackage())
                .addPackage(TokenizedStringParameterDecoder.class.getPackage())
                .addPackage(ContentHandler.class.getPackage())
                .addPackage(SAXElementVisitor.class.getPackage())
                .addPackage(SAXEventReplay.class.getPackage())
                .addPackage(SelectorStep.class.getPackage())
                .addPackage(HierarchyChangeListener.class.getPackage())
                .addPackage(DOMVisitBefore.class.getPackage())
                .addPackage(Producer.class.getPackage())
                .addPackage(ExecutionContext.class.getPackage())
                .addPackage(ContainerResourceLocator.class.getPackage())
                .addPackage(ProfileSet.class.getPackage())
                .addPackage(NullWriter.class.getPackage())
                .addPackage(XStreamXMLReader.class.getPackage())
                .addPackage(ExpressionEvaluator.class.getPackage())
                .addPackage(SmooksXMLReader.class.getPackage())
                .addPackage(SaxWriter.class.getPackage())
                .addPackage(AbstractDriver.class.getPackage())
                .addPackage(NameCoder.class.getPackage())
                .addPackage(ErrorReporter.class.getPackage())
                .addPackage(HierarchicalStreamReader.class.getPackage())
                .addPackage(DocumentReader.class.getPackage())
                .addPackage(XStreamException.class.getPackage())
                .addPackage(BaseException.class.getPackage())
                .addPackage(CustomObjectOutputStream.class.getPackage())
                .addPackage(ReflectionProvider.class.getPackage())
                .addPackage(Mapper.class.getPackage())
                .addPackage(PropertiesEditor.class.getPackage())
                .addPackage(EmptyResultDataAccessException.class.getPackage())
                .addPackage(Smooks.class.getPackage())
                .addPackage(ExecutionEvent.class.getPackage())
                .addPackage(AbstractSingleValueConverter.class.getPackage())
                .addPackage(StringUtils.class.getPackage())
				.addAsLibraries(libs)
				.addAsResource("smooks-submission-config.xml")
//                .addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	} 

	
	@EJB
	private FormSubmissionService formSubmissionService;

	@Inject 
	UserTransaction utx;

	@Before
	public void preparePersistenceTest() throws Exception {
		startTransaction();
	}

	private void startTransaction() throws Exception {
		utx.begin();
	}

	@After
	public void commitTransaction() throws Exception {
		boolean commitInd = true;
		if (commitInd) {
			System.out.println("\n\n\n\n\nCommit!!!!");
			utx.commit();
		} else {		
			System.out.println("\n\n\n\n\nRollback!!!!");
			utx.rollback();
		}
	}

	@Test 
	public void test() throws IOException {


		List<File> files = getFilesForFolder(new File("C:/Users/slorenz/workspace/frontOffice/frontOffice/frontoffice_ejb/src/test/resources/submissions"));
		if (files != null && files.size() > 0) {
			for (File file : files) {
				System.out.println("file: " + file.getAbsolutePath());
				
				if (file.getName().startsWith("submission")) {
					String fileContents = readFile(file);
					//System.out.println(fileContents);

				
					String result = formSubmissionService.postFormSubmission("2BDA7398-DBAB-4074-ADF8-96F307F503B7", "00B23690-B204-4F92-AF3E-38CD195A6F2D", fileContents);

					System.out.println("result: " + result);
				}
			
			}
		}


	}

	private String readFile(File file) throws IOException {

		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(
				new FileReader(file));
		char[] buf = new char[1024];
		int numRead=0;
		while((numRead=reader.read(buf)) != -1){
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();

		
		
		
		
		//		FileInputStream stream = new FileInputStream(file);
//		try {
//			FileChannel fc = stream.getChannel();
//			MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
//			/* Instead of using default, pass in a decoder. */
//			return Charset.defaultCharset().decode(bb).toString();
//		}
//		finally {
//			stream.close();
//		}
	}

	private List<File> getFilesForFolder(File folder) {
		List<File> files = new ArrayList<File>();
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				files.add(file);
			}
		}
		return files;
	}

}
