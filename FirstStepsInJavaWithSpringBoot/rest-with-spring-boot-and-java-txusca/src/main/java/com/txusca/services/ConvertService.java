package com.txusca.services;

public class ConvertService {
    public static Double convertToDouble(String strNumber) {
        if (strNumber == null)
            return 0D;

        String number = strNumber.replaceAll(",", ".");
        if (ValidateService.isNumeric(number))
            return Double.parseDouble(number);
        return 0D;
    }
}
