package com.ec.ecservice;


import com.demo.ecclient.model.PictureBase;
import com.ec.ecservice.exception.BusinessException;
import com.demo.ecclient.model.DelegateModel;
import com.ec.ecservice.model.TestModel;
import com.ec.ecservice.utils.MaskWithPaillier;
import io.reactivex.rxjava3.core.Observable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Type;
import  org.web3j.model.EdgeComputing;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class DelegateService {

    @Autowired
    private QuorumConnection quorumConnection;

    Optional<TestModel> hello(TestModel testModel) {
        return Optional.of(testModel);
    }
    Optional<String> ping() {
        return Optional.of("pong");
    }

    public Observable<BigInteger> createTask() {
        return Observable.create(emitter -> {
           EdgeComputing edgeComputing = EdgeComputing.load(quorumConnection.getContractAddress(), quorumConnection.getQuorum(), quorumConnection.getTransactionManager(),
                   new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT));

            EthFilter filter = new EthFilter(DefaultBlockParameterName.LATEST, DefaultBlockParameterName.LATEST, edgeComputing.getContractAddress());

            String encodedEventSignature = EventEncoder.encode(EdgeComputing.TASKCREATED_EVENT);

            filter.addSingleTopic(encodedEventSignature);
            System.out.println("Subscribing to event with filter");
            quorumConnection.getQuorum().ethLogFlowable(filter)
                    .subscribe(eventString -> {
                        eventString.toString();
                        List<Type> results = FunctionReturnDecoder.decode(
                                eventString.getData(), EdgeComputing.TASKCREATED_EVENT.getNonIndexedParameters());

                        BigInteger taskId = (BigInteger) results.get(0).getValue();
                        System.out.println("Task created with ID: " + taskId);

                        emitter.onNext(taskId);
                        emitter.onComplete();
                    });

            edgeComputing.createTask(Convert.toWei("3", Convert.Unit.ETHER).toBigInteger()).send();
            System.out.println("Task created");
        });
    }


    Optional<byte[]> delegateFile(byte[] serializedData) {
        DelegateModel delegateModel = deserialize(serializedData);
        int baseHeight = delegateModel.getBase().getHeight();
        int baseWidth = delegateModel.getBase().getWidth();
        return Optional.of(MaskWithPaillier.apply(delegateModel.getBase(), delegateModel.getMask(), delegateModel.getPublicKey()))
                .map(it -> PictureBase.builder()
                        .pixels(it)
                        .height(baseHeight)
                        .width(baseWidth).build())
                .map(SerializationUtils::serialize);
    }


    DelegateModel deserialize(byte[] serializedData) {
        return Optional.of(serializedData)
                .map(it -> (DelegateModel) SerializationUtils.deserialize(it))
                .orElseThrow(() -> new BusinessException("Error"));
    }

}
