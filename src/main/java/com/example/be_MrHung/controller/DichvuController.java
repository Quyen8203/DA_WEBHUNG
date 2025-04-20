package com.example.be_MrHung.controller;

import com.example.be_MrHung.eNum.ResponseData;
import com.example.be_MrHung.models.DichVu;
import com.example.be_MrHung.services.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
@RequestMapping("/dichvu")
public class DichvuController {
    @Autowired
    DichVuService dichvuService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllDichvu(){
        return ResponseEntity.ok(dichvuService.getAllDichVu());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createDichvu(@RequestBody DichVu dichVu){
        ResponseData<DichVu> response = dichvuService.createDichvu(dichVu);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<ResponseData<DichVu>> deleteDichVu(@PathVariable int id) {
        ResponseData<DichVu> response = dichvuService.deleDichvuById(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDichvu(
            @PathVariable int id,
            @RequestBody DichVu dichVu) {

        ResponseData<DichVu> response = dichvuService.updateDichvu(id, dichVu);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
