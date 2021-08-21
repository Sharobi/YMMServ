package com.retail.ecom.controller;

import java.io.File;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.util.Date;
import com.retail.ecom.utils.ResponseDetails;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import com.retail.ecom.model.Prescription;
import io.jsonwebtoken.Claims;
import org.springframework.http.MediaType;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.springframework.core.io.InputStreamResource;
import java.io.FileInputStream;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PathVariable;
import com.retail.ecom.config.TokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import com.retail.ecom.service.PrescriptionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping({ "/prescriptions" })
public class PrescriptionController
{
    private String UPLOAD_FOLDER;
    @Autowired
    private PrescriptionService ps;
    @Autowired
    TokenExtractor te;
    
    public PrescriptionController() {
        this.UPLOAD_FOLDER = "/home/yewmedmart/prescription/";
    }
    
    @GetMapping({ "/download/{pid}" })
    public ResponseEntity<?> download(@PathVariable("pid") final int pid, @RequestHeader("Authorization") final String auth) {
        final Claims claims = this.te.extractInfo(auth);
        final Prescription prescription = this.ps.findPrescriptionByUId(pid, Integer.valueOf(claims.get((Object)"user_id").toString()));
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource((InputStream)new FileInputStream(String.valueOf(this.UPLOAD_FOLDER) + prescription.getSysGenFilename()));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (ResponseEntity<?>)((ResponseEntity.BodyBuilder)((ResponseEntity.BodyBuilder)ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream")).header("x-filename", new String[] { prescription.getUserFilename() })).header("Content-Disposition", new String[] { "attachment; filename=\"" + prescription.getUserFilename() + "\"" })).body((Object)resource);
    }
    
    @GetMapping({ "/download/{pid}/foradmin" })
    public ResponseEntity<?> downloadforadmin(@PathVariable("pid") final int pid) {
        final Prescription prescription = this.ps.findPrescriptionById(pid);
        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource((InputStream)new FileInputStream(String.valueOf(this.UPLOAD_FOLDER) + prescription.getSysGenFilename()));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return (ResponseEntity<?>)((ResponseEntity.BodyBuilder)((ResponseEntity.BodyBuilder)ResponseEntity.ok().contentType(MediaType.parseMediaType("application/octet-stream")).header("x-filename", new String[] { prescription.getUserFilename() })).header("Content-Disposition", new String[] { "attachment; filename=\"" + prescription.getUserFilename() + "\"" })).body((Object)resource);
    }
    
    @PostMapping({ "/upload" })
    @ResponseBody
    public ResponseDetails upload(@RequestParam("file") final MultipartFile file, @RequestHeader("Authorization") final String auth) {
        final Claims claims = this.te.extractInfo(auth);
        if (file.isEmpty()) {
            return new ResponseDetails(new Date(), "Please select a file", (String)null, 0);
        }
        final Prescription prescription = new Prescription();
        prescription.setUserFilename(file.getOriginalFilename());
        prescription.setUserId((int)Integer.valueOf(claims.get((Object)"user_id").toString()));
        int pid = 0;
        try {
            //System.out.println("Uploaded file = " + file.getOriginalFilename());
            final byte[] bytes = file.getBytes();
            final String genName = this.generateName(file.getOriginalFilename());
            final Path path = Paths.get(String.valueOf(this.UPLOAD_FOLDER) + genName, new String[0]);
            Files.write(path, bytes, new OpenOption[0]);
            prescription.setSysGenFilename(genName);
            pid = this.ps.savePrescription(prescription);
        }
        catch (IOException e) {
            return new ResponseDetails(new Date(), "File upload unsuccessful", (String)null, 0);
        }
        return new ResponseDetails(new Date(), "File uploaded successfully", (String)null, pid);
    }
    
    private String generateName(final String name) {
        final File chk = new File(String.valueOf(this.UPLOAD_FOLDER) + name);
        if (!chk.exists()) {
            return name;
        }
        final String ext = this.getExtension(name);
        return this.generateName(String.valueOf(this.randomAlphaNumeric(12)) + ext);
    }
    
    private String getExtension(final String original) {
        final String ext = original.substring(original.lastIndexOf("."));
        return ext;
    }
    
    private String randomAlphaNumeric(int count) {
        final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            final int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}
