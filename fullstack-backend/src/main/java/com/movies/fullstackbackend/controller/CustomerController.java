package com.movies.fullstackbackend.controller;

import com.movies.fullstackbackend.services.CustomerServices;
import com.movies.fullstackbackend.model.Booking;
import com.movies.fullstackbackend.model.Customer;
import com.movies.fullstackbackend.repository.BookingRepository;
import com.movies.fullstackbackend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.movies.fullstackbackend.exception.UserNotFoundException;

import java.util.List; // Import the List class


//@RequestMapping("/customer") 
@RestController
@CrossOrigin("http://localhost:3000")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository; // Corrected variable name
    // @Autowired
    // private BookingRepository bookingRepository; // Corrected variable name

    @Autowired
	CustomerServices customerServices;



    //@ResponseBody
    @PostMapping(value="/customer")
    Customer newCustomer(@RequestBody Customer newCustomer) {
            // for (Booking booking : newCustomer.getBookingList()) {
  
            //     booking.setCustomer(newCustomer);
            // }
            return customerServices.insertCustomer(newCustomer);
        // // Booking booking = new Booking();
        // // booking.setCustomer.setCustomerid(newCustomer.getCustomerid());
    
        // // bookingRepository.save(booking);
        // //String bookingListType = newCustomer.getBookingList().getClass().getSimpleName();

        //     // Do something with the booking data
        //     //System.out.println(newBooking.getCustomer().getCustomerid);// + bookingListType);

        //     bookingRepository.save(booking);
        
        
        

        //return customerRepository.save(newCustomer);
    }
    
    
    @GetMapping("/customer")
    List<Customer> getAllCustomers(){
        return customerRepository.findAll(); // Corrected variable name
    }

    @DeleteMapping("/customer/{id}")
    String deleteCustomer(@PathVariable Long id) {
        if (!customerRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        customerRepository.deleteById(id);
        return "customer with id " + id + " has been deleted successfully.";
    }

    @PutMapping("/customer/{id}")
    Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {
        return customerRepository.findById(id)
                .map(customer -> {

                    customer.setName(newCustomer.getName());
                    customer.setEmail(newCustomer.getEmail());
                    customer.setPhoneno(newCustomer.getPhoneno());
                    customer.setAddress(newCustomer.getAddress());
                    
                    customer.setBookingList(newCustomer.getBookingList());

                    return customerRepository.save(customer);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @GetMapping("customer/{id}")
    Customer getUserById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}



