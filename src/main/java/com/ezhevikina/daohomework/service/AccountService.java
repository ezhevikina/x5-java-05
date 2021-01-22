package com.ezhevikina.daohomework.service;

import com.ezhevikina.daohomework.dao.exceptions.DaoException;
import com.ezhevikina.daohomework.service.exceptions.NotEnoughMoneyException;
import com.ezhevikina.daohomework.service.exceptions.UnknownAccountException;

/**
 * Интерфейс для проведения операций со счетом
 */
public interface AccountService {
  /**
   * Операция снятия суммы со счета
   * @param accountId id счета
   * @param amount запрашиваемая сумма для проведения операции
   * @throws NotEnoughMoneyException недостаточно средств на счете для снятия запрашиваемой суммы
   * @throws UnknownAccountException указанный счет не найден
   * @throws DaoException ошибка при работе с хранилищем данных
   */
  void withdraw(int accountId, int amount) throws
      NotEnoughMoneyException, UnknownAccountException, DaoException;

  /**
   * Получение информаци о состоянии баланса счета
   * @param accountId id счета
   * @return сумма на балансе счета
   * @throws UnknownAccountException указанный счет не найден
   * @throws DaoException ошибка при работе с хранилищем данных
   */
  int balance(int accountId) throws UnknownAccountException, DaoException;

  /**
   * Операция внесения суммы на счет
   * @param accountId id счета
   * @param amount запрашиваемая сумма для проведения операции
   * @throws UnknownAccountException указанный счет не найден
   * @throws DaoException ошибка при работе с хранилищем данных
   */
  void deposit(int accountId, int amount) throws UnknownAccountException, DaoException;

  /**
   *
   * @param from id счета с которого будет произведено списание запрашиваемой суммы
   * @param to id счета на который будет внесена запрашиваемая сумма
   * @param amount запрашиваемая сумма для проведения операции
   * @throws NotEnoughMoneyException недостаточно средств на счете для снятия запрашиваемой суммы
   * @throws UnknownAccountException указанный счет не найден
   * @throws DaoException ошибка при работе с хранилищем данных
   */
  void transfer(int from, int to, int amount) throws
      NotEnoughMoneyException, UnknownAccountException, DaoException;
}
