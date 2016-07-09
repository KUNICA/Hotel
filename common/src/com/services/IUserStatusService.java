package com.services;

/**
 * Интерфейс сервиса для управлением над статусом пользователя
 *
 * Created with IntelliJ IDEA.
 * User: killy
 * Date: 21.05.13
 * Time: 19:56
 */
public interface IUserStatusService {

    /**
     * Обрабатывает событие, связанное с неверным входом пользователя
     * @param username String логин пользователя, не прошедшего аутентификацию
     * */
    void processUnsuccessAuthentification(String username);

}
