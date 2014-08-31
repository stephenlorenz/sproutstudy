package edu.harvard.mgh.lcs.sprout.forms.study.bean;

import java.io.File;

import javax.inject.Inject;
import javax.naming.InvalidNameException;
import javax.transaction.UserTransaction;

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

@RunWith(Arquillian.class)
public class TestIdentityService {

	@Deployment
	public static Archive<?>createDeployment(){
				File[] libs = DependencyResolvers
						.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml")
						.includeDependenciesFromPom("pom.xml")
						.exclusion("org.hibernate:hibernate-core")
						.resolveAsFiles();
		
				return ShrinkWrap.create(WebArchive.class, "testFrontOffice.war")
						.addAsLibraries(libs)
						.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");		
	} 

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
	public void test() throws InvalidNameException {
		System.out.println("yo!");
	}
	
}
