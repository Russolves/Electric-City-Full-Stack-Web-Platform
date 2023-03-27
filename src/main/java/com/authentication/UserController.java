package com.authentication;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class UserController {
    
    //Autowires the user repository bean (Spring Data JPA Repository) responsible for performing CRUD operations on User Entities
    @Autowired
	private UserRepository userRepo;
	
    //Automated Email message thanking sign-ups
    @Autowired
    private EmailService emailService;

    public UserController(UserRepository userRepo, EmailService emailService){
        this.userRepo = userRepo;
        this.emailService = emailService;
    }

    // // Home Page
	// @GetMapping("")
	// public String viewHomePage() {  //Change this part if you would like for a home page and not a home page to be the first thing the user sees
	// 	return "home";
	// }
	
    // Maps to GET request at the root path, returns "home" view to be rendered by the browser
    @GetMapping("") //Use localhost:8080 to access the home page
    public String viewHomePage(@AuthenticationPrincipal User user, Model model) {
        if (user != null){
            model.addAttribute("firstName", user.getFirstName());
        }
        return "home";
    }

    @GetMapping("/login")
    // Navigates to login page
    public String showLogInForm(){
        return "login";
    }

    // Navigates to registration page
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
        // Add new empty "User" object to the model with the key "user"
		model.addAttribute("user", new User());
		// Returns sign-up form to be rendered by browser
		return "signup_form";
	}

	// In charge of encrypting password
	@PostMapping("/process_register")
	public String processRegister(User user, BindingResult result) {
        // Throw error if registration doesn't pass
            if (result.hasErrors()) {
                return "signup_form";
            }

        // Check if user is existing user
        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser != null) {
            //User already exists, redirect them back to login page
            return "login";
        }
        // Encodes password using BCryptPasswordEncoder
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
        // Set the user's password to the encoded password
		user.setPassword(encodedPassword);
		//Saves the user object to the database using the UserRepository
		userRepo.save(user);

        // Section that sends email to users
        String sub = "Thank you for Registering with E-city!";
        String welcome = "Hello " + user.getFirstName() + ", \n\nThank you for subscribing to E-city! We hope this message finds you well. We wanted to take a moment to express our gratitude for subscribing to our EV-charging services. At E-city, our aim is to provide sustainable and reliable EV-charging solutions to our customers, and we are thrilled to have you on board.\n\nWelcome to E-city's webservice platform. From here on you will be able to log-in to our official website and check out nearby E-city charger station locations. \n\n We understand the importance of having dependable charging options when it comes to your EV, and we are committed to delivering the best service possible. We take pride in our state-of-the-art charging stations and our highly skilled technicians who are dedicated to ensuring that your charging experience is seamless. \n\n Your subscription enables us to continue providing innovative and sustainable charging solutions that help to reduce carbon emissions and promote a cleaner environment. We appreciate your trust in us, and we are committed to exceeding your expectations. \n\n Thank you again for choosing E-city as your EV-charging provider. If you have any questions or concerns, please do not hesitate to contact us. \n\n Best regards, \nThe E-city Team";
        emailService.sendEmail(user.getEmail(), sub, welcome);  //using the sendEmail function (address, subject/title, body of email)


		// Returns "register_success" view for browser to render
		return "register_success";
	}

	// Retrieves a list of all users from the database using the "UserRepository"
	@GetMapping("/users")
	public String listUsers(Model model) {
        // Adds users to list of Users (list of User objects)
		List<User> listUsers = userRepo.findAll();
        // Adds list of users to the model with the key "listUsers"
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}

    //Get mapping for page about.html
    @GetMapping("/about")
    public String showAbout(@AuthenticationPrincipal User user, Model model) {
        if (user != null){
            model.addAttribute("firstName", user.getFirstName());
        }
        return "about";
    }

    //Get mapping for pages Menu__.html
    @GetMapping("/menu_one")
    public String showMenuOne(@AuthenticationPrincipal User user, Model model){
        if (user != null){
            model.addAttribute("firstName", user.getFirstName());

        }
        return "menu_one";
    }

    // //Post mapping in charge of storing user location after logging in
    // @PostMapping("/store-location")
    // public String storeUserLocation(User user, @RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude){
    //     // Get the logged-in user
    //     Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     if (principal instanceof UserDetails){
    //         String userEmail = ((UserDetails) principal).getUsername();

    //         // Check if user is existing user
    //         User existingUser = userRepo.findByEmail(userEmail);
    //         //Find the user within the database and update their latitude and longitude
    //         if (existingUser != null){
    //             existingUser.setLatitude(latitude);
    //             existingUser.setLongitude(longitude);
    //             userRepo.save(existingUser);
    //             return "home";
    //         }
    //     }

    //     return "home";
        
    // }
        // Replace @PostMapping with @ResponseBody and @RequestMapping
        @ResponseBody
        @RequestMapping(value = "/store-location", method = RequestMethod.POST, produces = "application/json")
        public ResponseEntity<String> storeUserLocation(@RequestParam("latitude") Double latitude, @RequestParam("longitude") Double longitude) {
            // Get the logged-in user
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                String userEmail = ((UserDetails) principal).getUsername();

                // Check if user is existing user
                User existingUser = userRepo.findByEmail(userEmail);
                // Find the user within the database and update their latitude and longitude
                if (existingUser != null) {
                    existingUser.setLatitude(latitude);
                    existingUser.setLongitude(longitude);
                    userRepo.save(existingUser);
                    return new ResponseEntity<>("{\"status\": \"success\"}", HttpStatus.OK);
                }
            }

            return new ResponseEntity<>("{\"status\": \"error\"}", HttpStatus.BAD_REQUEST);
        }


}
