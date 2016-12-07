package com.testgroup.api;

import com.testgroup.domain.Shipment;
import com.testgroup.domain.ShipmentRegistry;
import com.testgroup.domain.User;
import com.testgroup.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static com.testgroup.domain.ParcelType.*;

/**
 * Created by sbod on 06.12.16.
 */
@Component
public class DataLoaderInitialization implements ApplicationRunner {

    private UserRepository userRepository;
    private ShipmentRegistry shipmentRegistry;

    @Autowired
    public DataLoaderInitialization(UserRepository userRepository, ShipmentRegistry shipmentRegistry) {
        this.userRepository = userRepository;
        this.shipmentRegistry = shipmentRegistry;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments applicationArguments) throws Exception {
        User firstUser = new User("FirstUserName", "FirstUserSurname", "FirstUses@mail.com", "FirstUserAddress");
        User secondUser = new User("SecondUserName", "SecondUserSurname", "SecondUser@mail.com", "SecondUserAddress");
        User thirdUser = new User("ThirdUserName", "ThirdUserSurname", "ThirdUser@mail.com", "ThirdUser");
        Long firstUserId = userRepository.save(firstUser);
        Long secondUserId = userRepository.save(secondUser);
        Long thirdUserId = userRepository.save(thirdUser);

        Shipment firstShipment = new Shipment(1L, 1L, 2L, LETTER);
        Shipment secondShipment = new Shipment(2L, 1L, 3L, LETTER);
        Shipment thirdShipment = new Shipment(3L, 2L, 3L, LETTER);
        Shipment fourthShipment = new Shipment(4L, 2L, 3L, EMAIL);
        Shipment fifthShipment = new Shipment(5L, 3L, 2L, PACKAGE);

        Calendar calendar = new GregorianCalendar(2013,1,28,13,24);
        Calendar calendar2 = new GregorianCalendar(2014,6,28,13,24);
        Calendar calendar3 = new GregorianCalendar(2014,1,28,13,24);
        Calendar calendar4 = new GregorianCalendar(2015,1,28,13,24);

        secondShipment.setDate(calendar.getTime());
        thirdShipment.setDate(calendar2.getTime());
        fourthShipment.setDate(calendar3.getTime());
        fifthShipment.setDate(calendar4.getTime());

        shipmentRegistry.save(firstShipment);
        shipmentRegistry.save(secondShipment);
        shipmentRegistry.save(thirdShipment);
        shipmentRegistry.save(fourthShipment);
        shipmentRegistry.save(fifthShipment);

        System.out.println("FIRST USER ID: " + firstUserId);
        System.out.println("SECOND USER ID: " + secondUserId);
        System.out.println("THIRD USER ID: " + thirdUserId);
        System.out.println("First Shipment: " + firstShipment);
        System.out.println("Second Shipment: " + secondShipment);
        System.out.println("Third Shipment: " + thirdShipment);
        System.out.println("Fourth Shipment: " + fourthShipment);
        System.out.println("Fifth Shipment: " + fifthShipment);
    }
}
