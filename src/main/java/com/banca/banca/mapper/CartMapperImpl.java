package com.banca.banca.mapper;

import com.banca.banca.dto.CartDto;
import com.banca.banca.entity.Cart;
import com.banca.banca.entity.CustomerData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapperImpl implements CartMapper{
    @Override
    public CartDto toCartDto(Cart cart) {

        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        cartDto.setServiceBank(cart.getServiceBank());
        cartDto.setCustomerData(new CustomerData(cart.getCustomerData().getFiscalCode(),
                cart.getCustomerData().getName(),
                cart.getCustomerData().getSurname(),
                cart.getCustomerData().getDateOfBirth(),
                cart.getCustomerData().getEmail(),
                cart.getCustomerData().getInfoPhone(),
                cart.getCustomerData().getAddress()));
        cartDto.setServiceBank(cart.getServiceBank());

        return cartDto;
    }

    @Override
    public Cart toCart(CartDto cartDto) {

        Cart cart = new Cart();

        cart.setId(cartDto.getId());
        cart.setServiceBank(cartDto.getServiceBank());
        cart.setCustomerData(cartDto.getCustomerData());

        return cart;
    }

    @Override
    public List<CartDto> toCartDtoList(List<Cart> cartList) {

        List<CartDto> cartDtoList = new ArrayList<>();

        for(Cart cart : cartList) {
            CartDto cartDto = new CartDto();
            cartDto.setId(cart.getId());
            /*
            Ho dovuto mapparlo qui dentro perchè è da sistemare l'entity Role
            che ogni volta fa tornare un CustomerDAta in ricorsione
            */
            cartDto.setCustomerData(new CustomerData(cart.getCustomerData().getFiscalCode(),
                    cart.getCustomerData().getName(),
                    cart.getCustomerData().getSurname(),
                    cart.getCustomerData().getDateOfBirth(),
                    cart.getCustomerData().getEmail(),
                    cart.getCustomerData().getInfoPhone(),
                    cart.getCustomerData().getAddress()));
            cartDto.setServiceBank(cart.getServiceBank());
            cartDtoList.add(cartDto);
        }

        return cartDtoList;
    }
}
