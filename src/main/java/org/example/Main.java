package org.example;

import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String csvFilePath = "foreign_names.csv"; // файл должен быть в src/main/resources
        char separator = ';';

        List<Person> people = readPeopleFromCSV(csvFilePath, separator);

        // Вывод списка людей
        if (!people.isEmpty()) {
            people.forEach(System.out::println);
        } else {
            LOGGER.warning("Список людей пуст. Возможно, файл CSV пустой или содержит ошибки.");
        }
    }

    public static List<Person> readPeopleFromCSV(String csvFilePath, char separator) {
        Map<String, Subdivision> subdivisions = new HashMap<>();
        List<Person> people = new ArrayList<>();

        try (InputStream in = Main.class.getClassLoader().getResourceAsStream(csvFilePath);
             InputStreamReader inputStreamReader = in == null ? null : new InputStreamReader(in)) {
            if (inputStreamReader == null) {
                throw new FileNotFoundException("Файл не найден: " + csvFilePath);
            }

            CSVReader reader = new CSVReaderBuilder(inputStreamReader)
                    .withCSVParser(new CSVParserBuilder().withSeparator(separator).build())
                    .build();

            List<String[]> rows = reader.readAll();
            rows.removeFirst(); // Удаляем заголовок

            for (String[] row : rows) {
                try {
                    int id = Integer.parseInt(row[0]);
                    String name = row[1];
                    String gender = row[2];
                    String birthDate = row[3];
                    String subdivisionName = row[4];
                    String salaryString = row[5];

                    // Проверяем, что salary — это корректное число
                    if (!isValidDouble(salaryString)) {
                        LOGGER.log(Level.WARNING, "Некорректное значение зарплаты в строке: " + Arrays.toString(row));
                        continue; // Пропустить строку
                    }

                    double salary = Double.parseDouble(salaryString);

                    // Создаем или находим подразделение
                    Subdivision subdivision = subdivisions.computeIfAbsent(subdivisionName, nameKey ->
                            new Subdivision(UUID.randomUUID().toString(), nameKey));

                    // Добавляем человека в список
                    people.add(new Person(id, name, gender, subdivision, salary, birthDate));
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.WARNING, "Ошибка преобразования данных в строке: " + Arrays.toString(row), e);
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Ошибка обработки строки: " + Arrays.toString(row), e);
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "CSV файл не найден: " + csvFilePath, e);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Произошла ошибка при чтении файла CSV", e);
        }

        return people;
    }

    // Метод для проверки корректности числа
    private static boolean isValidDouble(String str) {
        Pattern doublePattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return doublePattern.matcher(str).matches();
    }
}
