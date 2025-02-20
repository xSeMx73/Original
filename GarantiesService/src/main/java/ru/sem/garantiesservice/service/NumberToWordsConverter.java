package ru.sem.garantiesservice.service;

import org.springframework.stereotype.Service;

@Service
public class NumberToWordsConverter {

    private static final String[] units = {
            "", "Одна", "Две", "Три", "Четыре",
            "Пять", "Шесть", "Семь", "Восемь", "Девять"
    };

    private static final String[] teens = {
            "Десять", "Одиннадцать", "Двенадцать", "Тринадцать",
            "Четырнадцать", "Пятнадцать", "Шестнадцать",
            "Семнадцать", "Восемнадцать", "Девятнадцать"
    };

    private static final String[] tens = {
            "", "Десять", "Двадцать", "Тридцать",
            "Сорок", "Пятьдесят", "Шестьдесят",
            "Семьдесят", "Восемьдесят", "Девяносто"
    };

    private static final String[] hundreds = {
            "", "Сто", "Двести", "Триста", "Четыреста",
            "Пятьсот", "Шестьсот", "Семьсот", "Восемьсот", "Девятьсот"
    };

    private static final String[] thousands = {
            "", "Тысяча", "Две тысячи", "Три тысячи", "Четыре тысячи",
            "Пять тысяч", "Шесть тысяч", "Семь тысяч", "Восемь тысяч", "Девять тысяч"
    };

    public String convert(int number) {
        if (number < 0 || number > 9999) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 0 до 9999.");
        }

        if (number == 0) {
            return "Ноль";
        }

        StringBuilder words = new StringBuilder();

        // Обработка тысяч
        int thousand = number / 1000;
        if (thousand > 0) {
            words.append(thousands[thousand]).append(" ");
        }

        // Обработка сотен
        int hundred = (number % 1000) / 100;
        if (hundred > 0) {
            words.append(hundreds[hundred]).append(" ");
        }

        // Обработка десятков
        int ten = (number % 100) / 10;
        int unit = number % 10;

        if (ten == 1 && unit > 0) { // Если десяток "десять"
            words.append(teens[unit - 1]).append(" ");
        } else {
            if (ten > 0) {
                words.append(tens[ten]).append(" ");
            }
            // Обработка единиц
            if (unit > 0) {
                words.append(units[unit]).append(" ");
            }
        }

        return words.toString().trim(); // Удаляем лишние пробелы
    }
}