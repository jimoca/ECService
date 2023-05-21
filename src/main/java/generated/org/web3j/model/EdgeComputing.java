package org.web3j.model;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple6;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.6.4.
 */
@SuppressWarnings("rawtypes")
public class EdgeComputing extends Contract {
    public static final String BINARY = "6080604052600060015534801561001557600080fd5b50600280546001600160a01b031916331790556109de806100376000396000f3fe60806040526004361061007b5760003560e01c80638d9776721161004e5780638d9776721461015c5780638da5cb5b1461018657806394aeb613146101b7578063b6cb58a5146101e15761007b565b80631d65e77e146100805780632508402c146100e95780632e1a7d4d1461010857806331d6693414610132575b600080fd5b34801561008c57600080fd5b506100aa600480360360208110156100a357600080fd5b5035610208565b604080519687526001600160a01b03909516602087015285850193909352901515606085015215156080840152151560a0830152519081900360c00190f35b610106600480360360208110156100ff57600080fd5b50356102fa565b005b34801561011457600080fd5b506101066004803603602081101561012b57600080fd5b503561046d565b34801561013e57600080fd5b506101066004803603602081101561015557600080fd5b5035610663565b34801561016857600080fd5b506100aa6004803603602081101561017f57600080fd5b5035610793565b34801561019257600080fd5b5061019b6107da565b604080516001600160a01b039092168252519081900360200190f35b3480156101c357600080fd5b50610106600480360360208110156101da57600080fd5b50356107e9565b3480156101ed57600080fd5b506101f6610908565b60408051918252519081900360200190f35b6000806000806000808660008111801561022457506001548111155b61026b576040805162461bcd60e51b815260206004820152601360248201527215185cdac8191bd95cc81b9bdd08195e1a5cdd606a1b604482015290519081900360640190fd5b61027361090e565b50505060009586525050506020838152604093849020845160c081018652815480825260018301546001600160a01b0316938201849052600283015496820187905260039092015460ff8082161515606084018190526101008304821615156080850181905262010000909304909116151560a09093018390529297939695509193509091565b8060008111801561030d57506001548111155b610354576040805162461bcd60e51b815260206004820152601360248201527215185cdac8191bd95cc81b9bdd08195e1a5cdd606a1b604482015290519081900360640190fd5b600082815260208181526040918290206002810154835134815292830152825190927f8c028d22f03f6f2f7446e80bd59032093ce2231e4dde5f789d284e17bfdf93b4928290030190a1806002015434146103f6576040805162461bcd60e51b815260206004820152601860248201527f496e636f7272656374207061796d656e7420616d6f756e740000000000000000604482015290519081900360640190fd5b600381015460ff1615610444576040805162461bcd60e51b815260206004820152601160248201527015185cdac8185b1c9958591e481c185a59607a1b604482015290519081900360640190fd5b600180820180546001600160a01b031916331790556003909101805460ff191690911790555050565b6002546001600160a01b031633146104b65760405162461bcd60e51b81526004018080602001828103825260218152602001806109446021913960400191505060405180910390fd5b806000811180156104c957506001548111155b610510576040805162461bcd60e51b815260206004820152601360248201527215185cdac8191bd95cc81b9bdd08195e1a5cdd606a1b604482015290519081900360640190fd5b6000828152602081905260409020600381015460ff1661056b576040805162461bcd60e51b815260206004820152601160248201527015185cdac81b9bdd081c185a59081e595d607a1b604482015290519081900360640190fd5b6003810154610100900460ff166105c2576040805162461bcd60e51b815260206004820152601660248201527515185cdac81b9bdd0818dbdb5c1b195d1959081e595d60521b604482015290519081900360640190fd5b600381015462010000900460ff161561060c5760405162461bcd60e51b81526004018080602001828103825260228152602001806109656022913960400191505060405180910390fd5b60038101805462ff000019166201000017905560028054908201546040516001600160a01b039092169181156108fc0291906000818181858888f1935050505015801561065d573d6000803e3d6000fd5b50505050565b6002546001600160a01b031633146106ac5760405162461bcd60e51b81526004018080602001828103825260218152602001806109446021913960400191505060405180910390fd5b6001805481018082556040805160c08101825282815260006020808301828152838501888152606085018481526080860185815260a087018681529886528585529487902095518655915185890180546001600160a01b0319166001600160a01b0390921691909117905551600285015551600390930180549251955160ff199093169315159390931761ff001916610100951515959095029490941762ff0000191662010000911515919091021790559154825190815291517fba46948ae716559226cede7aac0175e8ddd11b7cb3ea0369c9f218ef908b87d59281900390910190a150565b600060208190529081526040902080546001820154600283015460039093015491926001600160a01b039091169160ff808216916101008104821691620100009091041686565b6002546001600160a01b031681565b806000811180156107fc57506001548111155b610843576040805162461bcd60e51b815260206004820152601360248201527215185cdac8191bd95cc81b9bdd08195e1a5cdd606a1b604482015290519081900360640190fd5b600082815260208190526040902060018101546001600160a01b0316331461089c5760405162461bcd60e51b81526004018080602001828103825260228152602001806109876022913960400191505060405180910390fd5b6003810154610100900460ff16156108f4576040805162461bcd60e51b815260206004820152601660248201527515185cdac8185b1c9958591e4818dbdb5c1b195d195960521b604482015290519081900360640190fd5b600301805461ff0019166101001790555050565b60015481565b6040805160c081018252600080825260208201819052918101829052606081018290526080810182905260a08101919091529056fe4f6e6c79206f776e65722063616e2063616c6c20746869732066756e6374696f6e5061796d656e742068617320616c7265616479206265656e2077697468647261776e4f6e6c7920636c69656e742063616e20636f6e6669726d20636f6d706c6574696f6ea26469706673582212204f53fe789208305fb28859a5f38471eafd6ed977f014e69fd474c5feb9acf68c64736f6c63430007010033";

    public static final String FUNC_CONFIRMCOMPLETION = "confirmCompletion";

    public static final String FUNC_CREATETASK = "createTask";

    public static final String FUNC_GETTASK = "getTask";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAYTASK = "payTask";

    public static final String FUNC_TASKCOUNT = "taskCount";

    public static final String FUNC_TASKS = "tasks";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event AMOUNTTUPLE_EVENT = new Event("AmountTuple", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event TASKCREATED_EVENT = new Event("TaskCreated", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected EdgeComputing(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected EdgeComputing(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected EdgeComputing(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected EdgeComputing(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<AmountTupleEventResponse> getAmountTupleEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(AMOUNTTUPLE_EVENT, transactionReceipt);
        ArrayList<AmountTupleEventResponse> responses = new ArrayList<AmountTupleEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            AmountTupleEventResponse typedResponse = new AmountTupleEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.msgVal = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.taskPrice = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<AmountTupleEventResponse> amountTupleEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, AmountTupleEventResponse>() {
            @Override
            public AmountTupleEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(AMOUNTTUPLE_EVENT, log);
                AmountTupleEventResponse typedResponse = new AmountTupleEventResponse();
                typedResponse.log = log;
                typedResponse.msgVal = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.taskPrice = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<AmountTupleEventResponse> amountTupleEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(AMOUNTTUPLE_EVENT));
        return amountTupleEventFlowable(filter);
    }

    public List<TaskCreatedEventResponse> getTaskCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TASKCREATED_EVENT, transactionReceipt);
        ArrayList<TaskCreatedEventResponse> responses = new ArrayList<TaskCreatedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TaskCreatedEventResponse typedResponse = new TaskCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.taskId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TaskCreatedEventResponse> taskCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, TaskCreatedEventResponse>() {
            @Override
            public TaskCreatedEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TASKCREATED_EVENT, log);
                TaskCreatedEventResponse typedResponse = new TaskCreatedEventResponse();
                typedResponse.log = log;
                typedResponse.taskId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TaskCreatedEventResponse> taskCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TASKCREATED_EVENT));
        return taskCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> confirmCompletion(BigInteger _taskId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CONFIRMCOMPLETION, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_taskId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> createTask(BigInteger _price) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATETASK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_price)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>> getTask(BigInteger _taskId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTASK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_taskId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>>(function,
                new Callable<Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> payTask(BigInteger _taskId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAYTASK, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_taskId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> taskCount() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TASKCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>> tasks(BigInteger param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TASKS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}, new TypeReference<Bool>() {}));
        return new RemoteFunctionCall<Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>>(function,
                new Callable<Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>>() {
                    @Override
                    public Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple6<BigInteger, String, BigInteger, Boolean, Boolean, Boolean>(
                                (BigInteger) results.get(0).getValue(), 
                                (String) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (Boolean) results.get(3).getValue(), 
                                (Boolean) results.get(4).getValue(), 
                                (Boolean) results.get(5).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(BigInteger _taskId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_taskId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static EdgeComputing load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new EdgeComputing(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static EdgeComputing load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new EdgeComputing(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static EdgeComputing load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new EdgeComputing(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static EdgeComputing load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new EdgeComputing(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<EdgeComputing> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EdgeComputing.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<EdgeComputing> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(EdgeComputing.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EdgeComputing> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EdgeComputing.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<EdgeComputing> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(EdgeComputing.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class AmountTupleEventResponse extends BaseEventResponse {
        public BigInteger msgVal;

        public BigInteger taskPrice;
    }

    public static class TaskCreatedEventResponse extends BaseEventResponse {
        public BigInteger taskId;
    }
}
