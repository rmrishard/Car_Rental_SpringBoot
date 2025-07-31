package com.rental.Car.services;

import com.rental.Car.model.Car;
import com.rental.Car.model.Cart;
import com.rental.Car.model.User;
import com.rental.Car.repository.CarJpaRepository;
import com.rental.Car.repository.CartJPARepository;
import com.rental.Car.repository.UserJPARepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartJPARepository cartJPARepository;
    private final UserJPARepository userJPARepository;
    private final CarJpaRepository carJpaRepository;

    public CartService(CartJPARepository cartRepository, UserJPARepository userJPARepository, CarJpaRepository carJpaRepository) {
        this.cartJPARepository = cartRepository;
        this.userJPARepository = userJPARepository;
        this.carJpaRepository = carJpaRepository;
    }

    public List<Cart> findMyCart(Long user_id){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        return cartJPARepository.findByUser(user);
    }


    public void createCart(Long user_id, Long carId){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Car car = carJpaRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car not Found"));

        Cart cart;
        Optional<Cart> cartOptional = cartJPARepository.findByUserAndCar(user, car);
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.setDays(cart.getDays() + 1);
        }else {
            cart = new Cart(user,car);
        }
        cartJPARepository.save(cart);
    }

    public void updateCart(Long user_id, Long carId, int days){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Car car = carJpaRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car not Found"));
        Cart cart = cartJPARepository.findByUserAndCar(user, car)
                .orElseThrow(()-> new RuntimeException("Cart not Found"));


        cart.setDays(days);
        cartJPARepository.save(cart);
    }


}
