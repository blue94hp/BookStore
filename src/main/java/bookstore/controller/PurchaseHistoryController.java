package bookstore.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.websocket.server.UpgradeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bookstore.entity.PurchaseHistory;
import bookstore.entity.Users;
import bookstore.repository.PurchaseHistoryRepository;

@RestController
@CrossOrigin(origins="http://localhost:9000")
@RequestMapping(value = "/api")
public class PurchaseHistoryController {

	private PurchaseHistoryRepository purchaseHistoryRepository;

	@Autowired
	public PurchaseHistoryController(PurchaseHistoryRepository purchaseHistoryRepository) {
		this.purchaseHistoryRepository = purchaseHistoryRepository;
	}

	@RequestMapping(value="/purchase",method=RequestMethod.GET)
	public ResponseEntity<List<PurchaseHistory>> findAllPurchase(){
		return new ResponseEntity<List<PurchaseHistory>>(purchaseHistoryRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/purchase/{purchaseId}",method=RequestMethod.DELETE)
	public ResponseEntity<List<PurchaseHistory>> deletePurchase(@PathVariable String purchaseId){
		purchaseHistoryRepository.delete(purchaseHistoryRepository.findOne(purchaseId));
		return new ResponseEntity<List<PurchaseHistory>>(purchaseHistoryRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/history",method=RequestMethod.POST)
	public ResponseEntity<?> addPurchaseHistory(@RequestBody List<PurchaseHistory> purchaseHistory,
			HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session == null) {
			return new ResponseEntity<String>("Please login", HttpStatus.OK);
		}
		String username = (String) session.getAttribute("username");
		purchaseHistory.stream().forEach(p -> {
			double amount = (Double)(p.getPrice()*p.getQuantity());
			PurchaseHistory newPurchaseHistory = new PurchaseHistory(username, p.getBook(), p.getQuantity(),
					p.getPrice(), amount, getTime());
			purchaseHistoryRepository.save(newPurchaseHistory);
		});
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}

//	@RequestMapping(value="/history",method=RequestMethod.POST)
//	public ResponseEntity<?> addPurchaseHistory(@RequestBody PurchaseHistory purchaseHistory,
//			HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		if (session == null) {
//			return new ResponseEntity<String>("Please login", HttpStatus.BAD_REQUEST);
//		}
//		String username = (String) session.getAttribute("username");
//			double amount = (Double)(purchaseHistory.getPrice()*purchaseHistory.getQuantity());
//			PurchaseHistory newPurchaseHistory = new PurchaseHistory(username, purchaseHistory.getBook(), purchaseHistory.getQuantity(),
//					purchaseHistory.getPrice(), amount, getTime());
//			purchaseHistoryRepository.save(newPurchaseHistory);
//		return new ResponseEntity<String>("success",HttpStatus.OK);
//	}
	
	public String getTime() {
		Locale locale = new Locale("VN");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", locale);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

}
