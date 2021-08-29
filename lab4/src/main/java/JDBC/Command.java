package JDBC;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Command {

    private static Connection connection;
    private static Scanner input = new Scanner(System.in);
    private static int prodid;

    Command(Connection connection, int numberProduct)
    {
        Command.connection = connection;
        Command.prodid = numberProduct;
    }

    public static Connection getNewConnection() throws ClassNotFoundException, SQLException {
        final String URL = "jdbc:mysql://localhost:3306/production?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
        final String userName = "root";
        final String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, userName, password);

        if (connection.isValid(1)) {
            System.out.println("Подключена БД");
        }

        return connection;
    }

    public static void deleteProduct(Connection connection) throws SQLException {
        System.out.print("Какой товар удалить: ");
        String title = input.next();
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM product WHERE title = ? ")) {
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        }
    }

    public static void addProduct(Connection connection) throws SQLException {
        try{
            System.out.print("Введите название товара: ");
            String title = input.next();
            System.out.print("Введите цену товара: ");
            int cost = input.nextInt();

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product (prodid, title, cost) VALUE (?, ?, ?)");
            preparedStatement.setInt(1, ++prodid);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, cost);
            preparedStatement.executeUpdate();
        }

        catch (InputMismatchException e)
        {
            System.out.println("Неверные параметры команд!");
        }
    }

    public static void priceProduct(Connection connection) throws SQLException {
        {
            System.out.print("Введите название товара у которого хотите узнать цену: ");
            String title = input.next();
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT cost FROM product WHERE title = ?")) {
                preparedStatement.setString(1, title);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    System.out.println(resultSet.getInt("cost"));
                } else {
                    System.out.println("Такого товара нет");
                }
            }
        }
    }

    public static void change_priceProduct(Connection connection) {
        try
        {
            System.out.print("Введите товар у которого хотите поменять цену: ");
            String title = input.next();
            System.out.print("Какую цену установить: ");
            int cost = input.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product SET cost = ? WHERE title = ?");
            preparedStatement.setInt(1, cost);
            preparedStatement.setString(2, title);
            preparedStatement.executeUpdate();
        }

        catch (InputMismatchException | SQLException e)
        {
            System.out.println("Неверные параметры команд!");
        }
    }

    public static void filter_bi_priceProduct(Connection connection) {
        try
        {
            System.out.print("С какой цены: ");
            int firstPrice = input.nextInt();
            System.out.print("По какое число: ");
            int secondPrice = input.nextInt();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product WHERE cost > ? AND cost < ?");
            preparedStatement.setInt(1, Math.min(firstPrice, secondPrice));
            preparedStatement.setInt(2, Math.max(firstPrice, secondPrice));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                System.out.print(resultSet.getInt("id") + "  ");
                System.out.print(resultSet.getInt("prodid") + "  ");
                System.out.print(resultSet.getString("title") + "  ");
                System.out.print(resultSet.getInt("cost"));
                System.out.println();
            }
        }

        catch (InputMismatchException | SQLException e)
        {
            System.out.println("Неверные параметры команд!");
        }
    }

    public static void show_allProduct(Connection connection) throws SQLException {
        System.out.println("Вывод всех товаров");
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM product")) {
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id") + "  ");
                System.out.print(resultSet.getInt("prodid") + "  ");
                System.out.print(resultSet.getString("title") + "  ");
                System.out.print(resultSet.getInt("cost"));
                System.out.println();
            }
        }
    }
}
