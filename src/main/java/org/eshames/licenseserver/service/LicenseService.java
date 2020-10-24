package org.eshames.licenseserver.service;

import javax0.license3j.Feature;
import javax0.license3j.License;
import javax0.license3j.crypto.LicenseKeyPair;
import javax0.license3j.io.KeyPairReader;
import lombok.extern.slf4j.Slf4j;
import org.eshames.licenseserver.dto.LicenseRequestDTO;
import org.eshames.licenseserver.exception.LicenseCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class LicenseService {

    @Value("${license.private.key}")
    private String privateKeyPath;


    public License issueLicense(LicenseRequestDTO licenseRequestDTO) {
        License license;
        try (KeyPairReader keyReader = new KeyPairReader(privateKeyPath)) {
            LicenseKeyPair keyPair = keyReader.readPrivate();
            license = new License();
            license.setLicenseId();
            license.add(Feature.Create.stringFeature("CustomerName", licenseRequestDTO.getCustomerName()));
            license.add(Feature.Create.stringFeature("ProductName", licenseRequestDTO.getProductName()));
            license.add(Feature.Create.uuidFeature("HardwareId", licenseRequestDTO.getHardwareId()));
            license.sign(keyPair.getPair().getPrivate(), "SHA-512");
            log.debug("Created license: {}", license.toString());
        } catch (Exception e) {
            throw new LicenseCreationException("Failed to create new license");
        }
        return license;
    }
}
