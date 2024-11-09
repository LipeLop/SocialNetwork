
var socket = new SockJS('/chat');
var stompClient = Stomp.over(socket);
var currentUserId = document.getElementById("currentUserId").value;
var receiverId = document.getElementById("receiverId").value;
var chatId = generateChatId(currentUserId, receiverId);


stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);





    stompClient.subscribe('/user/' + chatId + '/queue/reply', function(messageOutput) {
        var message = JSON.parse(messageOutput.body);
        var chatDiv = document.getElementById("chat");
        var newMessage = document.createElement("div");

        if (message.sender === currentUserId) {
            newMessage.classList.add("sender-message");
        } else {
            newMessage.classList.add("receiver-message");
        }

        newMessage.innerHTML = "<strong>" + message.sender + ": </strong>" + message.content;
        chatDiv.appendChild(newMessage);
    });
});



function sendMessage() {
    var messageContent = document.getElementById("message").value;

    var message = {
        sender: currentUserId,
        receiver: receiverId,
        content: messageContent
    };


    stompClient.send("/app/sendMessage/" + chatId, {}, JSON.stringify(message));


    document.getElementById("message").value = '';
}
function generateChatId(senderId, receiverId) {
    return senderId < receiverId ? senderId + "_" + receiverId : receiverId + "_" + senderId;
}
