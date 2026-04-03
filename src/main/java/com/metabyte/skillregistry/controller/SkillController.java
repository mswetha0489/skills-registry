package com.metabyte.skillregistry.controller;

import com.metabyte.skillregistry.dto.AliasRequest;
import com.metabyte.skillregistry.dto.ResolveResponse;
import com.metabyte.skillregistry.dto.SkillRequest;
import com.metabyte.skillregistry.service.SkillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid SkillRequest request) {
        service.createSkill(request);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{id}/aliases")
    public ResponseEntity<?> addAlias(@PathVariable Long id,
                                      @RequestBody AliasRequest request) {
        service.addAlias(id, request.alias);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/resolve")
    public ResponseEntity<ResolveResponse> resolve(@RequestParam String name) {
        return ResponseEntity.ok(service.resolve(name));
    }
}
