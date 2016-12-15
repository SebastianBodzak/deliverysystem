package com.testgroup.api;

import com.testgroup.blockchain.BlockchainRepository;
import com.testgroup.domain.*;
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
        System.out.println("\n\n ******************************************  CREATES TRANSACTION AT:  " + LocalDateTime.now());
        Long parcelId = blockchainRepository.addParcel(request.getSender(), request.getRecipient(), request.getCommitedBy(),
                ParcelType.valueOf(request.getParcelType()).getNumber());
        System.out.println("\n\n ******************************************  ENDS TRANSACTION AT:  " + LocalDateTime.now());

        return parcelId;
    }

    private Parcel createParcel(CreateParcelRequest request, User recipient, User sender) {
        return new Parcel(new Attachment(request.getAttachment()), request.getConent(), ParcelType.valueOf(request.getParcelType()), recipient, sender);
    }
}
