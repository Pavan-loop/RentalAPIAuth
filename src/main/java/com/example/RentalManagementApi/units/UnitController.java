package com.example.RentalManagementApi.units;

import com.example.RentalManagementApi.units.impl.UnitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("unit")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UnitController {
    private final UnitServiceImpl unitService;

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnit(){
        List<Unit> units = unitService.getAllUnit();
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @PostMapping("/property/{propertyId}")
    public ResponseEntity<String> createUnit(@PathVariable Long propertyId,
                                             @RequestBody List<UnitDTO> unit){
        unitService.createUnit(propertyId,unit);
        return new ResponseEntity<>("Unit added Successfully", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable Long id){
        Unit unit = unitService.getUnitById(id);
        return new ResponseEntity<>(unit, HttpStatus.FOUND);
    }

    @PutMapping("{unitId}/property/{propertyId}")
    public ResponseEntity<String> updateUnit(
            @PathVariable Long unitId,
            @RequestBody UnitDTO unitDTO,
            @PathVariable Long propertyId
    ){
        unitService.updateById(unitId,unitDTO, propertyId);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUnit(@PathVariable Long id){
        boolean isDeleted = unitService.deleteById(id);
        if (isDeleted)
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<String> deleteAll(@PathVariable Long id){
        boolean isDeleted = unitService.deleteAll(id);
        if (isDeleted)
            return new ResponseEntity<>("SuccessFully Deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("Problem", HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/property/{id}")
    public ResponseEntity<List<Unit>> getSpecificProperty(@PathVariable Long id){
        List<Unit> units = unitService.findUnitByPropertyId(id);
        return new ResponseEntity<>(units, HttpStatus.OK);
    }

    @GetMapping("/status/property/{id}")
    public ResponseEntity<List<Status>> getStatus(@PathVariable Long id){
        List<Status> status = unitService.getStatus(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/rent-of/{id}")
    public ResponseEntity<String> getRentAmount(@PathVariable Long id){
        return new ResponseEntity<>(unitService.getRentAmount(id), HttpStatus.OK);
    }
}
