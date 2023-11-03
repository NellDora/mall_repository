package com.nelldora.mall.test;

import com.nelldora.mall.board.service.BoardService;
import com.nelldora.mall.cart.domain.Cart;
import com.nelldora.mall.cart.domain.CartItem;
import com.nelldora.mall.cart.service.CartService;
import com.nelldora.mall.item.domain.Item;
import com.nelldora.mall.item.repository.ItemRepository;
import com.nelldora.mall.order.service.OrderService;
import com.nelldora.mall.order.vo.OrderCheckState;
import com.nelldora.mall.session.SESSION_CON;
import com.nelldora.mall.user.domain.User;
import com.nelldora.mall.user.dto.UserDTO;
import com.nelldora.mall.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
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

    @GetMapping("/500")
    public String pay5(@SessionAttribute(SESSION_CON.LOGIN_USER) User user, HttpServletResponse response) throws IOException {
        OrderCheckState orderCheckState = OrderCheckState.STANDBY;
        response.setContentType("text/html; charset=utf-8");
        User findUser = userService.findById(user.getId()).get(0);
        Cart findCart = cartService.findByUserId(findUser.getId());
        List<CartItem> cartItems = cartService.findByCartIdForCartItemList(findCart.getId());
        orderCheckState = orderService.saveOrder(findUser,cartItems,null  );

        if(orderCheckState == OrderCheckState.LOQ){
            PrintWriter advise =  response.getWriter();
            List<CartItem> stockItem = orderService.returnListLackStockItem(cartItems);

            String message ="수량이 부족한 물품이 있습니다. 확인 부탁드립니다. [재고 부족 물품 나열]";
            String list = "";

            for(CartItem cartItem : stockItem){
                list += cartItem.getItem().getName()+" ";
            }
            advise.write("<script>alert('"+message+"');</script>");
            advise.write("<script>alert('"+list+"');</script>");
            advise.write("<script>history.back();</script>");
            advise.flush();

        }

        return "redirect:/test/600";
    }

    @GetMapping("/600")
    public String payview(){
        return "mall-test-pay";
    }
}
