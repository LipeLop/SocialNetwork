
var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);
var currentUser = [[${#objects.toJson(currentUser)}]];
var receiver = [[${#objects.toJson(friendDTO)}]];
var currentUserId = currentUser.id;
var receiverId = receiver.id;
var chatId = generateChatId(currentUserId, receiverId);


stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);


    stompClient.subscribe('/user/' + chatId + '/queue/reply', function(messageOutput) {
        var message = JSON.parse(messageOutput.body);
        var chatDiv = document.getElementById("chat");
        var newMessage = document.createElement("div");

        if (message.sender.id === currentUserId) {
            newMessage.classList.add("sender-message");
        } else {
            newMessage.classList.add("receiver-message");
        }
        newMessage.innerHTML = "<strong>" + message.sender.nickname + ": </strong>" + message.content;
        chatDiv.appendChild(newMessage);
    });
});



function sendMessage() {
    var messageContent = document.getElementById("message").value;
    var message = {
        sender: currentUser,
        receiver: receiver,
        content: messageContent
    };


    stompClient.send("/app/sendMessage/" + chatId, {}, JSON.stringify(message));


    document.getElementById("message").value = '';
}
function generateChatId(senderId, receiverId) {
    return senderId < receiverId ? senderId + "_" + receiverId : receiverId + "_" + senderId;
}
