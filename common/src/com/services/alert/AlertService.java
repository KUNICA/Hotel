package com.services.alert;

import com.entity.OfferEntity;
import com.entity.PersonalDataEntity;
import com.services.account.IPersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by user on 13.08.2016.
 */
@Component("alertService")
@Named
public class AlertService  implements AlertServiceImpl{

    @Qualifier("mailSender")
    @Autowired
    private JavaMailSender mailSender;

    @Inject
    protected IPersonalDataService personalDataService;

    // Отправляет сообщение об оферте
    public void sendAlert(final OfferEntity offer) {
        SimpleMailMessage message = new SimpleMailMessage();
        PersonalDataEntity person = personalDataService.getPersonalData(offer.getOperationIn().getUserName());
        message.setFrom(person.getEmail());
        message.setTo(person.getEmail());
        message.setSubject("New from " + offer.getOperationIn().getUserName());
        message.setText("Вы заказали на сайте отель. Спасибо за покупку! Сумма заказа состовляет: " + offer.getOrderPrice());
        mailSender.send(message);
    }


}
