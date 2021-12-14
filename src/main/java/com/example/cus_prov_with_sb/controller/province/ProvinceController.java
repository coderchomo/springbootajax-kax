package com.example.cus_prov_with_sb.controller.province;

import com.example.cus_prov_with_sb.model.Province;
import com.example.cus_prov_with_sb.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/province")
@CrossOrigin("*")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;
    @GetMapping("")
    public ResponseEntity<Iterable<Province>> findAll(){
        return new ResponseEntity<>(provinceService.findAll(), HttpStatus.OK);
    }
}
