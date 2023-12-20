package org.sid.outCome.service;

import lombok.RequiredArgsConstructor;
import org.sid.outCome.Client.CategoryClient;

import org.sid.outCome.Client.UserClient;
import org.sid.outCome.Entity.Category;
import org.sid.outCome.Entity.OutCome;
import org.sid.outCome.Repository.OutComeRepository;
import org.sid.outCome.validator.ObjectsValidator;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OutcomeService {

    private final CategoryClient categoryClient;
    private final OutComeRepository outComeRepository;
    private final UserClient userClient;
    private final ObjectsValidator<OutCome> outComeObjectsValidator;
    public OutCome create(OutCome outCome) {
        outComeObjectsValidator.validate(outCome);
        Category categories=categoryClient.getAllCategories().stream().filter(category1 -> category1.getId()==outCome.getCategoryId()).findFirst().orElseThrow();
        // afficher les categories
        outCome.setCategories(Collections.singletonList(categories));
        // Set other attributes
        outCome.setMontant(outCome.getMontant());
        outCome.setDate(outCome.getDate());
        return outComeRepository.save(outCome);
    }

    public List<OutCome> findAll() {
        return outComeRepository.findAll();
    }
    public OutCome update(long id,OutCome outCome){
        var outCome1 = outComeRepository.findById(id).orElseThrow();
        outCome1.setMontant(outCome.getMontant());

        outCome1.setUserId(outCome.getUserId());
        outCome1.setDate(outCome.getDate());
        return outComeRepository.save(outCome1);
    }
}
