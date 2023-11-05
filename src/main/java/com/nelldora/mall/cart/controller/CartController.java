package com.nelldora.mall.cart.controller;

import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.service.CartService;
import com.nelldora.mall.session.SESSION_CON;
import com.nelldora.mall.user.controller.UserController;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final UserService userService;
    private final CartService cartService;

    @GetMapping("/myCart")
    public String myCartList(@SessionAttribute(SESSION_CON.LOGIN_USER) User user, Model model){
        User findUser = userService.findById(user.getId());

        Cart findMyCart = cartService.findByUserIdForCart(findUser.getId());
        List<CartItem> myCartItemList = cartService.findByCartIdForCartItemList(findMyCart.getId());

        model.addAttribute("myCartitems", myCartItemList);
        model.addAttribute("myCart", findMyCart);
        return "mall-myCart";
    }

    //장바구니 아이템 뺄 때 사용
    @PostMapping("/myCart")
    public String myCartlistRemove(@SessionAttribute(SESSION_CON.LOGIN_USER) User user,@RequestParam ("cartItemId") Long id){

        User findUser = userService.findById(user.getId());

        cartService.removeOneforCartItem(id);

        return "redirect:/cart/myCart";
    }
}
