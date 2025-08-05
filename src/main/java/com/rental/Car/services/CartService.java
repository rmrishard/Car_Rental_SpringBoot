package com.rental.Car.services;

import com.rental.Car.model.Car;
import com.rental.Car.model.Cart;
import com.rental.Car.model.CartItem;
import com.rental.Car.model.User;
import com.rental.Car.repository.CarJpaRepository;
import com.rental.Car.repository.CartJPARepository;
import com.rental.Car.repository.CartItemJPARepository;
import com.rental.Car.repository.UserJPARepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Optional;

@Service
public class CartService {
    private final CartJPARepository cartJPARepository;
    private final CartItemJPARepository cartItemJPARepository;
    private final UserJPARepository userJPARepository;
    private final CarJpaRepository carJpaRepository;

    public CartService(CartJPARepository cartRepository, CartItemJPARepository cartItemJPARepository, UserJPARepository userJPARepository, CarJpaRepository carJpaRepository) {
        this.cartJPARepository = cartRepository;
        this.cartItemJPARepository = cartItemJPARepository;
        this.userJPARepository = userJPARepository;
        this.carJpaRepository = carJpaRepository;
    }

    public Cart findMyCart(Long user_id){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));

        return cartJPARepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart(user);
                    return cartJPARepository.save(newCart);
                });
    }

    public void addToCart(Long user_id, Long carId){
        addToCart(user_id, carId, 1);
    }

    public void addToCart(Long user_id, Long carId, int days){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Car car = carJpaRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car not Found"));

        // Get or create cart
        Cart cart = cartJPARepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart(user);
                    return cartJPARepository.save(newCart);
                });

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartItemJPARepository.findByCartAndCar(cart, car);
        
        if (existingItem.isPresent()) {
            // Update existing item
            CartItem item = existingItem.get();
            item.setDays(item.getDays() + days);
            cartItemJPARepository.save(item);
        } else {
            // Create new cart item
            CartItem newItem = new CartItem(cart, car, days, car.getDailyRate());
            cartItemJPARepository.save(newItem);
        }
    }

    public void updateCartItem(Long user_id, Long carId, int days){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Car car = carJpaRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car not Found"));
        
        Cart cart = cartJPARepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemJPARepository.findByCartAndCar(cart, car)
                .orElseThrow(()-> new RuntimeException("Cart item not found"));

        cartItem.setDays(days);
        cartItemJPARepository.save(cartItem);
    }

    //Added the Transactional here
    @Transactional
    public void removeFromCart(Long user_id, Long carId){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        Car car = carJpaRepository.findById(carId)
                .orElseThrow(()-> new RuntimeException("Car not Found"));
        
        Cart cart = cartJPARepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Cart not found"));

        CartItem cartItem = cartItemJPARepository.findByCartAndCar(cart, car)
                .orElseThrow(()-> new RuntimeException("Cart item not found"));

        cartItemJPARepository.delete(cartItem);
    }

    public void clearCart(Long user_id){
        User user = userJPARepository.findById(user_id)
                .orElseThrow(()-> new RuntimeException("User not found"));
        
        Cart cart = cartJPARepository.findByUser(user)
                .orElseThrow(()-> new RuntimeException("Cart not found"));

        cartItemJPARepository.deleteByCart(cart);
    }
}
