import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.simplon.kif.core.service.EmailAPIService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {  "/spring-config.xml" })
@WebAppConfiguration
public class AdminServiceTest {

	@Autowired
	AdminService adminService;

	@Testpublic final void testConnection(){
		fail("Not implemented yet");
	}


}