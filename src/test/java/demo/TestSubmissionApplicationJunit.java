package demo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import application.Application;
import application.SubmissionApplication;
import application.SubmissionApplicationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class TestSubmissionApplicationJunit {

	@Autowired
	private SubmissionApplicationRepository submissionApplicationRepository;
	SubmissionApplication subApp;

	@Before
	public void setUp() {
		subApp = submissionApplicationRepository.getOne((long) 1);
	}

	@Test
	public void testCustomerId() {
		assertEquals(2, subApp.getCustomer_id());
	}

	@Test
	public void testEmployeeId() {
		assertEquals(1, subApp.getEmployee_id());
	}

	@Test
	public void testReportedCondition() {
		assertEquals("Healthy", subApp.getReportedCondition());
	}

	@Test
	public void testRequests() {
		assertEquals("Adopted into home with other dogs", subApp.getRequests());
	}

	@Test
	public void testApplicationStatus() {
		assertEquals("pending", subApp.getApplication_status());
	}

	@Test
	public void testRejectionNotes() {
		assertNull(subApp.getRejection_notes());

	}

}
