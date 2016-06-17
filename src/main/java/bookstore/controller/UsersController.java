package bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bookstore.entity.Users;
import bookstore.repository.UserRolesRepository;
import bookstore.repository.UsersRepository;

@RestController
@CrossOrigin(origins="http://localhost:9000")
@RequestMapping(value="/api")
public class UsersController {

	private UsersRepository usersRepository;
	private UserRolesRepository userRolesRepository;
	
	@Autowired
	public UsersController(UsersRepository usersRepository,UserRolesRepository userRolesRepository){
		this.usersRepository = usersRepository;
		this.userRolesRepository = userRolesRepository;
	}
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	public ResponseEntity<List<Users>> findAllUsers(){
		return new ResponseEntity<List<Users>>(usersRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/{userId}",method=RequestMethod.DELETE)
	public ResponseEntity<List<Users>> deleteUser(@PathVariable String userId){
		usersRepository.delete(usersRepository.findOne(userId));
		return new ResponseEntity<List<Users>>(usersRepository.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public ResponseEntity<?> createAccount(@RequestBody Users user){
		if(usersRepository.findByUsername(user.getUsername()) != null){
			return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
		}
		if(!user.getPassword().equals(user.getConfirmPassword())){
			return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
		}
		Users newUser = new Users(user.getUsername(), user.getPassword(), user.getEmail());
		return new ResponseEntity<Users>(usersRepository.save(newUser),HttpStatus.OK);
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody Users user, HttpServletRequest request){
		if(usersRepository.findByUsername(user.getUsername()) == null){
			return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
		}
		HttpSession session = request.getSession();
		session.setAttribute("username", user.getUsername());
		return new ResponseEntity<Users>(usersRepository.findByUsername(user.getUsername()),HttpStatus.OK);
	}
	
	@RequestMapping(value="/profile", method=RequestMethod.GET )
	public ResponseEntity<?> getProfile(HttpServletRequest request){
		HttpSession session = request.getSession(false);
		if(session==null){
			return new ResponseEntity<String>("Please login first",HttpStatus.OK);
		}
		String username = (String)session.getAttribute("username");
		return new ResponseEntity<Users>(usersRepository.findByUsername(username),HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<?> logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return new ResponseEntity<String>("Log out",HttpStatus.OK);
	}
	
}
