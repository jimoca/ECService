// SPDX-License-Identifier: MIT
pragma solidity ^0.7.0;

contract EdgeComputing {
    struct Task {
        uint256 id;
        address payable client;
        uint256 price;
        bool isPaid;
        bool isCompleted;
        bool isWithdrawn;
    }

    mapping(uint256 => Task) public tasks;
    uint256 public taskCount = 0;
    address payable public owner;

    constructor() {
        owner = payable(msg.sender);
    }

    event TaskCreated(uint256 taskId);

    event AmountTuple(uint256 msgVal, uint256 taskPrice);

    modifier onlyOwner {
        require(msg.sender == owner, "Only owner can call this function");
        _;
    }

    modifier taskExists(uint256 _taskId) {
        require(_taskId > 0 && _taskId <= taskCount, "Task does not exist");
        _;
    }

    function getTask(uint256 _taskId) external view taskExists(_taskId) returns (uint256, address, uint256, bool, bool, bool) {
        Task memory task = tasks[_taskId];
        return (task.id, task.client, task.price, task.isPaid, task.isCompleted, task.isWithdrawn);
    }

    function createTask(uint256 _price) external onlyOwner {
        taskCount++;
        tasks[taskCount] = Task(taskCount, payable(address(0)), _price, false, false, false);
        emit TaskCreated(taskCount);
    }

    function payTask(uint256 _taskId) external payable taskExists(_taskId) {
        Task storage task = tasks[_taskId];
        emit AmountTuple(msg.value, task.price);
        require(msg.value == task.price, "Incorrect payment amount");
        require(!task.isPaid, "Task already paid");
        task.client = payable(msg.sender);
        task.isPaid = true;
    }

    function confirmCompletion(uint256 _taskId) external taskExists(_taskId) {
        Task storage task = tasks[_taskId];
        require(task.client == msg.sender, "Only client can confirm completion");
        require(!task.isCompleted, "Task already completed");
        task.isCompleted = true;
    }

    function withdraw(uint256 _taskId) external onlyOwner taskExists(_taskId) {
        Task storage task = tasks[_taskId];
        require(task.isPaid, "Task not paid yet");
        require(task.isCompleted, "Task not completed yet");
        require(!task.isWithdrawn, "Payment has already been withdrawn");
        task.isWithdrawn = true;
        owner.transfer(task.price);
    }

}