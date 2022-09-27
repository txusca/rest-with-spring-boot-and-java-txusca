package com.txusca.controllers;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.txusca.exceptions.UnsupportedMathOperationException;
import com.txusca.services.ConvertService;
import com.txusca.services.MathOperationService;
import com.txusca.services.ValidateService;

@RestController
// @Controller
// @ResponseBody
public class MathController {

    private final AtomicLong counter = new AtomicLong();

    private MathOperationService math = new MathOperationService();

    @RequestMapping(value = "/sum/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sum(
            @PathVariable(name = "numberOne") String numberOne, // para recuperar dados pela url
            @PathVariable(name = "numberTwo") String numberTwo // para recuperar dados pela url
    ) throws Exception {
        if (!ValidateService.isNumeric(numberOne) || !ValidateService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.sum(ConvertService.convertToDouble(numberOne), ConvertService.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sub/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double sub(
            @PathVariable(name = "numberOne") String numberOne, // para recuperar dados pela url
            @PathVariable(name = "numberTwo") String numberTwo // para recuperar dados pela url
    ) throws Exception {
        if (!ValidateService.isNumeric(numberOne) || !ValidateService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.sub(ConvertService.convertToDouble(numberOne), ConvertService.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/mult/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double mult(
            @PathVariable(name = "numberOne") String numberOne, // para recuperar dados pela url
            @PathVariable(name = "numberTwo") String numberTwo // para recuperar dados pela url
    ) throws Exception {
        if (!ValidateService.isNumeric(numberOne) || !ValidateService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.mult(ConvertService.convertToDouble(numberOne), ConvertService.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/div/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double div(
            @PathVariable(name = "numberOne") String numberOne, // para recuperar dados pela url
            @PathVariable(name = "numberTwo") String numberTwo // para recuperar dados pela url
    ) throws Exception {
        if (!ValidateService.isNumeric(numberOne) || !ValidateService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }
        return math.div(ConvertService.convertToDouble(numberOne), ConvertService.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/media/{numberOne}/{numberTwo}", method = RequestMethod.GET)
    public Double media(
            @PathVariable(name = "numberOne") String numberOne, // para recuperar dados pela url
            @PathVariable(name = "numberTwo") String numberTwo // para recuperar dados pela url
    ) throws Exception {
        if (!ValidateService.isNumeric(numberOne) || !ValidateService.isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return math.media(ConvertService.convertToDouble(numberOne), ConvertService.convertToDouble(numberTwo));
    }

    @RequestMapping(value = "/sqrt/{number}", method = RequestMethod.GET)
    public Double sqrt(
            @PathVariable(name = "number") String number // para recuperar dados pela url
    ) throws Exception {
        if (!ValidateService.isNumeric(number)) {
            throw new UnsupportedMathOperationException("Please set a numeric value");
        }

        return math.sqrt(ConvertService.convertToDouble(number));
    }
}
