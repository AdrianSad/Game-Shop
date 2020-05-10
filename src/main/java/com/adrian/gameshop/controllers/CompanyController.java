package com.adrian.gameshop.controllers;

import com.adrian.gameshop.models.Company;
import com.adrian.gameshop.models.Game;
import com.adrian.gameshop.services.CompanyService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public String companyListPage(Model model){

        model.addAttribute("companies", companyService.getCompanies());
        return "company/companyList";
    }

    @GetMapping("/{id}/show")
    public String companyGamesPage(@PathVariable Long id, Model model){

        model.addAttribute("companyGames", companyService.getCompanyGames(id));
        model.addAttribute("company", companyService.findById(id));

        return "company/show";
    }

    @GetMapping("/new")
    public String newCompanyForm(Model model){

        model.addAttribute("company", new Company());

        return "company/createForm";
    }

    @PostMapping("/new")
    public String saveOrUpdateCompany(@Valid @ModelAttribute("company") Company company, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "company/createForm";
        }

        Company savedCompany = companyService.saveCompany(company);

        return "redirect:/companies/" + savedCompany.getId() + "/logo";
    }

    @GetMapping("/{id}/update")
    public String updateCompany(@PathVariable Long id, Model model){

        model.addAttribute("company", companyService.findById(id));

        return "company/createForm";
    }

    @GetMapping("/{id}/delete")
    public String deleteCompany(@PathVariable Long id){

        companyService.deleteById(id);

        return "redirect:/companies";
    }

    @GetMapping("/{id}/logo")
    public String newImageForm(@PathVariable Long id, Model model){

        model.addAttribute("company", companyService.findById(id));
        return "company/uploadImageForm";
    }

    @PostMapping("/{id}/logo")
    public String saveOrUpdateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file){

        companyService.saveImageFile(id,file);

        return "redirect:/companies/" + id + "/show";
    }

    @GetMapping("/{id}/companyImage")
    public void renderImage(@PathVariable Long id, HttpServletResponse response) throws IOException {

        Company company = companyService.findById(id);
        byte[] byteArray = new byte[company.getLogo().length];
        int i = 0;

        for (Byte wrappedByte : company.getLogo()){
            byteArray[i++] = wrappedByte;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(byteArray);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
