package com.oosd.vstudent.controllers;

import com.oosd.vstudent.errors.InvalidEndpointException;
import com.oosd.vstudent.errors.SuccessResponse;
import com.oosd.vstudent.models.CarPool;
import com.oosd.vstudent.services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carpool")
public class CarPoolController {

    @Autowired
    DatabaseService databaseService;

    //get all carpools

    @GetMapping("/")
    public List<CarPool> retrieveAllCarPools()
    {
        return databaseService.getCarPoolRepository().findAll();
    }

    @PostMapping("/")
    public CarPool addCarPool(@RequestBody CarPool carPool)
    {
        databaseService.getCarPoolRepository().save(carPool);
        return carPool;
    }

    @PutMapping("/{id}")
    public CarPool editCarPool(@PathVariable int id, @RequestBody CarPool carPool)
    {
        if (!databaseService.getCarPoolRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }
        carPool.setId(id);
        databaseService.getCarPoolRepository().save(carPool);
        return carPool;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteCarPool(@PathVariable int id)
    {
        if (!databaseService.getCarPoolRepository().existsById(id))
        {
            throw new InvalidEndpointException("id not found");
        }

        SuccessResponse sr = new SuccessResponse();
        sr.setStatus(HttpStatus.OK.value());
        sr.setMessage("car pool deleted");

        return new ResponseEntity<SuccessResponse>(sr, HttpStatus.OK);
    }
}
