package com.testgroup.api;

import com.testgroup.domain.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sbod on 06.12.16.
 */
@Service
@AllArgsConstructor
public class SendingService {

    private UserRepository userRepository;
    private ShipmentRegistry shipmentRegistry;
    private ParcelRepository parcelRepository;

    @Transactional
    public Long addParcel(CreateParcelRequest request) {
        User sender = userRepository.load(request.getSenderId());
        User recipient = userRepository.load(request.getRecipientId());
        Parcel parcel = createParcel(request, sender, recipient);
        Long parcelId = parcelRepository.save(parcel);
        return shipmentRegistry.save(new Shipment(parcelId, request.getSenderId(), request.getRecipientId(), parcel.getParcelType()));
    }

    private Parcel createParcel(CreateParcelRequest request, User recipient, User sender) {
        return new Parcel(new Attachment(request.getAttachment()), request.getConent(), ParcelType.valueOf(request.getParcelType()), recipient, sender);
    }
}
