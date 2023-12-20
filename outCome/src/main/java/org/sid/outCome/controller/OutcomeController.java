package org.sid.outCome.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sid.outCome.Entity.OutCome;
import org.sid.outCome.service.OutcomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/outcomes")
@Tag(name = "Outcome", description = "Outcome API")
@RequiredArgsConstructor
@Slf4j
public class OutcomeController {
    private final OutcomeService outcomeService;


    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity<OutCome> create(@RequestBody  OutCome outCome) {
        return ResponseEntity.ok(outcomeService.create(outCome));
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok( outcomeService.findAll());
    }
     @PutMapping("/update/{id}")
    public ResponseEntity<OutCome> update(@PathVariable int id, @RequestBody OutCome outCome) {
        return ResponseEntity.ok(outcomeService.update( id , outCome));
}

}
