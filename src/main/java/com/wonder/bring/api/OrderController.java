package com.wonder.bring.api;

import com.wonder.bring.service.JwtService;
import com.wonder.bring.service.OrderService;
import com.wonder.bring.service.impl.OrderServiceImpl;
import com.wonder.bring.utils.auth.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wonder.bring.model.DefaultRes.FAIL_DEFAULT_RES;
import static com.wonder.bring.utils.Message.TOKEN_EMPTY;

@Slf4j
@RequestMapping("orders")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final JwtService jwtService;

    public OrderController(final OrderService orderService, final JwtService jwtService) {
        this.orderService = orderService;
        this.jwtService = jwtService;
    }

    //개인의 주문내역 조회
    @Auth
    @GetMapping()
    public ResponseEntity getOrderList(@RequestHeader("Authorization") final String header) {
        try {
            if(header != null) {
                int userIdx = jwtService.decode(header).getUser_idx();
                return new ResponseEntity<>(orderService.getOrderList(userIdx), HttpStatus.OK);
            }
            return new ResponseEntity<>(TOKEN_EMPTY, HttpStatus.UNAUTHORIZED);
        } catch(Exception e) {
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}