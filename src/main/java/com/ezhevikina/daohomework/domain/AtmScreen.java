package com.ezhevikina.daohomework.domain;

import com.ezhevikina.daohomework.dao.exceptions.DaoException;
import com.ezhevikina.daohomework.service.AccountManager;
import com.ezhevikina.daohomework.service.AccountService;
import com.ezhevikina.daohomework.service.exceptions.NotEnoughMoneyException;
import com.ezhevikina.daohomework.service.exceptions.UnknownAccountException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Меню взаимодействия с клиентом. Принимает на вход интерфейс AccountService.
 * В консоль выводятся доступные для обработки команды.
 * Производится обработка пользовательского ввода через консоль и преобразуется
 * в команды для AccountManager.
 */

public class AtmScreen {
  private final AccountService manager;

  public AtmScreen(AccountService manager) {
    this.manager = manager;
  }

  public void start() {
    System.out.println("Available operations:\n"
        + "* balance [id]\n"
        + "* withdraw [id] [amount]\n"
        + "* deposit [id] [amount]\n"
        + "* transfer [from] [to] [amount]\n"
        + "E to exit");

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      try {
        String[] command = (reader.readLine()).split(" ");

        switch (command[0].toLowerCase()) {
          case "balance": {
            int accountId = Integer.parseInt(command[1]);
            System.out.println(manager.balance(accountId));
            break;
          }
          case "withdraw": {
            int accountId = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);

            if (amount < 0) {
              throw new IllegalArgumentException("Amount should be positive");
            }

            manager.withdraw(accountId, amount);
            System.out.println("Successful operation");
            break;
          }
          case "deposit": {
            int accountId = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);

            if (amount < 0) {
              throw new IllegalArgumentException("Amount should be positive");
            }

            manager.deposit(accountId, amount);
            System.out.println("Successful operation");
            break;
          }
          case "transfer": {
            int accountFrom = Integer.parseInt(command[1]);
            int accountTo = Integer.parseInt(command[2]);
            int amount = Integer.parseInt(command[3]);

            if (amount < 0) {
              throw new IllegalArgumentException("Amount should be positive");
            }

            manager.transfer(accountFrom, accountTo, amount);
            System.out.println("Successful operation");
            break;
          }
          case "e": {
            reader.close();
            System.exit(0);
          }
          default: {
            System.out.println("Invalid command");
          }
        }
      } catch (IOException e) {
        e.printStackTrace();

      } catch (DaoException e) {
        System.out.println("DAO exception");
        e.printStackTrace();

      } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        System.out.println("Invalid command");

      } catch (UnknownAccountException | NotEnoughMoneyException | IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}