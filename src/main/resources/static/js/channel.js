document.addEventListener("DOMContentLoaded", function() {
    const user = sessionStorage.getItem("userName");
    if (!user) {
        //window.location.href = "/welcome";
        return;
    }

    const channelName = window.location.pathname.split("/").pop();
    console.log(channelName);

    const chatMessagesElement = document.getElementById("chatMessages");

    function pollMessages() {
        console.log("about to hit the fetch")
        fetch(`/channels/${channelName}/messages`)
            .then(response => response.json())
            .then(messages => {
                chatMessagesElement.innerHTML = "";
                messages.forEach(message => {
                    const messageElement = document.createElement("li");
                    messageElement.innerText = `${message.sender}: ${message.content}`;
                    chatMessagesElement.appendChild(messageElement);
                });
            })
            .catch(error => console.error("Error polling messages:", error));
    }

    pollMessages();
    setInterval(pollMessages, 500);

    const messageInput = document.getElementById("messageInput");

    const messageForm = document.getElementById("messageForm");
    messageForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const content = messageInput.value.trim();
        if (content) {
            const message = { sender: user, content };
            fetch(`/channels/${channelName}/sendMessage`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(message)
            })
                .then(response => {
                    if (response.ok) {
                        messageInput.value = "";
                    }
                })
                .catch(error => console.error("Error sending message:", error));
        }
    });
});
