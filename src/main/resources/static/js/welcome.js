// Wait for the DOM to be fully loaded
document.addEventListener("DOMContentLoaded", function() {
	var name = sessionStorage.getItem("userName");

	// Prompt for the user's name
	if (name) {
		alert("Welcome back, " + name + "!");
	} else {
		name = prompt("Please enter your name", "John Doe");
		if (name) {
			// Store the user's name in session storage
			sessionStorage.setItem("userName", name);
			alert("Welcome, " + name + "!");
		} else {
			alert("Welcome!");
		}
	}

	// Handle the creation of a new channel
	var createChannelForm = document.getElementById("create-channel-form");
	if (createChannelForm) {
		createChannelForm.addEventListener("submit", function(event) {
			event.preventDefault();
			var channelName = document.getElementById("channelName").value;
			if (channelName) {
				// Send a POST request to create a new channel
				fetch("/createChannel", {
					method: "POST",
					headers: {
						"Content-Type": "application/x-www-form-urlencoded",
					},
					body: "channelName=" + encodeURIComponent(channelName),
				})
					.then(function(response) {
						if (response.ok) {
							// Redirect to the new channel's page
							window.location.href = "/channels/" + encodeURIComponent(channelName);
						} else {
							alert("Failed to create channel.");
						}
					})
					.catch(function(error) {
						console.error("Error creating channel:", error);
						alert("An error occurred while creating the channel.");
					});
			}
		});
	}

	// TODO: Add your other code here if needed
	// ... (rest of your code)
});
