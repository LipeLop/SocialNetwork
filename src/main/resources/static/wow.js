
var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);
var currentUserJson = document.getElementById("currentUser").value;
var receiverJson = document.getElementById("receiver").value;
var currentUser = JSON.parse(currentUserJson);
var receiver = JSON.parse(receiverJson);
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
        chat_id: chatId,
        content: messageContent
    };


    stompClient.send("/app/sendMessage", {}, JSON.stringify(message));


    document.getElementById("message").value = '';
}
function generateChatId(senderId, receiverId) {
    return senderId < receiverId ? senderId + "_" + receiverId : receiverId + "_" + senderId;
}
