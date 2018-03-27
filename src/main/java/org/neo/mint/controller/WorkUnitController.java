package org.neo.mint.controller;

import org.neo.mint.db.domain.WorkUnit;
import org.neo.mint.db.repository.WorkUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

/**
 * Created by vanosov on 20.03.2018.
 */
@RestController
public class WorkUnitController {

    private WorkUnitRepository workUnitRepository;

    @Autowired
    public WorkUnitController(WorkUnitRepository workUnitRepository){
        this.workUnitRepository = workUnitRepository;
    }

    @GetMapping("/workunit/{name}")
    public ResponseEntity<WorkUnit> getWorkUnit(@PathVariable String name){
        Optional<WorkUnit> workUnit = workUnitRepository.findById(name);

        if(workUnit.isPresent()){
            return ResponseEntity.ok(workUnit.get());
        } else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/workunit")
    public ResponseEntity<HttpStatus> saveWorkUnit(@RequestBody WorkUnit workUnit) {
        workUnitRepository.save(workUnit);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/workunit/{name}")
    public ResponseEntity<HttpStatus> deleteWorkUnit(@PathVariable String name) {
        workUnitRepository.delete(name);
        return ResponseEntity.noContent().build();
    }

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String [] input = br.readLine().split(" ");
        for(int i = 0; i < input.length; i++){
            System.out.print(args[i] + "\t1");
        }
    }
}
