package com.nelldora.mall.test;

import com.nelldora.mall.board.service.BoardService;
import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.service.CartService;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.order.service.OrderService;
import com.nelldora.mall.session.SESSION_CON;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.dto.UserDTO;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class MallTestController {

    private final BoardService boardService;
    private final UserService userService;
    private final ItemRepository itemRepository;
    private final OrderService orderService;
    private final CartService cartService;


    @GetMapping("/main")
    public String main(){

        return "/mall-main";
    }

    @GetMapping("/pay")
    public String pay(){
        return "/test/mall-pay";
    }


    @GetMapping("/pay2")
    public String pay2(){
        return "/test/mall-pay3";
    }


    @GetMapping("/300")
    public String login(Model model){

        model.addAttribute("user", new UserDTO());
        return "/test/mall-login";
    }

    @PostMapping("/300")
    public String loginAction(@ModelAttribute("user") UserDTO userDTO, HttpServletRequest request){
        User findUser = userService.findById(userDTO.getId()).get(0);

        if(findUser.getPassword().equals(userDTO.getPasswordOne())){

            HttpSession session = request.getSession();
            session.setAttribute(SESSION_CON.LOGIN_USER, findUser);
        }


        return "redirect:/test/400";
    }

    @GetMapping("/400")
    public String stateView(@SessionAttribute(SESSION_CON.LOGIN_USER) User user, Model model){

        User findUser = userService.findByNum(user.getUserNumber());

        Cart userCart = cartService.findByUserId(findUser.getId());
        List<CartItem> cartItems = cartService.findByCartIdForCartItemList(userCart.getId());

        model.addAttribute("cartItems", cartItems);

        List<Item> itemlist = new ArrayList<>();

        for(CartItem cartItem : cartItems){
            itemlist.add(cartItem.getItem());
        }

        model.addAttribute("itemlist", itemlist);
        model.addAttribute("totalPrice" , userCart.getTotalPrice());

        return "/test/merchantTest";
    }

    @GetMapping
    public String pay(@SessionAttribute(SESSION_CON.LOGIN_USER) User user){
        User findUser = userService.findById(user.getId()).get(0);
        Cart findCart = cartService.findByUserId(findUser.getId());
        List<CartItem> cartItems = cartService.findByCartIdForCartItemList(findCart.getId())
        orderService.saveOrder(findUser,cartItems,null  );

        return "redirect:/500";
    }

    @GetMapping
    public String payview(){
        return "";
    }
}
