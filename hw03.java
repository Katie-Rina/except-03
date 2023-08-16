import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class hw03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите данные (Фамилия Имя Отчество дата рождения номер телефона пол): ");
        String input = scanner.nextLine();

        try {
            String[] data = input.split(" ");
            if (data.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных");
            }

            String surName = data[0];
            String name = data[1];
            String patronymic = data[2];
            LocalDate birthDate = parseBirthDate(data[3]);
            long phoneNumber = Long.parseLong(data[4]);
            char gender = parseGender(data[5]);

            String fileName = surName + ".txt";
            String userData = surName + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;

            writeToFile(fileName, userData);
            System.out.println("Данные успешно записаны в файл " + fileName);
        } catch (DateTimeParseException e) {
            System.out.println("Неверный формат даты рождения");
        } catch (NumberFormatException e) {
            System.out.println("Неверный формат номера телефона");
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner.close();
    }

    private static LocalDate parseBirthDate(String input) {
        return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    private static char parseGender(String input) {
        char gender = input.charAt(0);
        if (gender != 'f' && gender != 'm') {
            throw new IllegalArgumentException("Неверное значение для пола");
        }
        return gender;
    }

    private static void writeToFile(String fileName, String data) throws IOException {
        FileWriter writer = new FileWriter(fileName, true);
        writer.write(data + "\n");
        writer.close();
    }
}