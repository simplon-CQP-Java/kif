package co.simplon.kif.core.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import co.simplon.kif.core.model.Booking;
import co.simplon.kif.core.service.BookingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/mail-bean.xml", "/spring-config.xml" })
@WebAppConfiguration
public class BookingServiceTest {
	
	@Autowired
	BookingService bookingService;
	
	@Test
	public void testFindAll() {
		List<Booking> list = bookingService.findAll();
		System.out.println("Test");
		for (Booking booking : list ) {
			System.out.println(booking);
		}
	}

}
