package com.banca.banca.mapper;

import com.banca.banca.dto.CartDto;
import com.banca.banca.entity.Cart;

import java.util.List;

public interface CartMapper {

    CartDto toCartDto (Cart cart);

    Cart toCart (CartDto cartDto);

    List<CartDto> toCartDtoList (List<Cart> cartList);
}
