document.addEventListener("DOMContentLoaded", function() {
    // Check if user is already in session, if not redirect to welcome page
    const user = sessionStorage.getItem("user");
    if (!user) {
      window.location.href = "/welcome";
        return;
    }
    
    // Get the current channel name from the URL
    const channelName = window.location.pathname.split("/").pop();
    
    const chatMessagesElement = document.getElementById("chatMessages");
    
    function pollMessages() {
        // Poll messages from the server every 500 milliseconds
        fetch(`/channels/${channelName}/messages`)
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
    
    // Handle sending a message when the Send button is clicked
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
                    messageInput.value = ""; // Clear the input field
                }
            })
            .catch(error => console.error("Error sending message:", error));
        }
    });
});
