import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.simplon.kif.core.service.EmailAPIService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/mail-bean.xml", "/spring-config.xml" })
@WebAppConfiguration
public class EmailAPIServiceTest {

	@Autowired
	EmailAPIService emailAPIService;

	@Test
	public void testSendEmail() {
		String fromAddr = "simplon.kif@gmail.com";
		String toAddr = "fab-oger@live.fr";
		String subject = "Foo";
		String body = "Bar";
		emailAPIService.sendEmail(fromAddr, toAddr, subject, body);
	}
}
