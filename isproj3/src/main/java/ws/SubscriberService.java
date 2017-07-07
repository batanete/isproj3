package ws;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@WebService
public interface SubscriberService {
	String createSub(String email,String course);
	String listSubs();
	String delSub(String email,String course);
}