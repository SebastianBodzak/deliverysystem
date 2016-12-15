package com.testgroup.api;

import com.testgroup.api.dtos.CreateParcelRequest;
import com.testgroup.api.dtos.IdsParcelResponse;
import com.testgroup.api.dtos.StringParcelResponse;
import com.testgroup.blockchain.BlockchainRepository;
import com.testgroup.domain.ParcelType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by sbod on 06.12.16.
 */
@Service
public class SendingService {

    private BlockchainRepository blockchainRepository;

    public SendingService(BlockchainRepository blockchainRepository) {
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

    public StringParcelResponse getStringParcel(Long id) {
        String parcelAsString = blockchainRepository.getParcelAsString(id);
//        String sender = parcelAsString.substring(0);
//        String recipient = parcelAsString.substring(0);
//        String committedBy = parcelAsString.substring(0);
//        String connectedPersonId = parcelAsString.substring(0);
//        String parcelType = parcelAsString.substring(0);
//        LocalDateTime commitTimestamp = LocalDateTime.parse(parcelAsString.substring(0));
        return new StringParcelResponse(parcelAsString);
    }

    public IdsParcelResponse getParcelDataIds(Long id) {
        byte[] parcelAsIds = blockchainRepository.getParcelDataIds(id);
        Long senderId = null;
        Long receiverId = null;
        Long connectedPersonId = null;
        Long committedById = null;
        LocalDateTime commitTimestamp = null;
        String parcelType = null;
        return new IdsParcelResponse(senderId, receiverId, connectedPersonId, committedById, commitTimestamp, parcelType);
    }

    public Object[] getParcelIdsBySender(String name) {
        return blockchainRepository.getParcelIdsBySender(name);
    }
}
