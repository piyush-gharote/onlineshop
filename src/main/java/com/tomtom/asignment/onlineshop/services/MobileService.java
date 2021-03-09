package com.tomtom.asignment.onlineshop.services;

import com.tomtom.asignment.onlineshop.entities.Mobile;
import com.tomtom.asignment.onlineshop.entities.Product;
import com.tomtom.asignment.onlineshop.repositories.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MobileService {

    @Autowired
    private MobileRepository mobileRepository;

    public Mobile getMobile(int id){
        return mobileRepository.findById(id).get();
    }

    public List<Product> getAllMobiles(){
        ArrayList<Product> mobiles = new ArrayList<>();
        mobileRepository.findAll().forEach(mobiles :: add);
        return mobiles;
    }

    public Mobile updateMobile(Mobile mobile, int id){
       Optional<Mobile> mobileOptional = mobileRepository.findById(id);

       if(mobileOptional.isPresent()){
           Mobile mobileOld = mobileOptional.get();
           mobileOld.setCameraPixels(mobile.getCameraPixels());
           mobileOld.setRam(mobile.getRam());
           mobileOld.setScreenSize(mobile.getScreenSize());
           mobileOld.setBrand(mobile.getBrand());
           mobileOld.setRom(mobile.getRom());
           mobile.setTouchScreen(mobile.isTouchScreen());
           mobile.setInStock(mobile.isInStock());
           mobile.setTurbocharge(mobile.isTurbocharge());

           mobileRepository.save(mobileOld);
       }
       return mobileRepository.save(mobile);
    }

    public Mobile addMobile(Mobile mobile){
        return mobileRepository.save(mobile);
    }

    public void deleteAllMobiles(){
        mobileRepository.deleteAll();
    }

    public void deleteMobile(int id){
        mobileRepository.deleteById(id);
    }

    public void addAll(List<Mobile> mobiles) {
       mobileRepository.saveAll(mobiles);
    }
}
