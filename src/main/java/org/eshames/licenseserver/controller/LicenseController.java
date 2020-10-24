package org.eshames.licenseserver.controller;

import com.google.gson.Gson;
import org.eshames.licenseserver.dto.LicenseRequestDTO;
import org.eshames.licenseserver.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/license")
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @Autowired
    private Gson gson;


    @PostMapping("/issueLicense")
    public ResponseEntity<String> getLicense(@RequestBody LicenseRequestDTO licenseRequestDTO) {
        return new ResponseEntity<>(gson.toJson(licenseService.issueLicense(licenseRequestDTO)), HttpStatus.CREATED);
    }

}
