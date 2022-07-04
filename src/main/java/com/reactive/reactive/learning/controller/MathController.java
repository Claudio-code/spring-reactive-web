package com.reactive.reactive.learning.controller;

import com.reactive.reactive.learning.dto.ResponseDTO;
import com.reactive.reactive.learning.service.MathService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("math")
public class MathController {
    private MathService mathService;

    @GetMapping("square/{input}")
    public ResponseDTO findSquare(@PathVariable int input) {
        return mathService.findSquare(input);
    }

    @GetMapping("table/{input}")
    public List<ResponseDTO> findTable(@PathVariable int input) {
        return mathService.multiplicationTable(input);
    }
}
