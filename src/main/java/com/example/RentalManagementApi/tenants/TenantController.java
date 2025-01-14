package com.example.RentalManagementApi.tenants;

import com.example.RentalManagementApi.tenants.impl.TenantServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tenant")
@CrossOrigin("*")
@RequiredArgsConstructor
public class TenantController {
    private final TenantServiceImpl tenantService;

    @GetMapping
    public ResponseEntity<List<Tenant>> getAllTenants(){
        List<Tenant> tenants = tenantService.getAllTenant();
        return new ResponseEntity<>(tenants, HttpStatus.OK);
    }

    @PostMapping("/unit/{id}")
    public ResponseEntity<String> addTenants(@PathVariable Long id,
                                             @RequestBody TenantDTO tenant){
        tenantService.addTenant(id, tenant);
        return new ResponseEntity<>("Added successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTenant(@PathVariable Long id){
        boolean isDeleted = tenantService.deleteTenant(id);
        if (isDeleted)
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Unsuccessfull", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCount(){
        long num = tenantService.getCount();
        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    @DeleteMapping("/property/{id}")
    public ResponseEntity<String> deletePTenant(@PathVariable Long id){
        try{
            tenantService.deletePTenant(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Problem",HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id){
        boolean isUpdated = tenantService.updateStatus(id);
        if (isUpdated)
            return new ResponseEntity<>("Updated", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem Updating Status", HttpStatus.NOT_ACCEPTABLE);
    }
}
