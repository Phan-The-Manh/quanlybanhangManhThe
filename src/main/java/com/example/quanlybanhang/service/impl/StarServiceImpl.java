package com.example.quanlybanhang.service.impl;

import com.example.quanlybanhang.models.Star;
import com.example.quanlybanhang.repository.StarRepository;
import com.example.quanlybanhang.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StarServiceImpl implements StarService {

    @Autowired
    private StarRepository starRepository;

    @Override
    public List<Star> findAll() {
        return starRepository.findAll();
    }

    @Override
    public Optional<Star> findById(Long id) {
        return starRepository.findById(id);
    }

    @Override
    public void save(Star star) {
        starRepository.save(star);
    }

    @Override
    public void delete(Long id) {
        starRepository.deleteById(id);
    }

    @Override
    public List<Star> findAllByProductIdAndType(Long idProduct, String type) {
        return starRepository.findAllByProductIdAndType(idProduct, type);
    }

    @Override
    public List<Star> findAllByProductId(Long idProduct) {
        return starRepository.findAllByProductId(idProduct);
    }

    @Override
    public Star initStar(Long idUser, Long idProduct, String type) throws Exception {
        String[] rangeOfType = {"1", "2", "3", "4", "5"};
        int count = 0;
        for (int i = 0; i < rangeOfType.length; i++) {
            if (type.equals(rangeOfType[i])) {
                break;
            } else {
                count++;
            }
        }
        if (count == 5) {
            throw new Exception("Chỉ có thể đánh giá từ 1 đến 5 sao");
        }
        Star star = new Star();
        star.setType(type);
        star.setProductId(idProduct);
        star.setIdUser(idUser);
        return star;
    }
}
