package com.example.RentalManagementApi.properties;

import com.example.RentalManagementApi.properties.impl.PropertyServiceImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    private final PropertyServiceImpl propertyService;

    public PropertyController(PropertyServiceImpl propertyService) {
        this.propertyService = propertyService;
    }


    @GetMapping
    public ResponseEntity<List<Property>> getAllProperty(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        List<Property> properties = propertyService.getAllProperties(token);
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addProperty(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody Property property
    ){
        Long val = propertyService.addProperty(property, token);
        return new ResponseEntity<>(val, HttpStatus.CREATED);
    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getById(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable Long propertyId
    ){
        Property property = propertyService.getById(propertyId, token);
        return new ResponseEntity<>(property, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProp(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable Long id
    ){
        propertyService.deleteProp(id,token);
        return new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<String> updateProp(@PathVariable Long id,
                                             @RequestBody Property property){
       propertyService.UpdateProp(id,property);
       return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getPropertyCount(){
        long val = propertyService.getPropertyCount();
        return new ResponseEntity<>(val, HttpStatus.OK);
    }

}
