package com.ec.ecservice;

import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.quorum.Quorum;
import org.web3j.tx.RawTransactionManager;
import org.web3j.model.EdgeComputing;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.File;
import java.math.BigInteger;

@Component
public class QuorumConnection {

    private static final String Q_URL = "http://localhost:22000";

    private static final String WALLET_FILE = "C:/Users/Public/playground/wallets";

    private static final long CHAIN_ID = 10;
    private Quorum quorum;

    private RawTransactionManager transactionManager;

    private String contractAddress;

    public QuorumConnection() {
        try{
            this.quorum = Quorum.build(new HttpService(Q_URL));
            Web3ClientVersion web3ClientVersion = this.quorum.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            System.out.println("This is ClientVersion" + clientVersion);
            Credentials credentials = WalletUtils.loadCredentials("", new File(WALLET_FILE));
            String address = credentials.getAddress();
            System.out.println("This is address: " + address);
            this.transactionManager = new RawTransactionManager(
                    quorum,
                    credentials,
                    CHAIN_ID // replace with your chainId
            );

            EdgeComputing edgeComputing = EdgeComputing.deploy(quorum, transactionManager,
                    new StaticGasProvider(BigInteger.ZERO, DefaultGasProvider.GAS_LIMIT)).send();
            System.out.println("Deployed contract address: " + edgeComputing.getContractAddress());
            this.contractAddress = edgeComputing.getContractAddress();
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Quorum getQuorum() {
        return quorum;
    }

    public RawTransactionManager getTransactionManager() {
        return transactionManager;
    }

    public String getContractAddress() {
        return contractAddress;
    }
}
