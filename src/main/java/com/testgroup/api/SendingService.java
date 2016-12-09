package com.testgroup.api;

import com.testgroup.blockchain.BlockchainRepository;
import com.testgroup.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by sbod on 06.12.16.
 */
@Service
public class SendingService {

    private UserRepository userRepository;
    private ShipmentRegistry shipmentRegistry;
    private ParcelRepository parcelRepository;
    private BlockchainRepository blockchainRepository;

    public SendingService(UserRepository userRepository, ShipmentRegistry shipmentRegistry,
                          ParcelRepository parcelRepository, BlockchainRepository blockchainRepository) {
        this.userRepository = userRepository;
        this.shipmentRegistry = shipmentRegistry;
        this.parcelRepository = parcelRepository;
        this.blockchainRepository = blockchainRepository;
    }

    @Transactional
    public Long addParcel(CreateParcelRequest request) {
        User sender = userRepository.load(request.getSenderId());
        User recipient = userRepository.load(request.getRecipientId());
        Parcel parcel = createParcel(request, sender, recipient);
        Long parcelId = parcelRepository.save(parcel);
        Long shipmentID = shipmentRegistry.save(new Shipment(parcelId, request.getSenderId(),
                request.getRecipientId(), parcel.getParcelType()));

        System.out.println("\n\n ******************************************  CREATES TRANSACTION AT:  " + LocalDateTime.now());
        blockchainRepository.createTransaction(parcel.toString());
        System.out.println("\n\n ******************************************  ENDS TRANSACTION AT:  " + LocalDateTime.now());

        return shipmentID;
    }

    private Parcel createParcel(CreateParcelRequest request, User recipient, User sender) {
        return new Parcel(new Attachment(request.getAttachment()), request.getConent(), ParcelType.valueOf(request.getParcelType()), recipient, sender);
    }
}
