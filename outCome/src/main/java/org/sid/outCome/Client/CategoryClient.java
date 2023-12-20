package org.sid.outCome.Client;

import org.sid.outCome.Entity.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name ="category",url = "http://localhost:8070/api/v1/category")
public interface CategoryClient {
    @GetMapping(path="/{categoryId}" )
    Category getCategoryById(@PathVariable("categoryId") int categoryId);
    @GetMapping("/all")
    List<Category> getAllCategories();
    @GetMapping(path="/{id}" )
    List<Category>getCategoryByUserId(@PathVariable("id") int userId);

}
