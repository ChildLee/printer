package com.ymy.printer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrinterAction {

    @RequestMapping("setPrint")
    public String setPrint() {
        return null;
    }
}
