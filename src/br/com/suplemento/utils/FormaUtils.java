/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.suplemento.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author Aluno
 */
public class FormaUtils {
        private static final Locale ptBR = new Locale("pt", "BR");
    private static final NumberFormat formatDecimals = NumberFormat.getInstance(ptBR);
    private static final NumberFormat formatIntegers = NumberFormat.getIntegerInstance(ptBR);
    private static final SimpleDateFormat formatDates = new SimpleDateFormat("dd/MM/yyyy", ptBR);
    private static final SimpleDateFormat formatDatesWithHour = new SimpleDateFormat("dd/MM/yyyy HH:mm", ptBR);

    static {
        formatDecimals.setMaximumFractionDigits(2);
        formatDecimals.setMinimumFractionDigits(2);
    }

    public static final String formatDecimal(Double value) {
        if (value != null) {
            return formatDecimals.format(value);
        }
        return "";
    }

    public static final String formatLong(Long value) {
        if (value != null) {
            return formatIntegers.format(value);
        }
        return "";
    }
    
    public static final String formatInteger(Integer value) {
        if (value != null) {
            return formatIntegers.format(value);
        }
        return "";
    }

    public static final String formatDate(Date value) {
        if (value != null) {
            return formatDates.format(value);
        }
        return "";
    }

    public static final String formatDateWithHour(Date value) {
        if (value != null) {
            return formatDatesWithHour.format(value);
        }
        return "";
    }

    public static final Double parseDecimal(String value) throws ParseException {
        if (value != null && !value.isEmpty()) {
            return formatDecimals.parse(value).doubleValue();
        }
        return null;
    }

    public static final Integer parseInteger(String value) throws ParseException {
        if (value != null && !value.isEmpty()) {
            return formatIntegers.parse(value).intValue();
        }
        return null;
    }

    public static final Long parseLong(String value) throws ParseException {
        if (value != null && !value.isEmpty()) {
            return formatIntegers.parse(value).longValue();
        }
        return null;
    }

    public static final Date parseDate(String value) throws ParseException {
        if (value != null && !value.isEmpty()) {
            return formatDates.parse(value);
        }
        return null;
    }

    public static final Date parseDateWithHour(String value) throws ParseException {
        if (value != null && !value.isEmpty()) {
            return formatDatesWithHour.parse(value);
        }
        return null;
    }
}
