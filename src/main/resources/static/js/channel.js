document.addEventListener("DOMContentLoaded", function() {
    // Check if user is already in session, if not redirect to welcome page
    const user = sessionStorage.getItem("user");
    if (!user) {
        window.location.href = "/welcome";
        return;
    }
    
    // Get the current channel ID from the URL
    const channelId = window.location.pathname.split("/").pop();
    
    const chatMessagesElement = document.getElementById("chatMessages");
    
    function pollMessages() {
        // Poll messages from the server every 500 milliseconds
        fetch(`/getMessages/${channelId}`)
            .then(response => response.json())
            .then(messages => {
                chatMessagesElement.innerHTML = ""; // Clear existing messages
                messages.forEach(message => {
                    const messageElement = document.createElement("li");
                    messageElement.innerText = `${message.sender}: ${message.content}`;
                    chatMessagesElement.appendChild(messageElement);
                });
            })
            .catch(error => console.error("Error polling messages:", error));
    }
    
    // Poll messages initially and then set an interval for continuous polling
    pollMessages();
    setInterval(pollMessages, 500);
    
    const messageInput = document.getElementById("messageInput");
    
    // Handle sending a message when Enter key is pressed
    messageInput.addEventListener("keypress", function(event) {
        if (event.key === "Enter") {
            const content = messageInput.value.trim();
            if (content) {
                const message = { channelId, sender: user, content };
                fetch("/sendMessage", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(message)
                })
                .then(response => {
                    if (response.ok) {
                        messageInput.value = ""; // Clear the input field
                    }
                })
                .catch(error => console.error("Error sending message:", error));
            }
        }
    });
});
