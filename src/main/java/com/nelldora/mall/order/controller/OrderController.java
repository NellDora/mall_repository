package com.nelldora.mall.order.controller;

import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.service.CartService;
import com.nelldora.mall.order.service.OrderService;
import com.nelldora.mall.order.vo.OrderCheckState;
import com.nelldora.mall.session.SESSION_CON;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    @GetMapping("/createOrder/{orderId}")
    public String pay5(@SessionAttribute(SESSION_CON.LOGIN_USER) User user, HttpServletResponse response, @PathVariable ("orderId") Long orderId) throws IOException {
        OrderCheckState orderCheckState = OrderCheckState.STANDBY;
        response.setContentType("text/html; charset=utf-8");
        User findUser = userService.findById(user.getId());
        Cart findCart = cartService.findByUserIdForCart(findUser.getId());
        List<CartItem> cartItems = cartService.findByCartIdForCartItemList(findCart.getId());
        orderCheckState = orderService.saveOrder(findUser, cartItems, null);

        if (orderCheckState == OrderCheckState.LOQ) {
            PrintWriter advise = response.getWriter();
            List<CartItem> stockItem = orderService.returnListLackStockItem(cartItems);

            String message = "수량이 부족한 물품이 있습니다. 확인 부탁드립니다. [재고 부족 물품 나열]";
            String list = "";

            for (CartItem cartItem : stockItem) {
                list += cartItem.getItem().getName() + " ";
            }
            advise.write("<script>alert('" + message + "');</script>");
            advise.write("<script>alert('" + list + "');</script>");
            advise.write("<script>history.back();</script>");
            advise.flush();

        }

        return "redirect:/order/orderPayment/{orderId}";
    }

    @GetMapping("/orderPayment/{orderId}")
    public String OrderPayment(@PathVariable("orderId") Long orderId, Model model){


        return  null;
    }
}
