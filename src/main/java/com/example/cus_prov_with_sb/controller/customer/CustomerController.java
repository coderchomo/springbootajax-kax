package com.example.cus_prov_with_sb.controller.customer;

import com.example.cus_prov_with_sb.model.Customer;
import com.example.cus_prov_with_sb.model.Province;
import com.example.cus_prov_with_sb.service.customer.ICustomerService;
import com.example.cus_prov_with_sb.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinceList")
    public Iterable<Province> showProvinceList(){
       return provinceService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        customerService.save(customer);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ModelAndView getAllCustomerPage(){
        ModelAndView modelAndView=new ModelAndView("/list");
//        modelAndView.addObject("customers",customerService.findAll());
        return modelAndView;
    }
    @GetMapping("/showAll")
    public ResponseEntity<Iterable<Customer>> allCustomer(){
        return new ResponseEntity<>(customerService.findAll(),HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long id){
        Optional<Customer> customerOptional=customerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            customerService.remove(id);
            return new ResponseEntity<>(customerOptional.get(),HttpStatus.NO_CONTENT );
    }
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomerById(@PathVariable Long id,@RequestBody Customer customer){
        Optional<Customer> customerOptional=customerService.findById(id);
        if(!customerOptional.isPresent()){
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        customer.setId(customerOptional.get().getId());
        customerService.save(customer);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}
