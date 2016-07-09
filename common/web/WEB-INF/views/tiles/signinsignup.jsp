<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 24.06.2016
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<div>
<table style="width: 100%;height: 100%; text-align:center;">
    <tr>
        <td align="center">
            <form name="f" action="<c:url value='j_spring_security_check'/>" method="POST">
                <table style="background-color: buttonface;">
                    <tbody>
                    <tr>
                        <td style="padding: 0px; height: 23px; background-color: activecaption">
                            <table style="width: 100%; height: 100%">
                                <tbody>
                                <tr>
                                    <td nowrap="nowrap" style="width: 300px">
                                        <strong><span
                                                style="font-size: 9pt; color: #ffffff; line-height: 8pt; font-family: Trebuchet MS">Авторизация</span></strong>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <span></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1"
                            style="border: buttonshadow thin solid; vertical-align: top; background-color: buttonface; text-align: left">
                            <table style="margin-left:30px;margin-right:30px;">
                                <tbody>
                                <tr>
                                    <td nowrap="nowrap" style="width: 129px; text-align: right">
                                        <span style="font-size: 8pt; font-family: Tahoma">Имя пользователя:</span>
                                    </td>
                                    <td style="width: 150px; height: 100%">
                                        <input type='text' name='j_username'
                                               value='<c:if test="${not empty param.login_error}"><c:out value="${SPRING_SECURITY_LAST_USERNAME}"/></c:if>'/>
                                        <!--<input id="Text3" name="login" type="text"/>-->
                                    </td>
                                </tr>
                                <tr>
                                    <td nowrap="nowrap" style="width: 129px; text-align: right">
                                        <span style="font-size: 8pt; font-family: Tahoma">Пароль:</span></td>
                                    <td style="width: 150px; height: 100%">
                                        <input type='password' name='j_password'/>
                                        <!--<input id="Password1" name="password"  type="password"/>-->
                                    </td>
                                </tr>
                                <!--<tr>-->
                                <%--<td nowrap="nowrap" style="width: 129px; text-align: right">--%>
                                <!--<input type="checkbox" name="_spring_security_remember_me">-->
                                <!--</td>-->
                                <%--<td nowrap="nowrap" style="width: 150px; height: 100%; text-align: left;">--%>
                                <%--<span style="font-size: 8pt; font-family: Tahoma">Запомнить меня</span>--%>
                                <!--</td>-->
                                <!--</tr>-->
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="1" style="padding: 0px; margin: 0px; height: 50px; background-color: white">
                            <table style="padding: 0px; margin: 0px; width: 100%; height: 100%">
                                <tr>
                                    <td nowrap="nowrap">
                                        <span style="font-size: 8pt"><span
                                                style="font-family: Tahoma">
                                            <!--<a href="">-->
                                            <!--Регистрация...</a>-->
                                            <%--<input type="checkbox" name="_spring_security_remember_me">--%>
                                            <%--<span style="font-size: 8pt; font-family: Tahoma">&nbsp;Запомнить меня</span>--%>
                                        </span></span>
                                    </td>
                                    <td>
                                        <div  style="width: 255; font-size: 8pt; font-family: Tahoma; color: red;">
                                            <!--- Перехват сообщений об ошибках -->
                                            <c:if test="${param.error_code == 1}">
                                                <div id="comment1">
                                                    Пользователь или пароль некорректен, повторите ввод.
                                                </div>
                                            </c:if>

                                            <c:if test="${param.error_code == 2}">
                                                <div id="comment2">
                                                    Доступ для пользователя временно запрещен.
                                                </div>
                                            </c:if>

                                            <c:if test="${param.error_code == 99}">
                                                <div id="comment3">
                                                    Ошибка авторизации.
                                                </div>
                                            </c:if>

                                            <%--<c:if test="${param.error_code == 2}">
                                                <div class="errors">
                                                    Кто-то уже зашел под вашим пользователем в систему.
                                                </div>
                                            </c:if>

                                            <c:if test="${param.error_code == 3}">
                                                <div class="errors">
                                                    Время бездействия истекло, пожалуйста авторизируйтесь снова.
                                                </div>
                                            </c:if>--%>
                                        </div>
                                    </td>
                                    <td style="text-align: right; margin-right:10px;">
                                        <!--<input size="20" style="font-size: 8pt; width: 84px; font-family: Tahoma;
                                                                height: 25px" type="button" value="Вход" onclick=
                           'window.alert("Извините. В данный момент сервис НЕ ДОСТУПЕН. Ведутся ТЕХНИЧЕСКИЕ РАБОТЫ.");'/>-->
                                        <input id="ok" size="20" style="font-size: 8pt; width: 84px; font-family: Tahoma;
                                                                height: 25px" type="submit" value="Вход"/>
                                        <input type="hidden" name="subjectId" value="10000"/>
                                    </td>
                                </tr>
                            </table>
                            <span/>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </td>
    </tr>
</table>
    </div>